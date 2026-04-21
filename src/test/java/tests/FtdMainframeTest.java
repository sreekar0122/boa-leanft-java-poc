package tests;

import com.hp.lft.report.Status;
import driver.BaseTest;
import libraries.ReportHelper;
import libraries.TerminalHelper;
import objectrepository.Screens;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

public class FtdMainframeTest extends BaseTest {

    @Test(dataProvider = "ValidLoginData", dataProviderClass = data.TestDataProvider.class)
    public void TC001_ValidLogin(String tcId, String desc, String userId, String pwd, String expScreen) throws Exception {
        ReportHelper.log("STARTING TEST: " + tcId + " - " + desc);
        TerminalHelper.login(session, userId, pwd);
        
        String screenText = session.getText();
        Assert.assertTrue(screenText.contains(expScreen), "Main Menu not displayed.");
        ReportHelper.logStep("Validation", "Main Menu displayed after login", Status.Passed);
    }

    @Test(dataProvider = "InvalidLoginData", dataProviderClass = data.TestDataProvider.class)
    public void TC002_InvalidLogin(String tcId, String desc, String userId, String pwd, String expError) throws Exception {
        ReportHelper.log("STARTING TEST: " + tcId + " - " + desc);
        TerminalHelper.login(session, userId, pwd);

        String errorText = TerminalHelper.readField(
                session.describe(com.hp.lft.sdk.mainframe.MainframeScreen.class, Screens.ERROR_SCREEN), 
                Screens.ERROR_MSG);

        Assert.assertTrue(errorText.toUpperCase().contains(expError), "Error message missing.");
        ReportHelper.logStep("Validation", "Error message verified", Status.Passed);
    }

    @Test(dataProvider = "AccountInquiryData", dataProviderClass = data.TestDataProvider.class)
    public void TC003_AccountInquiry(String tcId, String desc, String user, String pwd, String accNum, String type, String expBal, String expStatus) throws Exception {
        ReportHelper.log("STARTING TEST: " + tcId + " - " + desc);
        com.hp.lft.sdk.mainframe.MainframeScreen menuScreen = TerminalHelper.login(session, user, pwd);
        
        TerminalHelper.selectMenuOption(menuScreen, "01");
        Map<String, String> result = TerminalHelper.accountInquiry(session, accNum, type);

        Assert.assertEquals(result.get("balance"), expBal, "Balance mismatch.");
        Assert.assertEquals(result.get("status"), expStatus, "Status mismatch.");
        
        menuScreen.sendKeys(com.hp.lft.sdk.mainframe.Keys.F3); // Back to menu
    }

    @Test(dataProvider = "FundsTransferData", dataProviderClass = data.TestDataProvider.class)
    public void TC005_FundsTransfer(String tcId, String desc, String user, String pwd, String from, String to, String amt, String curr, String expMsg) throws Exception {
        ReportHelper.log("STARTING TEST: " + tcId + " - " + desc);
        com.hp.lft.sdk.mainframe.MainframeScreen menuScreen = TerminalHelper.login(session, user, pwd);

        TerminalHelper.selectMenuOption(menuScreen, "02");
        String confNum = TerminalHelper.fundsTransfer(session, from, to, amt, curr);

        String actualMessage = TerminalHelper.readField(
                session.describe(com.hp.lft.sdk.mainframe.MainframeScreen.class, Screens.FUNDS_TRANSFER_CONFIRM), 
                Screens.MESSAGE);

        Assert.assertTrue(actualMessage.toUpperCase().contains(expMsg), "Confirmation failed.");
        Assert.assertNotNull(confNum, "Confirmation number is null.");

        TerminalHelper.signOff(session);
    }
}
