package securelogin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import securelogin.model.User;
import securelogin.service.UserService;

import java.util.Set;

@SpringBootApplication
public class SecureLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureLoginApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserService userService) {
        return args -> {
            if (!userService.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin123"); // será criptografada no saveUser
                admin.setEmail("admin@example.com");
                admin.setRoles(Set.of("ROLE_ADMIN"));
                userService.saveUser(admin);
            }
        };
    }
}
