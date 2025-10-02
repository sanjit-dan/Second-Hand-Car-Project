package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry}")
    private int expiry;


    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
        System.out.println(algorithmKey);
        System.out.println(issuer);
        System.out.println(expiry);

        algorithm = Algorithm.HMAC256(algorithmKey);
    }
         public String generateToken(String userName){
             return JWT.create()
                     .withClaim("userName",userName)
                     .withExpiresAt(new Date(System.currentTimeMillis()+expiry))
                     .withIssuer(issuer)
                     .sign(algorithm);
        }

            public String getUserName(String token){
                DecodedJWT decodedJWT= JWT.require(algorithm)
                        .withIssuer(issuer)
                        .build().
                        verify(token);
                return decodedJWT.getClaim("userName").asString();
            }
    }

