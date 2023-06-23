package pl.coderslab.charity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entities.CurrentUser;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (currentUser.getUser().hasRole("ROLE_ADMIN")) {
            redirectURL = "/admins/admin";
        } else if (currentUser.getUser().hasRole("ROLE_USER")) {
            redirectURL = "/users/user";
        }

        response.sendRedirect(redirectURL);
    }

}