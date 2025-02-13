package tech.astrareal.residential.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.astrareal.residential.account.AccountRepository;

import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return accountRepository.findById(UUID.fromString(id))
                .map(u -> new User(
                        u.getId().toString(),
                        u.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        u.getRoles().stream().map(r -> new SimpleGrantedAuthority(String.format("ROLE_%s", r.getName()))).toList()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("No user with UUID '" + id + "' found in the database."));
    }
}
