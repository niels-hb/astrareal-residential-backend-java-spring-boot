package tech.astrareal.residential.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tech.astrareal.residential.account.Account;
import tech.astrareal.residential.account.AccountRepository;

import java.util.UUID;

@Component
public class AuthenticationUtil {
    @Autowired
    private AccountRepository accountRepository;

    public Account getAccount() {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return accountRepository.findById(UUID.fromString(details.getUsername()))
                .orElseThrow(() -> new RuntimeException("No account found with email '" + details.getUsername() + "'"));
    }
}
