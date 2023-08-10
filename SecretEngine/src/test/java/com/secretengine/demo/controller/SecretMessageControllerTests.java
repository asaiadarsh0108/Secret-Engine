package com.secretengine.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.secretengine.demo.EncodeAndDecode;
import com.secretengine.demo.entity.SecretMessage;
import com.secretengine.demo.entity.User;
import com.secretengine.demo.service.ResourceNotFoundException;
import com.secretengine.demo.service.SecretMessageService;
import com.secretengine.demo.service.UserService;

@SpringBootTest
public class SecretMessageControllerTests {

    @Mock
    private SecretMessageService secretMessageService;

    @Mock
    private UserService userService;

    @Mock
    private EncodeAndDecode encodeAndDecode;

    @InjectMocks
    private SecretMessageController secretMessageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSecretMessage() throws ResourceNotFoundException {
        int userId = 1;
        SecretMessage mockSecretMessage = new SecretMessage();
        when(userService.getById(userId)).thenReturn(new User());
//        when(encodeAndDecode.encode("this is message", anyInt())).thenReturn("encodedMessage");
        when(secretMessageService.insert(any(SecretMessage.class))).thenReturn(mockSecretMessage);
        SecretMessage result = secretMessageController.CreateSecretMessage(mockSecretMessage, userId);
        assertEquals(mockSecretMessage, result);
        verify(secretMessageService, times(1)).insert(mockSecretMessage);
    }

    @Test
    public void testGetAll() throws ResourceNotFoundException {
        int userId = 1;
        List<SecretMessage> mockMessages = new ArrayList<>();
        when(userService.getById(userId)).thenReturn(new User());
        when(secretMessageService.getByUserId(userId)).thenReturn(mockMessages);
        List<SecretMessage> result = secretMessageController.getAll(userId);
        assertEquals(mockMessages, result);
        verify(secretMessageService, times(1)).getByUserId(userId);
    }

    @Test
    public void testGetOneValid() throws ResourceNotFoundException {
        int messageId = 1;
        int userId = 1;
        SecretMessage mockSecretMessage = new SecretMessage();
        User mockUser = new User(userId, "username", "password");
        mockSecretMessage.setUser(mockUser);
        when(userService.getById(userId)).thenReturn(mockUser);
        when(secretMessageService.getById(messageId)).thenReturn(mockSecretMessage);
        when(secretMessageService.decode(mockSecretMessage)).thenReturn(mockSecretMessage);
        ResponseEntity<?> response = secretMessageController.getOne(messageId, userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSecretMessage, response.getBody());
    }

    @Test
    public void testGetOneInvalid() throws ResourceNotFoundException {
        int messageId = 1;
        int userId = 1;
        when(userService.getById(userId)).thenReturn(new User());
        when(secretMessageService.getById(messageId)).thenThrow(new ResourceNotFoundException("Invalid ID given"));
        ResponseEntity<?> response = secretMessageController.getOne(messageId, userId);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid ID given", response.getBody());
    }

    @Test
    public void testUpdateValid() throws ResourceNotFoundException {
        int messageId = 1;
        SecretMessage mockSecretMessage = new SecretMessage();
        when(secretMessageService.getById(messageId)).thenReturn(mockSecretMessage);
        when(secretMessageService.insert(any(SecretMessage.class))).thenReturn(mockSecretMessage);
        ResponseEntity<?> response = secretMessageController.update(messageId, mockSecretMessage);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSecretMessage, response.getBody());
        verify(secretMessageService, times(1)).getById(messageId);
        verify(secretMessageService, times(1)).insert(mockSecretMessage);
    }

    @Test
    public void testUpdateInvalid() throws ResourceNotFoundException {
        int messageId = 1;
        when(secretMessageService.getById(messageId)).thenThrow(new ResourceNotFoundException("Invalid ID given"));
        ResponseEntity<?> response = secretMessageController.update(messageId, new SecretMessage());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid ID given", response.getBody());
    }

    @Test
    public void testDeleteValid() throws ResourceNotFoundException {
        int messageId = 1;
        ResponseEntity<?> response = secretMessageController.delete(messageId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(secretMessageService, times(1)).getById(messageId);
        verify(secretMessageService, times(1)).delete(messageId);
    }

    @Test
    public void testDeleteInvalid() throws ResourceNotFoundException {
        int messageId = 1;
        when(secretMessageService.getById(messageId)).thenThrow(new ResourceNotFoundException("Invalid ID given"));
        ResponseEntity<?> response = secretMessageController.delete(messageId);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid ID given", response.getBody());
    }

}
