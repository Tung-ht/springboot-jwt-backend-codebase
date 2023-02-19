package com.sapo.mock.techshop.common.constant;

public class JwtConstant {
    public static final long EXPIRATION_TIME = 43200000; // 12 h
    public static final long REFRESH_TOKEN_EXP_TIME = 43200000 * 6; // 3 days
    public static final String SECRET_KEY = "$2y$10$ibD0TTC.m4Kc.Vk9Q1o1sODigtxScJzDDUOPZ0xOGtTLLfRtTFATq";
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
}
