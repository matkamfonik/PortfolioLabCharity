package pl.coderslab.charity.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.CurrentUser;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.services.interfaces.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SpringDataUserDetailsService implements UserDetailsService {
 
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByEmail(username);
        if (user == null) {throw new UsernameNotFoundException(username); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new CurrentUser(username, user.getPassword(),
                grantedAuthorities, user);
    }
}