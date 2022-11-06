package app.service.wstore.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "products")
@Table(name = "products")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "float")
    private float price;

    @Column(name = "sale", nullable = true, columnDefinition = "float")
    private float sale;

    @Column(name = "desctiption", nullable = true, columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String image;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int inStock;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default true")
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;
}
