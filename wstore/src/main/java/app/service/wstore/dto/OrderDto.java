package app.service.wstore.dto;

import java.util.Set;

import app.service.wstore.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto extends BaseEntity {
    private String fullName;

    private String address;

    private String phone;

    private String orderStatus;

    private String paymentStatus;

    private String discountCode;

    private float total;

    private Set<OrderDetailDto> orderDetails;
}
