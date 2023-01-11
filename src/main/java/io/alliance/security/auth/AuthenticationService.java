package io.alliance.security.auth;

import io.alliance.security.auth.payload.request.AuthenticationRequest;
import io.alliance.security.auth.payload.request.RegistrationRequest;
import io.alliance.security.auth.payload.response.AuthenticationResponse;
import io.alliance.security.config.jwt.JwtService;
import io.alliance.security.role.Role;
import io.alliance.security.role.RoleService;
import io.alliance.security.user.User;
import io.alliance.security.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${security.role-with-registration}")
    private String roleWithRegistration;

    public AuthenticationResponse register(RegistrationRequest request) {
        final Set<Role> roles = Collections.singleton(
                this.roleService.findByName(this.roleWithRegistration));

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        userService.save(user);
        var jwtToken = jwtService.generateJwt(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );
        var jwt = jwtService.generateJwt((User) authentication.getPrincipal());
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}