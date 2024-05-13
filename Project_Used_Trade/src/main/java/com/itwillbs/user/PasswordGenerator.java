package com.itwillbs.user;

import java.security.SecureRandom;

public class PasswordGenerator {

    // 임시 비밀번호 길이
    private static final int PASSWORD_LENGTH = 10;

    // 임시 비밀번호 생성 메서드
    public static String generateRandomPassword() {
        // 임시 비밀번호에 사용할 문자열
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        
        // 임시 비밀번호를 담을 StringBuilder 생성
        StringBuilder password = new StringBuilder();
        
        // SecureRandom 객체 생성
        SecureRandom random = new SecureRandom();
        
        // 비밀번호 길이만큼 반복하여 임시 비밀번호 생성
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            // characters 문자열에서 무작위로 문자 선택하여 임시 비밀번호에 추가
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        
        // 생성된 임시 비밀번호 반환
        return password.toString();
    }

    public static void main(String[] args) {
        // 임시 비밀번호 생성하여 출력
        String password = generateRandomPassword();
        System.out.println("임시 비밀번호: " + password);
    }
}