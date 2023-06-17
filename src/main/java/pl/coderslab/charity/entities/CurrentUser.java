package pl.coderslab.charity.entities;
import java.util.Collection;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
public class CurrentUser extends User {
	private final pl.coderslab.charity.entities.User user;
	public CurrentUser(String email, String password,
                       Collection<? extends GrantedAuthority> authorities,
					   pl.coderslab.charity.entities.User user) {
            super(email, password, authorities);
            this.user = user;
	}
}