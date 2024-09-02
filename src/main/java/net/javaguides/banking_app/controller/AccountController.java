package net.javaguides.banking_app.controller;

import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.entity.Account;
import net.javaguides.banking_app.service.AccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController{
    private AccountsService accountsService;

    public AccountController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    // add account rest api

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountsService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountsService.getAccounyById(id);
        return ResponseEntity.ok(accountDto);
    }


    //Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                             @RequestBody Map<String, Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountsService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);

    }

    //WITHDRAW REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity< AccountDto> withdraw(@PathVariable Long id,
                                               @RequestBody Map<String,Double> request){

        double amount = request.get("amount");
        AccountDto accountDto = accountsService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //Get All Accounts REST API
    @GetMapping
    public  ResponseEntity<List<AccountDto>> gatAllAccounts(){
        List<AccountDto> accounts = accountsService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    //DELETE ACCOUNT REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountsService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully!");
    }







}
