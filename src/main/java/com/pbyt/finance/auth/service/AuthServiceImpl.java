package com.pbyt.finance.auth.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbyt.finance.auth.model.LoginModel;
import com.pbyt.finance.auth.model.LoginResponse;
import com.pbyt.finance.exception.InvalidCredential;
import com.pbyt.finance.repository.UserRepository;
import com.pbyt.finance.service.JwtService;
import com.pbyt.finance.user.entity.TblUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
        try {
            ObjectMapper mapper = new ObjectMapper();
            String role =  mapper.writeValueAsString(user.getRoles());
            String token = jwtService.GenerateToken(role+"-"+user.getId().toString(), user.getMobileNumber().toString());
            ModelMapper modelMapper = new ModelMapper();
            LoginResponse response = modelMapper.map(user,LoginResponse.class);
            response.setToken(token);
            return response;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
