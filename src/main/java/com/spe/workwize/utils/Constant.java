package com.spe.workwize.utils;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface Constant {

    //------------------------------JWT Secret Key----------------------------------------------------//
    String JWT_SECRET_KEY = "98hjdsfhhkjfd9qe1j2eufsdf12lskfdldkfn98hjdsfhhkjfd9qe1j2eufsdf12lskfdldkfn";
    //------------------------------------------------------------------------------------------------//


    //---------------------Numbers-----------------------------------//
    Integer DAY = 1000*60*60*24; // milliseconds
    Integer OTP_LENGTH = 6;
    Integer OTP_EXPIRATION_TIME = 1000 * 60 * 30;
    //---------------------------------------------------------------//


    //---------------------------------------Headers-------------------------------//
    String FORGOT_PASSWORD_SECRET = "secret";
    String JWT_AUTH_HEADER = "Authorization";
    String JWT_TOKEN_PREFIX = "Bearer ";
    String USERNAME = "username";
    //-----------------------------------------------------------------------------//



    // Password Encoder
    static PasswordEncoder passwordEncode(){
        return new BCryptPasswordEncoder();
    }

    static ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
