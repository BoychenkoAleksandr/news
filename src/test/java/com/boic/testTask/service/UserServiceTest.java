package com.boic.testTask.service;

import com.boic.testTask.common.Role;
import com.boic.testTask.users.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserJpaMapper userJpaMapper;

    @InjectMocks
    private UserService userService;

    // Тестовые данные
    private final String testUsername = "testUser";
    private User testUser;
    private UserJpa testUserJpa;

    @BeforeEach
    void setUp() {
        testUserJpa = new UserJpa();
        testUserJpa.setUsername(testUsername);
        testUserJpa.setPassword("encodedPassword");
        testUserJpa.setName("testName");
        testUserJpa.setSurname("testSurname");
        testUserJpa.setParentName("testParentName");
        testUserJpa.setRole(Role.ROLE_ADMIN);

        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername(testUsername);
        testUser.setPassword("password");
        testUser.setName("testName");
        testUser.setSurname("testSurname");
        testUser.setParentName("testParentName");
        testUser.setRole(Role.ROLE_ADMIN);
    }

    @Test
    void findByUsernameIgnoreCase_ShouldReturnUser_WhenUserExists() {
        // Подготовка
        when(userRepository.findByUsernameIgnoreCase(testUsername))
                .thenReturn(Optional.of(testUserJpa));
        when(userJpaMapper.fromJpaEntity(testUserJpa)).thenReturn(testUser);

        // Действие
        User result = userService.findByUsernameIgnoreCase(testUsername);

        // Проверка
        assertNotNull(result);
        assertEquals(testUsername, result.getUsername());

        // Проверка вызовов
        verify(userRepository, times(1)).findByUsernameIgnoreCase(testUsername);
        verify(userJpaMapper, times(1)).fromJpaEntity(testUserJpa);
    }

    @Test
    void persist_ShouldSaveAndReturnUser() {
        // Подготовка
        when(userJpaMapper.toJpaEntity(testUser)).thenReturn(testUserJpa);
        when(userRepository.save(testUserJpa)).thenReturn(testUserJpa);
        when(userJpaMapper.fromJpaEntity(testUserJpa)).thenReturn(testUser);

        // Действие
        User result = userService.persist(testUser);

        // Проверка
        assertNotNull(result);
        assertEquals(testUsername, result.getUsername());

        // Проверка вызовов
        verify(userJpaMapper, times(1)).toJpaEntity(testUser);
        verify(userRepository, times(1)).save(testUserJpa);
        verify(userJpaMapper, times(1)).fromJpaEntity(testUserJpa);
    }
}
