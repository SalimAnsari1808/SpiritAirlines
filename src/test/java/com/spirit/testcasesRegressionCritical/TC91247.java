package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91247
//Description: Task 24743: 35126 CP_Header_9 FC
//Created By: Gabriela
//Created On: 26-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 27-Jun-2019
//**********************************************************************************************

public class TC91247 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"HomeUI", "Header"})
    public void CP_Header_9_FC(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91247 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String BOOK           = "Book";
        final String MY_TRIPS       = "My Trips";
        final String CHECK_IN       = "Check-In";
        final String FLIGHT_STATUS  = "Flight Status";
        final String SIGN_IN        = "SIGN-IN";
        final String NEED_HELP      = "HELP";
        final String CONTACT_US     = "CONTACT US";
        final String DEALS          = "Deals";
        final String $9_FARE_CLUB   = "$9 Fare Club®";
        final String SPIRIT_101     = "Spirit 101";
        final String DESTINATIONS   = "Destinations";
        final String $9FC_URL       = "spirit.com/club-enrollment";

//-- Step 1: Access the SkySales (Web) test environment.
        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();

        WaitUtil.untilPageLoadComplete(getDriver());
//-- Step 2: Navigate to  "Español" in the Header. For validate the translation see the Attachment Translation_Header
        //--HDR-0001
        ValidationUtil.validateTestStep("Validating right verbiage on header",
                pageObjectManager.getHomePage().getBookPathLink().getText(), BOOK);

        //--HDR-0002
        ValidationUtil.validateTestStep("Validating right verbiage on header",
                pageObjectManager.getHomePage().getMyTripPathLink().getText(), MY_TRIPS);

        //--HDR-0003
        ValidationUtil.validateTestStep("Validating right verbiage on header",
                pageObjectManager.getHomePage().getCheckInPathLink().getText(), CHECK_IN);

        //--HDR-0004
        ValidationUtil.validateTestStep("Validating right verbiage on header",
                pageObjectManager.getHomePage().getFlightStatusPathLink().getText(), FLIGHT_STATUS);
        //--HDR-0005
        ValidationUtil.validateTestStep("Validating right verbiage on heather",
                pageObjectManager.getHomePage().getSignInListLink().get(1).getText(), SIGN_IN);
        //--HDR-0006
        ValidationUtil.validateTestStep("Validating right verbiage on header",
                pageObjectManager.getHeader().getHelpLink().getText(), NEED_HELP);
        //--HDR-0007
        ValidationUtil.validateTestStep("Validating right verbiage on header",
                pageObjectManager.getHeader().getContactUsLink().getText(), CONTACT_US);
        //--HDR-0008
        ValidationUtil.validateTestStep("Validating right verbiage on header",
                pageObjectManager.getHeader().getDealsLinkLink().getText(), DEALS);
        //--HDR-0009
        ValidationUtil.validateTestStep("Validating right verbiage on header",
                pageObjectManager.getHeader().getNineFareClubLink().getText(), $9_FARE_CLUB);
        //--HDR-0010
        ValidationUtil.validateTestStep("Validating right verbiage on header",
                pageObjectManager.getHeader().getSpirit101Link().getText().trim(), SPIRIT_101);
        //--HDR-0011
        ValidationUtil.validateTestStep("Validating right verbiage on header",
                pageObjectManager.getHeader().getDestinationsLink().getText(), DESTINATIONS);
        //--HDR-0012 || HDR-0013 || HDR-0014 Invalid Validation

//-- Step 3: In the Header of the Home Page click on the $9 Fare Club link
        WaitUtil.untilTimeCompleted(3000);
        pageObjectManager.getHeader().getNineFareClubLink().click();
        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(2000);
        ValidationUtil.validateTestStep("Validating user is taken to the right URL", getDriver().getCurrentUrl(), $9FC_URL);
    }
}