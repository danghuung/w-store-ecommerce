package app.service.wstore.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponse {

    private String id;

    private String brand;

    private Long expMonth;

    private Long expYear;

    private String last4;

    public CardResponse(String id, String brand, Long expMonth, Long expYear, String last4) {
        this.id = id;
        this.brand = brand;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.last4 = last4;
    }
}
