package io.devandre.furtmates.users.boundary.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {

    private String jwtToken;
    private String email;
    private List<String> roles;

    public LoginResponse(String jwtToken, String email, List<String> roles) {
        this.jwtToken = jwtToken;
        this.email = email;
        this.roles = roles;
    }
}
