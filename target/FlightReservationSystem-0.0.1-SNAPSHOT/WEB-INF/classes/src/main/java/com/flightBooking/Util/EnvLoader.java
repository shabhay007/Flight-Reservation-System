
package com.flightBooking.Util;

import io.github.cdimascio.dotenv.Dotenv;

//public class EnvLoader {
//    public static void loadEnv() {
//    	Dotenv dotenv = Dotenv.configure()
//                .directory("C:/Users/shabh/Desktop/Eclipse") // Local path
//                .filename(".env")
//                .ignoreIfMissing() // Ignore missing .env file in AWS
//                .load();
//
//        // Try fetching from .env or fallback to system env
////        String dbUrl = getEnv(dotenv, "DB_URL");
//        String dbHost = getEnv(dotenv, "DB_HOST");
//        String dbPort = getEnv(dotenv, "DB_PORT");
//        String dbName = getEnv(dotenv, "DB_NAME");
//        String dbUsername = getEnv(dotenv, "DB_USERNAME");
//        String dbPassword = getEnv(dotenv, "DB_PASSWORD");
//
//        if (dbHost == null || dbPort == null || dbName == null || dbUsername == null || dbPassword == null) {
//            throw new RuntimeException("Missing required DB environment variables.");
//        }
//
//        // Only loading environment-specific properties like DB credentials
////        System.setProperty("db.url", dotenv.get("DB_URL"));
//        System.setProperty("db.host", dotenv.get("DB_HOST"));
//        System.setProperty("db.port", dotenv.get("DB_PORT"));
//        System.setProperty("db.name", dotenv.get("DB_NAME"));
//        System.setProperty("db.username", dotenv.get("DB_USERNAME"));
//        System.setProperty("db.password", dotenv.get("DB_PASSWORD"));
//    }
//    
//    private static String getEnv(Dotenv dotenv, String key) {
//        // Priority: .env > system env
//        String value = dotenv.get(key);
//        if (value == null) {
//            value = System.getenv(key);
//        }
//        return value;
//    }
//}


public class EnvLoader {
    public static void loadEnv() {
        // In AWS, these will come from Beanstalk environment variables
        String dbHost = System.getenv("DB_HOST");
        String dbPort = System.getenv("DB_PORT");
        String dbName = System.getenv("DB_NAME");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbHost == null || dbPort == null || dbName == null || dbUsername == null || dbPassword == null) {
            // Try .env file only in local development
            Dotenv dotenv = Dotenv.configure()
                    .directory("C:/Users/shabh/Desktop/Eclipse")
                    .filename(".env")
                    .ignoreIfMissing()
                    .load();
            
            dbHost = getEnv(dotenv, "DB_HOST");
            dbPort = getEnv(dotenv, "DB_PORT");
            dbName = getEnv(dotenv, "DB_NAME");
            dbUsername = getEnv(dotenv, "DB_USERNAME");
            dbPassword = getEnv(dotenv, "DB_PASSWORD");

            if (dbHost == null || dbPort == null || dbName == null || dbUsername == null || dbPassword == null) {
                throw new RuntimeException("Missing required DB environment variables.");
            }
        }

        System.setProperty("db.host", dbHost);
        System.setProperty("db.port", dbPort);
        System.setProperty("db.name", dbName);
        System.setProperty("db.username", dbUsername);
        System.setProperty("db.password", dbPassword);
    }
    
    private static String getEnv(Dotenv dotenv, String key) {
        return dotenv.get(key);
    }
}

