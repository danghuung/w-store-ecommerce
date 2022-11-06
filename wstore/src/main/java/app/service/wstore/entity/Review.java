package app.service.wstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "reviews")
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", updatable = false)
    private Product product;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", updatable = false)
    private Order order;
}
