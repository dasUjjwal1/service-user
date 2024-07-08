package com.pbyt.finance.user.service;

import com.pbyt.finance.user.entity.TblUser;
import com.pbyt.finance.user.model.UserCreateModel;
import org.springframework.data.domain.Page;

public interface UserService {
    void createUser(UserCreateModel userCreateModel);
    boolean isUserExists(Long mobileNumber);
    Page<TblUser> findAllUser(int pageNo,int pageSize);
}
