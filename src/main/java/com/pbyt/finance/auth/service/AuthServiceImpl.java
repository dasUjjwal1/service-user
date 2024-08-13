package com.pbyt.finance.auth.service;

import com.pbyt.finance.auth.model.LoginModel;
import com.pbyt.finance.auth.model.LoginResponse;
import com.pbyt.finance.exception.InvalidCredential;
import com.pbyt.finance.repository.UserRepository;
import com.pbyt.finance.service.JwtService;
import com.pbyt.finance.user.entity.TblUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public Optional<TblUser> checkRegister(Long mobileNumber) {
        return userRepository.findUserByMobileNumber(mobileNumber);
    }

    @Override
    public LoginResponse login(LoginModel loginModel,TblUser user) throws InvalidCredential {
        boolean isCredentialCorrect = new BCryptPasswordEncoder().matches(loginModel.getPassword(),user.getPassword());
        if (!isCredentialCorrect) throw new InvalidCredential("Invalid Credential");
        String token = jwtService.GenerateToken(user.getAuthorities()+"-"+user.getId().toString(), user.getMobileNumber().toString());
        ModelMapper modelMapper = new ModelMapper();
        LoginResponse response = modelMapper.map(user,LoginResponse.class);
        response.setToken(token);
        return response;
    }
}
