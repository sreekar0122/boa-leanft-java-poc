public package libraries;

import com.hp.lft.report.*;
import com.hp.lft.sdk.mainframe.MainframeScreen;

public class ReportHelper {

    public static void log(String message) {
        try {
            Reporter.reportEvent("Info", message, Status.Passed);
            System.out.println("[INFO] " + message);
        } catch (Exception e) {}
    }

    public static void logStep(String stepName, String detail, Status status) {
        try {
            Reporter.reportEvent(stepName, detail, status);
            System.out.println("[" + status.toString() + "] " + stepName + " | " + detail);
        } catch (Exception e) {}
    }

    public static void attachSnapshot(MainframeScreen screen, String description) {
        try {
            Reporter.reportEvent("Snapshot", description, Status.Passed, screen.getSnapshot());
        } catch (Exception e) {}
    }
} ReportHelper {
    
}
