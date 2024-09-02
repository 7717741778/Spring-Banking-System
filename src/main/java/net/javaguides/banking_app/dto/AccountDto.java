package net.javaguides.banking_app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data     // This annotation is used to automatically create getter, setters and constructor
@AllArgsConstructor


public class AccountDto {

    private Long id;
    private String accountHolderName;
    private double balance;
}
