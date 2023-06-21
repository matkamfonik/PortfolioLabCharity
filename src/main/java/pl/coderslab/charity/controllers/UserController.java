package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entities.CurrentUser;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.services.interfaces.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
 
    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute(name = "user") @Valid User user, BindingResult result){
        if (userService.findByEmail(user.getEmail()) != null){
            result.rejectValue("email","error.user","Użytkownik o takim adresie email już istnieje");
        }
        if (result.hasErrors()){
            return "register";
        }
        userService.saveUser(user);

        return "login";
    }

    @GetMapping("users")
    public String show(Model model,
            @AuthenticationPrincipal CurrentUser currentUser){

        return "users/user";
    }
}