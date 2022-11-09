package com.user.jwt.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.user.service.implementation.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	private static final String Secret_Key = "This is your secret key to sign in to our Digital Books";
	
	private static final int Token_validiy = 3*5;
	
	
	public String genreatingTokenForJwt(Authentication authenticate) {
		UserDetailsImpl userDI = (UserDetailsImpl) authenticate.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userDI.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + Token_validiy * 10))
				.signWith(SignatureAlgorithm.HS512, Secret_Key)
				.compact();
	}
	
	public String gettingUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(Secret_Key).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean jwtTokenValidate(String validateToken) {
		try {
			Jwts.parser().setSigningKey(Secret_Key).parseClaimsJws(validateToken);
			return true;
		}
		catch(SignatureException exception) {
			logger.error("Invalid JWT signature: {}", exception.getMessage());
		} catch (MalformedJwtException exception) {
			logger.error("Invalid JWT token: {}", exception.getMessage());
		} catch (ExpiredJwtException exception) {
			logger.error("JWT token is expired: {}", exception.getMessage());
		} catch (UnsupportedJwtException exception) {
			logger.error("JWT token is unsupported: {}", exception.getMessage());
		} catch (IllegalArgumentException exception) {
			logger.error("JWT claims string is empty: {}", exception.getMessage());
		}

		return false;
	}
}

