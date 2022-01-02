package com.spirit.managers;

import com.spirit.pageObjects.*;
import org.openqa.selenium.WebDriver;


public class PageObjectManager {
	
	//declare class variable used for PageObjectManager class
	private WebDriver driver;
	private Header header;
	private Footer footer;
	private Common common;
	private CarPage   carPage;
	private BagsPage bagsPage;
	private SeatsPage seatsPage;
	private HomePage  homePage;
	private HotelPage hotelPage;
	private AccountProfilePage accountProfilePage;
	private FlightAvailabilityPage flightAvailabilityPage;
	private PassengerInfoPage passengerInfoPage;
	private MemberEnrollmentPage memberEnrollmentPage;
	private OptionsPage optionsPage;
	private PaymentPage paymentPage;
	private ConfirmationPage confirmationPage;
	private ReservationSummaryPage reservationSummaryPage;
	private CancelReservationPage cancelReservationPage;
	private ActivityPage activityPage;
	private PassportPage passportPage;
	private BoardingPassPage boardingPassPage;
	private OptionalServicesPage optionalServicesPage;
	private RetrievePasswordPage retrievePasswordPage;
	private TriseptREZAgentPage triseptREZAgentPage;
	private DeniedBoardingToolPage deniedBoardingToolPage;

	//declare constructor to initialize selenium driver
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public Header getHeader() {
		if(header == null) {
			return header = new Header(driver);
		}else {
			return header;
		}
	}
	
	public Footer getFooter() {
		if(footer == null) {
			return footer = new Footer(driver);
		}else {
			return footer;
		}
	}
	
	public Common getCommon() {
		if(common == null) {
			return common = new Common(driver);
		}else {
			return common;
		}
	}
	
	public AccountProfilePage getAccountProfilePage() {
		if(accountProfilePage == null) {
			return accountProfilePage = new AccountProfilePage(driver);
		}else {
			return accountProfilePage;
		}
	}
	
	public MemberEnrollmentPage getMemberEnrollmentPage() {
		if(memberEnrollmentPage == null) {
			return memberEnrollmentPage = new MemberEnrollmentPage(driver);
		}else {
			return memberEnrollmentPage;
		}
	}
	
	public HomePage getHomePage() {
		if(homePage == null) {
			return homePage = new HomePage(driver);
		}else {
			return homePage;
		}
	}
	
	public FlightAvailabilityPage getFlightAvailabilityPage() {
		if(flightAvailabilityPage == null) {
			return flightAvailabilityPage = new FlightAvailabilityPage(driver);
		}else {
			return flightAvailabilityPage;
		}
	}
	
	public PassengerInfoPage getPassengerInfoPage() {
		if(passengerInfoPage == null) {
			return passengerInfoPage = new PassengerInfoPage(driver);
		}else {
			return passengerInfoPage;
		}
	}
	
	public BagsPage getBagsPage() {
		if(bagsPage == null) {
			return bagsPage = new BagsPage(driver);
		}else {
			return bagsPage;
		}
	}
	
	public SeatsPage getSeatsPage() {
		if(seatsPage == null) {
			return seatsPage = new SeatsPage(driver);
		}else {
			return seatsPage;
		}
	}

	public PassportPage getPassportPage() {
		if(passportPage == null) {
			return passportPage = new PassportPage(driver);
		}else {
			return passportPage;
		}
	}
	
	
	public HotelPage getHotelPage() {
		if(hotelPage == null) {
			return hotelPage = new HotelPage(driver);
		}else {
			return hotelPage;
		}
	}
	
	public CarPage getCarPage() {
		if(carPage == null) {
			return carPage = new CarPage(driver);
		}else {
			return carPage;
		}
	}
	
	public ActivityPage getActivityPage() {
		if(activityPage == null) {
			return activityPage = new ActivityPage(driver);
		}else {
			return activityPage;
		}
	}
	
	public OptionsPage getOptionsPage() {
		if(optionsPage == null) {
			return optionsPage = new OptionsPage(driver);
		}else {
			return optionsPage;
		}
	}
	
	public PaymentPage getPaymentPage() {
		if(paymentPage == null) {
			return paymentPage = new PaymentPage(driver);
		}else {
			return paymentPage;
		}
	}
	
	public ConfirmationPage getConfirmationPage() {
		if(confirmationPage == null) {
			return confirmationPage = new ConfirmationPage(driver);
		}else {
			return confirmationPage;
		}
	}
	
	public ReservationSummaryPage getReservationSummaryPage() {
		if(reservationSummaryPage == null) {
			return reservationSummaryPage = new ReservationSummaryPage(driver);
		}else {
			return reservationSummaryPage;
		}
	}
	
	public CancelReservationPage getCancelReservationPage() {
		if(cancelReservationPage == null) {
			return cancelReservationPage = new CancelReservationPage(driver);
		}else {
			return cancelReservationPage;
		}
	}

	public BoardingPassPage getBoardingPassPage() {
		if(boardingPassPage == null) {
			return boardingPassPage = new BoardingPassPage(driver);
		}else {
			return boardingPassPage;
		}
	}

	public OptionalServicesPage getOptionalServicesPage() {
		if(optionalServicesPage == null) {
			return optionalServicesPage = new OptionalServicesPage(driver);
		}else {
			return optionalServicesPage;
		}
	}

	public RetrievePasswordPage getRetrievePasswordPage() {
		if(retrievePasswordPage == null) {
			return retrievePasswordPage = new RetrievePasswordPage(driver);
		}else {
			return retrievePasswordPage;
		}
	}

	public TriseptREZAgentPage getTriseptREZAgentPage() {
		if(triseptREZAgentPage == null) {
			return triseptREZAgentPage = new TriseptREZAgentPage(driver);
		}else {
			return triseptREZAgentPage;
		}
	}


	public DeniedBoardingToolPage getDeniedBoardingToolPage() {
		if(deniedBoardingToolPage == null) {
			return deniedBoardingToolPage = new DeniedBoardingToolPage(driver);
		}else {
			return deniedBoardingToolPage;
		}
	}


}
