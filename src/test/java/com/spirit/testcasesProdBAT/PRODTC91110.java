package com.spirit.testcasesProdBAT;

import com.spirit.baseClass.*;
import com.spirit.utility.*;
import org.openqa.selenium.*;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.util.*;

//**********************************************************************************************
//Test Case ID: TC91110
//Description: Breadcrumb_CP_BP_NEG_Progress_Bar_Flight
//Created By : Anthony C
//Created On : 14-Mar-2019
//Reviewed By: Salim Ansari
//Reviewed On: 15-Mar-2019
//**********************************************************************************************
public class PRODTC91110 extends BaseClass {

	protected String[] breadCrumbText = { "Flight" , "Passenger", "Bags" ,  "Seats" , "Options" , "Payment" , "Confirmation" };
	/**
	 * @author Anhony Cardona
	 * Purpose: Test Spanish Happy Path
	 * @param platform
	 */
	@Parameters({"platform"})
	@Test(groups="Production")

	public void Breadcrumb_CP_BP_NEG_Progress_Bar_Flight (@Optional("NA") String platform) {
		/******************************************************************************
		 ***********************Navigate to Flight Availability Page********************
		 ******************************************************************************/
		//Mention Suite and Browser in reports
		if(!platform.equals("NA")) {
			ValidationUtil.validateTestStep("Starting Test Case ID PRODTC91110 under PRODUCTION Suite on " + platform + " Browser"   , true);
		}
		//Home Page Constant Values
		final String LANGUAGE 			= "English";
		final String JOURNEY_TYPE 		= "Flight";
		final String TRIP_TYPE 			= "OneWay";
		final String DEP_AIRPORTS 		= "AllLocation";
		final String DEP_AIRPORT_CODE 	= "FLL";
		final String ARR_AIRPORTS 		= "AllLocation";
		final String ARR_AIRPORT_CODE 	= "LGA";
		final String DEP_DATE 			= "25";

		//Flight Availability Constant Variables
		final String DEP_FLIGHT 		= "NonStop";

		//Flight Availability Constant Values
		final String MEMBER_FARE_TYPE 	= "Standard";
		final String upgrade_VALUE		= "BookIt";

		//open browser
		openBrowser( platform);

		//Home Page Methods
		pageMethodManager.getHomePageMethods().launchSpiritApp();
		pageMethodManager.getHomePageMethods().selectApplicationLanguage(LANGUAGE);
		pageMethodManager.getHomePageMethods().selectJourneyType(JOURNEY_TYPE);
		pageMethodManager.getHomePageMethods().selectTripType(TRIP_TYPE);
		pageMethodManager.getHomePageMethods().selectAirports(DEP_AIRPORTS, DEP_AIRPORT_CODE, ARR_AIRPORTS, ARR_AIRPORT_CODE);
		pageMethodManager.getHomePageMethods().selectDates(DEP_DATE, "NA");

		pageMethodManager.getHomePageMethods().clickSearchButton();

		WaitUtil.untilPageLoadComplete(getDriver());

		//validate breadcrumbs for the FA page
		validateBreadCrumbs(1);

		//Flight Availability Methods
		pageMethodManager.getFlightAvailabilityMethods().selectFlightNatureType("Dep", DEP_FLIGHT);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyFare(MEMBER_FARE_TYPE);
		pageMethodManager.getFlightAvailabilityMethods().selectJourneyUpgrade(upgrade_VALUE);
		WaitUtil.untilPageLoadComplete(getDriver());

		//validate Flight Availability BreadCrumbs Does Not Work
		validateFlightAvailabilityBreadCrumbsDoesNotWork();

		//validate the breadcrumbs on Passenger Information page
		validateBreadCrumbs(2);

		//Input Passenger Information Page
		pageMethodManager.getPassengerInfoMethods().fillPassengersMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().fillContactMandatoryFields();
		pageMethodManager.getPassengerInfoMethods().clickContinueButton();
		WaitUtil.untilPageLoadComplete(getDriver());

		//reach Bags page
		validateFlightAvailabilityBreadCrumbsDoesNotWork();
		validateBreadCrumbs(3);
		userClicksOnTheBreadcrumb("Passenger");
		proceedToPage("Seat");

		//reach Seats page
		validateFlightAvailabilityBreadCrumbsDoesNotWork();
		validateBreadCrumbs(4);
		userClicksOnTheBreadcrumb("Passenger");
		proceedToPage("Seat");
		userClicksOnTheBreadcrumb("Bags");
		proceedToPage("Options");

		//reach the options page
		validateFlightAvailabilityBreadCrumbsDoesNotWork();
		validateBreadCrumbs(5);
		userClicksOnTheBreadcrumb("Passenger");
		proceedToPage("Options");
		userClicksOnTheBreadcrumb("Bags");
		proceedToPage("Options");
		userClicksOnTheBreadcrumb("Seat");
		proceedToPage("Payment");

		//reach the payment page
		validateFlightAvailabilityBreadCrumbsDoesNotWork();
		validateBreadCrumbs(6);
		userClicksOnTheBreadcrumb("Passenger");
		proceedToPage("Payment");
		userClicksOnTheBreadcrumb("Bags");
		proceedToPage("Payment");
		userClicksOnTheBreadcrumb("Seat");
		proceedToPage("Payment");
		userClicksOnTheBreadcrumb("Options");
//		proceedToPage("confirmation");

		//reach the confirmation page
//		WaitUtil.untilPageLoadComplete(getDriver());
//		pageMethodManager.getConfirmationPageMethods().closeROKTPopup();
	}


