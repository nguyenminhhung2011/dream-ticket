package com.ticket.server.config;

import com.ticket.server.model.AppToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {
    private static final String SECRET_KEY =  "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

//    private final Date expiredAccessToken = new Date(System.currentTimeMillis()+ 1000*60*24);
//    private final Date expiredRefreshToken =new Date(System.currentTimeMillis()+ 1000*60*48);

    private Date expiredAccessToken(){
        return new Date(System.currentTimeMillis()+ 1000*60*1000);
    }

    private Date expiredRefreshToken(){
        return new Date(System.currentTimeMillis()+ 1000*60*1200);
    }
    public String extractUsername(String token){
        return extractClaims(token,Claims::getSubject);
    }

    public AppToken.AppTokenBuilder generateAccessToken(UserDetails userDetails){
        return AppToken.builder()
                .token(generateToken(new HashMap<>(),userDetails,expiredAccessToken()))
                .expiredTime(expiredAccessToken())
                .isExpired(false)
                .isRevoke(false);
    }
    public AppToken.AppTokenBuilder generateRefreshToken(UserDetails userDetails){
        return AppToken.builder()
                .token(generateToken(new HashMap<>(),userDetails,expiredRefreshToken())).
                expiredTime(expiredRefreshToken())
                .isExpired(false)
                .isRevoke(false);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails,Date expiredTime){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS256, getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return false;
    }

    public Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
