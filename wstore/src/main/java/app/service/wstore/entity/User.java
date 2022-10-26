package app.service.wstore.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "text")
    private String email;

    @Column(name = "phone", nullable = false, columnDefinition = "text")
    private String phone;

    @Column(name = "password", nullable = false, columnDefinition = "text")
    private String password;

    @Column(name = "fullname", nullable = true, columnDefinition = "text")
    private String fullname;
}
