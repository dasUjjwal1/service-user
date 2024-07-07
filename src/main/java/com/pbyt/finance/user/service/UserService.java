package com.pbyt.finance.user.service;

import com.pbyt.finance.user.model.UserCreateModel;

public interface UserService {
    void createUser(UserCreateModel userCreateModel);
    boolean isUserExists(Long mobileNumber);
}
