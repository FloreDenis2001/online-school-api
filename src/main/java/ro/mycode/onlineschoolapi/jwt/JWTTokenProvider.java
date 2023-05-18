package ro.mycode.onlineschoolapi.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static java.util.Arrays.stream;
import static ro.mycode.onlineschoolapi.constante.Utils.*;


@Component
public class JWTTokenProvider {
    @Value("application.jwt.secretKey")
    private String secret;

    public String generateJWTToken(UserDetails user) {
        String[] claims = getClaimsFromUser(user);
        return JWT.create().withIssuer(MY_CODE)
                .withAudience(ADMINISTRATION)
                .withIssuedAt(new Date())
                .withSubject(user.getUsername()).withArrayClaim(AUTHORITIES,claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .sign(HMAC512(secret.getBytes()));
    }

    public String[] getClaimsFromUser(UserDetails user) {
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
            authorities.add(grantedAuthority.getAuthority());
        }
        return authorities.toArray(new String[0]);
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        String[] claims = getClaimsFormToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,null,authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

    public boolean isTokenValid(String username,String token){
        JWTVerifier verifier=getJWTVerifier();
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);
    }
    private boolean isTokenExpired(JWTVerifier verifier,String token){
        Date expiration=verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    public String getSubject(String token){
        JWTVerifier verifier=getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    private String[] getClaimsFormToken(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(MY_CODE).build();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException(TOKEN_CANNOT_BE_VERIFIED);
        }
        return verifier;
    }


}