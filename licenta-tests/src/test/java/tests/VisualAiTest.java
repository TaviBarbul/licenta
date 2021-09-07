package tests;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.Eyes;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class VisualAiTest extends BaseTest {

    private Eyes eyes = new Eyes();

    @BeforeClass
    public void beforeClass() {
        eyes.setApiKey("QWl2T0BERDiB4xYvKiwC5tN2StVO9HkBPTYf103gh9Lqo110");
        eyes.setBatch(new BatchInfo("Holiday Shopping"));
    }

    @Parameters( {"url"})
    @BeforeMethod
    public void beforeSetup(Method method, @Optional("https://demo.applitools.com/tlcHackathonMasterV1.html") String url) {
        driver.navigateTo(url);
        eyes.open( driver.webDriver, "Applitool-app", method.getName());
    }

    @AfterMethod
    public void tearDown() {
        eyes.closeAsync();
        if (eyes != null)
            eyes.abortIfNotClosed();
    }
}
