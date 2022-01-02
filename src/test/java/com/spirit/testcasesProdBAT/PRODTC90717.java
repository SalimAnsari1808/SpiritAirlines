package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

//**********************************************************************************************
//Test Case ID: TC90717
//Description: Task 23345: 31498 CONTINUE WITHOUT BAGS BUTTON (WITH BAGS)
//Created By : Anthony Cardona
//Created On : 09-Apr-2019
//Reviewed By: Salim Ansari
//Reviewed On: 25-Apr-2019
//**********************************************************************************************

public class PRODTC90717 extends BaseClass {

    @Parameters({"platform"})
    @Test(groups="Production")

    public void CONTINUE_WITHOUT_BAGS_BUTTON_WITH_BAGS(@Optional("NA") String platform) {

        /******************************************************************************
         ****************************Navigate to Bags Page*****************************
         ******************************************************************************/
        //Mention Suite and Browser in reports
        if (!platform.equals("NA")) {
            ValidationUtil.validateTestStep("Starting Test Case ID PRODTC90717 under SMOKE Suite on " + platform + " PRODUCTION", true);
        }

        //Home Page Constant Values
        final String LANGUAGE           = "English";
        final String JOURNEY_TYPE       = "Flight";
        final String TRIP_TYPE          = "Oneway";
        final String DEP_AIRPORTS       = "AllLocation";
        final String DEP_AIRPORT_CODE   = "FLL";
        final String ARR_AIRPORTS       = "AllLocation";
        final String ARR_AIRPORT_CODE   = "LGA";
        final String DEP_DATE           = "25";
        final String ARR_DATE           = "NA";
        final String ADULTS             = "1";
        final String CHILD              = "0";
        final String INFANT_LAP         = "0";
        final String INFANT_SEAT        = "0";

        //Flight Availability Page Constant Values
        final String DEP_FLIGHT         = "Nonstop";
        final String FARE_TYPE          = "Standard";
        final String UPGRADE_VALUE      = "BookIt";
        final String JOURNEY            = "Dep";

        //bag page constant value
        final String BAG_PAGE_URL       = "bags";
        final String BAGS               = "Carry_1|Checked_1";

        //open browser
        openBrowser(platform);

//Step 1
        //get to the Bags page
        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
        pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
        pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
        pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, ARR_DATE);
        pageMethodManager.getHomePageMethods().selectTravellingPassenger(ADULTS, CHILD, INFANT_SEAT, INFANT_LAP);
        pageMethodManager.getHomePageMethods().clickSearchButton();

        //Flight Availability Methods
        pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType(JOURNEY, DEP_FLIGHT);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(FARE_TYPE);
        pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(UPGRADE_VALUE);

        //Passenger Info Methods
        pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
        pageMethodManager.getPassengerInfoMethods().clickContinueButton();

        WaitUtil.untilPageLoadComplete(getDriver());
//without having bags click on continue without bags, validate the Pop-Up
        //click on continue without bags
        for(WebElement continueWithoutBags : pageObjectManager.getBagsPage().getContinueWithoutBagsButton())
        {
            if(continueWithoutBags.isDisplayed()) {
                continueWithoutBags.click();
                break;
            }
        }

        WaitUtil.untilTimeCompleted(1500);
        validateAreYouSureBagsPopUp();

//click on "I need bags" and validate that the user is not redirected to the Seats page
        pageObjectManager.getBagsPage().getAreYouSurePopUpINeedBagsButton().click();
        ValidationUtil.validateTestStep("After clicking on I need bags the user is not redirected to another page" , getDriver().getCurrentUrl(),BAG_PAGE_URL);

        //adding bags
        pageMethodManager.getBagsPageMethods().selectDepartingBags(BAGS);

        //Validate Shopping Cart
        //Declare variable used for validation
        String tempCarryOnBagPrice , tempCheckedBagPrice;
        String breakDownTotalPrice;
        String bagsTotalst;
        double carryonPrice;
        double checkedBagPrice;
        double bagsTotal;

        //get carryon price
        tempCarryOnBagPrice = pageObjectManager.getBagsPage().getCarryOnBagPriceDisplayText().getText();
        tempCarryOnBagPrice = tempCarryOnBagPrice.substring(tempCarryOnBagPrice.indexOf("$"), tempCarryOnBagPrice.length()).replace("$", "").trim();
        carryonPrice = Double.parseDouble(tempCarryOnBagPrice); //changed to double
        carryonPrice = carryonPrice * (Integer.parseInt(ADULTS)  + Integer.parseInt(CHILD) + Integer.parseInt(INFANT_SEAT)); //Multiply price by num of pax

        //get checked bag price
        tempCheckedBagPrice = pageObjectManager.getBagsPage().getCheckedBagPriceDisplayText().getText();
        tempCheckedBagPrice = tempCheckedBagPrice.substring(tempCheckedBagPrice.indexOf("$"), tempCheckedBagPrice.length()).replace("$", "").trim();
        checkedBagPrice = Double.parseDouble(tempCheckedBagPrice); //changed to double
        checkedBagPrice = checkedBagPrice * (Integer.parseInt(ADULTS)  + Integer.parseInt(CHILD) + Integer.parseInt(INFANT_SEAT)); //Multiply price by num of pax

