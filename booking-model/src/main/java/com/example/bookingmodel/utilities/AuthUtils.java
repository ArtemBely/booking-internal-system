package com.example.bookingmodel.utilities;

import com.example.bookingmodel.data.entity.Customer;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Date;

@UtilityClass
@Slf4j
public class AuthUtils {

    public Long getActualLevelId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer c1 = (Customer) authentication.getPrincipal();
        return c1.getLevelId();
    }

    public String getActualBirthday() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer c1 = (Customer) authentication.getPrincipal();
        return c1.getDateOfBirth().toString();
    }

    public int getActualId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer c1 = (Customer) authentication.getPrincipal();
        return c1.getId();
    }


//    public String getUserIdFromToken(String token, String secretKey) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//
//        return claims.getSubject();
//    }
//
//    public String extractToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
}
