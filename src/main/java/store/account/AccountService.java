package store.account;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account create(Account account) {
        if (null == account.password()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Password is mandatory!"
            );
        }
        // clean special caracters
        account.password(account.password().trim());
        if (account.password().length() < 4) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Password is too short!"
            );
        }
        
        account.sha256(hash(account.password()));

        return accountRepository.save(
            new AccountModel(account)
        ).to();
    }

    public List<Account> findAll() {
        return StreamSupport.stream(
            accountRepository.findAll().spliterator(), false)
            .map(AccountModel::to)
            .toList();
    }

    public String hash(String pass) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                pass.getBytes(StandardCharsets.UTF_8)
            );
            return Base64.getEncoder().encodeToString(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
    
}
