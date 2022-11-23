package io.rhenez.irembotest.security;


import io.rhenez.irembotest.models.User;
import io.rhenez.irembotest.repositories.UserRepository;
import io.rhenez.irembotest.services.SMSService;
import io.rhenez.irembotest.services.UserService;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomSuccessHandler.class);
    private final GrantedAuthority adminAuthority = new SimpleGrantedAuthority(
            "ROLE_ADMIN");

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    SMSService smsService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Random random = new Random();
        int code = random.nextInt(999999);
        IremboUserPrincipal user = (IremboUserPrincipal) authentication.getPrincipal();
        smsService.sendSMS(user.getUser().getPhoneNumber(), "Welcome to Irembo Your verification code is " + code);

        String token = RandomString.make(64);
        User savedUser = userRepository.findById(user.getUser().getId()).get();
        savedUser.setOTP(String.valueOf(code));
        savedUser.setOtpToken(token);
        userRepository.save(savedUser);
        invalidateSession(request, response, token);
        clearAuthenticationAttributes(request);
    }

    protected void invalidateSession(final HttpServletRequest request, final HttpServletResponse response, String token) throws IOException {
        SecurityContextHolder.getContext().setAuthentication(null);


        request.getSession().invalidate();
        redirectStrategy.sendRedirect(request, response, "/validate/otp/" + token);
    }
}