package com.spirit.managers;

import com.spirit.APIMethods.APIMethods;
import com.spirit.mobileMethods.MyProfilePageMethods;
import com.spirit.pageMethods.*;
import org.openqa.selenium.WebDriver;

import com.spirit.baseClass.ScenarioContext;

public class PageMethodManager {
	
	private WebDriver driver;
	private PageObjectManager pageObjectManager;
	private ScenarioContext scenarioContext;
	private HomePageMethods homePageMethods;
	private FlightAvailabilityMethods flightAvailabilityMethods;
	private PassengerInfoMethods passengerInfoMethods;
	private BagsPageMethods bagsPageMethods;
	private SeatsPageMethods seatsPageMethods;
	private OptionsPageMethods optionsPageMethods;
	private PaymentPageMethods paymentPageMethods;
	private ConfirmationPageMethods confirmationPageMethods;
	private PassportPageMethods passportPageMethods;
	private ReservationSummaryPageMethods reservationSummaryPageMethods;
	private CarPageMethods carPageMethods;
	private MemberEnrollmentPageMethods memberEnrollmentPageMethods;
	private OptionalServicesPageMethods optionalServicesPageMethods;
	private CommonPageMethods commonPageMethods;
	private HotelPageMethods hotelPageMethods;
	private APIMethods apiMethods;
	private DeniedBoardingToolMethods deniedBoardingToolMethods;
	
	public PageMethodManager(WebDriver driver,PageObjectManager pageObjectManager,ScenarioContext scenarioContext) {
		this.driver=driver;
		this.pageObjectManager = pageObjectManager;
		this.scenarioContext = scenarioContext;
	}
	
	public HomePageMethods getHomePageMethods() {
		if(homePageMethods == null) {
			return homePageMethods = new HomePageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return homePageMethods;
		}
	}
	
	public FlightAvailabilityMethods getFlightAvailabilityMethods() {
		if(flightAvailabilityMethods == null) {
			return flightAvailabilityMethods = new FlightAvailabilityMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return flightAvailabilityMethods;
		}
	}
	
	public PassengerInfoMethods getPassengerInfoMethods() {
		if(passengerInfoMethods == null) {
			return passengerInfoMethods = new PassengerInfoMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return passengerInfoMethods;
		}
	}
	
	//
	public BagsPageMethods getBagsPageMethods() {
		if(bagsPageMethods == null) {
			return bagsPageMethods = new BagsPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return bagsPageMethods;
		}
	}
	
	public SeatsPageMethods getSeatsPageMethods() {
		if(seatsPageMethods == null) {
			return seatsPageMethods = new SeatsPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return seatsPageMethods;
		}
	}
	
	public OptionsPageMethods getOptionsPageMethods() {
		if(optionsPageMethods == null) {
			return optionsPageMethods = new OptionsPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return optionsPageMethods;
		}
	}
	
	public PaymentPageMethods getPaymentPageMethods() {
		if(paymentPageMethods == null) {
			return paymentPageMethods = new PaymentPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return paymentPageMethods;
		}
	}
	
	public ConfirmationPageMethods getConfirmationPageMethods() {
		if(confirmationPageMethods == null) {
			return confirmationPageMethods = new ConfirmationPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return confirmationPageMethods;
		}
	}

	public ReservationSummaryPageMethods getReservationSummaryPageMethods() {
		if(reservationSummaryPageMethods == null) {
			return reservationSummaryPageMethods = new ReservationSummaryPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return reservationSummaryPageMethods;
		}
	}

	public PassportPageMethods getPassportPageMethods() {
		if(passportPageMethods == null) {
			return passportPageMethods = new PassportPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return passportPageMethods;
		}
	}

	public CarPageMethods getCarPageMethods() {
		if(carPageMethods == null) {
			return carPageMethods = new CarPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return carPageMethods;
		}
	}

	public MemberEnrollmentPageMethods getMemberEnrollmentPageMethods() {
		if(memberEnrollmentPageMethods == null) {
			return memberEnrollmentPageMethods = new MemberEnrollmentPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return memberEnrollmentPageMethods;
		}
	}

	public OptionalServicesPageMethods getOptionalServicesPageMethods() {
		if(optionalServicesPageMethods == null) {
			return optionalServicesPageMethods = new OptionalServicesPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return optionalServicesPageMethods;
		}
	}

	public CommonPageMethods getCommonPageMethods() {
		if(commonPageMethods == null) {
			return commonPageMethods = new CommonPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return commonPageMethods;
		}
	}

	public HotelPageMethods getHotelPageMethods() {
		if(hotelPageMethods == null) {
			return hotelPageMethods = new HotelPageMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return hotelPageMethods;
		}
	}

	public APIMethods getAPIMethods(){
		if(apiMethods == null) {
			return apiMethods = new APIMethods(scenarioContext);
		}else {
			return apiMethods;
		}
	}

	public DeniedBoardingToolMethods getDeniedBoardingToolMethods(){
		if(deniedBoardingToolMethods == null) {
			return deniedBoardingToolMethods = new DeniedBoardingToolMethods(driver, pageObjectManager, scenarioContext);
		}else {
			return deniedBoardingToolMethods;
		}
	}
}
