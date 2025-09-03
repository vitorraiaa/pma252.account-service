package store.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AccountResource implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<AccountOut> create(AccountIn in) {
        // parser AccountIn to Account
        Account account = AccountParser.to(in);

        Account saved = accountService.create(account);

        // parser Account to AccountOut and build to
        // HATEAOS standard
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri()
        ).body(AccountParser.to(saved));
    }

    @Override
    public ResponseEntity<AccountOut> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<List<AccountOut>> findAll() {
        return ResponseEntity
            .ok()
            .body(AccountParser.to(accountService.findAll()));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
