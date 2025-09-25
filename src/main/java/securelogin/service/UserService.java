package securelogin.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import securelogin.model.User;
import securelogin.repository.UserRepository;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Salva usuário com senha criptografada e roles padrão
    public User saveUser(User user) {
        // Remove confirmPassword antes de salvar
        user.setConfirmPassword(null);

        // Criptografa senha
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Define role padrão se nenhuma estiver presente
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Set.of("ROLE_USER"));
        }

        return userRepository.save(user);
    }

    // Verifica se usuário já existe pelo username
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Verifica se usuário já existe pelo email
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
