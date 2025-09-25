package securelogin.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import securelogin.model.User;
import securelogin.repository.UserRepository;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        // Evita username ou password nulos/vazios
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new UsernameNotFoundException("Usuário ou senha inválidos para: " + username);
        }

        // Garante que roles nunca sejam nulas
        Set<GrantedAuthority> authorities = (user.getRoles() == null ? Collections.emptySet() : user.getRoles().stream()
                .filter(role -> role != null && !role.isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet()));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