        bagsTotal = carryonPrice + checkedBagPrice;   //expected total for bags
        bagsTotalst = Double.toString(bagsTotal); //bags total converted to a string

        breakDownTotalPrice = pageObjectManager.getBagsPage().getBagsTotalContainerAmountTotalText().getText().replace("$", "");
        validateBagsPriceOnShoppingCart(bagsTotalst);

        WaitUtil.untilPageLoadComplete(getDriver());
        WaitUtil.untilTimeCompleted(1500);

//click on continue without bags, validate pop-up
        for(WebElement continueWithoutBags : pageObjectManager.getBagsPage().getContinueWithoutBagsButton())
        {
            if(continueWithoutBags.isDisplayed()) {
                continueWithoutBags.click();
                break;
            }
        }
        WaitUtil.untilTimeCompleted(1500);
        validateAreYouSureBagsPopUp();


//click on I don't need bags and validate that the user is taken to the Seats page
        pageObjectManager.getBagsPage().getAreYouSurePopUpIDontNeedBagButton().click();

        validateUserTakenToSeatspageAndNoBagsInShoppingCart();

//go back to the bags page, user should have 0 bags and validate pop up and no bags on seats page
        getDriver().navigate().back();
        WaitUtil.untilPageLoadComplete(getDriver());

        for(WebElement continueWithoutBags : pageObjectManager.getBagsPage().getContinueWithoutBagsButton())
        {
            if(continueWithoutBags.isDisplayed()) {
                continueWithoutBags.click();
                break;
            }
        }

        WaitUtil.untilTimeCompleted(1500);
        validateAreYouSureBagsPopUp();

//click on I don't need bags and validate that the user is taken to the Seats page and shopping cart does not contain bags
        WaitUtil.untilPageLoadComplete(getDriver());
        pageObjectManager.getBagsPage().getAreYouSurePopUpIDontNeedBagButton().click();

        validateUserTakenToSeatspageAndNoBagsInShoppingCart();

    }

    /**
     * Description: Validates the bags price on the shopping cart vs the price from the bags page <BR>
     * @author Anthony Cardona
     * @param bagsTotal : price from the bags page
     */
    private void validateBagsPriceOnShoppingCart (String bagsTotal)
    {
        //open the shopping cart breakdown
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();
        WaitUtil.untilTimeCompleted(1200);
        //bags total displayed on shopping cart
        ValidationUtil.validateTestStep("The Shopping Cart is displayed correctly",
                pageObjectManager.getHeader().getBagsPriceItineraryText().getText().trim(),bagsTotal);
    }

    /**
     * Validate Content on the "Are You Sure?" pop-up content
     */
    private void validateAreYouSureBagsPopUp()
    {
        boolean headerIsDisplayed = pageObjectManager.getBagsPage().getAreYouSureHeader().isDisplayed();
        boolean closeBtnIsDisplayed = pageObjectManager.getBagsPage().getAreYouSurePopUpCloseButton().isDisplayed();
        boolean popUpTextIsDisplayed = pageObjectManager.getBagsPage().getAreYouSurePopUpVerbageText().isDisplayed();
        boolean needBagsBtnIsDisplayed = pageObjectManager.getBagsPage().getAreYouSurePopUpINeedBagsButton().isDisplayed();
        boolean noBagsBtnIsDisplayed = pageObjectManager.getBagsPage().getAreYouSurePopUpIDontNeedBagButton().isDisplayed();

        ValidationUtil.validateTestStep("The \"Are you sure?\" Pop Up modal is correctly displayed" , headerIsDisplayed && closeBtnIsDisplayed && popUpTextIsDisplayed && needBagsBtnIsDisplayed && noBagsBtnIsDisplayed);

    }

    /**
     * validate user taken to the seats page, validate that the shopping cart does not have bags included after selecting continue without bags
     */
    private void validateUserTakenToSeatspageAndNoBagsInShoppingCart()
    {
        final String SEAT_PAGE_URL = "seats";

        WaitUtil.untilPageLoadComplete(getDriver());
        ValidationUtil.validateTestStep("After clicking on I dont't need bags the user is not redirected to another page" , getDriver().getCurrentUrl(),SEAT_PAGE_URL);

        //expand the shopping cart
        pageObjectManager.getHeader().getArrowYourItineraryImage().click();

        //check the shopping cart and validate that there is no Bags seat price
        try
        {
            WaitUtil.untilTimeCompleted(1200);
            String bagsText = pageObjectManager.getHeader().getBagsPriceItineraryText().getText();
            ValidationUtil.validateTestStep("The seats are kept after clicking continue without seats" , false);//should fail if bags are still on the  breakdown
        }
        catch (Exception e)
        {
            ValidationUtil.validateTestStep("The Shopping Cart does not have bags after clicking continue without bags", true); //pass if exception was thrown when searching for bags on breakdown
        }
    }
}