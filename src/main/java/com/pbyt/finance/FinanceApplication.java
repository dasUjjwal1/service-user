package com.pbyt.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserRepository userRepository) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        Collection<RoleEnum> authorities = HashSet.newHashSet(1);
//        authorities.add(RoleEnum.ADMIN);
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
