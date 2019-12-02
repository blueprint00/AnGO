package model_utility;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Token {

	private final static String key = "WEHAVEGOTAWHAMBAMSHANGALANGANDASHALALALALALATHINGS";

	public static String createToken(String user_id) {

		String jwt = null;

		try {

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("typ", "JWT");
			headers.put("alg", "HS256");

			HashMap<String, Object> payloads = new HashMap<>();
			Long expiredTime = 1000 * 60 * 60 * 24L;
			Date expired_Time = new Date();

			expired_Time.setTime(expired_Time.getTime() + expiredTime);
			System.out.println(user_id + ": created token expired time : " + expired_Time);

			payloads.put("iss", "ango_server");
			payloads.put("sub", "user_token");
			payloads.put("aud", user_id);
			payloads.put("exp", expired_Time);

			jwt = Jwts.builder().setHeader(headers).setClaims(payloads)
					.signWith(SignatureAlgorithm.HS256, key.getBytes()).compact();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return jwt;
	}

	public static String getUserID(String jwt) {

		try {
			Claims claims = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(jwt).getBody();
			return claims.getAudience();

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	public static Boolean checkExpToken(String jwt) {

		try {
			Claims claims = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(jwt).getBody();

			Date expiration = claims.getExpiration();
			Date now = new Date();

			if (expiration.after(now)) {

				return true;
			} else {

				return false;

			}

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

}
