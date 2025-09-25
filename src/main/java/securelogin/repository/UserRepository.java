package securelogin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import securelogin.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    // Buscar usuário pelo username
    User findByUsername(String username);

    // Verifica se username já existe
    boolean existsByUsername(String username);

    // Verifica se email já existe
    boolean existsByEmail(String email);
}
