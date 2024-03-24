package com.onyshkiv.util.password;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordHashGenerator {
    private static Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
    public static String hash(String password){
        return argon2.hash(2,15*1024,1, password.toCharArray());
    }
    public static boolean verify(String password,String hash){
        return  argon2.verify(hash, password.toCharArray());
    }


}
