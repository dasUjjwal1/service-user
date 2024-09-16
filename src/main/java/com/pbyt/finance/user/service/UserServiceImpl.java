package com.pbyt.finance.user.service;

import com.pbyt.finance.applicationEntity.TblStateDistrict;
import com.pbyt.finance.applicationEntity.TblWorkArea;
import com.pbyt.finance.exception.NotFound;
import com.pbyt.finance.repository.StateDistrictRepository;
import com.pbyt.finance.repository.UserRepository;
import com.pbyt.finance.repository.WorkAreaRepository;
import com.pbyt.finance.user.entity.TblUser;
import com.pbyt.finance.user.model.UpdateUserModel;
import com.pbyt.finance.user.model.UserCreateModel;
import com.pbyt.finance.user.model.UserResponseDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkAreaRepository workAreaRepository;

    @Override
    public void createUser(UserCreateModel userCreateModel) {
        try {
            String hashedPassword = new BCryptPasswordEncoder().encode(userCreateModel.getPassword());
            List<Integer> role = userCreateModel.getAuthorities()
                    .stream().map(Enum::ordinal).toList();
            userRepository.save(TblUser.builder()
                    .name(userCreateModel.getName())
                    .email(userCreateModel.getEmail())
                    .mobileNumber(userCreateModel.getMobileNumber())

                    .address(userCreateModel.getAddress())
                    .authorities(role)
                    .password(hashedPassword)
                    .dob(userCreateModel.getDob())
                    .build());
            workAreaRepository.save(TblWorkArea.builder()

                    .build());
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
        return userRepository.findAllUsers();
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
//        userUpdate.setWorkingArea(userModel.getWorkingArea());
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
