package com.pbyt.finance.user.controller;

import com.pbyt.finance.exception.AlreadyPresent;
import com.pbyt.finance.exception.BadRequestHandler;
import com.pbyt.finance.global.model.MessageResponse;
import com.pbyt.finance.user.entity.TblUser;
import com.pbyt.finance.user.model.UserCreateModel;
import com.pbyt.finance.user.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/app/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Validated UserCreateModel userCreateModel) throws AlreadyPresent {
        boolean isAlreadyPresent = userService.isUserExists(userCreateModel.getMobileNumber());
        if (isAlreadyPresent) throw new AlreadyPresent("User Already Present");
        userService.createUser(userCreateModel);
        return ResponseEntity.ok(MessageResponse
                .builder()
                .status(HttpStatus.OK)
                .message("User Created Successfully")
                .build());
    }
    @RolesAllowed({"ADMIN"})
    @GetMapping("/get-all-user")
    public ResponseEntity<?> findAllUser(@RequestParam(defaultValue = "1",value = "pageNo") int pageNo, @RequestParam(defaultValue = "20",value = "pageSize") int pageSize, HttpRequest httpRequest) throws BadRequestHandler {
           Page<TblUser> data = userService.findAllUser(pageNo, pageSize);
           return ResponseEntity.ok(MessageResponse
                   .builder()
                   .status(HttpStatus.OK)
                   .message("Successfully")
                   .object(data)
                   .build());
    }
}
