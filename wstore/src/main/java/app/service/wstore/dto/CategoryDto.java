package app.service.wstore.dto;

import app.service.wstore.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto extends BaseEntity {

    private String name;
}
