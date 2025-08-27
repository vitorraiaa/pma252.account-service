package store.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private List<Account> accounts = new ArrayList<>();

    public Account create(Account account) {
        accounts.add(account);
        return account;
    }

    public List<Account> findAll() {
        return accounts;
    }
    
}
