package com.boic.testTask.service;

import com.boic.testTask.auth.AuthService;
import com.boic.testTask.common.Role;
import com.boic.testTask.configuration.CustomUserDetails;
import com.boic.testTask.configuration.CustomUserDetailsService;
import com.boic.testTask.configuration.JwtTokenUtil;
import com.boic.testTask.users.User;
import com.boic.testTask.users.UserDtoOut;
import com.boic.testTask.users.UserMapper;
import com.boic.testTask.users.UserService;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    // Тестовые данные
    private final String testUsername = "testUser";
    private final String testPassword = "testPassword";
    private final String encodedPassword = "encodedPassword";
    private User testUser;
    private UserDtoOut testUserDto;
    private final CustomUserDetails testUserDetails = new CustomUserDetails(1L, testUsername, encodedPassword, Collections.emptyList());

    @BeforeEach
    void setUp() {
        testUserDto = new UserDtoOut();
        testUserDto.setUsername(testUsername);
        testUserDto.setPassword(testPassword);

        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername(testUsername);
        testUser.setPassword(testPassword);
        testUser.setName("testName");
        testUser.setSurname("testSurname");
        testUser.setParentName("testParentName");
        testUser.setRole(Role.ROLE_ADMIN);
    }


    @Test
    void registerUser_ShouldReturnUserDto_WhenRegistrationSuccessful() {
        // Подготовка
        when(userService.findByUsernameIgnoreCase(testUsername)).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
        when(userService.persist(any(User.class))).thenReturn(testUser);
        when(userMapper.toOut(testUser)).thenReturn(testUserDto);

        // Действие
        UserDtoOut result = authService.registerUser(testUser);

        // Проверка
        assertNotNull(result);
        assertEquals(testUsername, result.getUsername());
        assertNotNull(result.getPassword()); // Проверяем что сгенерирован пароль

        // Проверка вызовов
        verify(userService, times(1)).findByUsernameIgnoreCase(testUsername);
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userService, times(1)).persist(any(User.class));
        verify(userMapper, times(1)).toOut(testUser);
    }

    @Test
    void login_ShouldReturnToken_WhenAuthenticationSuccessful() throws AuthException {
        // Подготовка
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null); // Успешная аутентификация возвращает null
        when(userDetailsService.loadUserByUsername(testUsername)).thenReturn(testUserDetails);
        String testToken = "test.jwt.token";
        when(jwtTokenUtil.generateToken(testUserDetails)).thenReturn(testToken);

        // Действие
        String result = authService.login(testUsername, testPassword);

        // Проверка
        assertEquals(testToken, result);

        // Проверка вызовов
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDetailsService, times(1)).loadUserByUsername(testUsername);
        verify(jwtTokenUtil, times(1)).generateToken(testUserDetails);
    }
}
