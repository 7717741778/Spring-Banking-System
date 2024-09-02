package net.javaguides.banking_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// @Entity is used to make the class as JPA entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts" )
@Entity
public class Account {

    @Id                                                      // this annotation is used to amke is as a primary key in database.
    @GeneratedValue(strategy = GenerationType.IDENTITY)      // this annotation is used to configure primary key generation strategy. This will automatically increase the primary key id.


    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;
}