	/**
	 * Validate that the Flight availability breadcrumb does not redirect you to Flight availability page
	 */
	private void validateFlightAvailabilityBreadCrumbsDoesNotWork () {
		try {
			breadCrumbList().get(0).click(); //click on the FA breadcrumb
		}
		catch (Exception e) {}

		String currentPageHeader = getDriver().findElement(By.xpath("//h1")).getText();
		//is the current page FA Page
		boolean isFApageHeader = currentPageHeader.contains("Choose Your Flight");
		//User should not be redirected to the FA page
		ValidationUtil.validateTestStep("User not redirected to FA page", !isFApageHeader);
	}

	/**
	 * Validates whether all breadcrumbs are correct (complete, active, invalid)
	 * @param currentPageBeingValidated number assigned to current page being validated
	 * 1: Flight, 2: Passenger, 3:Bags, 4:Seat, 5:Options, 6: Payment, 7: Confirmation
	 */
	private void validateBreadCrumbs (int currentPageBeingValidated) {
		currentPageBeingValidated -= 1;
		//Displayed breadcrumbs
		List<WebElement> breadCrumbsList = breadCrumbList();
		List<WebElement> StatusOfBreadCrumbdiv = getDriver().findElements(By.xpath("//div[contains(@class,'col col-md-4 col-lg-auto d-md-flex ng-tns-c24-')]//div[@class='align-self-md-center']//div"));

		//current page = breadcrumbNum
		for (int i = 0 ; i < breadCrumbsList.size() ; i++) {
			//get text for current page title on Bread Crumb
			String title = breadCrumbsList.get(i).getText();

			//page being validated should have active class
			if (i == currentPageBeingValidated) {
				//get class for current Bread Crumb should be 'active'
				boolean isEnabled = StatusOfBreadCrumbdiv.get(i).getAttribute("class").contains("active");
				ValidationUtil.validateTestStep(breadCrumbText[i] + " is active", isEnabled &&  title.contains(breadCrumbText[i]) &&  title.contains(breadCrumbText[i]) );
			}

			//Breadcrumbs less than currentpage, class should be disabled and number of completed breadcrumbs should be the same as currentPageBeingValidated-1
			else if (i < currentPageBeingValidated ) {
				//get class for FA page(i == 1), should be disabled
				boolean isDisabled = StatusOfBreadCrumbdiv.get(i).getAttribute("class").contains("disabled");
				ValidationUtil.validateTestStep(breadCrumbText[i] + " page is marked as complete", isDisabled && numberOfCompletedBreadCrumbs() == currentPageBeingValidated);
			}

			//Breadcrumbs greater than i (current page) should have in-active class
			else {
				//get class for current Bread Crumb should be 'inactive'
				boolean isInactive = StatusOfBreadCrumbdiv.get(i).getAttribute("class").contains("inactive");

				int pageNum = i+1;
				ValidationUtil.validateTestStep("The " + breadCrumbText[i] + " page  (number "+ pageNum +"), is white/inactive: pass" , isInactive &&  title.contains(breadCrumbText[i]));
			}
		}
	}

	// Completes current page until user gets to the parameter goToPage
	// @param goToPage page the user wants to go to

