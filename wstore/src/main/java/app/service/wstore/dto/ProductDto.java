package app.service.wstore.dto;

import app.service.wstore.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto extends BaseEntity {

    private String name;

    private float price;

    private float sale;

    private String description;

    private String image;

    private int inStock;

    private int categoryId;
}
