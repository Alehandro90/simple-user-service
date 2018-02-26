package com.dgeorgiev.userservice.core.config;

import com.dgeorgiev.userservice.core.util.FlywayMigrator;
import com.novarto.lang.crypto.PasswordHasher;
import com.novarto.sanedbc.core.interpreter.ValidationDbInterpreter;
import com.novarto.sanedbc.hikari.Hikari;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.DriverManager;
import java.util.Properties;

public class Configuration
{


    private static final Config CONFIG = ConfigFactory.load();

    private static final HikariDataSource DS;

    static {
        DS = Hikari.createHikari("jdbc:hsqldb:mem:test", "sa", "", new Properties());
    }

    private static final int SALT_SIZE = CONFIG.getInt("userservice.core.saltSize");
    private static final int HASH_SIZE = CONFIG.getInt("userservice.core.hashSize");
    private static final int KEY_STRETCH_ITERATIONS =  CONFIG.getInt("userservice.core.keyStrechIterations");
    
    public static final PasswordHasher PASSWORD_HASHER = new PasswordHasher(SALT_SIZE, HASH_SIZE, KEY_STRETCH_ITERATIONS);

    public static final ValidationDbInterpreter DBI = new ValidationDbInterpreter(
            () -> DS.getConnection()
    );

    public static final FlywayMigrator MIGRATOR = new FlywayMigrator("migration", DS);

    public static String DEFAULT_ADMIN = CONFIG.getString("userservice.core.defaultAdmin.name");
    public static String DEFAULT_ADMIN_EMAIL = CONFIG.getString("userservice.core.defaultAdmin.email");
    public static String DEFAULT_ADMIN_PASSWORD = CONFIG.getString("userservice.core.defaultAdmin.password");




}
