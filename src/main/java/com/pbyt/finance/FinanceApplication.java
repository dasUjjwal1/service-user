package com.pbyt.finance;

import com.pbyt.finance.enums.RoleEnum;
import com.pbyt.finance.repository.UserRepository;
import com.pbyt.finance.user.entity.TblUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.HashSet;

@SpringBootApplication
public class FinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserRepository userRepository) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        Collection<Integer> authorities = HashSet.newHashSet(1);
//        authorities.add(RoleEnum.ADMIN.ordinal());
//        return args -> {
//            TblUser admin = TblUser.builder()
//                    .mobileNumber(9647012776L)
//                    .authorities(authorities)
//                    .password(encoder.encode("password"))
//                    .isSuperAdmin(true)
//                    .build();
//            userRepository.save(admin);
//        };
//
//    }
}
