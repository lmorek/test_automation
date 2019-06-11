package core;

import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

@Data
public class TestProperties {

    private final String environment;
    private final String serverUrl;
    private final String browser;

    public TestProperties(Properties properties) {
        this.environment = properties.get("environment").toString();
        this.serverUrl = properties.get("serverUrl").toString();
        this.browser = properties.get("browser").toString();

    }

    public static Properties readPropertiesFromFile(String fileName) {
        Properties properties = new Properties();
        String propertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/properties" + fileName;
        try (Reader reader = new InputStreamReader(new FileInputStream(propertiesFilePath), "UTF-8")) {
            properties.load(reader);
            reader.close();
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
