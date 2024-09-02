package net.javaguides.banking_app.service.impl;

import net.javaguides.banking_app.dto.AccountDto;
import net.javaguides.banking_app.entity.Account;
import net.javaguides.banking_app.mapper.AccountMapper;
import net.javaguides.banking_app.repository.AccountRepository;
import net.javaguides.banking_app.service.AccountsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service  // it will automatically create the spring beam for this class. Now we will inject the dependency
public class AcccountServiceImpl  implements AccountsService {

    private AccountRepository accountRepository;

    public AcccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



     /*within this method, we will convert account dto into account jpa entity
     then we will save that account jpa entity into database,
      now we will create a mapper class with common  logic which convert dto into jpa and vice versa */


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);


    }

    @Override
    public AccountDto getAccounyById(Long id) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account doesnt exist "));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesnt exist "));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);


        return  AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesnt exist "));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
       Account savedAccount =  accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
       List<Account> accounts =  accountRepository.findAll();
       return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
               .collect(Collectors.toList());


    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesnt exist "));

        accountRepository.deleteById(id);


    }
}
