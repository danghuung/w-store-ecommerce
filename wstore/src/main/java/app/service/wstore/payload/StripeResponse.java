package app.service.wstore.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StripeResponse {

    private String id;

    private String clientSecret;

    public StripeResponse(String id, String clientSecret) {
        this.id = id;
        this.clientSecret = clientSecret;
    }
}
