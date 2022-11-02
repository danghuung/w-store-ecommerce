package app.service.wstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "orders")
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "order_status", columnDefinition = "varchar(255) default 'proccessing'")
    private String orderStatus;

    @Column(name = "payment_status", columnDefinition = "varchar(255) default 'incomplete'")
    private String paymentStatus;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "total", columnDefinition = "float default 0.0")
    private float total;

}
