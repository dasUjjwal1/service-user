package com.pbyt.finance.user.service;

import com.pbyt.finance.repository.UserRepository;
import com.pbyt.finance.user.entity.TblUser;
import com.pbyt.finance.user.model.UserCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(UserCreateModel userCreateModel) {
        try {

            String hashedPassword = new BCryptPasswordEncoder().encode(userCreateModel.getPassword());
            userRepository.save(TblUser.builder()
                    .name(userCreateModel.getName())
                    .email(userCreateModel.getEmail())
                    .mobileNumber(userCreateModel.getMobileNumber())
                    .address(userCreateModel.getAddress())
                    .password(hashedPassword)
                    .workingArea(userCreateModel.getWorkingArea())
                    .dob(userCreateModel.getDob())
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
    public Page<TblUser> findAllUser(int pageNo, int pageSize) {
        PageRequest pr = PageRequest.of(pageNo, pageSize);
        return userRepository.findAll(pr);
    }

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        Optional<TblUser> user = userRepository.findUserByMobileNumber(Long.parseLong(mobile));
        if (user.isEmpty()) throw new UsernameNotFoundException("User not found");
        return user.get();
    }
}
