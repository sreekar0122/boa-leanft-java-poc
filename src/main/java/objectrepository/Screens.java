package objectrepository;

import com.hp.lft.sdk.mainframe.*;

/**
 * Object Repository: Strictly-typed Java Definitions.
 * Replaces generic JS objects to provide compile-time safety.
 */
public class Screens {

    // LOGON SCREEN
    public static final MainframeScreenDescription LOGON_SCREEN = new MainframeScreenDescription.Builder()
            .text("LOGON").build();
    public static final MainframeFieldDescription USER_ID = new MainframeFieldDescription.Builder()
            .attachedText("USER ID").startRow(10).startColumn(20).length(8).build();
    public static final MainframeFieldDescription PASSWORD = new MainframeFieldDescription.Builder()
            .attachedText("PASSWORD").startRow(12).startColumn(20).length(8).build();

    // MAIN MENU
    public static final MainframeScreenDescription MAIN_MENU = new MainframeScreenDescription.Builder()
            .text("MAIN MENU").build();
    public static final MainframeFieldDescription OPTION = new MainframeFieldDescription.Builder()
            .attachedText("OPTION").startRow(20).startColumn(2).length(2).build();

    // ACCOUNT INQUIRY
    public static final MainframeScreenDescription ACCOUNT_INQUIRY = new MainframeScreenDescription.Builder()
            .text("ACCOUNT INQUIRY").build();
    public static final MainframeFieldDescription ACC_NUMBER = new MainframeFieldDescription.Builder()
            .attachedText("ACCOUNT NUMBER").startRow(8).startColumn(25).length(10).build();
    public static final MainframeFieldDescription ACC_TYPE = new MainframeFieldDescription.Builder()
            .attachedText("ACCOUNT TYPE").startRow(9).startColumn(25).length(3).build();
    public static final MainframeFieldDescription BALANCE = new MainframeFieldDescription.Builder()
            .attachedText("BALANCE").startRow(14).startColumn(25).length(15).build();
    public static final MainframeFieldDescription STATUS = new MainframeFieldDescription.Builder()
            .attachedText("STATUS").startRow(15).startColumn(25).length(10).build();

    // FUNDS TRANSFER
    public static final MainframeScreenDescription FUNDS_TRANSFER_INPUT = new MainframeScreenDescription.Builder()
            .text("FUNDS TRANSFER").build();
    public static final MainframeFieldDescription FROM_ACC = new MainframeFieldDescription.Builder()
            .attachedText("FROM ACCOUNT").startRow(8).startColumn(25).length(10).build();
    public static final MainframeFieldDescription TO_ACC = new MainframeFieldDescription.Builder()
            .attachedText("TO ACCOUNT").startRow(10).startColumn(25).length(10).build();
    public static final MainframeFieldDescription AMOUNT = new MainframeFieldDescription.Builder()
            .attachedText("AMOUNT").startRow(12).startColumn(25).length(12).build();
    public static final MainframeFieldDescription CURRENCY = new MainframeFieldDescription.Builder()
            .attachedText("CURRENCY").startRow(14).startColumn(25).length(3).build();

    public static final MainframeScreenDescription FUNDS_TRANSFER_CONFIRM = new MainframeScreenDescription.Builder()
            .text("TRANSFER SUCCESSFUL").build();
    public static final MainframeFieldDescription CONFIRM_NUM = new MainframeFieldDescription.Builder()
            .attachedText("CONFIRMATION NUMBER").startRow(10).startColumn(25).length(20).build();
    public static final MainframeFieldDescription MESSAGE = new MainframeFieldDescription.Builder()
            .attachedText("MESSAGE").startRow(12).startColumn(25).length(60).build();

    // ERROR SCREEN
    public static final MainframeScreenDescription ERROR_SCREEN = new MainframeScreenDescription.Builder()
            .text("INVALID").build();
    public static final MainframeFieldDescription ERROR_MSG = new MainframeFieldDescription.Builder()
            .attachedText("MESSAGE").startRow(20).startColumn(2).length(79).build();
}
