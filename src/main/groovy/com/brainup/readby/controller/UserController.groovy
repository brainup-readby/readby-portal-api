package com.brainup.readby.controller

import com.brainup.readby.dto.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.util.stream.Collectors

@RestController
@CrossOrigin
public class UserController {

    @PostMapping("/user")
    public ResponseEntity login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        String token = getJWTToken(username)
        User user = new User()
        user.user = username
        user.token = token
        ResponseEntity.status(HttpStatus.OK).body(user)

    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey"
        List grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER")
        grantedAuthorities.stream().map({ obj -> GrantedAuthority.&getAuthority }).collect(Collectors.toList())

        Claims claims = Jwts.claims().setSubject(username)
        String token =  Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact()

                String tokens = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream().map({ obj -> GrantedAuthority.&getAuthority }).collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact()

        return "Bearer " + tokens
    }
}
