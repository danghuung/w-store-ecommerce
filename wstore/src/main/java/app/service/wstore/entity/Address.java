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

@Entity(name = "address")
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "district")
    private String district;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "type", columnDefinition = "tinyint(1) default true")
    private Boolean type = true;

    @Column(name = "is_default", columnDefinition = "tinyint(1) default true")
    private Boolean isDefault = true;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;
}
