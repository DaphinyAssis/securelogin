package securelogin.controller;

import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import securelogin.model.User;
import securelogin.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    // ==========================
    // LOGIN PAGE
    // ==========================
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        RedirectAttributes redirectAttributes) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            return "redirect:/success"; // Redirecionamento após login
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("error", "Credenciais inválidas!");
            return "redirect:/login";
        }
    }

    // ==========================
    // REGISTRATION PAGE
    // ==========================
    @GetMapping("/register")
    public String registerPage(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        // Valida campos obrigatórios e exibe mensagens específicas
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register"; // Exibe erros inline
        }

        // Valida senhas
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("user", user);
            model.addAttribute("error", "As senhas não conferem.");
            return "register";
        }

        // Verifica se username já existe
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Nome de usuário já está em uso.");
            return "register";
        }

        // Verifica se email já existe
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Email já está em uso.");
            return "register";
        }

        // Salva usuário com senha criptografada e roles padrão
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("success", "Cadastro realizado com sucesso!");
        return "redirect:/login"; // Redireciona para login com mensagem de sucesso
    }

    // ==========================
    // HOME PAGE
    // ==========================
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // ==========================
    // SUCCESS PAGE (após login)
    // ==========================
    @GetMapping("/success")
    public String successPage() {
        return "success";
    }
}
