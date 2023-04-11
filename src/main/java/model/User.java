package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String accountName;

    @Column(length = 50)
    private String password;

    @Column(length = 10)
    private String firstName;

    @Column(length = 10)
    private String lastName;

    @Column(length = 20)
    private String identityCard;

    @Column(length = 20)
    private String phone;

    @Column(length = 50)
    private String email;

    //True is male, False is female
    private Boolean gender;

    // role: 0 is admin, role: 1 is manager and role: 2 is customer
    @Column(length = 1)
    private Integer role;
}
