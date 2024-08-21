package com.pbyt.finance.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pbyt.finance.auth.model.LoginModel;
import com.pbyt.finance.auth.model.LoginResponse;
import com.pbyt.finance.exception.InvalidCredential;
import com.pbyt.finance.user.entity.TblUser;

import java.util.Optional;

public interface AuthService {
    Optional<TblUser> checkRegister(Long mobileNumber);
    LoginResponse login(LoginModel loginModel, TblUser user) throws InvalidCredential, JsonProcessingException;
}
