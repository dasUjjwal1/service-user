package com.pbyt.finance.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pbyt.finance.auth.model.AgentLoginResponse;
import com.pbyt.finance.auth.model.AgentRegisterModel;
import com.pbyt.finance.auth.model.UserLoginModel;
import com.pbyt.finance.auth.model.UserLoginResponse;
import com.pbyt.finance.exception.InvalidCredential;
import com.pbyt.finance.user.entity.TblAgent;
import com.pbyt.finance.user.entity.TblUser;

import java.util.Optional;

public interface AuthService {
    Optional<TblUser> checkRegister(Long mobileNumber);
    UserLoginResponse login(UserLoginModel userLoginModel, TblUser user) throws InvalidCredential, JsonProcessingException;
    Optional<TblAgent> getAgentRegister(Long mobileNumber);
    boolean checkAgentRegistered(Long mobileNumber);
    AgentLoginResponse register(AgentRegisterModel registerModel)  throws JsonProcessingException;
}
