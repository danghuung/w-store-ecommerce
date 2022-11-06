package app.service.wstore.dto;

import app.service.wstore.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto extends BaseEntity {

    private String content;

    private int rating;

    private int productId;

    private int orderId;
}
