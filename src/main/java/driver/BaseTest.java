package driver;

import com.hp.lft.sdk.*;
import com.hp.lft.sdk.mainframe.*;
import config.Settings;
import libraries.ReportHelper;
import org.testng.annotations.*;
import java.net.URI;

public class BaseTest {
    protected TeSession session;

    @BeforeSuite
    public void setupSuite() throws Exception {
        ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();
        config.setServerAddress(new URI("ws://" + Settings.AGENT_HOST + ":" + Settings.AGENT_PORT));
        SDK.init(config);
        ReportHelper.log("LeanFT SDK Initialized for Environment: " + Settings.ENVIRONMENT);
    }

    @BeforeMethod
    public void setupTest() throws Exception {
        session = TeSession.open(new TeSessionDescription.Builder()
                .host(Settings.TE_HOST)
                .port(Settings.TE_PORT)
                .sessionType(TeSessionType.TN3270)
                .build());
        ReportHelper.log("TE Session Opened.");
    }

    @AfterMethod
    public void teardownTest() throws Exception {
        if (session != null) {
            session.close();
        }
    }

    @AfterSuite
    public void teardownSuite() throws Exception {
        SDK.cleanup();
    }
}
