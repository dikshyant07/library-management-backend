package com.library.management.system.libraryManagementSystem.dtos;

import com.library.management.system.libraryManagementSystem.enums.Gender;
import com.library.management.system.libraryManagementSystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationResponseDto {
    private Long userId;
    private String name;
    private int age;
    private Gender gender;
    private String email;
    private Role role;

}
