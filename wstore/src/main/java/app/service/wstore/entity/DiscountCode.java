package app.service.wstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "discount_codes")
@Table(name = "discount_codes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCode extends BaseEntity {

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "amount_off", nullable = false)
    private float amountOff;

    @Column(name = "min_order_value")
    private float minOrderValue;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default true")
    private Boolean isActive = true;

}
