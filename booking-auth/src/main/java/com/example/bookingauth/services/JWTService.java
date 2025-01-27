package com.example.bookingauth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@AllArgsConstructor
@NoArgsConstructor
//@ConfigurationProperties(prefix = "application.security.jwt")
public class JWTService {

    @Value("${application.security.jwt.secret-key:e6e0f7f3da92315175573342a6f43af384a6b4a6bbff7229decf83e5907f6d62}")
    //404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
//    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${application.security.jwt.expiration:1200000}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration:2100000}")
    private long refreshExpiration;


//    private static final String SECRET_KEY = "e6e0f7f3da92315175573342a6f43af384a6b4a6bbff7229decf83e5907f6d62";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Simulate switching to user.
     *
     * @param userEmail incoming user id which was chosen
     * @param adminUserDetails
     * @return token which include userEmail
     */
    public String generateImpersonationToken(String userEmail, UserDetails adminUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("impersonateUserId", userEmail);
        return buildToken(claims, adminUserDetails, jwtExpiration);
    }

    public String extractImpersonateUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("impersonateUserId", String.class);
    }

    public String generateToAdminToken(String userEmail, UserDetails adminUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userEmail", userEmail);
        return buildToken(claims, adminUserDetails, jwtExpiration);
    }

    public String extractToAdminUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("userEmail", String.class);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
