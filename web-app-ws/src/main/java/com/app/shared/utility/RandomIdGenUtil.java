package com.app.shared.utility;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomIdGenUtil {
	private static final String ALPHABETS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";
	private final Random random = new SecureRandom();
	private static final String NUMBERS = "0123456789";
	private static final String CAPITAL_ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public String generateUserID(int length) {
		return generateRandomString(length);
	}

	public String generateAddressID(int length) {
		return generateRandomString(length);
	}
	private String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(ALPHABETS.charAt(random.nextInt(ALPHABETS.length())));
		}
		return new String(sb);
	}
	
	public String generateRegistrationID() {
		StringBuilder sb= new StringBuilder();
		sb.append(generateRandomNumberString(5,NUMBERS)).append("-").append(generateRandomNumberString(2,CAPITAL_ALPHABETS));
		return sb.toString();
	}
	private String generateRandomNumberString(int length,String characters) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(characters.charAt(random.nextInt(characters.length())));
		}
		return new String(sb);
	}
}
