package core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

}
