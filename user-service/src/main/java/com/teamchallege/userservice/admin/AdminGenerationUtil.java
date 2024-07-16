//package com.teamchallege.userservice.admin;
//
//import com.teamchallege.common.entities.user.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import com.teamchallege.userservice.role.service.RoleService;
//import com.teamchallege.userservice.user.service.UserService;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class AdminGenerationUtil {
//
//    private final UserService userService;
//    private final RoleService roleService;
//
//    private final PasswordEncoder passwordEncoder;
//
//    //    @Scheduled(fixedRate = 10000)
//    public void generateAdmin() {
//        User admin = new User();
//        admin.setEmail("admin@mail.com");
//        admin.setRoles(List.of(roleService.findByName("ROLE_ADMIN")));
//        admin.setPassword(passwordEncoder.encode("Secret"));
//        userService.save(admin);
//        System.out.println("Admin generated");
//    }
//
//}
