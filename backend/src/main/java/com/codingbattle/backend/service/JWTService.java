package com.codingbattle.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    /**
     * Secret key used for signing and verifying JWT tokens.
     */
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /**
     * Expiration time for JWT tokens in milliseconds.
     */
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    /**
     * Generates a JWT token for the specified username.
     *
     * @param username the username for which to generate the token
     * @return the generated JWT token
     */
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        // USer role can be dynamic based on your requirements
        claims.put("role", role);
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .and()
                .signWith(SignatureAlgorithm.HS256, getKey())
                .compact();

    }

    /**
     * Retrieves the secret key for signing and verifying JWT tokens.
     *
     * @return the secret key
     */
    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * Extracts the username from the specified JWT token.
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts the expiration date from the specified JWT token.
     *
     * @param token the JWT token from which to extract the expiration date
     * @return the extracted expiration date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }



    /**
     * Extracts all claims from the specified JWT token.
     *
     * @param token the JWT token from which to extract the claims
     * @return the extracted Claims object
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Validates the specified JWT token against the provided user details.
     *
     * @param token       the JWT token to be validated
     * @param userDetails the user details to validate against
     * @return true if the token is valid and matches the user details, false otherwise
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUserName(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Checks if the specified JWT token has expired.
     *
     * @param token the JWT token to be checked
     * @return true if the token has expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the user role from the specified JWT token.
     *
     * @param token the JWT token from which to extract the user role
     * @return the extracted user role
     */
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }
}
