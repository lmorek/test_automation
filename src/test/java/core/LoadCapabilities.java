package core;

import core.properties.CapabilitiesProperties;
import core.properties.PropertiesLoader;
import org.openqa.selenium.remote.DesiredCapabilities;

//TODO add more capabilities when mobile support is added
public class LoadCapabilities {


    private String environment;
    private String browser;

    public static CapabilitiesProperties capabilitiesProperties;

    private DesiredCapabilities capabilities;

    public LoadCapabilities( String environment, String browser) {
        this.environment = environment;
        this.browser = browser;
        capabilities = new DesiredCapabilities();
    }

    public DesiredCapabilities prepareCapabilities() {
        capabilitiesProperties = new  CapabilitiesProperties(PropertiesLoader.readPropertiesFromFile("capabilities.properties"));
        capabilities.setCapability("os", capabilitiesProperties.getOs());
        capabilities.setCapability("os_version", capabilitiesProperties.getOsVersion());
        capabilities.setCapability("browser", browser);
        capabilities.setCapability("browserstack.local", capabilitiesProperties.getBrowserstackLocal());
        capabilities.setCapability("browserstack.selenium_version", capabilitiesProperties.getSeleniumVersion());
        return capabilities;
    }

}
