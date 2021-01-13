package com.rspatil45.first_project.util;

import java.util.Date;

import com.rspatil45.first_project.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtUtils {
	public String createToken(String name) {
		/**
		 * Current time
		 */
		long now = (new Date()).getTime();
		/**
		 * Validity date
		 */
		Date validity;
		validity = new Date(now + SecurityConstants.EXPIRATION_TIME);
		/**
		 * create token
		 */
		return Jwts.builder().setSubject(name).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.TOKEN_SECRET).compact();
	}

	public void parseToken(String token) {
		String user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token).getBody()
				.getSubject();
		System.out.println("user" + user);
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new RuntimeException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw new RuntimeException("Session Expired, Log in again.");
		} 
	}
}
