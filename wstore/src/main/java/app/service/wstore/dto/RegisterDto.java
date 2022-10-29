package app.service.wstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String email;

    private String phone;

    private String password;
}
