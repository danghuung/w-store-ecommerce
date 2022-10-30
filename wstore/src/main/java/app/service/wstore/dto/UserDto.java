package app.service.wstore.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private int id;
    private String email;
    private String phone;
    private String fullname;
    private String birthday;
    private boolean gender;
    private Set<RoleDto> roles;
}
