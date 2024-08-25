package com.futureboost.FutureBoostify.auth;

import com.futureboost.FutureBoostify.enums.Role;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Role> roles;


}
