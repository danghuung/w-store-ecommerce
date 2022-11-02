package app.service.wstore.dto;

import app.service.wstore.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountCodeDto extends BaseEntity {

    private String code;

    private float amountOff;

    private float minOrderValue;

    private Boolean isActive;
}
