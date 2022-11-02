package app.service.wstore.dto;

import app.service.wstore.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto extends BaseEntity {

    private String street;

    private String ward;

    private String district;

    private String city;

    private String country;

    private Boolean type;

    private Boolean isDefault;
}
