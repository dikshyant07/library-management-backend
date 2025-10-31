package com.library.management.system.libraryManagementSystem.services;

import com.library.management.system.libraryManagementSystem.dtos.ApiResponse;
import com.library.management.system.libraryManagementSystem.dtos.RegistrationRequestDto;
import com.library.management.system.libraryManagementSystem.dtos.RegistrationResponseDto;
import com.library.management.system.libraryManagementSystem.enums.Gender;
import com.library.management.system.libraryManagementSystem.exceptions.MemberAlreadyExistsException;
import com.library.management.system.libraryManagementSystem.exceptions.UserDoesNotExistsException;
import com.library.management.system.libraryManagementSystem.mappers.UserMapper;
import com.library.management.system.libraryManagementSystem.models.User;
import com.library.management.system.libraryManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.library.management.system.libraryManagementSystem.enums.Role.MEMBER;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ApiResponse<RegistrationResponseDto> registerMember(RegistrationRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new MemberAlreadyExistsException("Member with this email already exists");
        }
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        User member = userMapper.toUser(requestDto);
        member.setPassword(encodedPassword);
        member.setRole(MEMBER);
        member.setGender(Gender.valueOf(requestDto.getGender().toUpperCase()));
        RegistrationResponseDto responseDto = userMapper.toDto(userRepository.save(member));
        return ApiResponse.<RegistrationResponseDto>builder()
                .status(true)
                .httpStatus(HttpStatus.CREATED)
                .message("Successfully created the member")
                .reponseObject(responseDto)
                .build();

    }

    public ApiResponse<String> deleteMember(Long id) {
        int rowsEffected = userRepository.deleteFromId(id);
        if (rowsEffected <= 0) {
            throw new UserDoesNotExistsException("Member with this id does not exists");
        }
        return ApiResponse.<String>builder()
                .status(true)
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted the member with id " + id)
                .reponseObject(rowsEffected + " records are deleted from the database")
                .build();

    }
}





















