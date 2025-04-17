package com.boic.testTask.controller;

import com.boic.testTask.auth.*;
import com.boic.testTask.common.Role;
import com.boic.testTask.users.User;
import com.boic.testTask.users.UserDtoOut;
import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Mock
    private AuthService authService;

    @Mock
    private AuthMapper authMapper;

    @InjectMocks
    private AuthController authController;

    private AuthRegister testRegister;
    private AuthLogin testLogin;
    private UserDtoOut testUserDto;
    private User testUser;

    @BeforeEach
    void setUp() {
        testRegister = new AuthRegister();
        testRegister.setUsername("test@example.com");
        testRegister.setName("testName");
        testRegister.setSurname("testSurname");
        testRegister.setParentName("testParentName");
        testRegister.setRole("ROLE_ADMIN");

        testLogin = new AuthLogin();
        testLogin.setUsername("test@example.com");
        testLogin.setPassword("password");

        testUserDto = new UserDtoOut();
        testUserDto.setUsername("test@example.com");
        testUserDto.setPassword("password");

        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("test@example.com");
        testUser.setPassword("password");
        testUser.setName("testName");
        testUser.setSurname("testSurname");
        testUser.setParentName("testParentName");
        testUser.setRole(Role.ROLE_ADMIN);
    }

    @Test
    void registerUser_ShouldReturnRegisteredUser_WhenInputValid() {
        // Подготовка
        when(authMapper.fromAuthRegister(testRegister)).thenReturn(testUser);
        when(authService.registerUser(testUser)).thenReturn(testUserDto);

        // Действие
        ResponseEntity<UserDtoOut> response = authController.registerUser(testRegister);

        // Проверка
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUserDto, response.getBody());

        // Проверка вызовов
        verify(authMapper, times(1)).fromAuthRegister(testRegister);
        verify(authService, times(1)).registerUser(testUser);
    }

    @Test
    void login_ShouldReturnToken_WhenCredentialsValid() throws AuthException {
        // Подготовка
        String testToken = "test.jwt.token";
        when(authService.login(testLogin.getUsername(), testLogin.getPassword()))
                .thenReturn(testToken);

        // Действие
        String result = authController.login(testLogin);

        // Проверка
        assertEquals(testToken, result);

        // Проверка вызовов
        verify(authService, times(1)).login(testLogin.getUsername(), testLogin.getPassword());
    }
}
