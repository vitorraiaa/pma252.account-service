package store.account_service;

import java.util.List;

import store.account.AccountIn;
import store.account.AccountOut;

public class AccountParser {

    public static Account to(AccountIn in) {
        return in == null ? null :
            Account.builder()
                .name(in.name())
                .email(in.email())
                .password(in.password())
                .build();
    }

    public static AccountOut to(Account a) {
        return a == null ? null :
            AccountOut.builder()
                .id(a.id())
                .name(a.name())
                .email(a.email())
                .build();
    }

    public static List<AccountOut> to(List<Account> as) {
        return as == null ? null :
            as.stream().map(AccountParser::to).toList();
    }
    
}
