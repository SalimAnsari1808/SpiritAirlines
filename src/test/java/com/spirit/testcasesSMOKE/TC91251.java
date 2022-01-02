package com.spirit.testcasesSMOKE;

import com.spirit.baseClass.BaseClass;
import com.spirit.dataType.LoginCredentialsData;
import com.spirit.managers.FileReaderManager;
import com.spirit.utility.ValidationUtil;
import com.spirit.utility.WaitUtil;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//**********************************************************************************************
//Check7 (TC31241  | TC378739)
//Test Case ID: TC91251
//Title: Task 22925: 31241 CP_Header_Ingresar-Login_FS_number
//Description: Validating user can log in with FS# in Spanish path
//Created By : Gabriela
//Created On : 10-May-2019
//Reviewed By: Salim Ansari
//Reviewed On: 11-May-2019
//**********************************************************************************************

public class TC91251 extends BaseClass {
    @Parameters({"platform"})
    @Test(groups = {"Header" , "BookPath" , "FreeSpirit", "Spanish","HomeUI"})
    public void CP_Header_Ingresar_Login_FS_number(@Optional("NA") String platform) {
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID TC91251 under SMOKE Suite on " + platform + " Browser", true);
        }

        //Home Page Constant Values
        final String LANGUAGE 			= "Spanish";
        final String BOOK               = "Reservar";
        final String MY_TRIPS           = "Mis Viajes";
        final String CHECK_IN           = "Registrarme";
        final String FLIGHT_STATUS      = "Estado del Vuelo";
        final String LOG_IN             = "INGRESAR";
        final String HELP               = "AYUDA";
        final String CONTACT_US         = "COMUNÍQUESE CON NOSOTROS";
        final String DEALS              = "Ofertas";
        final String SPIRIT_101         = "Spirit 101";
        final String DESTINATION        = "Destinos";


//--Step 2: Access the web test environment. Spirit home page
        //open browser
        openBrowser(platform);
        pageMethodManager.getHomePageMethods().launchSpiritApp();


        ValidationUtil.validateTestStep("Validating the user land on Home Page",
                getDriver().getCurrentUrl().equals(FileReaderManager.getInstance().getConfigReader().getApplicationURL()));

//--Step 3: Navigate to "Español" in the Header. For validate the translation see the Attachment Translation_Header
        WaitUtil.untilTimeCompleted(1200);
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);

        ValidationUtil.validateTestStep("Validate Book tab right Spanish translation on Home Page",
                pageObjectManager.getHomePage().getBookPathLink().getText(),BOOK);

        ValidationUtil.validateTestStep("Validate My Trips tab right Spanish translation on Home Page",
                pageObjectManager.getHomePage().getMyTripPathLink().getText(),MY_TRIPS);

        ValidationUtil.validateTestStep("Validate Check In tab right Spanish translation on Home Page",
                pageObjectManager.getHomePage().getCheckInPathLink().getText(),CHECK_IN);

        ValidationUtil.validateTestStep("Validate Flight Status tab right Spanish translation on Home Page",
                pageObjectManager.getHomePage().getFlightStatusPathLink().getText(),FLIGHT_STATUS);

        ValidationUtil.validateTestStep("Validate Sign In link right Spanish translation on Home Page",
                pageObjectManager.getHeader().getSignInLink().getText(),LOG_IN);

        ValidationUtil.validateTestStep("Validate Help link right Spanish translation on Home Page",
                pageObjectManager.getHeader().getHelpLink().getText().toUpperCase(),HELP);

        ValidationUtil.validateTestStep("Validate Contact Us link right Spanish translation on Home Page",
                pageObjectManager.getHeader().getContactUsLink().getText(),CONTACT_US);

        ValidationUtil.validateTestStep("Validate Deals link right Spanish translation on Home Page",
                pageObjectManager.getHeader().getDealsLinkLink().getText(),DEALS);

        ValidationUtil.validateTestStep("Validate Spirit 101 displayed on Home Page",
                pageObjectManager.getHeader().getSpirit101Link().getText(),SPIRIT_101);

        ValidationUtil.validateTestStep("Validate Destination link right Spanish translation on Home Page",
                pageObjectManager.getHeader().getDestinationsLink().getText(),DESTINATION);

//-- Step 4: Navigate to and select "Ingresar" in the Header
        //pageObjectManager.getHeader().getContactUsLink().click();


//-- Step 5: Input a Free Spirit number and password then Login with the pre-existing FS account
        LoginCredentialsData loginCredentialsData = FileReaderManager.getInstance().getJsonReader().getCredentialsByUserType("FSEmail");

        //wait for load page is complete
        WaitUtil.untilPageLoadComplete(getDriver());

        //login with FS number
        pageMethodManager.getHomePageMethods().loginToApplication(loginCredentialsData.fsNumber,loginCredentialsData.password);

        //validate sign in complete
        ValidationUtil.validateTestStep("After Login with FS Member Sign-In will not appear on Home Page",
                !pageObjectManager.getHeader().getUserDropDown().getText().contains(LOG_IN));

    }
}
