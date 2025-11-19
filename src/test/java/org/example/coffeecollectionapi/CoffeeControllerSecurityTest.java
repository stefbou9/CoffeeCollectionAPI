package org.example.coffeecollectionapi;

import org.example.coffeecollectionapi.model.AuthenticationRequest;
import org.example.coffeecollectionapi.model.AuthenticationResponse;
import org.example.coffeecollectionapi.model.CoffeeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoffeeControllerSecurityTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private org.example.coffeecollectionapi.repository.UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
        // Register a user for testing
        AuthenticationRequest registerRequest = new AuthenticationRequest("admin", "password");
        restTemplate.postForEntity("/api/auth/register", registerRequest, String.class);
    }

    @Test
    public void accessProtectedEndpointWithoutToken_ShouldReturn403() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/coffees", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void loginAndAccessProtectedEndpoint_ShouldReturn200() {
        // 1. Login
        AuthenticationRequest loginRequest = new AuthenticationRequest("admin", "password");
        ResponseEntity<AuthenticationResponse> loginResponse = restTemplate.postForEntity("/api/auth/login",
                loginRequest, AuthenticationResponse.class);

        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        String token = loginResponse.getBody().getJwt();
        assertThat(token).isNotNull();

        // 2. Access protected endpoint with token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("/api/coffees", HttpMethod.GET, entity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void createCoffeeWithInvalidData_ShouldReturn400() {
        // 1. Login
        AuthenticationRequest loginRequest = new AuthenticationRequest("admin", "password");
        ResponseEntity<AuthenticationResponse> loginResponse = restTemplate.postForEntity("/api/auth/login",
                loginRequest, AuthenticationResponse.class);
        String token = loginResponse.getBody().getJwt();

        // 2. Create invalid coffee (missing name)
        CoffeeDTO invalidCoffee = new CoffeeDTO();
        invalidCoffee.setPrice(BigDecimal.TEN);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<CoffeeDTO> entity = new HttpEntity<>(invalidCoffee, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/coffees", entity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void deleteCoffee_AsNormalUser_ShouldReturn403() {
        // 1. Login (User "admin" has ROLE_USER by default)
        AuthenticationRequest loginRequest = new AuthenticationRequest("admin", "password");
        ResponseEntity<AuthenticationResponse> loginResponse = restTemplate.postForEntity("/api/auth/login",
                loginRequest, AuthenticationResponse.class);
        String token = loginResponse.getBody().getJwt();

        // 2. Try to DELETE
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange("/api/coffees/1", HttpMethod.DELETE, entity, Void.class);

        // Should be Forbidden because only ROLE_ADMIN can delete
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void swaggerEndpoint_ShouldBeAccessible() {
        ResponseEntity<String> response = restTemplate.getForEntity("/v3/api-docs", String.class);
        System.out.println("Swagger Response Status: " + response.getStatusCode());
        System.out.println("Swagger Response Body: " + response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
