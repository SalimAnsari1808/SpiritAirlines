package com.spirit.testcasesRegressionCritical;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC91285
//Description : Misc_CP_MT_SinglePAX _ Options_Verify other options page is correct
//Created By : Alex Rodriguez
//Created On : 26-JUL-2019
//Reviewed By: Salim Ansari
//Reviewed On:14-Aug-2019
//**********************************************************************************************
public class TC91285 extends BaseClass {
  @Parameters({"platform"})
  @Test(groups = {"OptionalServicesUI"})
  public void Misc_CP_MT_SinglePAX_Options_Verify_other_options_page_is_correct(@Optional("NA") String platform) {

    //************************************************************************************
    //*************************Navigate to Reservation Summary Page***********************
    //************************************************************************************
    //mention the browser
    if (!platform.equals("NA")) {
      ValidationUtil.validateTestStep("Starting Test Case ID TC91285 under REGRESSION-CRITICAL suite on " + platform + " Browser", true);
    }

        //Home Page Constant Values
        final String LANGUAGE = "English";

        //Boarding Constant Values
        final String OP_SERVICE_URL     = "/optional-services";
        final String BP_TEXT            = "We have plenty of additional extras and services listed below - snacks and drinks, too. All of it costs something, because the Bare Fare is unbundled. You never pay for someone else’s options. Even if you purchase a few extras, it’s likely you’ll fly with us for less than any other airline.";
        final String BP_TEXT1           = "Boarding pass printed at home";
        final String BP_TEXT2           = "Boarding pass printed by Airport Kiosk";
        final String BP_TEXT3           = "Boarding pass printed by Airport Agent";
        final String BP_TEXT4           = "Unaccompanied Minors (Price includes snack and beverage)";
        final String BP_TEXT5           = "Infant (Lap Child)";
        final String BP_TEXT6           = "Pet Transportation (Limit 4 pets total in cabin)";
        final String BP_TEXT7           = "Travel Guard Insurance / Domestic Itinerary";
        final String BP_TEXT8           = "Travel Guard Insurance / International Itinerary";
        final String BP_TEXT9           =  "Travel Guard Insurance / Vacation Package Itinerary";
        final String BP_TOOLTIP_TEXT1   = "Print your boarding pass at home to save.";
        final String BP_TOOLTIP_TEXT2   = "Print your boarding pass at home to save.";
        final String BP_TOOLTIP_TEXT3   = "Print your boarding pass at home to save.";
        final String BP_TOOLTIP_TEXT4   = "We allow certain ages of children to travel as an unaccompanied minor. More Info";
        final String BP_TOOLTIP_TEXT5   = "Some of our littlest flyers are welcome to fly on your lap free of charge. (Government taxes/fees may apply.) More Info";
        final String BP_TOOLTIP_TEXT6   = "We love all pets, but unfortunately we can only transport certain types.  More Info";
        final String BP_TOOLTIP_TEXT7   = "Protect your trip from the unexpected with travel insurance from  Travel Guard. ";
        final String BP_TOOLTIP_TEXT8   = "Protect your trip from the unexpected with travel insurance from  Travel Guard. ";
        final String BP_TOOLTIP_TEXT9   = "Protect your trip from the unexpected with travel insurance from  Travel Guard. ";
        final String BP_PRICE_TEXT1     = "Free";
        final String BP_PRICE_TEXT2     = "$2 per boarding pass printed";
        final String BP_PRICE_TEXT3     = "$10 per boarding pass printed";
        final String BP_PRICE_TEXT4     = "$100 per customer, each way";
        final String BP_PRICE_TEXT5     = "Free (taxes may apply for certain countries)";
        final String BP_PRICE_TEXT6     = "$110 per pet container, each way";
        final String BP_PRICE_TEXT7     = "From $14";
        final String BP_PRICE_TEXT8     = "From $25";
        final String BP_PRICE_TEXT9     = "From $28";

        //Onboard Drinks and Snacks Constant Values
        final String ONBOARD_HEADER     = "Onboard Snacks and Drinks";
        final String SNACK_TEXT         = "Snacks";
        final String DRINK_TEXT         = "Drinks";
        final String SNK_TOOLTIP_TXT    = "We offer a large variety of snacks for purchase onboard our aircraft. Save even more by bringing your own snacks for the flight. More Info";
        final String DRINK_TOOLTIP_TXT  = "We offer a large variety of drinks for purchase onboard our aircraft. More Info";
        final String SNACK_PRICE_TXT    = "$1 to $10";
        final String DRINK_PRICE_TXT     = "$1 to $15";

        //Booking Related Constant Values
        final String BOOKING_HEADER           = "Booking Related";
        final String RES_CENTER_BOOKING       = "Reservation Center Booking (including packages)";
        final String GROUP_BOOKING            = "Group Booking";
        final String COLOMBIA_CHARGE          = "Colombia Administrative Charge";
        final String STANDBY_FLIGHT           = "Standby for Earlier Flight";
        final String PASSENGER_CHARGE         = "Passenger Usage Charge";
        final String REGULATORY_CHARGE        = "Regulatory Compliance Charge";
        final String FUEL_CHARGE              = "Fuel Charge";
        final String RES_CENTER_TOOLTIP       = "This applies to bookings made via our reservation center since a live agent is more expensive than self-service online. Save even more by booking your trip online.";
        final String GROUP_BOOKING_TOOLTIP    = "This applies to group bookings, which consist of 10 or more passengers on a single itinerary. Unfortunately, group bookings cannot be made online at this time. More Info";
        final String COLOMBIA_CHARGE_INFO     = "This applies to bookings that are sold in or depart from Colombia and is regulated by the Colombian Government.";
        final String STANDBY_FLIGHT_TOOLTIP   = "This applies to customers who arrive to the airport and want to fly out on an earlier flight.";
        final String PASSENGER_CHARGE_INFO    = "This applies to bookings created online or via reservations centers.";
        final String REGULATORY_CHARGE_INFO   = "This charge is to recover costs incurred due to Department of Transportation (DOT) Regulations.";
        final String FUEL_CHARGE_TOOLTIP      = "Fuel charge applies to most reservations. This surcharge will not apply to certain discount fares.";
        final String RES_CENTER_FEE           = "$35 per booking";
        final String GROUP_BOOKING_FEE        = "$5 per booking";
        final String COLOMBIA_CHARGE_FEE      = "$15 to $95 per booking";
        final String STANDBY_FLIGHT_FEE       = "$99 each way";
        final String PASSENGER_CHARGE_FEE     = "$11.99 to $22.99 per segment";
        final String REGULATORY_CHARGE_FEE    = "$7 per segment";
        final String FUEL_CHARGE_FEE          = "$12 per segment";

        //Free Spirit Award Booking Constant Values
        final String AGENT_TRANSACTION        = "Agent Transaction - Reservation Center";
        final String AWARD_REDEMPTION         = "Award Redemption";
        final String AWARD_MODIFICATION       = "Award Modification";
        final String MILEAGE_REDEPOSIT        = "Mileage Redeposit";
        final String AGENT_TRANSACTION_INFO   = "This applies to reservation center transactions for award bookings. Vacation bookings are $35, while all other bookings are $25. Save more by booking and modifying your award bookings online.";
        final String AWARD_REDEMPTION_INFO    = "This applies to all award bookings. Save more by booking your trip further in advance. More Info";
        final String AWARD_MODIFICATION_INFO  = "This applies to all award booking modifications. More Info";
        final String MILEAGE_REDEPOSIT_INFO   = "This applies to all award booking cancellations and includes re-depositing your miles back into your account. Save more by managing your trip online.";
        final String AGENT_TRANSACTION_FEE    = "$25 to $35";
        final String AWARD_REDEMPTION_FEE     = "Free to $75";
        final String AWARD_MODIFICATION_FEE   = "$110";
        final String MILEAGE_REDEPOSIT_FEE    = "$110";

        //Modification or Cancellation
        final String WEB_MOD_OR_CANCEL        = "Web Modification or Cancellation";
        final String RESERVE_MOD_CANCEL       = "Reservations / Airport Modification or Cancellation";
        final String GROUP_MOD_OR_CANCEL      = "Group Booking Itinerary Modification or Cancellation";
        final String WEB_MOD_OR_CANCEL_INFO   = "This applies to web modifications or cancellations. As a courtesy, some bookings are eligible for a free cancellation for 24 hours after initial purchase. More Info";
        final String RESERVE_MOD_CANCEL_INFO  = "This applies to reservations and airport modifications or cancellations. Save even more by modifying or cancelling your booking online. More Info ";
        final String GROUP_MOD_OR_CANCEL_INFO = "This applies to modifications or cancellations to a group booking itinerary. More Info";
        final String WEB_MOD_OR_CANCEL_FEE    = "$90";
        final String RESERVE_MOD_CANCEL_FEE   = "$100";
        final String GROUP_MOD_OR_CANCEL_FEE  = "$50";

        //Extras
        final String FLIGHT_FLEX              = "Flight Flex";
        final String SHORTCUT_BOARDING        = "Shortcut Boarding";
        final String SHORTCUT_SECURITY        = "Shortcut Security";
        final String FLIGHT_FLEX_TOOLTIP      = "Modify your itinerary once for free, online, up to 24 hours before departure (fare difference applies).";
        final String SHORTCUT_BOARDING_INFO   = "Zone 2 Priority Boarding";
        final String FLIGHT_FLEX_PRICE        = "$35 to $45";
        final String SHORTCUT_BOARDING_PRICE  = "$5.99+ each way";
        final String SHORTCUT_SECURITY_PRICE  = "up to $15";

//open browser
        openBrowser(platform);

        //Home Page Methods
        pageMethodManager.getHomePageMethods().launchSpiritApp();
        pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
        WaitUtil.untilTimeCompleted(2000);

        JSExecuteUtil.clickOnElement(getDriver(), pageObjectManager.getHomePage().getOptionalServiceLink());
        WaitUtil.untilPageLoadComplete(getDriver());
//Step 2
        //URL Validation
        ValidationUtil.validateTestStep("Validating Payment Page URL",
                getDriver().getCurrentUrl(), OP_SERVICE_URL);
//Step 3
        pageObjectManager.getOptionalServicesPage().getOtherLabel().click();
        ValidationUtil.validateTestStep("User validates Boarding pass printed at home verbiage",
                BP_TEXT, pageObjectManager.getOptionalServicesPage().getOtherHeaderText().getText().trim());
        ValidationUtil.validateTestStep("User validates Boarding pass printed at home verbiage",
                BP_TEXT1, pageObjectManager.getOptionalServicesPage().getBoardingPassPrintedAtHomeText().getText().trim());
        ValidationUtil.validateTestStep("User validates Boarding pass printed at home price verbiage",
                BP_PRICE_TEXT1, pageObjectManager.getOptionalServicesPage().getBPPrintedAtHomePriceText().getText().trim());

        ValidationUtil.validateTestStep("User validates Boarding pass printed by Kiosk verbiage",
                BP_TEXT2, pageObjectManager.getOptionalServicesPage().getBoardingPassByAirportKioskText().getText().trim());
        ValidationUtil.validateTestStep("User validates Boarding pass printed by Kiosk price verbiage",
                BP_PRICE_TEXT2, pageObjectManager.getOptionalServicesPage().getBPPrintedAtKioskPriceText().getText().trim());

        ValidationUtil.validateTestStep("User validates Boarding pass printed by Agent verbiage",
                BP_TEXT3, pageObjectManager.getOptionalServicesPage().getBoardingPassAtAirportAgentText().getText().trim());
        ValidationUtil.validateTestStep("User validates Boarding pass printed by Agent price verbiage",
                BP_PRICE_TEXT3, pageObjectManager.getOptionalServicesPage().getBPPrintedByAgentPriceText().getText().trim());

        ValidationUtil.validateTestStep("User validates Unaccompanied Minors verbiage", BP_TEXT4,
                pageObjectManager.getOptionalServicesPage().getUnaccompaniedMinorsText().getText().trim());
        ValidationUtil.validateTestStep("User validates Unaccompanied Minors price verbiage", BP_PRICE_TEXT4,
                pageObjectManager.getOptionalServicesPage().getUMNRPriceText().getText().trim());

        ValidationUtil.validateTestStep("User validates Infant on Lap verbiage",
                BP_TEXT5, pageObjectManager.getOptionalServicesPage().getInfantText().getText().trim());
        ValidationUtil.validateTestStep("User validates Infant on Lap price verbiage",
                BP_PRICE_TEXT5, pageObjectManager.getOptionalServicesPage().getInfantFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates pet transportation limit verbiage",
                BP_TEXT6, pageObjectManager.getOptionalServicesPage().getPetTransportationText().getText().trim());
        ValidationUtil.validateTestStep("User validates pet transportation limit price verbiage",
                BP_PRICE_TEXT6, pageObjectManager.getOptionalServicesPage().getPetContainerPriceText().getText().trim());

        ValidationUtil.validateTestStep("User validates Domestic Travel Guard Insurance verbiage",
                BP_TEXT7, pageObjectManager.getOptionalServicesPage().getTGInsuranceDomesticText().getText().trim());
        ValidationUtil.validateTestStep("User validates Domestic Travel Guard Insurance price verbiage",
                BP_PRICE_TEXT7, pageObjectManager.getOptionalServicesPage().getTGIDOmesticFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates International Travel Guard Insurance verbiage",
                BP_TEXT8, pageObjectManager.getOptionalServicesPage().getTGInsuranceInternationalText().getText().trim());
        ValidationUtil.validateTestStep("User validates International Travel Guard Insurance price verbiage",
                BP_PRICE_TEXT8, pageObjectManager.getOptionalServicesPage().getTGIInternationalFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates Vacation Travel Guard Insurance verbiage",
                BP_TEXT9, pageObjectManager.getOptionalServicesPage().getTGInsuranceVacationText().getText().trim());
        ValidationUtil.validateTestStep("User validates Vacation Travel Guard Insurance price verbiage",
                BP_PRICE_TEXT9, pageObjectManager.getOptionalServicesPage().getTGIVacationFeeText().getText().trim());

        pageObjectManager.getOptionalServicesPage().getOnboardSnacksAndDrinksChevronLink().click();
        ValidationUtil.validateTestStep("User validates the header for Onboard Snacks and Drinks",
                ONBOARD_HEADER, pageObjectManager.getOptionalServicesPage().getOnboardSnacksAndDrinksLabel().getText().trim());
        ValidationUtil.validateTestStep("User validates Snacks verbiage",
                SNACK_TEXT, pageObjectManager.getOptionalServicesPage().getSnacksText().getText().trim());
        ValidationUtil.validateTestStep("User validates Snacks price verbiage",
                SNACK_PRICE_TXT, pageObjectManager.getOptionalServicesPage().getSnacksPriceText().getText().trim());

        ValidationUtil.validateTestStep("User validates Drinks verbiage",
                DRINK_TEXT, pageObjectManager.getOptionalServicesPage().getDrinksText().getText().trim());
        ValidationUtil.validateTestStep("User validates Drinks price verbiage",
                DRINK_PRICE_TXT, pageObjectManager.getOptionalServicesPage().getDrinksPriceText().getText().trim());

        pageObjectManager.getOptionalServicesPage().getBookingRelatedChevronLink().click();
        ValidationUtil.validateTestStep("User validates Booking Related Header verbiage",
                BOOKING_HEADER, pageObjectManager.getOptionalServicesPage().getBookingRelatedLabel().getText().trim() );
        ValidationUtil.validateTestStep("User validates Reservation Center Booking (including packages) verbiage",
                RES_CENTER_BOOKING, pageObjectManager.getOptionalServicesPage().getReservationCenterText().getText().trim() );
        ValidationUtil.validateTestStep("User validates Reservation Center Booking price verbiage",
                RES_CENTER_FEE, pageObjectManager.getOptionalServicesPage().getReservationCenterBookingFeeText().getText().trim() );

        ValidationUtil.validateTestStep("User validates Group Booking verbiage",
                GROUP_BOOKING, pageObjectManager.getOptionalServicesPage().getGroupBookingText().getText().trim() );
        ValidationUtil.validateTestStep("User validates Group Booking fee verbiage",
                GROUP_BOOKING_FEE, pageObjectManager.getOptionalServicesPage().getGroupBookingFeeText().getText().trim() );

        ValidationUtil.validateTestStep("User validates Colombia Administrative Charge verbiage",
                COLOMBIA_CHARGE, pageObjectManager.getOptionalServicesPage().getColombiaAdministrativeChargeText().getText().trim() );
        ValidationUtil.validateTestStep("User validates Colombia Administrative Charge fee verbiage",
                COLOMBIA_CHARGE_FEE, pageObjectManager.getOptionalServicesPage().getColombiaAdministrativeFeeText().getText().trim() );

        ValidationUtil.validateTestStep("User validates Standby for Earlier Flight verbiage",
                STANDBY_FLIGHT, pageObjectManager.getOptionalServicesPage().getStandbyForEarlierFlightText().getText().trim());
        ValidationUtil.validateTestStep("User validates Standby for Earlier Flight fee verbiage",
                STANDBY_FLIGHT_FEE, pageObjectManager.getOptionalServicesPage().getStandbyFlightFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates Passenger Usage Charge verbiage",
                PASSENGER_CHARGE, pageObjectManager.getOptionalServicesPage().getPassengerUsageChargeText().getText().trim() );
        ValidationUtil.validateTestStep("User validates Passenger Usage fee verbiage",
                PASSENGER_CHARGE_FEE, pageObjectManager.getOptionalServicesPage().getPassengerUsageFeeText().getText().trim() );

        ValidationUtil.validateTestStep("User validates Regulatory Compliance Charge verbiage",
                REGULATORY_CHARGE, pageObjectManager.getOptionalServicesPage().getRegulatoryComplianceChargeText().getText().trim() );
        ValidationUtil.validateTestStep("User validates Regulatory Compliance Charge fee verbiage",
                REGULATORY_CHARGE_FEE, pageObjectManager.getOptionalServicesPage().getRegulatoryComplianceChargeFeeText().getText().trim() );

        ValidationUtil.validateTestStep("User validates Fuel Charge verbiage",
                FUEL_CHARGE, pageObjectManager.getOptionalServicesPage().getFuelChargeText().getText().trim() );
        ValidationUtil.validateTestStep("User validates Fuel Charge fee verbiage",
                FUEL_CHARGE_FEE, pageObjectManager.getOptionalServicesPage().getFuelChargeFeeText().getText().trim() );

        pageObjectManager.getOptionalServicesPage().getFreeSpiritAwardBookingChevronLink().click();
        ValidationUtil.validateTestStep("User validates Agent Transaction verbiage",
                AGENT_TRANSACTION, pageObjectManager.getOptionalServicesPage().getAgentTransactionReservationCenterText().getText().trim());
        ValidationUtil.validateTestStep("User validates Agent Transaction fee verbiage",
                AGENT_TRANSACTION_FEE, pageObjectManager.getOptionalServicesPage().getAgentTransactionFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates Award Redemption verbiage",
                AWARD_REDEMPTION, pageObjectManager.getOptionalServicesPage().getAwardRedemptionText().getText().trim());
        ValidationUtil.validateTestStep("User validates Award Redemption fee verbiage",
                AWARD_REDEMPTION_FEE, pageObjectManager.getOptionalServicesPage().getAwardRedemptionFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates Award Modification verbiage",
                AWARD_MODIFICATION, pageObjectManager.getOptionalServicesPage().getAwardModificationText().getText().trim());
        ValidationUtil.validateTestStep("User validates Award Modification fee verbiage",
                AWARD_MODIFICATION_FEE, pageObjectManager.getOptionalServicesPage().getAwardModificationFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates Mileage Redeposit verbiage",
                MILEAGE_REDEPOSIT, pageObjectManager.getOptionalServicesPage().getMileageRedepositText().getText().trim());
        ValidationUtil.validateTestStep("User validates Mileage Redeposit fee verbiage",
                MILEAGE_REDEPOSIT_FEE, pageObjectManager.getOptionalServicesPage().getMileageRedepositFeeText().getText().trim());

        pageObjectManager.getOptionalServicesPage().getModificationOrCancellationChevronLink().click();
        ValidationUtil.validateTestStep("User validates Web Modification or Cancellation verbiage",
                WEB_MOD_OR_CANCEL, pageObjectManager.getOptionalServicesPage().getWebModificationText().getText().trim());
        ValidationUtil.validateTestStep("User validates Web Modification or Cancellation fee verbiage",
                WEB_MOD_OR_CANCEL_FEE, pageObjectManager.getOptionalServicesPage().getWebModificationFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates Reservation/Airport Modification or Cancellation verbiage",
                RESERVE_MOD_CANCEL, pageObjectManager.getOptionalServicesPage().getReservationModificationCancellationText().getText().trim());
        ValidationUtil.validateTestStep("User validates Reservation/Airport Modification or Cancellation  fee verbiage",
                RESERVE_MOD_CANCEL_FEE, pageObjectManager.getOptionalServicesPage().getReservationsModificationFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates Group Modification or Cancellation  verbiage",
                GROUP_MOD_OR_CANCEL, pageObjectManager.getOptionalServicesPage().getGroupBookingCancellationModificationText().getText().trim());
        ValidationUtil.validateTestStep("User validates Group Modification or Cancellation fee verbiage",
                GROUP_MOD_OR_CANCEL_FEE, pageObjectManager.getOptionalServicesPage().getGroupBookingCancellationModificationFeeText().getText().trim());

        pageObjectManager.getOptionalServicesPage().getExtrasChevronLink().click();
        ValidationUtil.validateTestStep("User validates Flight flex verbiage",
                FLIGHT_FLEX, pageObjectManager.getOptionalServicesPage().getFlightFlexText().getText().trim());
        ValidationUtil.validateTestStep("User validates Flight flex fee verbiage",
                FLIGHT_FLEX_PRICE, pageObjectManager.getOptionalServicesPage().getFlightFlexFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates Shortcut Boarding verbiage",
                SHORTCUT_BOARDING, pageObjectManager.getOptionalServicesPage().getShortcutBoardingText().getText().trim());
        ValidationUtil.validateTestStep("User validates Shortcut Boarding verbiage",
                SHORTCUT_BOARDING_PRICE, pageObjectManager.getOptionalServicesPage().getShortcutBoardingFeeText().getText().trim());

        ValidationUtil.validateTestStep("User validates Shortcut Security verbiage",
                SHORTCUT_SECURITY, pageObjectManager.getOptionalServicesPage().getShortcutSecurityText().getText().trim());
        ValidationUtil.validateTestStep("User validates Shortcut Security verbiage",
                SHORTCUT_SECURITY_PRICE, pageObjectManager.getOptionalServicesPage().getShortcutSecurityFeeText().getText().trim());

        String[] ToolTipVerbiage = new String[]{
                BP_TOOLTIP_TEXT1, BP_TOOLTIP_TEXT2, BP_TOOLTIP_TEXT3, BP_TOOLTIP_TEXT4, BP_TOOLTIP_TEXT5, BP_TOOLTIP_TEXT6, BP_TOOLTIP_TEXT7, BP_TOOLTIP_TEXT8, BP_TOOLTIP_TEXT9,
                SNK_TOOLTIP_TXT, DRINK_TOOLTIP_TXT,
                RES_CENTER_TOOLTIP, GROUP_BOOKING_TOOLTIP, COLOMBIA_CHARGE_INFO, STANDBY_FLIGHT_TOOLTIP, PASSENGER_CHARGE_INFO, REGULATORY_CHARGE_INFO, FUEL_CHARGE_TOOLTIP,
                AGENT_TRANSACTION_INFO, AWARD_REDEMPTION_INFO, AWARD_MODIFICATION_INFO, MILEAGE_REDEPOSIT_INFO,
                WEB_MOD_OR_CANCEL_INFO, RESERVE_MOD_CANCEL_INFO, GROUP_MOD_OR_CANCEL_INFO,
                FLIGHT_FLEX_TOOLTIP, SHORTCUT_BOARDING_INFO
        };
        List<WebElement> TooltipXpath = new ArrayList<>();

        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getBoardingPassAtHomeNotificationText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getBoardingPassAtHomeNotificationText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getBoardingPassAtHomeNotificationText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getUNMRTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getInfantTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getPetTransportTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getTGDomesticTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getTGTripInternationalTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getTGVacationTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getSnacksTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getDrinksTooltipText());

        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getReservationCenterTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getGroupBookingTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getColombiaChargeTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getStandbyTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getPassengerChargeTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getRegulatoryChargeTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getFuelChargeTooltipText());

        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getAgentTransactionTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getAwardRedemptionTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getAwardModificationTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getMileageRedepositTooltipText());

        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getWebModificationCancellationTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getReservationsModificationTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getGroupBookingModificationsTooltipText());

        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getFlightFlexTooltipText());
        TooltipXpath.add(pageObjectManager.getOptionalServicesPage().getShortcutBoardingTooltipText());

        for (int i = 0; i < pageObjectManager.getOptionalServicesPage().getInfoTooltipIcon().size() ; i ++ )
        {
            pageObjectManager.getOptionalServicesPage().getInfoTooltipIcon().get(i).click();
            WaitUtil.untilTimeCompleted(1000);
            ValidationUtil.validateTestStep("Validate the tooltip icon verbiage: " + ToolTipVerbiage[i], ToolTipVerbiage[i], TooltipXpath.get(i).getText().trim() );

            if(TestUtil.verifyElementDisplayed(getDriver().findElement(By.xpath("//a[@class=\"icon-close popover-circle\"]")))) {
                getDriver().findElement(By.xpath("//a[@class=\"icon-close popover-circle\"]")).click();
            }

        }

    }
}