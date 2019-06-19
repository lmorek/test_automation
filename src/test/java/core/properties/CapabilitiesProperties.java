package core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Properties;

@EqualsAndHashCode(callSuper = true)
@Data
public class CapabilitiesProperties extends PropertiesLoader {

    private final String configFile;
    private final String testReport;
    private final String appFilenName;
    private final String appiumVersion;
    private final String deviceName;
    private final String platformName;
    private final String platformVersion;
    private final String osVersion;
    private final String device;
    private final String realMobile;
    private final String project;
    private final String build;
    private final String name;
    private final String browserstackLocal;
    private final String os;
    private final String seleniumVersion;

    public CapabilitiesProperties(Properties capabilities) {

        this.configFile = capabilities.get("config-file").toString();
        this.testReport = capabilities.get("append-test-report").toString();
        this.appFilenName = capabilities.get("app-filename").toString();
        this.appiumVersion = capabilities.get("appium-version").toString();
        this.deviceName =capabilities.get("device-name").toString();
        this.platformName = capabilities.get("platform-name").toString();
        this.platformVersion = capabilities.get("platform-version").toString();
        this.osVersion=capabilities.get("os_version").toString();
        this.device=capabilities.get("device").toString();
        this.realMobile= capabilities.get("real_mobile").toString();
        this.project=capabilities.get("project").toString();
        this.build =capabilities.get("build").toString();
        this.name = capabilities.get("name").toString();
        this.browserstackLocal = capabilities.get("browserstack.local").toString();
        this.os = capabilities.get("os").toString();
        this.seleniumVersion= capabilities.get("browserstack.selenium_version").toString();

    }


}
