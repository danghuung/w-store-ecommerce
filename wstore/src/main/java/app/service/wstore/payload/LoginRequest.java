package app.service.wstore.payload;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
