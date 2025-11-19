package org.example.coffeecollectionapi;

import org.example.coffeecollectionapi.model.AuthenticationRequest;
import org.example.coffeecollectionapi.model.AuthenticationResponse;
import org.example.coffeecollectionapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void registerAndLogin_ShouldReturnToken() {
        // 1. Register
        AuthenticationRequest registerRequest = new AuthenticationRequest("testuser", "password123");
        ResponseEntity<String> registerResponse = restTemplate.postForEntity("/api/auth/register", registerRequest,
                String.class);
        assertThat(registerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(registerResponse.getBody()).isEqualTo("User registered successfully");

        // 2. Login
        AuthenticationRequest loginRequest = new AuthenticationRequest("testuser", "password123");
        ResponseEntity<AuthenticationResponse> loginResponse = restTemplate.postForEntity("/api/auth/login",
                loginRequest, AuthenticationResponse.class);

        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotNull();
        assertThat(loginResponse.getBody().getJwt()).isNotNull();
    }

    @Test
    public void loginWithWrongPassword_ShouldFail() {
        // 1. Register
        AuthenticationRequest registerRequest = new AuthenticationRequest("testuser", "password123");
        restTemplate.postForEntity("/api/auth/register", registerRequest, String.class);

        // 2. Login with wrong password
        AuthenticationRequest loginRequest = new AuthenticationRequest("testuser", "wrongpassword");
        ResponseEntity<AuthenticationResponse> loginResponse = restTemplate.postForEntity("/api/auth/login",
                loginRequest, AuthenticationResponse.class);

        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
