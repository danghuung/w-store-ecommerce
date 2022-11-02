package app.service.wstore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
    private int id;

    private int productId;

    private int quantity;

    private float price;

    private float sale;
}
