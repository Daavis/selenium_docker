package googleTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.runners.Parameterized;

public class GridParallelTestBase {
    protected String browserName;
    protected WebDriver driver;

    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        env.add(new String[]{"firefox"});
        env.add(new String[]{"chrome"});
        return env;
    }

    public GridParallelTestBase(String browserName) {
        this.browserName = browserName;
    }

    @Before
    public void setUp() throws Exception {
    	System.out.println("setUp: "+ browserName);
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browserName.equals("firefox")) {
        	System.out.println("FirefoxProfile: "+ browserName);
            FirefoxProfile profile = new FirefoxProfile();

            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(false);

            profile.setPreference("network.proxy.type", 0);

            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            System.out.println("END FirefoxProfile: "+ browserName);
        }
        
        capabilities.setCapability("browserN1ame", browserName);
        capabilities.setCapability("build", "JUnit-Parallel");
        driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities);
    }
    
    public void takeScreenShot () {
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotName = getClass().getSimpleName();
        System.out.println("ScreenShotName: " + screenshotName);
        try {
            FileUtils.copyFile(srcFile, new File("screenshotName"+browserName+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
