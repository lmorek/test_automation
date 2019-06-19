package core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

@EqualsAndHashCode(callSuper = true)
@Data
public class TestProperties extends PropertiesLoader {

    private final String environment;
    private final String serverUrl;
    private final String testUrl;
    private final String browser;
    private final String browserVersion;

    public TestProperties(Properties properties) {
        this.environment = properties.get("environment").toString();
        this.serverUrl = properties.get("serverUrl").toString();
        this.testUrl = properties.get("testUrl").toString();
        this.browser = properties.get("browser").toString();
        this.browserVersion=properties.get("browser_version").toString();

    }

//    public static Properties readPropertiesFromFile(String fileName) {
//        Properties properties = new Properties();
//        String propertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/properties/" + fileName;
//        try (Reader reader = new InputStreamReader(new FileInputStream(propertiesFilePath), "UTF-8")) {
//            properties.load(reader);
//            reader.close();
//            return properties;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