	private void proceedToPage (String goToPage) {
		String currentPageHeader = getDriver().findElement(By.xpath("//h1")).getText().toLowerCase();

		//while current page does not goToPage, complete current page and continue
		while (!currentPageHeader.toLowerCase().contains(goToPage.toLowerCase())) {
			if (currentPageHeader.contains("passenger")) {
				pageMethodManager.getPassengerInfoMethods().clickContinueButton();
				WaitUtil.untilPageLoadComplete(getDriver());
			}

			else if (currentPageHeader.contains("bags")) {
				pageMethodManager.getBagsPageMethods().continueWithoutSelectingBags();
				WaitUtil.untilPageLoadComplete(getDriver());
			}

			else if (currentPageHeader.contains("seat")) {
				try {Thread.sleep(2000);}
				catch (InterruptedException e) {}
				pageMethodManager.getSeatsPageMethods().continueWithoutSeats();
				WaitUtil.untilPageLoadComplete(getDriver());
			}

			else if (currentPageHeader.contains("options")) {
				try {Thread.sleep(2000);}
				catch (InterruptedException e) {}
				WaitUtil.untilPageLoadComplete(getDriver());

				pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown().click();
				getDriver().findElement(By.xpath("//select[@id='checkInSelect']//option[2]")).click();
//				Select drpdnCheckInOptions = new Select(pageObjectManager.getOptionsPage().getCheckInOptionCardBodySelectDropDown());
//				drpdnCheckInOptions.selectByValue("CheckInOption:MobileFree");

				pageMethodManager.getOptionsPageMethods().continueToPurchaseButton();
				WaitUtil.untilPageLoadComplete(getDriver());
			}

			else if (currentPageHeader.contains("payment")) {
				WaitUtil.untilPageLoadComplete(getDriver());
				pageMethodManager.getPaymentPageMethods().fillCardPaymentDetails("VisaCard");
				pageMethodManager.getPaymentPageMethods().selectTravelGuard("NotRequired");
				pageMethodManager.getPaymentPageMethods().acceptAndCompleteBooking();
				WaitUtil.untilPageLoadComplete(getDriver());
				break;
			}

			WaitUtil.untilPageLoadComplete(getDriver());
			try {Thread.sleep(2000);}
			catch (InterruptedException e) {}

			currentPageHeader = getDriver().findElement(By.xpath("//h1")).getText().toLowerCase();
		}
	}

	/**
	 * Clicks on paramater breadcrumb and validates user is redirected to the correct page
	 * @param breadCrumb to be clicked
	 */
	private void userClicksOnTheBreadcrumb (String breadCrumb) {
		List<WebElement> breadCrumbList = breadCrumbList();

		for (int i = 0 ; i < breadCrumbText.length ; i ++) {
			if (breadCrumbText[i].toLowerCase().contains(breadCrumb.toLowerCase())) {
				breadCrumbList.get(i).click();
				WaitUtil.untilPageLoadComplete(getDriver());
			}
		}
		WaitUtil.untilPageLoadComplete(getDriver());

		//get page header
		String currentPageHeader = getDriver().findElement(By.xpath("//h1")).getText();
		//If page header contains paramter
		boolean isCorrctPage = currentPageHeader.contains(breadCrumb);
		//User should not be redirected to the FA page
		ValidationUtil.validateTestStep("User not redirected to FA page", isCorrctPage);
	}

	/**
	 *
	 * @return List of Visible breadcrumb elements
	 */
	private List<WebElement> breadCrumbList() {
		List<WebElement> tempBreadCrumbs = getDriver().findElements(By.xpath("(//div[contains(@class,'mobile-background-container')])[2]//div[contains(@class, 'breadcrumbs')] //span"));

		List<WebElement> tempbreadCrumbsList = Arrays.asList(new WebElement[8]); //temporary displayed breadcrumbs of size 8
		List<WebElement> breadCrumbsList = Arrays.asList(new WebElement[7]); //displayed breadcrumbs of size 7


		int iterator = 0;

		if(tempBreadCrumbs.size() == 7 ) {
			for (WebElement breadcrumb : tempBreadCrumbs) {
				if (breadcrumb.isDisplayed()) {
					try {
						breadCrumbsList.set(iterator, breadcrumb); //store/set webelements that are displayed in list
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					iterator++;
				}
			}
		}

		else if(tempBreadCrumbs.size() == 8) {
			for (WebElement breadcrumb : tempBreadCrumbs) {
				if (breadcrumb.isDisplayed() ) {
					try {
						tempbreadCrumbsList.set(iterator, breadcrumb); //store/set webelements that are displayed in list
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
					iterator ++;
				}
			}
			//reset iterator to 0
			iterator = 0;
			for (int i = 1 ; i < tempbreadCrumbsList.size() ; i ++) {
				breadCrumbsList.set(iterator , tempbreadCrumbsList.get(i));
				iterator++;
			}
		}

		return breadCrumbsList;
	}

	private int numberOfCompletedBreadCrumbs() {
		List<WebElement> tempCompletedBreadCrumbs = getDriver().findElements(By.xpath("//div[contains(@class,'col col-md-4 col-lg-auto d-md-flex ng-tns-c24-')]//div[@class='align-self-md-center confirmed-step']"));
		int completedBreadCrumbs = 0;
		for (WebElement breadcrumb : tempCompletedBreadCrumbs) {
			if (breadcrumb.isDisplayed()) {
				completedBreadCrumbs ++;
			}
		}
		return completedBreadCrumbs;
	}

}
