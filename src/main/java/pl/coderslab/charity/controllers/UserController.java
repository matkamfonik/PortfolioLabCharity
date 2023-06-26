package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dtos.UserDTO;
import pl.coderslab.charity.entities.CurrentUser;
import pl.coderslab.charity.entities.Role;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.mappers.UserMapper;
import pl.coderslab.charity.services.interfaces.RoleService;
import pl.coderslab.charity.services.interfaces.UserService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    private final RoleService roleService;

    @ModelAttribute("roles")
    public List<Role> roles() {
        return roleService.findAll();
    }

    @GetMapping({"/register", "/admins/register"})
    public String register(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute(name = "user") @Valid UserDTO userDTO, BindingResult result) {
        if (userService.findByEmail(userDTO.getEmail()) != null) {
            result.rejectValue("email", "error.user", "Użytkownik o takim adresie email już istnieje");
        }
        if (result.hasErrors()) {
            return "register";
        }
        User user = userMapper.toEntity(userDTO);
        userService.saveNewUser(user);

        return "login";
    }

    @GetMapping("users/user")
    public String showUser() {

        return "users/userPanel";
    }

    @GetMapping("users/user/edit")
    public String editCurrentUser(Model model,
                                  @AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        UserDTO userDTO = userMapper.toDTO(user);
        model.addAttribute("user", userDTO);
        return "users/editUser";
    }

    @PostMapping("users/user/edit")
    public String editCurrentUser(@ModelAttribute(name = "user") @Valid UserDTO userDTO,
                                  BindingResult result,
                                  @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "users/editUser";
        }
        User user = currentUser.getUser();
        user = userMapper.toEntity(userDTO, user);
        userService.saveUser(user);
        return "users/userPanel";
    }

    @GetMapping("users/user/change-password")
    public String changePassword() {
        return "users/change-password";
    }

    @PostMapping("users/user/change-password")
    public String changePassword(@RequestParam(name = "password") String pass,
                                 @AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        user.setPassword(pass);
        userService.changePassword(user);
        return "users/userPanel";
    }

    @GetMapping("users/user/disable")
    public String banUser(@AuthenticationPrincipal CurrentUser currentUser, HttpServletRequest httpServletRequest) throws ServletException {
        userService.disable(currentUser.getUser().getId());
        httpServletRequest.logout();
        return "redirect:/";
    }

    @GetMapping("admins/users/all")
    public String showUsers(Model model) {
        Role roleUser = roleService.findByName("ROLE_USER");
        model.addAttribute("users", userService.findAllByRole(roleUser).stream().map(userMapper::toDTO).toList());
        return "admins/users";
    }

    @GetMapping({"admins/users/{id}", "admins/{id}"})
    public String showUser(Model model,
                           @PathVariable(name = "id") Long id) {
        User user = userService.findById(id).orElseThrow(EntityNotFoundException::new);
        UserDTO userDTO = userMapper.toDTO(user);
        model.addAttribute("user", userDTO);
        return "admins/user";
    }

    @GetMapping({"admins/users/{id}/edit", "admins/{id}/edit"})
    public String editUser(Model model,
                           @PathVariable(name = "id") Long id) {
        User user = userService.findById(id).orElseThrow(EntityNotFoundException::new);
        UserDTO userDTO = userMapper.toDTO(user);
        model.addAttribute("user", userDTO);
        return "admins/editUser";
    }

    @PostMapping({"admins/users/{id}/edit", "admins/{id}/edit"})
    public String editUser(@ModelAttribute(name = "user") @Valid UserDTO userDTO,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "admins/editUser";
        }
        User user = userService.findById(userDTO.getId()).orElseThrow(EntityNotFoundException::new);
        user = userMapper.toEntity(userDTO, user);
        Set<Role> roles = userDTO.getRolesNames().stream().map(roleService::findByName).collect(Collectors.toSet());
        user.setRoles(roles);
        userService.saveUser(user);
        String usersListAddress = "";
        if (roles.contains(roleService.findByName("ROLE_ADMIN"))) {
            usersListAddress = "/admins/all";
        } else {
            usersListAddress = "/admins/users/all";
        }

        return "redirect:" + usersListAddress;
    }

    @GetMapping({"admins/users/{id}/disable", "admins/{id}/disable"})
    public String banUser(@PathVariable(name = "id") Long id) {
        userService.disable(id);
        Set<Role> roles = userService.findById(id).get().getRoles();
        String usersListAddress = "";
        if (roles.contains(roleService.findByName("ROLE_ADMIN"))) {
            usersListAddress = "/admins/all";
        } else {
            usersListAddress = "/admins/users/all";
        }
        return "redirect:" + usersListAddress;
    }

    @GetMapping({"admins/users/{id}/delete", "admins/{id}/delete"})
    public String deleteUser(@PathVariable(name = "id") Long id) {
        Set<Role> roles = userService.findById(id).get().getRoles();
        userService.delete(id);
        String usersListAddress = "";
        if (roles.contains(roleService.findByName("ROLE_ADMIN"))) {
            usersListAddress = "/admins/all";
        } else {
            usersListAddress = "/admins/users/all";
        }
        return "redirect:" + usersListAddress;
    }

    @GetMapping("admins/admin")
    public String showAdmin() {

        return "admins/adminPanel";
    }

    @GetMapping("admins/all")
    public String showAdmins(Model model) {
        Role roleAdmin = roleService.findByName("ROLE_ADMIN");
        model.addAttribute("admins", userService.findAllByRole(roleAdmin).stream().map(userMapper::toDTO).toList());
        return "admins/admins";
    }

    @PostMapping("/admins/register")
    public String createAdmin(@ModelAttribute(name = "user") @Valid UserDTO userDTO, BindingResult result) {
        if (userService.findByEmail(userDTO.getEmail()) != null) {
            result.rejectValue("email", "error.user", "Użytkownik o takim adresie email już istnieje");
        }
        if (result.hasErrors()) {
            return "register";
        }
        User user = userMapper.toEntity(userDTO);
        userService.saveNewAdmin(user);

        return "login";
    }

}