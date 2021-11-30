package com.JWT.JWToken.controller;


import com.JWT.JWToken.Utility.JWTUtility;
import com.JWT.JWToken.model.JWTRequest;
import com.JWT.JWToken.model.JWTResponce;
import com.JWT.JWToken.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
public class JWTController {

    String token=null,name=null;
    @Autowired
    private JWTUtility jwtUtility;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String home() {
        return "Welcome to Daily Code Buffer!!";
    }

    @PostMapping("/add")
    public ResponseEntity<JWTRequest> addEmployee(@RequestBody JWTRequest jpareq) {
        JWTRequest newEmployee = userService.saveDeoartment(jpareq);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/home")
    public String Welcome() {

        return token;
    }
    @PostMapping("/authenticate")
    public JWTResponce authenticate(@RequestBody JWTRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
            name=jwtRequest.getUsername()+" "+jwtRequest.getPassword();

        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

         token =
                jwtUtility.generateToken(userDetails);

        JWTRequest jwtRequest1=userService.saveDeoartment(jwtRequest);
        return  new JWTResponce(token);
    }
}
