package app.service.wstore.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    // public static ResponseEntity<?> ok(String accessToken) {
    // return ResponseEntity.status(HttpStatus.OK).header("gotyouin",
    // accessToken).body(null);
    // }
}
