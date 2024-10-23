package com.pbyt.finance.user.service;

import com.pbyt.finance.applicationEntity.Address;
import com.pbyt.finance.applicationEntity.TblWorkArea;
import com.pbyt.finance.applicationModel.WorkArea;
import com.pbyt.finance.exception.NotFound;
import com.pbyt.finance.repository.UserRepository;
import com.pbyt.finance.repository.WorkAreaRepository;
import com.pbyt.finance.user.entity.TblUser;
import com.pbyt.finance.user.model.UpdateUserModel;
import com.pbyt.finance.user.model.UserCreateModel;
import com.pbyt.finance.user.model.UserResponseDetails;
import com.pbyt.finance.util.AddressConverter;
import com.pbyt.finance.util.MapperObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkAreaRepository workAreaRepository;

@Autowired
    private MapperObject<List<WorkArea>> workAreaConverter;

//    private AddressConverter addressConverter;

    @Override
    public void createUser(UserCreateModel userCreateModel) {
        try {
            String hashedPassword = new BCryptPasswordEncoder().encode(userCreateModel.getPassword());
            List<Integer> role = userCreateModel.getAuthorities()
                    .stream().map(Enum::ordinal).toList();
            TblUser user = userRepository.save(TblUser.builder()
                    .name(userCreateModel.getName())
                    .email(userCreateModel.getEmail())
                    .mobileNumber(userCreateModel.getMobileNumber())
                    .address(userCreateModel.getAddress())
                    .authorities(role)
                    .password(hashedPassword)
                    .dob(userCreateModel.getDob())
                    .build());
            Set<TblWorkArea> workAreaList = userCreateModel.getWorkArea().stream().map(
                    it -> TblWorkArea
                            .builder()
                            .userId(user.getId())
                            .areaId(it)
                            .build()).collect(Collectors.toSet());
            workAreaRepository.saveAll(workAreaList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean isUserExists(Long mobileNumber) {
        try {
            return userRepository.findUserExists(mobileNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserResponseDetails> findAllUser(int pageNo, int pageSize) {
        List<Object[]> res = userRepository.findAllUsers();
        List<UserResponseDetails> data = res.stream()
                .map(row -> {
                    Integer id = (Integer) row[0];
                    String name = (String) row[1];
                    Long mobileNumber = (Long) row[2];
                    Date dob = (Date) row[3];
                    String email = (String) row[4];
                    String authorities = (String) row[6];
                    String createdBy = (String) row[7];
                    LocalDateTime createdOn = (LocalDateTime) row[8];
                    List<WorkArea> workArea = workAreaConverter.convertToEntityAttribute((String) row[9]);
//                    Address address = addressConverter.convertToEntityAttribute((String) row[5]);
                    return UserResponseDetails.builder()
                            .id(id)
                            .dob(dob)
                            .email(email)
//                            .address(address)
                            .name(name)
                            .mobileNumber(mobileNumber)
                            .workArea(workArea)
                            .build();

                }).collect(Collectors.toList());
        return data;
    }

    @Override
    public void updateUser(UpdateUserModel userModel) throws NotFound {
        Optional<TblUser> user = userRepository.findById(userModel.getId());
        if (user.isEmpty()) throw new NotFound("User not found");
        TblUser userUpdate = user.get();
        List<Integer> role = userModel.getAuthorities()
                .stream().map(Enum::ordinal).toList();
        userUpdate.setId(userUpdate.getId());
        userUpdate.setEmail(userUpdate.getEmail());
        userUpdate.setAddress(userModel.getAddress());
        userUpdate.setAuthorities(role);
        userUpdate.setDob(userModel.getDob());
        userUpdate.setName(userModel.getName());
        userUpdate.setModifiedOn(LocalDateTime.now());
        userRepository.save(userUpdate);
    }

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        Optional<TblUser> user = userRepository.findUserByMobileNumber(Long.parseLong(mobile));
        if (user.isEmpty()) throw new UsernameNotFoundException("User not found");
        return user.get();
    }
}
