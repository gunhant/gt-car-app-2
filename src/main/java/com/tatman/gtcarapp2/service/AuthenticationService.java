package com.tatman.gtcarapp2.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Date;
import static java.util.Collections.emptyList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AuthenticationService {

    static final long EXPIRATIONTIME = 864_000_00; //1 DAY IN milliseconds
    static final String SIGNINGKEY = "SecretKey";
    static final String PREFIX = "Bearer";

    static public void addToken(HttpServletResponse res, String username){
        String jwtToken = Jwts.builder().setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
        .signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
        .compact();

        res.addHeader("Authorization", PREFIX + " " + jwtToken);
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
    }

    static public Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null){
            String user = Jwts.parser()
                    .setSigningKey(SIGNINGKEY)
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            if(user != null)
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
        }
        return null;
    }

}
