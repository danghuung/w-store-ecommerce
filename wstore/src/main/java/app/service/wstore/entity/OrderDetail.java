package app.service.wstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "order_details")
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private float price;

    @Column(name = "sale")
    private float sale;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", updatable = false)
    private Product product;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", updatable = false)
    private Order order;
}
