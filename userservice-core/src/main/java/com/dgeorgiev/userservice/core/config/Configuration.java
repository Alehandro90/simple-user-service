package com.dgeorgiev.userservice.core.config;

import com.novarto.lang.crypto.PasswordHasher;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import fj.P1;

import javax.sql.DataSource;

public class Configuration
{


    private static final Config CONFIG = ConfigFactory.load();

    private static final int SALT_SIZE = CONFIG.getInt("userservice.core.saltSize");
    private static final int HASH_SIZE = CONFIG.getInt("userservice.core.hashSize");
    private static final int KEY_STRETCH_ITERATIONS =  CONFIG.getInt("userservice.core.keyStrechIterations");
    
    public static final PasswordHasher PASSWORD_HASHER = new PasswordHasher(SALT_SIZE, HASH_SIZE, KEY_STRETCH_ITERATIONS);


}
