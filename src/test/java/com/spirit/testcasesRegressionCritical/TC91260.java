package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.BaseClass;
import com.spirit.utility.JSExecuteUtil;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC91260
//Description: Task 24747: 35129 CP_Header_Español_9 FC
//Created By: Gabriela
//Created On: 26-Jun-2019
//Reviewed By: Salim Ansari
//Reviewed On: 27-Jun-2019
//**********************************************************************************************

public class TC91260 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups={"HomeUI", "Spanish", "Header", "NineDFC"})

    public void CP_Header_Español_9_FC(@Optional("NA") String platform) {

        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91260 under REGRESSION-CRITICAL Suite on " + platform + " Browser", true);
        }

        //Home Page Constant variables
        final String LANGUAGE       = "Spanish";
        final String BOOK           = "Reservar";
        final String MY_TRIPS       = "Mis Viajes";
        final String CHECK_IN       = "Registrarme";
        final String FLIGHT_STATUS  = "Estado del Vuelo";
        final String SIGN_IN        = "INGRESAR";
        final String NEED_HELP      = "AYUDA";
        final String CONTACT_US     = "COMUNÍQUESE CON NOSOTROS";
        final String DEALS          = "Ofertas";
        final String $9_FARE_CLUB   = "$9 Fare Club®";
        final String SPIRIT_101     = "Spirit 101";
        final String DESTINATIONS   = "Destinos";
        final String $9FC_URL       = "spirit.com/club-enrollment";

//-- Step 1: Access the SkySales (Web) test environment.
        //open browser
        openBrowser(platform);

        /****************************************************************************
         * ************************Home Page Methods*********************************
         ****************************************************************************/
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        WaitUtil.untilPageLoadComplete(getDriver());
//-- Step 2: Navigate to  "Español" in the Header. For validate the translation see the Attachment Translation_Header
        //--HDR-0001
        ValidationUtil.validateTestStep("Validating Book properly Spanish translation",
                pageObjectManager.getHomePage().getBookPathLink().getText(), BOOK.trim().toUpperCase());
        //--HDR-0002
        ValidationUtil.validateTestStep("Validating My Trips properly Spanish translation",
                pageObjectManager.getHomePage().getMyTripPathLink().getText(), MY_TRIPS.trim().toUpperCase());
        //--HDR-0003
        ValidationUtil.validateTestStep("Validating Check-In properly Spanish translation",
                pageObjectManager.getHomePage().getCheckInPathLink().getText(), CHECK_IN.trim().toUpperCase());
        //--HDR-0004
        ValidationUtil.validateTestStep("Validating Flight Status properly Spanish translation",
                pageObjectManager.getHomePage().getFlightStatusPathLink().getText(), FLIGHT_STATUS.trim().toUpperCase());
        //--HDR-0005
        ValidationUtil.validateTestStep("Validating Sign In properly Spanish translation",
                pageObjectManager.getHomePage().getSignInListLink().get(1).getText(), SIGN_IN.trim().toUpperCase());
        //--HDR-0006
        ValidationUtil.validateTestStep("Validating Help properly Spanish translation",
                pageObjectManager.getHeader().getHelpLink().getText(), NEED_HELP.toUpperCase().trim());
        //--HDR-0007
        ValidationUtil.validateTestStep("Validating Contact Us properly Spanish translation",
                pageObjectManager.getHeader().getContactUsLink().getText(), CONTACT_US.toUpperCase().trim());
        //--HDR-0008
        ValidationUtil.validateTestStep("Validating Deals properly Spanish translation",
                pageObjectManager.getHeader().getDealsLinkLink().getText(), DEALS.toUpperCase().trim());
        //--HDR-0009
        ValidationUtil.validateTestStep("Validating $9 Fare Club properly Spanish translation",
                pageObjectManager.getHeader().getNineFareClubLink().getText(), $9_FARE_CLUB.toUpperCase().trim());
        //--HDR-0010
        ValidationUtil.validateTestStep("Validating Spirit 101 properly Spanish translation",
                pageObjectManager.getHeader().getSpirit101Link().getText(), SPIRIT_101.toUpperCase().trim());
        //--HDR-0011
        ValidationUtil.validateTestStep("Validating Destinations properly Spanish translation",
                pageObjectManager.getHeader().getDestinationsLink().getText(), DESTINATIONS.toUpperCase().trim());
        //--HDR-0012 || HDR-0013 || HDR-0014 Invalid Validation

//-- Step 3: In the Header of the Home Page click on the $9 Fare Club link
        WaitUtil.untilTimeCompleted(3000);
        JSExecuteUtil.clickOnElement(getDriver(),pageObjectManager.getHeader().getNineFareClubLink());
        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("Validating user is taken to the right URL", getDriver().getCurrentUrl(), $9FC_URL);
    }
}