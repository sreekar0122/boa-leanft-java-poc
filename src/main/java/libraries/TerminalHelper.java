package libraries;

import com.hp.lft.report.Status;
import com.hp.lft.sdk.mainframe.*;
import config.Settings;
import objectrepository.Screens;
import java.util.HashMap;
import java.util.Map;

public class TerminalHelper {

    public static MainframeScreen waitForScreen(TeSession session, MainframeScreenDescription desc, String screenName) throws Exception {
        MainframeScreen screen = session.describe(MainframeScreen.class, desc);
        screen.waitUntilExists(Settings.SCREEN_TIMEOUT_MS);
        ReportHelper.logStep("Screen Verified", screenName, Status.Passed);
        return screen;
    }

    public static void typeInField(MainframeScreen screen, MainframeFieldDescription fieldDesc, String value) throws Exception {
        MainframeField field = screen.describe(MainframeField.class, fieldDesc);
        field.setValue(value);
    }

    public static String readField(MainframeScreen screen, MainframeFieldDescription fieldDesc) throws Exception {
        MainframeField field = screen.describe(MainframeField.class, fieldDesc);
        return field.getText().trim();
    }

    // --- BUSINESS KEYWORDS ---

    public static MainframeScreen login(TeSession session, String userId, String password) throws Exception {
        ReportHelper.logStep("KEYWORD: Login", "User: " + userId, Status.Passed);
        MainframeScreen logonScreen = waitForScreen(session, Screens.LOGON_SCREEN, "Logon Screen");
        typeInField(logonScreen, Screens.USER_ID, userId);
        typeInField(logonScreen, Screens.PASSWORD, password);
        logonScreen.sendKeys(Keys.ENTER);
        return session.describe(MainframeScreen.class, Screens.MAIN_MENU);
    }

    public static void selectMenuOption(MainframeScreen menuScreen, String option) throws Exception {
        ReportHelper.logStep("KEYWORD: Select Menu Option", option, Status.Passed);
        typeInField(menuScreen, Screens.OPTION, option);
        menuScreen.sendKeys(Keys.ENTER);
    }

    public static Map<String, String> accountInquiry(TeSession session, String accNum, String accType) throws Exception {
        ReportHelper.logStep("KEYWORD: Account Inquiry", "Account: " + accNum, Status.Passed);
        MainframeScreen screen = waitForScreen(session, Screens.ACCOUNT_INQUIRY, "Account Inquiry");
        typeInField(screen, Screens.ACC_NUMBER, accNum);
        typeInField(screen, Screens.ACC_TYPE, accType);
        screen.sendKeys(Keys.ENTER);

        ReportHelper.attachSnapshot(screen, "Account Inquiry Result");
        
        Map<String, String> result = new HashMap<>();
        result.put("balance", readField(screen, Screens.BALANCE));
        result.put("status", readField(screen, Screens.STATUS));
        return result;
    }

    public static String fundsTransfer(TeSession session, String fromAcc, String toAcc, String amt, String curr) throws Exception {
        ReportHelper.logStep("KEYWORD: Funds Transfer", "From: " + fromAcc + " To: " + toAcc, Status.Passed);
        MainframeScreen screen = waitForScreen(session, Screens.FUNDS_TRANSFER_INPUT, "Funds Transfer Input");
        typeInField(screen, Screens.FROM_ACC, fromAcc);
        typeInField(screen, Screens.TO_ACC, toAcc);
        typeInField(screen, Screens.AMOUNT, amt);
        typeInField(screen, Screens.CURRENCY, curr);
        screen.sendKeys(Keys.ENTER);

        MainframeScreen confirmScreen = waitForScreen(session, Screens.FUNDS_TRANSFER_CONFIRM, "Confirmation");
        ReportHelper.attachSnapshot(confirmScreen, "Transfer Confirmation");
        return readField(confirmScreen, Screens.CONFIRM_NUM);
    }

    public static void signOff(TeSession session) throws Exception {
        ReportHelper.logStep("KEYWORD: Sign Off", "Executing Sign Off", Status.Passed);
        MainframeScreen menuScreen = session.describe(MainframeScreen.class, Screens.MAIN_MENU);
        typeInField(menuScreen, Screens.OPTION, "99");
        menuScreen.sendKeys(Keys.ENTER);
    }
}
