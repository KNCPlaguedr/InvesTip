
package com.atv.pi.controller;

import com.atv.pi.model.cadastro;
import com.atv.pi.repository.CalculoRepository;
import com.atv.pi.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class CalcController {
    
    @Autowired
    private CalculoRepository calculoRepository;
    
    @Autowired
    private UserService userService;

    
    /* @GetMapping
    public String calc(Model model) {
    return "Home";
    }*/
    
    @GetMapping
public String calc(HttpSession session, Model model) {
    // Recupera o nome de usuário da sessão
    String username = (String) session.getAttribute("username");
    
    // Adiciona o nome de usuário ao modelo se estiver presente
    if (username != null) {
        model.addAttribute("username", username);
    }

    return "Home"; // Nome da página Thymeleaf
}
    
    
    
    @GetMapping("/como-investir")
    public String how(Model model) {
    return "howto";
    }
    @GetMapping("/onde-investir")
    public String where(Model model){
        return "where";
    }
    @GetMapping("/login")
    public String login (Model model){
        return "register";
    }
    
   @GetMapping("/cadastrar")
public String registrar(Model model){
    model.addAttribute("cadastro", new cadastro());
    return "cad";
}

@PostMapping("/cadastrar")
public String salvarCadastro(@ModelAttribute("cadastro") cadastro Cadastro, BindingResult result, Model model) {
    // Verifica se o formulário tem erros de validação
    if (result.hasErrors()) {
        return "cad";  // Retorna para a tela de cadastro se houver erros
    }
    
    // Verifica se o email já está cadastrado
    if (calculoRepository.existsByEmail(Cadastro.getEmail())) {
        result.rejectValue("email", "error.cadastro", "Este e-mail já está cadastrado.");
    }
    
    // Verifica se o username já está em uso
    if (calculoRepository.existsByUsername(Cadastro.getUsername())) {
        result.rejectValue("username", "error.cadastro", "Este nome de usuário já está em uso.");
    }
    
    // Se houver erros de email ou username duplicados
    if (result.hasErrors()) {
        return "cad";
    }

    // Salva o cadastro se não houver erros
    calculoRepository.save(Cadastro);
    return "redirect:/home/login";  // Redireciona para a tela de login após o cadastro bem-sucedido
}


@PostMapping("/login")
public String processLogin(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model,
                           HttpSession session) {
    if (userService.login(username, password)) {
        // Adiciona o nome de usuário na sessão
        session.setAttribute("username", username);
        return "redirect:/home"; // Redireciona para a página home
    } else {
        model.addAttribute("error", "Usuário ou senha inválidos");
        return "register"; // Retorna para a página de login com erro
    }
}

    
    
}
