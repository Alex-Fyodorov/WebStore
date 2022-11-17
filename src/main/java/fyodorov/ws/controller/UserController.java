package fyodorov.ws.controller;

import fyodorov.ws.items.User;
import fyodorov.ws.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public String userPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

//    @PostMapping("/insert")
//    public String saveUser(@Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "add_product";
//        }
//        userRepository.save(user.getName());
//        return "redirect:/user";
//    }
}
