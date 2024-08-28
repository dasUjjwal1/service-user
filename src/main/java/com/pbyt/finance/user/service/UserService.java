package com.pbyt.finance.user.service;

import com.pbyt.finance.user.model.UserCreateModel;
import com.pbyt.finance.user.model.UserResponseDetails;

import java.util.List;

public interface UserService {
    void createUser(UserCreateModel userCreateModel);
    boolean isUserExists(Long mobileNumber);
    List<UserResponseDetails> findAllUser(int pageNo, int pageSize);
}
