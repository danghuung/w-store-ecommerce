package app.service.wstore.dto;

import app.service.wstore.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto extends BaseEntity {

    private ProductDto products;

    private int quantity;
}
