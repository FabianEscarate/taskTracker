package config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static EnvConfig instance;
    private final Dotenv dotenv;

    public static EnvConfig getInstance() {
        if (instance == null) {
            instance = new EnvConfig();
        }
        return instance;
    }

    EnvConfig() {
        dotenv = Dotenv.configure()
              .directory("./")
              .filename(".env")
              .load();
    }

    public String get(String key) {
        String value = dotenv.get(key);
        if (value == null) {
          value = System.getenv(key);
        }
        return value;
    } 

    public Integer getInt(String key) {
        String value = get(key);
        if (value != null) {
            return Integer.parseInt(value);
        }
        return null;
    }
}
