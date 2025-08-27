package store.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountResource implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    public AccountOut create(AccountIn in) {
        // parser AccountIn to Account
        Account account = AccountParser.to(in);

        Account saved = accountService.create(account);

        // parser Account to AccountOut
        return AccountParser.to(saved);
    }

    @Override
    public AccountOut findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AccountOut> findAll() {
        return AccountParser.to(accountService.findAll());
    }

    @Override
    public Void delete(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
