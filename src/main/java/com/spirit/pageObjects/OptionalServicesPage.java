package com.spirit.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OptionalServicesPage {
    /*
 * Prefix with WebElement      PostFix with Method
 		ifr     				-  IFrame
      	btn      				-  Button
       	chkbx    				-  CheckBox
       	chklst   				-  CheckBoxList
       	drpdwn   				-  DropDown
       	grdvew   				-  GridView
       	hyrlnk   				-  Hyperlink
       	img      				-  Image
       	imgbtn   				-  ImageButton
       	lbl      				-  Label
       	lnkbtn   				-  LinkButton
       	lnk     				-  Link
       	lstbx    				-  ListBox
       	lit      				-  Literal
       	pnl      				-  Panel
       	ph      				-  PlaceHolder
       	rdbtn    				-  RadioButton
       	rdbtnlst 				-  RadioButtonList
       	txtbx    				-  Textbox
       	txt      				-  Text
 */

    public OptionalServicesPage(WebDriver driver) {
        //this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //************************************************************
    //*****************Bag O Tron Header**************************
    //************************************************************
    public final String xpath_BagOTronNewTripLabel = "//span[contains(text(),'A New Trip') or contains(text(),'Un viaje nuevo')]";
    @FindBy(xpath=xpath_BagOTronNewTripLabel)
    private WebElement lbl_BagOTronNewTrip;

    public final String xpath_BagOTronExistingTripLabel = "//span[contains(text(),'An Existing Trip') or contains(text(),'Un viaje existente')]";
    @FindBy(xpath=xpath_BagOTronExistingTripLabel)
    private WebElement lbl_BagOTronExistingTrip;


    //************************************************************
    //**************************New Trip**************************
    //************************************************************
    public final String xpath_NewTripDepartingAirportDropDown = "//select[@id='originStationCode']";
    @FindBy(xpath=xpath_NewTripDepartingAirportDropDown)
    private WebElement drpdn_NewTripDepartingAirport;

    public final String xpath_NewTripReturningAirportDropDown = "//select[@id='destinationStationCode']";
    @FindBy(xpath=xpath_NewTripReturningAirportDropDown)
    private WebElement drpdn_NewTripReturningAirport;

    public final String xpath_NewTripDepartingDateTextBox = "//input[@id='departureDate']";
    @FindBy(xpath=xpath_NewTripDepartingDateTextBox)
    private WebElement txtbx_NewTripDepartingDate;

    public final String xpath_NewTripReturningDateTextBox = "//input[@name='returnDate']";
    @FindBy(xpath=xpath_NewTripReturningDateTextBox)
    private WebElement txtbx_NewTripReturningDate;

    public final String xpath_NewTripAdultCountDropDown = "//select[@id='adultCount']";
    @FindBy(xpath=xpath_NewTripAdultCountDropDown)
    private WebElement drpdn_NewTripAdultCount;

    public final String xpath_NewTripChildCountDropDown = "//select[@id='childCount']";
    @FindBy(xpath=xpath_NewTripChildCountDropDown)
    private WebElement drpdn_NewTripChildCount;

    public final String xpath_NewTripPromoCodeTextBox = "//input[@name='promoCode']";
    @FindBy(xpath=xpath_NewTripPromoCodeTextBox)
    private WebElement txtbx_NewTripPromoCode;

    public final String xpath_NewTripPurchaseFlightMilesCheckBox = "//label[@for='usePoints']";
    @FindBy(xpath=xpath_NewTripPurchaseFlightMilesCheckBox)
    private WebElement chkbx_NewTripPurchaseFlightMiles;

    //************************************************************
    //*****************Existing Trip******************************
    //************************************************************
    public final String xpath_ExistingTripLastNameTextBox = "//input[@id='lastName']";
    @FindBy(xpath=xpath_ExistingTripLastNameTextBox)
    private WebElement txtbx_ExistingTripLastName;

    public final String xpath_ExistingTripConfirmationCodeTextBox = "//input[@id='confirmationCode']";
    @FindBy(xpath=xpath_ExistingTripConfirmationCodeTextBox)
    private WebElement txtbx_ExistingTripConfirmationCode;

    public final String xpath_ExistingTripConfirmationCodeLink = "//a[contains(text(),'How to find your Confirmation Code.') or contains(text(),'Cómo encontrar su Clave de Confirmación.')]";
    @FindBy(xpath=xpath_ExistingTripConfirmationCodeLink)
    private WebElement lnk_HowToFindConfirmationCode;


    //************************************************************
    //*****************Bag O Tron Footer**************************
    //************************************************************
    public final String xpath_BagOTronDisplayBagPriceButton = "//button[contains(text(),'display bag prices') or contains(text(),'Mostrar el precio del equipaje')]";
    @FindBy(xpath=xpath_BagOTronDisplayBagPriceButton)
    private WebElement btn_BagOTronDisplayBagPrice;

    public final String xpath_BagOTronUpdateBagPriceButton = "//button[contains(text(),'update bag prices') or contains(text(),'Actualizar los precios del equipaje')]";
    @FindBy(xpath=xpath_BagOTronUpdateBagPriceButton)
    private WebElement btn_BagOTronUpdateBagPrice;

    public final String xpath_BagOTronStartOverButton = "//button[contains(text(),'start over') or contains(text(),'comenzar de nuevo')]";
    @FindBy(xpath=xpath_BagOTronStartOverButton)
    private WebElement btn_BagOTronStartOver;

    public final String xpath_BagOTronBookTripButton = "//button[contains(text(),'Book Now') or contains(text(),'Reservar ahora')]";
    @FindBy(xpath=xpath_BagOTronBookTripButton)
    private WebElement btn_BagOTronBookTrip;

    //************************************************************
    //*****************Bag O Tron Bag Price***********************
    //************************************************************
    public final String xpath_BagPriceDifferentPriceLink = "//a[contains(text(),'Prices Different') or contains(text(),'Precios Diferentes?')]";
    @FindBy(xpath=xpath_BagPriceDifferentPriceLink)
    private WebElement lnk_BagPriceDifferentPrice;

    public final String xpath_BagPriceDifferentPriceVerbiageText = "//a[contains(text(),'Prices Different') or contains(text(),'Precios Diferentes?')]/../../following-sibling::div/div/p";
    @FindBy(xpath=xpath_BagPriceDifferentPriceVerbiageText)
    private WebElement txt_BagPriceDifferentPriceVerbiage;


    //************************************************************
    //**************Overweight or Oversized Baggage***************
    //************************************************************
    public final String xpath_OverweightOrOversizedLink = "//strong[contains(text(),'Overweight or Oversized Baggage') or contains(text(),' Equipaje de peso o tamaño excesivo')]/../following-sibling::div/a";
    @FindBy(xpath=xpath_OverweightOrOversizedLink)
    private WebElement lnk_OverweightOrOversized;

    public final String xpath_OverweightOrOversizedText = "//strong[contains(text(),'Overweight or Oversized Baggage') or contains(text(),'Equipaje de peso o tamaño excesivo')]";
    @FindBy(xpath=xpath_OverweightOrOversizedText)
    private WebElement txt_OverweightOrOversized;

    public final String xpath_41SizeText = "//p[contains(text(),'41 – 50 lbs. (18 – 23 kg)') or contains(text(),'41 – 50 lbs. (18 – 23 kg)')]";
    @FindBy(xpath=xpath_41SizeText)
    private WebElement txt_41Size;

    public final String xpath_infoTooltipIcon = "//i[@class='icon-info-circle']";
    @FindBy(xpath=xpath_infoTooltipIcon)
    private List<WebElement> icn_infoTooltipIcon;

    public final String xpath_NotificationIcon = "//i[@class='icon-info-circle']";
    @FindBy(xpath= xpath_NotificationIcon)
    private WebElement icn_Notificationicon;

    public final String xpath_41NotificationText = "//div[contains(text(),'Remember, any checked bag over 40 pounds is considered overweight.') or contains(text(),'Recuerde, cualquier equipaje registrado de')]";
    @FindBy(xpath= xpath_41NotificationText)
    private WebElement txt_41Notification;

    public final String xpath_41Notification2Text = "//div[contains(text(),'Save more by packing light! ') or contains(text(),'Equipaje de peso o tamaño excesivo')]";
    @FindBy(xpath=xpath_41Notification2Text)
    private WebElement txt_41Notification2;

    public final String xpath_41NotificationMoreInfoText = "//a[contains(text(),'More Info') or contains(text(),'Más info')]";
    @FindBy(xpath=xpath_41NotificationMoreInfoText)
    private WebElement txt_41NotificationMoreInfo;

    public final String xpath_PerBagText = "//em[contains(text(),'Per Bag') or contains(text(),'Por equipaje')]";
    @FindBy(xpath=xpath_PerBagText)
    private WebElement txt_PerBag;

    public final String xpath_30DollarText = "//em[contains(text(),'+ $30') or contains(text(),'+ $30')]";
    @FindBy(xpath=xpath_30DollarText)
    private WebElement txt_30Dollar;

    public final String xpath_51SizeText = "//strong[contains(text(),'51 – 70 lbs. (23 - 32 kg)') or contains(text(),'51 – 70 lbs. (23 - 32 kg)')]";
    @FindBy(xpath=xpath_51SizeText)
    private WebElement txt_51Size;

    public final String xpath_51NotificationText = "//div[contains(text(),'Remember, any checked bag over 50 pounds') or contains(text(),'Recuerde, cualquier equipaje registrado de')]";
    @FindBy(xpath= xpath_51NotificationText)
    private WebElement txt_51Notification;

    public final String xpath_51Notification2Text = "//div[contains(text(),'Save more by packing light! ') or contains(text(),'Equipaje de peso o tamaño excesivo')]";
    @FindBy(xpath=xpath_51Notification2Text)
    private WebElement txt_51Notification2;

    public final String xpath_51NotificationMoreInfoText = "//a[contains(text(),'More Info') or contains(text(),'Más info')]";
    @FindBy(xpath=xpath_51NotificationMoreInfoText)
    private WebElement txt_51NotificationMoreInfo;

    public final String xpath_55DollarText = "//em[contains(text(),'+ $55') or contains(text(),'+ $55')]";
    @FindBy(xpath=xpath_55DollarText)
    private WebElement txt_55Dollar;

    public final String xpath_71SizeText = "//strong[contains(text(),'71 – 100 lbs. (32 - 45 kg)') or contains(text(),'71 – 100 lbs. (32 - 45 kg)')]";
    @FindBy(xpath=xpath_71SizeText)
    private WebElement txt_71Size;

    public final String xpath_71NotificationText = "//div[contains(text(),'Remember, any checked bag over 70 pounds') or contains(text(),'Recuerde, cualquier equipaje registrado de')]";
    @FindBy(xpath= xpath_71NotificationText)
    private WebElement txt_71Notification;

    public final String xpath_71Notification2Text = "//div[contains(text(),'Save more by packing light! ') or contains(text(),'Equipaje de peso o tamaño excesivo')]";
    @FindBy(xpath=xpath_71Notification2Text)
    private WebElement txt_71Notification2;

    public final String xpath_71NotificationMoreInfoText = "//a[contains(text(),'More Info') or contains(text(),'Más info')]";
    @FindBy(xpath=xpath_71NotificationMoreInfoText)
    private WebElement txt_71NotificationMoreInfo;

    public final String xpath_100Dollar71LbsText = "//em[contains(text(),'+ $100') or contains(text(),'+ $100')]";
    @FindBy(xpath=xpath_100Dollar71LbsText)
    private WebElement txt_100Dollar71Lbs;

    public final String xpath_63SizeText = "//p[contains(text(),'63-80 linear inches (158-203 cm)') or contains(text(),'63-80 pulgadas lineales (158-203 cm)')]";
    @FindBy(xpath=xpath_63SizeText)
    private WebElement txt_63Size;

    public final String xpath_63NotificationText = "//div[contains(text(),'Remember, bags over 62 linear inches (length + width + height)') or contains(text(),'Recuerde, cualquier equipaje registrado de')]";
    @FindBy(xpath= xpath_63NotificationText)
    private WebElement txt_63Notification;

    public final String xpath_63Notification2Text = "//div[contains(text(),'Save more by packing light! ') or contains(text(),'Equipaje de peso o tamaño excesivo')]";
    @FindBy(xpath=xpath_63Notification2Text)
    private WebElement txt_63Notification2;

    public final String xpath_63NotificationMoreInfoText = "//a[contains(text(),'More Info') or contains(text(),'Más info')]";
    @FindBy(xpath=xpath_63NotificationMoreInfoText)
    private WebElement txt_63NotificationMoreInfo;

    public final String xpath_100DollarLinearInchesText = "//em[contains(text(),'+ $100') or contains(text(),'+ $100')]";
    @FindBy(xpath=xpath_100DollarLinearInchesText)
    private WebElement txt_100DollarLinearInches;

    public final String xpath_SpecialItemsText = "//strong[contains(text(),'Special items over 80 linear inches (203 cm)') or contains(text(),'Artículos especiales de más de 80 pulgadas lineales (203 cm)')]";
    @FindBy(xpath=xpath_SpecialItemsText)
    private WebElement txt_SpecialItems;

    public final String xpath_SpecialItemsNotificationText = "//div[contains(text(),'No bags over 80 linear inches (length + width + height)') or contains(text(),'No se aceptará ningún equipaje de más de 80 pulgadas')]";
    @FindBy(xpath= xpath_SpecialItemsNotificationText)
    private WebElement txt_SpecialItemsNotification;

    public final String xpath_SpecialItemsNotificationMoreInfoText = "//a[contains(text(),'More Info') or contains(text(),'Más info')]";
    @FindBy(xpath=xpath_SpecialItemsNotificationMoreInfoText)
    private WebElement txt_SpecialItemsNotificationMoreInfo;

    public final String xpath_150SpecialItemText = "//em[contains(text(),'+ $150') or contains(text(),'+ $150')]";
    @FindBy(xpath=xpath_150SpecialItemText)
    private WebElement txt_150SpecialItem;

    //************************************************************
    //*********************Sporting Equipment*********************
    //************************************************************
    public final String xpath_SportingEquipmentLink = "//strong[contains(text(),'Sporting Equipment') or contains(text(),'Equipo deportivo')]/../following-sibling::div/a";
    @FindBy(xpath=xpath_SportingEquipmentLink)
    private WebElement lnk_SportingEquipment;

    public final String xpath_SportingEquipmentText = "//strong[contains(text(),' Sporting Equipment') or contains(text(),' Equipo deportivo')]";
    @FindBy(xpath=xpath_SportingEquipmentText)
    private WebElement txt_SportingEquipment;

    public final String xpath_BicycleText = "//p[contains(text(),'Bicycle') or contains(text(),'Bicicleta')]";
    @FindBy(xpath=xpath_BicycleText)
    private WebElement txt_Bicycle;

    public final String xpath_NotificationiconBicycleIcon = "//i[@class='icon-info-circle']";
    @FindBy(xpath= xpath_NotificationiconBicycleIcon)
    private WebElement icn_NotificationiconBicycle;

    public final String xpath_BringYourBicycleText = "//i[contains(text(),'Bring your bicycle with you on your next trip.') or contains(text(),'Llévese su bicicleta en su próximo viaje.')]";
    @FindBy(xpath=xpath_BringYourBicycleText)
    private WebElement txt_BringYourBicycle;

    public final String xpath_75DollarText = "//div[contains(text(),'+ $75') or contains(text(),'+ $75')]";
    @FindBy(xpath=xpath_75DollarText)
    private WebElement txt_75Dollar;

    public final String xpath_SurfBoardText = "//p[contains(text(),'Surf Board (Maximum of 2 surfboards in one bag) ') or contains(text(),'Tabla de surf (máximo de 2 tablas de surf en un bolso)')]";
    @FindBy(xpath=xpath_SurfBoardText)
    private WebElement txt_SurfBoard;

    public final String xpath_BringYourSurfBoardText = "//div[contains(text(),'Bring your surf board with you on your next trip.') or contains(text(),'Llévese su tabla de surf en su próximo viaje.')]";
    @FindBy(xpath=xpath_BringYourSurfBoardText)
    private WebElement txt_BringYourSurfBoard;

    public final String xpath_100DollarText = "//div[contains(text(),'+ $100') or contains(text(),'+ $100')]";
    @FindBy(xpath=xpath_100DollarText)
    private WebElement txt_100Dollar;

    public final String xpath_GolfClubsText = "//p[contains(text(),'Golf Clubs') or contains(text(),'Palos de golf')]";
    @FindBy(xpath=xpath_GolfClubsText)
    private WebElement txt_GolfClubs;

    public final String xpath_BringYourClubsText = "//div[contains(text(),'Bring your clubs with you on your next golf outing.') or contains(text(),'Llévese sus palos en su próximo viaje.')]";
    @FindBy(xpath=xpath_BringYourClubsText)
    private WebElement txt_BringYourClubs;

    public final String xpath_GolfClubsBagPriceText = "//div[contains(text(),'Same as checked bags, overweight charges may apply') or contains(text(),'Igual que el equipaje registrado, pueden aplicar cargos por peso excesivo')]";
    @FindBy(xpath=xpath_GolfClubsBagPriceText)
    private WebElement txt_GolfClubsBagPrice;

    public final String xpath_SkisText = "//p[contains(text(),'Skis/Snowboards') or contains(text(),'Esquís/tablas de snowboard')]";
    @FindBy(xpath=xpath_SkisText)
    private WebElement txt_Skis;

    public final String xpath_BringYourSkisText = "//div[contains(text(),'Bring your skis or snowboard with you on your next ski trip.') or contains(text(),'Llévese sus esquís o sus tablas de snowboard en su próximo viaje.')]";
    @FindBy(xpath=xpath_BringYourSkisText)
    private WebElement txt_BringYourSkis;

    public final String xpath_SkisBagPriceText = "//p[contains(text(),'Same as checked bags, overweight charges may apply') or contains(text(),'Igual que el equipaje registrado, pueden aplicar cargos por peso excesivo')]";
    @FindBy(xpath=xpath_SkisBagPriceText)
    private WebElement txt_SkisBagPrice;

    public final String xpath_SportingEquipmentFullListText = "//a[contains(text(),'Full list of sporting equipment') or contains(text(),'Lista completa de equipo deportivo')]";
    @FindBy(xpath=xpath_SportingEquipmentFullListText)
    private WebElement txt_SportingEquipmentFullList;

    //************************************************************
    //*********************OPTIONAL SERVICES-SEATS****************
    //************************************************************
    public final String xpath_SeatLabel = "//strong[contains(text(),'Seats') or contains(text(),'ASIENTOS')]";
    @FindBy(xpath=xpath_SeatLabel)
    private WebElement lbl_Seat;

    public final String xpath_EveryGuestText = "//span[contains(text(),'Every Guest has the opportunity to select') or contains(text(),'Cada cliente tiene la oportunidad de seleccionar')]";
    @FindBy(xpath=xpath_EveryGuestText)
    private WebElement txt_EveryGuest;

    public final String xpath_SpiritAssignedText = "//p[contains(text(),'Spirit Assigned Seating at Check In') or contains(text(),'Spirit le asigna asientos al registrarse')]";
    @FindBy(xpath=xpath_SpiritAssignedText)
    private WebElement txt_SpiritAssigned;

    public final String xpath_SpiritAssignedNotificationIcon = "//i[@class='icon-info-circle']";
    @FindBy(xpath=xpath_SpiritAssignedNotificationIcon)
    private List<WebElement> icn_SpiritAssignedNotification;

    public final String xpath_WeWillAssignText = "//div[contains(text(),'We’ll assign random seat assignments at check-in.') or contains(text(),'Le asignaremos asientos al azar al registrarse.')]";
    @FindBy(xpath=xpath_WeWillAssignText)
    private WebElement txt_WeWillAssign;

    public final String xpath_PerSeatText = "//em[contains(text(),'Per Seat') or contains(text(),'Por asiento')]";
    @FindBy(xpath=xpath_PerSeatText)
    private WebElement txt_PerSeat;

    public final String xpath_FreeText = "//div[contains(text(),'Free') or contains(text(),'Gratis')]";
    @FindBy(xpath=xpath_FreeText)
    private WebElement txt_Free;

    public final String xpath_CustomerRequestedText = "//p[contains(text(),'Customer-Requested Seat Assignments / Regular Seats') or contains(text(),'Asientos asignados solicitados por el cliente / asientos regulares')]";
    @FindBy(xpath=xpath_CustomerRequestedText)
    private WebElement txt_CustomerRequested;

    public final String xpath_SeatAssignmentsText = "//div[contains(text(),'These are seat assignments requested') or contains(text(),'Estos son asientos asignados a solicitud del cliente')]";
    @FindBy(xpath=xpath_SeatAssignmentsText)
    private WebElement txt_SeatAssignments;

    public final String xpath_1To50Text = "//div[contains(text(),'$1 to $50') or contains(text(),'$1 to $50')]";
    @FindBy(xpath=xpath_1To50Text)
    private WebElement txt_1To50;

    public final String xpath_BigFrontSeatText = "//p[contains(text(),'Big Front Seats (in advance)') or contains(text(),'Big Front Seats (por adelantado)')]";
    @FindBy(xpath=xpath_BigFrontSeatText)
    private WebElement txt_BigFrontSeat;

    public final String xpath_WidestSeatsText = "//div[contains(text(),'These are our widest seats,') or contains(text(),'Estos son nuestros asientos mas amplios,')]";
    @FindBy(xpath=xpath_WidestSeatsText)
    private WebElement txt_WidestSeats;

    public final String xpath_12To150Text = "//div[contains(text(),'$12 to $150') or contains(text(),'$12 to $150')]";
    @FindBy(xpath=xpath_12To150Text)
    private WebElement txt_12To150;

    public final String xpath_BigFrontSeatOnBoardUpgradesText = "//p[contains(text(),'Big Front Seats (onboard upgrades—depending on flight length)') or contains(text(),'Big Front Seats (ascenso de categoría a bordo - dependiendo de la duración del vuelo)')]";
    @FindBy(xpath=xpath_BigFrontSeatOnBoardUpgradesText)
    private WebElement txt_BigFrontSeatOnBoardUpgrades;

    public final String xpath_BFSOnBoardUpgradesNotificationText = "//div[contains(text(),'These are our widest seats, which') or contains(text(),'Estos son nuestros asientos mas amplios')]";
    @FindBy(xpath=xpath_BFSOnBoardUpgradesNotificationText)
    private WebElement txt_BFSOnBoardUpgradesNotification;

    public final String xpath_25To175Text = "//div[contains(text(),'$25 to $175') or contains(text(),'$25 to $175')]";
    @FindBy(xpath=xpath_25To175Text)
    private WebElement txt_25To175;

    //************************************************************
    //*********************OPTIONAL SERVICES-MEMBERSHIPS**********
    //************************************************************
    public final String xpath_MembershipsText = "//strong[contains(text(),'Memberships') or contains(text(),'MEMBRESIAS')]";
    @FindBy(xpath=xpath_MembershipsText)
    private WebElement txt_Memberships;

    public final String xpath_ThereNoBetterText = "//p[contains(text(),'no better way to save than') or contains(text(),'No hay mejor manera de ahorrar que')]";
    @FindBy(xpath=xpath_ThereNoBetterText)
    private WebElement txt_ThereNoBetter;

    public final String xpath_FreeSpiritMembershipText = "//p[contains(text(),'FREE SPIRIT Membership ') or contains(text(),'Membresía de Free Spirit®')]";
    @FindBy(xpath=xpath_FreeSpiritMembershipText)
    private WebElement txt_FreeSpiritMembership;

    public final String xpath_FSMembershipNotificationText = "//i[@class='icon-info-circle']";
    @FindBy(xpath=xpath_FSMembershipNotificationText)
    private WebElement txt_FSMembershipNotification;

    public final String xpath_OurFrequentFlyerText = "//div[contains(text(),'Our frequent flyer program helps you get') or contains(text(),'Nuestro programa de viajero frequente')]";
    @FindBy(xpath=xpath_OurFrequentFlyerText)
    private WebElement txt_OurFrequentFlyer;

    public final String xpath_PerMemberText = "//em[contains(text(),'Per Member') or contains(text(),'Por miembro')]";
    @FindBy(xpath=xpath_PerMemberText)
    private WebElement txt_PerMember;

    public final String xpath_FreeMembershipsText = "//div[contains(text(),'Free') or contains(text(),'Gratis')]";
    @FindBy(xpath=xpath_FreeMembershipsText)
    private WebElement txt_FreeMemberships;

    public final String xpath_FairClubTrialMembershipText = "//p[contains(text(),'$9 Fare Club Trial Membership (60 days trial)') or contains(text(),'Membresía de prueba del $9 Fare Club (60 días de prueba)')]";
    @FindBy(xpath=xpath_FairClubTrialMembershipText)
    private WebElement txt_FairClubTrialMembership;

    public final String xpath_UnlikeAnnualMembershipText = "//div[contains(text(),'Unlike an annual membership, trials') or contains(text(),'A diferencia de una membresía anual, las')]";
    @FindBy(xpath=xpath_UnlikeAnnualMembershipText)
    private WebElement txt_UnlikeAnnualMembership;

    public final String xpath_19DollarText = "//div[contains(text(),'$19.95') or contains(text(),'$19.95')]";
    @FindBy(xpath=xpath_19DollarText)
    private WebElement txt_19Dollar;

    public final String xpath_FareClubAnnualMembershipText = "//p[contains(text(),'$9 Fare Club Annual Membership') or contains(text(),'Membresía anual del $9 Fare Club')]";
    @FindBy(xpath=xpath_FareClubAnnualMembershipText)
    private WebElement txt_FareClubAnnualMembership;

    public final String xpath_FareClubGiveExclusiveText = "//div[contains(text(),'Our $9 Fare Club gives you exclusive') or contains(text(),'Nuestro $9 Fare Club le da acceso exclusivo')]";
    @FindBy(xpath=xpath_FareClubGiveExclusiveText)
    private WebElement txt_FareClubGiveExclusive;

    public final String xpath_59DollarText = "//div[contains(text(),'$59.95') or contains(text(),'$59.95')]";
    @FindBy(xpath=xpath_59DollarText)
    private WebElement txt_59Dollar;

    public final String xpath_FairClubAnnualRenewalText = "//p[contains(text(),'$9 Fare Club Annual Renewal') or contains(text(),'Renovación anual del $9 Fare Club')]";
    @FindBy(xpath=xpath_FairClubAnnualRenewalText)
    private WebElement txt_FairClubAnnualRenewal;

    public final String xpath_ForyourConvenienceText = "//div[contains(text(),'For your convenience,') or contains(text(),'Para su conveniencia, renovaremos su')]";
    @FindBy(xpath=xpath_ForyourConvenienceText)
    private WebElement txt_ForyourConvenience;

    public final String xpath_69DollarText = "//div[contains(text(),'$69.95') or contains(text(),'$69.95')]";
    @FindBy(xpath=xpath_69DollarText)
    private WebElement txt_69Dollar;

    //************************************************************
    //*********************OPTIONAL SERVICES-OTHER****************
    //************************************************************
    public final String xpath_OtherLabel = "//strong[contains(text(),'Other') or contains(text(),'OTROS')]";
    @FindBy(xpath=xpath_OtherLabel)
    private WebElement lbl_Other;

    public final String xpath_OtherHeaderText = "//p[contains(text(),'We have plenty of additional extras and services ') or contains(text(),'Tenemos muchos extras y servicios adicionales')]";
    @FindBy(xpath=xpath_OtherHeaderText)
    private WebElement txt_OtherHeader;

    public final String xpath_BoardingPassPrintedAtHomeText = "//div[contains(text(),'Boarding pass printed at home') or contains(text(),'Pase de embarque impreso en el hogar')]";
    @FindBy(xpath=xpath_BoardingPassPrintedAtHomeText)
    private WebElement txt_BoardingPassPrintedAtHome;

    public final String xpath_BoardingPassAtHomeNotificationText = "//i[@class='icon-info-circle']";
    @FindBy(xpath=xpath_BoardingPassAtHomeNotificationText)
    private WebElement txt_BoardingPassAtHomeNotification;

    public final String xpath_PBPAtHomeText = "//div[contains(text(),'Print your boarding pass at home to save.') or contains(text(),'Para ahorrar, imprima su pase de embarque en su casa. ')]";
    @FindBy(xpath=xpath_PBPAtHomeText)
    private WebElement txt_PBPAtHome;

    public final String xpath_BPPrintedAtHomePriceText = "//div[contains(text(),'Free') or contains(text(),'Gratis')]";
    @FindBy(xpath=xpath_BPPrintedAtHomePriceText)
    private WebElement txt_BPPrintedAtHomePrice;

    public final String xpath_BoardingPassByAirportKioskText = "//div[contains(text(),'Boarding pass printed by Airport Kiosk') or contains(text(),'Pase de embarque impreso en el quiosco del aeropuerto')]";
    @FindBy(xpath=xpath_BoardingPassByAirportKioskText)
    private WebElement txt_BoardingPassByAirportKiosk;

    public final String xpath_PBPAtHomeToSaveText = "//div[contains(text(),'Print your boarding pass at home to save.') or contains(text(),'Para ahorrar, imprima su pase de embarque en su casa. ')]";
    @FindBy(xpath=xpath_PBPAtHomeToSaveText)
    private WebElement txt_PBPAtHomeToSave;

    public final String xpath_BPPrintedAtKioskPriceText = "//div[contains(text(),'$2 per boarding pass printed') or contains(text(),'$2 por cada pase de embarque impreso')]";
    @FindBy(xpath=xpath_BPPrintedAtKioskPriceText)
    private WebElement txt_BPPrintedAtKioskPrice;

    public final String xpath_BoardingPassAtAirportAgentText = "//div[contains(text(),'Boarding pass printed by Airport Agent') or contains(text(),'Pase de embarque impreso con el agente del aeropuerto')]";
    @FindBy(xpath=xpath_BoardingPassAtAirportAgentText)
    private WebElement txt_BoardingPassAtAirportAgent;

    public final String xpath_PBPAtAirportAgentHommeToSaveText = "//div[contains(text(),'Print your boarding pass at home to save.') or contains(text(),'Para ahorrar, imprima su pase de embarque en su casa. ')]";
    @FindBy(xpath=xpath_PBPAtAirportAgentHommeToSaveText)
    private WebElement txt_PBPAtAirportAgentHommeToSave;

    public final String xpath_BPPrintedByAgentPriceText = "//div[contains(text(),'$10 per boarding pass printed') or contains(text(),'$10 por cada pase de embarque impreso')]";
    @FindBy(xpath=xpath_BPPrintedByAgentPriceText)
    private WebElement txt_BPPrintedByAgentPrice;

    public final String xpath_UnaccompaniedMinorsText = "//div[contains(text(),'Unaccompanied Minors (Price includes snack and beverage)') or contains(text(),'Menores sin acompañante (el precio incluye un snack y una bebida)')]";
    @FindBy(xpath=xpath_UnaccompaniedMinorsText)
    private WebElement txt_UnaccompaniedMinors;

    public final String xpath_UNMRTooltipText = "//div[contains(text(),'We allow certain ages of children') or contains(text(),'Permitimos viajar a niños de ciertas edades como menores sin acompañante.')]";
    @FindBy(xpath=xpath_UNMRTooltipText)
    private WebElement txt_UNMRTooltip;

    public final String xpath_UMNRPriceText = "//div[contains(text(),'$100 per customer, each way') or contains(text(),'$100 por cliente, cada trayecto')]";
    @FindBy(xpath=xpath_UMNRPriceText)
    private WebElement txt_UMNRPrice;

    public final String xpath_InfantText = "//div[contains(text(),'Infant (Lap Child)') or contains(text(),'Infante (niño de regazo)')]";
    @FindBy(xpath=xpath_InfantText)
    private WebElement txt_Infant;

    public final String xpath_InfantTooltipText = "//div[contains(text(),'Some of our littlest flyers are') or contains(text(),'Algunos de nuestros pasajeros más pequeños')]";
    @FindBy(xpath=xpath_InfantTooltipText)
    private WebElement txt_InfantTooltip;

    public final String xpath_InfantFeeText = "//div[contains(text(),'Free (taxes may apply for certain countries)') or contains(text(),'Gratis (podrían aplicar impuestos para ciertos países)')]";
    @FindBy(xpath=xpath_InfantFeeText)
    private WebElement txt_InfantFee;

    public final String xpath_PetTransportationText = "//div[contains(text(),'Pet Transportation (Limit 4 pets total in cabin)') or contains(text(),'Transportación de mascotas (límite de 4 mascotas en la cabina)')]";
    @FindBy(xpath=xpath_PetTransportationText)
    private WebElement txt_PetTransportation;

    public final String xpath_PetTransportTooltipText = "//div[contains(text(),'We love all pets, but unfortunately') or contains(text(),'Adoramos a todas nuestras mascotas')]";
    @FindBy(xpath=xpath_PetTransportTooltipText)
    private WebElement txt_PetTransportTooltip;

    public final String xpath_PetContainerPriceText = "//div[contains(text(),'$110 per pet container, each way') or contains(text(),'$110 por contenedor de mascota, cada trayecto')]";
    @FindBy(xpath=xpath_PetContainerPriceText)
    private WebElement txt_PetContainerPrice;

    public final String xpath_TGInsuranceDomesticText = "//div[contains(text(),'Travel Guard Insurance / Domestic Itinerary') or contains(text(),'Seguro Travel Guard / Itinerario doméstico ')]";
    @FindBy(xpath=xpath_TGInsuranceDomesticText)
    private WebElement txt_TGInsuranceDomestic;

    public final String xpath_TGDomesticTooltipText = "//div[contains(text(),'Protect your trip from the unexpected with travel insurance from ') or contains(text(),'Proteja su viaje de lo inesperado con el seguro de viaje de ')]";
    @FindBy(xpath=xpath_TGDomesticTooltipText)
    private WebElement txt_TGDomesticTooltip;

    public final String xpath_TGIDomesticTravelGuardLink = "//a[contains(text(),'Travel Guard') or contains(text(),'Travel Guard')]";
    @FindBy(xpath=xpath_TGIDomesticTravelGuardLink)
    private WebElement lnk_TGIDomesticTravelGuard;

    public final String xpath_TGIDOmesticFeeText = "//div[contains(text(),'From $14') or contains(text(),'Desde $14')]";
    @FindBy(xpath=xpath_TGIDOmesticFeeText)
    private WebElement Txt_TGIDOmesticFee;

    public final String xpath_TGInsuranceInternationalText = "//div[contains(text(),'Travel Guard Insurance / International Itinerary') or contains(text(),'Seguro Travel Guard / Itinerario internacional ')]";
    @FindBy(xpath=xpath_TGInsuranceInternationalText)
    private WebElement txt_TGInsuranceInternational;

    public final String xpath_TGTripInternationalTooltipText = "//div[contains(text(),'Protect your trip from the unexpected with travel insurance from ') or contains(text(),'Proteja su viaje de lo inesperado con el seguro de viaje de ')]";
    @FindBy(xpath=xpath_TGTripInternationalTooltipText)
    private WebElement txt_TGTripInternationalTooltip;

    public final String xpath_TGIInternationalTravelGuardLink = "//a[contains(text(),'Travel Guard') or contains(text(),'Travel Guard')]";
    @FindBy(xpath=xpath_TGIInternationalTravelGuardLink)
    private WebElement lnk_TGIInternationalTravelGuard;

    public final String xpath_TGIInternationalFeeText = "//div[contains(text(),'From $25') or contains(text(),'Desde $25')]";
    @FindBy(xpath=xpath_TGIInternationalFeeText)
    private WebElement Txt_TGIInternationalFee;

    public final String xpath_TGInsuranceVacationText = "//div[contains(text(),'Travel Guard Insurance / Vacation Package Itinerary') or contains(text(),'Seguro Travel Guard / Itinerario de paquete de vacaciones')]";
    @FindBy(xpath=xpath_TGInsuranceVacationText)
    private WebElement txt_TGInsuranceVacation;

    public final String xpath_TGVacationTooltip = "//div[contains(text(),'Protect your trip from the unexpected with travel insurance from ') or contains(text(),'Proteja su viaje de lo inesperado con el seguro de viaje de ')]";
    @FindBy(xpath=xpath_TGVacationTooltip)
    private WebElement txt_TGVacationTooltip;

    public final String xpath_TGIVacationTravelGuardLink = "//a[contains(text(),'Travel Guard') or contains(text(),'Travel Guard')]";
    @FindBy(xpath=xpath_TGIVacationTravelGuardLink)
    private WebElement lnk_TGIVacationTravelGuard;

    public final String xpath_TGIVacationFeeText = "//div[contains(text(),'From $28') or contains(text(),'Desde $28')]";
    @FindBy(xpath=xpath_TGIVacationFeeText)
    private WebElement Txt_TGIVacationFee;

    //************************************************************
    //***********OPTIONAL SERVICES-OTHER-ONBOARD SNACKS***********
    //************************************************************
    public final String xpath_OnboardSnacksAndDrinksChevronLink = "//strong[contains(text(),'Onboard Snacks and Drinks') or contains(text(),'Snacks y Bebidas A Bordo')]/../following-sibling::div/a";
    @FindBy(xpath = xpath_OnboardSnacksAndDrinksChevronLink)
    private WebElement lnk_OnboardSnacksAndDrinksChevron;

    public final String xpath_OnboardSnacksAndDrinksLabel = "//strong[contains(text(), 'Onboard Snacks and Drinks') or contains(text(), 'Snacks y Bebidas A Bordo')]";
    @FindBy(xpath = xpath_OnboardSnacksAndDrinksLabel)
    private WebElement lbl_OnboardSnacksAndDrinks;

    public final String xpath_SnacksText = "//p[contains(text(),'Snacks') or contains(text(),'Snacks')]";
    @FindBy(xpath=xpath_SnacksText)
    private WebElement txt_Snacks;

    public final String xpath_OtherOnBoardSnacksText = "//i[@class='icon-info-circle']";
    @FindBy(xpath=xpath_OtherOnBoardSnacksText)
    private WebElement txt_OtherOnBoardSnacks;

    public final String xpath_SnacksTooltipText = "//div[contains(text(),'We offer a large variety of snacks') or contains(text(),'Ofrecemos una gran variedad de snacks')]";
    @FindBy(xpath=xpath_SnacksTooltipText)
    private WebElement txt_SnacksTooltip;

    public final String xpath_SnacksPriceText = "//div[contains(text(),'$1 to $10') or contains(text(),'$1 a $10')]";
    @FindBy(xpath=xpath_SnacksPriceText)
    private WebElement txt_SnacksPrice;

    public final String xpath_DrinksText = "//p[contains(text(),'Drinks') or contains(text(),'Bebidas')]";
    @FindBy(xpath=xpath_DrinksText)
    private WebElement txt_Drinks;

    public final String xpath_DrinksTooltipText = "//div[contains(text(),'We offer a large variety of drinks') or contains(text(),'Para ahorrar, imprima su pase de embarque en su casa. ')]";
    @FindBy(xpath=xpath_DrinksTooltipText)
    private WebElement txt_DrinksTooltip;

    public final String xpath_DrinksPriceText = "//div[contains(text(),'$1 to $15') or contains(text(),'$1 a $15')]";
    @FindBy(xpath=xpath_DrinksPriceText)
    private WebElement txt_DrinksPrice;

    //************************************************************
    //***********OPTIONAL SERVICES-OTHER-BOOKING RELATED**********
    //************************************************************
    public final String xpath_BookingRelatedChevronLink = "//strong[contains(text(),'Booking Related') or contains(text(),'Relacionado a Reservaciones')]/../following-sibling::div/a";
    @FindBy(xpath = xpath_BookingRelatedChevronLink)
    private WebElement lnk_BookingRelatedChevron;

    public final String xpath_BookingRelatedLabel = "//strong[contains(text(),'Booking Related') or contains(text(),'Relacionado a Reservaciones')]";
    @FindBy(xpath=xpath_BookingRelatedLabel)
    private WebElement lbl_BookingRelated;

    public final String xpath_OtherBookingRelatedIcon = "//i[@class='icon-info-circle']";
    @FindBy(xpath=xpath_OtherBookingRelatedIcon)
    private WebElement icn_OtherBookingRelated;

    public final String xpath_ReservationCenterText = "//p[contains(text(),'Reservation Center Booking (including packages)') or contains(text(),'Reservación a través del Centro de Reservaciones (incluyendo los paquetes)')]";
    @FindBy(xpath=xpath_ReservationCenterText)
    private WebElement txt_ReservationCenter;

    public final String xpath_ReservationCenterTooltipText = "//div[contains(text(),'This applies to bookings made via our') or contains(text(),'Esto aplica a reservaciones hechas a través de nuestro Centro de Reservaciones')]";
    @FindBy(xpath=xpath_ReservationCenterTooltipText)
    private WebElement txt_ReservationCenterTooltip;

    public final String xpath_ReservationCenterBookingFeeText = "//div[contains(text(),'$35 per booking') or contains(text(),'$35 por reservación')]";
    @FindBy(xpath=xpath_ReservationCenterBookingFeeText)
    private WebElement txt_ReservationCenterBookingFee;

    public final String xpath_GroupBookingText = "//p[contains(text(),'Group Booking') or contains(text(),'Reservación de grupo')]";
    @FindBy(xpath=xpath_GroupBookingText)
    private WebElement txt_GroupBooking;

    public final String xpath_GroupBookingTooltipText = "//div[contains(text(),'This applies to group bookings, which consist of 10') or contains(text(),'Esto aplica a reservaciones de grupo que consistan en 10 pasajeros')]";
    @FindBy(xpath=xpath_GroupBookingTooltipText)
    private WebElement txt_GroupBookingTooltip;

    public final String xpath_GroupBookingFeeText = "//div[contains(text(),'$5 per booking') or contains(text(),'$5 por reservación')]";
    @FindBy(xpath=xpath_GroupBookingFeeText)
    private WebElement txt_GroupBookingFee;

    public final String xpath_ColombiaAdministrativeChargeText = "//p[contains(text(),'Colombia Administrative Charge') or contains(text(),'Cargo administrativo de Colombia')]";
    @FindBy(xpath=xpath_ColombiaAdministrativeChargeText)
    private WebElement txt_ColombiaAdministrativeCharge;

    public final String xpath_ColombiaChargeTooltipText = "//div[contains(text(),'This applies to bookings that are sold in or depart from Colombia') or contains(text(),'Esto aplica a reservaciones que se vendan en o salgan de Colombia')]";
    @FindBy(xpath=xpath_ColombiaChargeTooltipText)
    private WebElement txt_ColombiaChargeTooltip;

    public final String xpath_ColombiaAdministrativeFeeText = "//div[contains(text(),'$15 to $95 per booking') or contains(text(),'$15 a $95 por reservación')]";
    @FindBy(xpath=xpath_ColombiaAdministrativeFeeText)
    private WebElement txt_ColombiaAdministrativeFee;

    public final String xpath_StandbyForEarlierFlightText = "//p[contains(text(),'Standby for Earlier Flight') or contains(text(),'Lista de espera para un vuelo más temprano')]";
    @FindBy(xpath=xpath_StandbyForEarlierFlightText)
    private WebElement txt_StandbyForEarlierFlight;

    public final String xpath_StandbyTooltipText = "//div[contains(text(),'This applies to customers who arrive to the airport') or contains(text(),'Esto aplica a clientes que lleguen al aeropuerto')]";
    @FindBy(xpath=xpath_StandbyTooltipText)
    private WebElement txt_StandbyTooltip;

    public final String xpath_StandbyFlightFeeText = "//div[contains(text(),'$99 each way') or contains(text(),'$99 cada trayecto')]";
    @FindBy(xpath=xpath_StandbyFlightFeeText)
    private WebElement txt_StandbyFlightFee;

    public final String xpath_PassengerUsageChargeText = "//p[contains(text(),'Passenger Usage Charge') or contains(text(),'Cargo por uso del pasajero')]";
    @FindBy(xpath=xpath_PassengerUsageChargeText)
    private WebElement txt_PassengerUsageCharge;

    public final String xpath_PassengerChargeTooltipText = "//div[contains(text(),'This applies to bookings created online or via reservations centers.') or contains(text(),'Esto aplica a reservaciones creadas en línea')]";
    @FindBy(xpath=xpath_PassengerChargeTooltipText)
    private WebElement txt_PassengerChargeTooltip;

    public final String xpath_PassengerUsageFeeText = "//div[contains(text(),'$11.99 to $22.99 per segment') or contains(text(),'$11.99 a $22.99 cada trayecto')]";
    @FindBy(xpath=xpath_PassengerUsageFeeText)
    private WebElement txt_PassengerUsageFee;

    public final String xpath_RegulatoryComplianceCharge = "//p[contains(text(),'Regulatory Compliance Charge') or contains(text(),'Cargo por cumplimiento con las regulaciones')]";
    @FindBy(xpath=xpath_RegulatoryComplianceCharge)
    private WebElement RegulatoryComplianceCharge;

    public final String xpath_RegulatoryChargeTooltipText = "//div[contains(text(),'This charge is to recover costs incurred due') or contains(text(),'Este cargo es para recuperar gastos causados por las regulaciones del')]";
    @FindBy(xpath=xpath_RegulatoryChargeTooltipText)
    private WebElement txt_RegulatoryChargeTooltip;

    public final String xpath_RegulatoryComplianceChargeFeeText = "//div[contains(text(),'$7 per segment') or contains(text(),'$7 cada trayecto')]";
    @FindBy(xpath=xpath_RegulatoryComplianceChargeFeeText)
    private WebElement txt_RegulatoryComplianceChargeFee;

    public final String xpath_FuelChargeText = "//p[contains(text(),'Fuel Charge') or contains(text(),'Cargo por combustible')]";
    @FindBy(xpath=xpath_FuelChargeText)
    private WebElement txt_FuelCharge;

    public final String xpath_FuelChargeTooltipText = "//div[contains(text(),'This surcharge will not apply to certain discount fares') or contains(text(),'Esto aplica a reservaciones creadas en línea o a través de centros de reservaciones. Este recargo no aplicará para ciertas tarifas con descuentos.')]";
    @FindBy(xpath=xpath_FuelChargeTooltipText)
    private WebElement txt_FuelChargeTooltip;

    public final String xpath_FuelChargeFeeText = "//div[contains(text(),'$12 per segment') or contains(text(),'$12 cada trayecto')]";
    @FindBy(xpath=xpath_FuelChargeFeeText)
    private WebElement txt_FuelChargeFee;

    //************************************************************
    //*****OOPTIONAL SERVICES-OTHER-Free Spirit Award Booking*****
    //************************************************************
    public final String xpath_FreeSpiritAwardBookingChevronLink = "//strong[contains(text(),'Award Booking') or contains(text(),'Reservación de Premio')]/../following-sibling::div/a";
    @FindBy(xpath = xpath_FreeSpiritAwardBookingChevronLink)
    private WebElement lnk_FreeSpiritAwardBookingChevron;

    public final String xpath_FREESPIRITAwardBookingLabel = "//strong[contains(text(),'FREE SPIRIT® Award Booking') or contains(text(),'Reservación de Premio Free Spirit®')]";
    @FindBy(xpath=xpath_FREESPIRITAwardBookingLabel)
    private WebElement lbl_FREESPIRITAwardBooking;

    public final String xpath_AgentTransactionReservationCenterText = "//p[contains(text(),'Agent Transaction - Reservation Center') or contains(text(),'Transacción de Agente - Centro de Reservaciones')]";
    @FindBy(xpath=xpath_AgentTransactionReservationCenterText)
    private WebElement txt_AgentTransactionReservationCenter;

    public final String xpath_OtherFSAwardBookingIcon = "//i[@class='icon-info-circle']";
    @FindBy(xpath=xpath_OtherFSAwardBookingIcon)
    private WebElement icn_OtherFSAwardBooking;

    public final String xpath_AgentTransactionTooltipText = "//div[contains(text(),'This applies to reservation center transactions for award bookings') or contains(text(),'Esto aplica a reservaciones hechas a través de nuestro Centro de Reservaciones')]";
    @FindBy(xpath=xpath_AgentTransactionTooltipText)
    private WebElement txt_AgentTransactionTooltip;

    public final String xpath_PerCustomerPerBookingText = "//em[contains(text(),'Per Customer, Per Booking') or contains(text(),'Por cliente, por reservación')]";
    @FindBy(xpath=xpath_PerCustomerPerBookingText)
    private WebElement txt_PerCustomerPerBooking;

    public final String xpath_AgentTransactionFeeText = "//p[contains(text(),'Agent Transaction')]//following::div[contains(text(),'$35')][1]";
    @FindBy(xpath=xpath_AgentTransactionFeeText)
    private WebElement txt_AgentTransactionFee;

    public final String xpath_AwardRedemptionText = "//p[contains(text(),'Award Redemption') or contains(text(),'Canjear premio')]";
    @FindBy(xpath=xpath_AwardRedemptionText)
    private WebElement txt_AwardRedemption;

    public final String xpath_AwardRedemptionTooltipText = "//div[contains(text(),'This applies to all award bookings. Save ') or contains(text(),'Esto aplica en todas las reservaciones de premio. Ahorre')]";
    @FindBy(xpath=xpath_AwardRedemptionTooltipText)
    private WebElement txt_AwardRedemptionTooltip;

    public final String xpath_AwardRedemptionMoreInfoLink = "//p[contains(text(),'More Info') or contains(text(),'Más info')]";
    @FindBy(xpath=xpath_AwardRedemptionMoreInfoLink)
    private WebElement lnk_AwardRedemptionMoreInfo;

    public final String xpath_AwardRedemptionFeeText = "//div[contains(text(),'Free to $75') or contains(text(),'Gratis hasta $75')]";
    @FindBy(xpath=xpath_AwardRedemptionFeeText)
    private WebElement txt_AwardRedemptionFee;

    public final String xpath_AwardModificationText = "//p[contains(text(),'Award Modification') or contains(text(),'Modificación de premio')]";
    @FindBy(xpath=xpath_AwardModificationText)
    private WebElement txt_AwardModification;

    public final String xpath_AwardModificationTooltipText = "//div[contains(text(),'This applies to all award booking modifications. ') or contains(text(),'Esto aplica a todas las modificaciones de reservaciones de premio. ')]";
    @FindBy(xpath=xpath_AwardModificationTooltipText)
    private WebElement txt_AwardModificationTooltip;

    public final String xpath_AwardModificationMoreInfoLink = "//p[contains(text(),'More Info') or contains(text(),'Más info')]";
    @FindBy(xpath=xpath_AwardModificationMoreInfoLink)
    private WebElement lnk_AwardModificationMoreInfo;

    public final String xpath_AwardModificationFeeText = "//p[contains(text(),'Award Redemption') or contains(text(),'Canjear premio')]//following::div[contains(text(),'$110')][1]";
    @FindBy(xpath=xpath_AwardModificationFeeText)
    private WebElement txt_AwardModificationFee;

    public final String xpath_MileageRedepositText = "//p[contains(text(),'Mileage Redeposit') or contains(text(),'Acreditación de millas')]";
    @FindBy(xpath=xpath_MileageRedepositText)
    private WebElement txt_MileageRedeposit;

    public final String xpath_MileageRedepositTooltipText = "//div[contains(text(),'This applies to all award booking cancellations') or contains(text(),'Esto aplica a todas las cancelaciones de reservaciones')]";
    @FindBy(xpath=xpath_MileageRedepositTooltipText)
    private WebElement txt_MileageRedepositTooltip;

    public final String xpath_MileageRedepositFeeText = "//p[contains(text(),'Mileage Redeposit') or contains(text(),'Acreditación de millas')]//following::div[contains(text(),'$110')][1]";
    @FindBy(xpath=xpath_MileageRedepositFeeText)
    private WebElement txt_MileageRedepositFee;

    //************************************************************
    //***OPTIONAL SERVICES-OTHER- Modification Or Cancellation****
    //************************************************************
    public final String xpath_ModificationOrCancellationChevronLink = "//strong[contains(text(),'Modification Or Cancellation') or contains(text(),'Modificación o Cancelación')]/../following-sibling::div/a";
    @FindBy(xpath = xpath_ModificationOrCancellationChevronLink)
    private WebElement lnk_ModificationOrCancellationChevron;

    public final String xpath_ModificationOrCancellationLabel = "//strong[contains(text(),'Modification Or Cancellation') or contains(text(),'Reservación de Premio Free Spirit®')]";
    @FindBy(xpath=xpath_ModificationOrCancellationLabel)
    private WebElement lbl_ModificationOrCancellation;

    public final String xpath_WebModificationText = "//p[contains(text(),'Web Modification or Cancellation') or contains(text(),'Transacción de Agente - Centro de Reservaciones')]";
    @FindBy(xpath=xpath_WebModificationText)
    private WebElement txt_WebModification;

    public final String xpath_OtherModificationIcon = "//i[@class='icon-info-circle']";
    @FindBy(xpath=xpath_OtherModificationIcon)
    private WebElement icn_OtherModification;

    public final String xpath_WebModificationCancellationTooltipText = "//div[contains(text(),'This applies to web modifications or cancellations') or contains(text(),'Esto aplica a reservaciones hechas a través de nuestro Centro de Reservaciones')]";
    @FindBy(xpath=xpath_WebModificationCancellationTooltipText)
    private WebElement txt_WebModificationCancellationTooltip;

    public final String xpath_ModificationPerCustomerPerBookingText = "//em[contains(text(),'Per Customer, Per Booking') or contains(text(),'Por cliente, por reservación')]";
    @FindBy(xpath=xpath_ModificationPerCustomerPerBookingText)
    private WebElement txt_ModificationPerCustomerPerBooking;

    public final String xpath_WebModificationFeeText = "//div[contains(text(),'$90') or contains(text(),'$90')]";
    @FindBy(xpath=xpath_WebModificationFeeText)
    private WebElement txt_WebModificationFee;

    public final String xpath_ReservationModificationCancellationText = "//p[contains(text(),'Reservations / Airport Modification or Cancellation') or contains(text(),'Canjear premio')]";
    @FindBy(xpath=xpath_ReservationModificationCancellationText)
    private WebElement txt_ReservationModificationCancellation;

    public final String xpath_ReservationsModificationTooltipText = "//div[contains(text(),'This applies to reservations and airport modifications or cancellations') or contains(text(),'Esto aplica en todas las reservaciones de premio. Ahorre')]";
    @FindBy(xpath=xpath_ReservationsModificationTooltipText)
    private WebElement txt_ReservationsModificationTooltip;

    public final String xpath_ReservationModificationCancellationMoreInfoLink = "//p[contains(text(),'More Info') or contains(text(),'Más info')]";
    @FindBy(xpath=xpath_ReservationModificationCancellationMoreInfoLink)
    private WebElement lnk_ReservationModificationCancellationMoreInfo;

    public final String xpath_ReservationsModificationFeeText = "//p[contains(text(),'Reservations / Airport Modification or Cancellation') or contains(text(),'Canjear premio')]//following::div[contains(text(),'$100')]";
    @FindBy(xpath=xpath_ReservationsModificationFeeText)
    private WebElement txt_ReservationsModificationFee;

    public final String xpath_GroupBookingCancellationModificationText = "//p[contains(text(),'Group Booking Itinerary Modification or Cancellation') or contains(text(),'Canjear premio')]";
    @FindBy(xpath=xpath_GroupBookingCancellationModificationText)
    private WebElement txt_GroupBookingCancellationModification;

    public final String xpath_GroupBookingModificationsTooltipText = "//div[contains(text(),'This applies to modifications or cancellations to a group booking itinerary. ') or contains(text(),'Esto aplica en todas las reservaciones de premio. Ahorre')]";
    @FindBy(xpath=xpath_GroupBookingModificationsTooltipText)
    private WebElement txt_GroupBookingModificationsTooltip;

    public final String xpath_GroupBookingCancellationModificationMoreInfoLink = "//p[contains(text(),'More Info') or contains(text(),'Más info')]";
    @FindBy(xpath=xpath_GroupBookingCancellationModificationMoreInfoLink)
    private WebElement lnk_GroupBookingCancellationModificationMoreInfo;

    public final String xpath_GroupBookingCancellationModificationFeeText = "//div[contains(text(),'$50')]";
    @FindBy(xpath=xpath_GroupBookingCancellationModificationFeeText)
    private WebElement txt_GroupBookingCancellationModificationFee;

    //************************************************************
    //***************OPTIONAL SERVICES-OTHER-EXTRAS***************
    //************************************************************
    public final String xpath_ExtrasChevronLink = "//strong[contains(text(),'Extras')]/../following-sibling::div/a";
    @FindBy(xpath = xpath_ExtrasChevronLink)
    private WebElement lnk_ExtrasChevron;

    public final String xpath_ExtrasLabel = "//strong[contains(text(),'Extras') or contains(text(),'Reservación de Premio Free Spirit®')]";
    @FindBy(xpath=xpath_ExtrasLabel)
    private WebElement lbl_Extras;

    public final String xpath_FlightFlexText = "//p[contains(text(),'Flight Flex') or contains(text(),'Transacción de Agente - Centro de Reservaciones')]";
    @FindBy(xpath=xpath_FlightFlexText)
    private WebElement txt_FlightFlex;

    public final String xpath_OtherExtrasIcon = "//i[@class='icon-info-circle']";
    @FindBy(xpath=xpath_OtherExtrasIcon)
    private WebElement icn_OtherExtras;

    public final String xpath_FlightFlexTooltipText = "//div[contains(text(),'Modify your itinerary once for free, online') or contains(text(),'Esto aplica a reservaciones hechas a través de nuestro Centro de Reservaciones')]";
    @FindBy(xpath=xpath_FlightFlexTooltipText)
    private WebElement txt_FlightFlexTooltip;

    public final String xpath_PerCustomerText = "//em[contains(text(),'Per Customer') or contains(text(),'Por cliente, por reservación')]";
    @FindBy(xpath=xpath_PerCustomerText)
    private WebElement txt_PerCustomer;

    public final String xpath_FlightFlexFeeText = "//div[contains(text(),'$35 to $45')]";
    @FindBy(xpath=xpath_FlightFlexFeeText)
    private WebElement txt_FlightFlexFee;

    public final String xpath_ShortcutBoardingText = "//p[contains(text(),'Shortcut Boarding') or contains(text(),'Transacción de Agente - Centro de Reservaciones')]";
    @FindBy(xpath=xpath_ShortcutBoardingText)
    private WebElement txt_ShortcutBoarding;

    public final String xpath_ShortcutBoardingTooltipText = "//div[contains(text(),'Zone 2 Priority Boarding') or contains(text(),'Esto aplica a reservaciones hechas a través de nuestro Centro de Reservaciones')]";
    @FindBy(xpath=xpath_ShortcutBoardingTooltipText)
    private WebElement txt_ShortcutBoardingTooltip;

    public final String xpath_ShortcutBoardingFeeText = "//div[contains(text(),'$5.99+ each way')]";
    @FindBy(xpath=xpath_ShortcutBoardingFeeText)
    private WebElement txt_ShortcutBoardingFee;

    public final String xpath_ShortcutSecurityText = "//p[contains(text(),'Shortcut Security') or contains(text(),'Transacción de Agente - Centro de Reservaciones')]";
    @FindBy(xpath=xpath_ShortcutSecurityText)
    private WebElement txt_ShortcutSecurity;

    public final String xpath_ShortcutSecurityFeeText = "//div[contains(text(),'Up to $15')]";
    @FindBy(xpath=xpath_ShortcutSecurityFeeText)
    private WebElement txt_ShortcutSecurityFee;

    //************************************************************
    //****************Your Confirmation Code Popup****************
    //************************************************************
    public final String xpath_ConfirmationCodePopUpHeaderText = "//h2[contains(text(),'Your Confirmation Code') or contains(text(),'Cómo encontrar su Clave de Confirmación.')]";
    @FindBy(xpath=xpath_ConfirmationCodePopUpHeaderText)
    private WebElement txt_ConfirmationCodePopUpHeader;

    public final String xpath_ConfirmationCodePopUpCrossButton = "//i[@class='icon-close']";
    @FindBy(xpath=xpath_ConfirmationCodePopUpCrossButton)
    private WebElement btn_ConfirmationCodePopUpCross;

    //************************************************************
    //*****************Reservation Not Found Popup****************
    //************************************************************
    public final String xpath_ReservationNotFoundPopUpHeaderText = "//h2[contains(text(),'Reservation Not Found') or contains(text(),'No Se Encontró La Reservación')]";
    @FindBy(xpath=xpath_ReservationNotFoundPopUpHeaderText)
    private WebElement txt_ReservationNotFoundPopUpHeader;

    public final String xpath_ReservationNotFoundPopUpCrossButton = "//i[@class='icon-close']";
    @FindBy(xpath=xpath_ReservationNotFoundPopUpCrossButton)
    private WebElement btn_ReservationNotFoundPopUpCross;

    public final String xpath_ReservationNotFoundPopUpVerbiageText = "//div[@class='modal-body']/p";
    @FindBy(xpath=xpath_ReservationNotFoundPopUpVerbiageText)
    private WebElement txt_ReservationNotFoundPopUpVerbiage;

    public final String xpath_ReservationNotFoundPopUpCloseButton = "//div[@class='modal-body']//button";
    @FindBy(xpath=xpath_ReservationNotFoundPopUpCloseButton)
    private WebElement btn_ReservationNotFoundPopUpClose;

    //************************************************************
    //*****************Bag O Tron Header**************************
    //************************************************************
    public WebElement getBagOTronNewTripLabel(){
      return lbl_BagOTronNewTrip;
    }

    public WebElement getBagOTronExistingTripLabel(){
        return lbl_BagOTronExistingTrip;
    }

    //************************************************************
    //**************************New Trip**************************
    //************************************************************
    public WebElement getNewTripDepartingAirportDropDown(){
        return drpdn_NewTripDepartingAirport;
    }

    public WebElement getNewTripReturningAirportDropDown(){
        return drpdn_NewTripReturningAirport;
    }

    public WebElement getNewTripDepartingDateTextBox(){
        return txtbx_NewTripDepartingDate;
    }

    public WebElement getNewTripReturningDateTextBox(){
        return txtbx_NewTripReturningDate;
    }

    public WebElement getNewTripAdultCountDropDown(){
        return drpdn_NewTripAdultCount;
    }

    public WebElement getNewTripChildCountDropDown(){
        return drpdn_NewTripChildCount;
    }

    public WebElement getBNewTripPromoCodeTextBox(){
        return txtbx_NewTripPromoCode;
    }

    public WebElement getNewTripPurchaseFlightMilesCheckBox(){
        return chkbx_NewTripPurchaseFlightMiles;
    }

    //************************************************************
    //*****************Existing Trip******************************
    //************************************************************
    public WebElement getExistingTripLastNameTextBox(){
        return txtbx_ExistingTripLastName;
    }

    public WebElement getExistingTripConfirmationCodeTextBox(){
        return txtbx_ExistingTripConfirmationCode;
    }

    public WebElement getHowToFindConfirmationCodeLink(){
        return lnk_HowToFindConfirmationCode;
    }

    //************************************************************
    //*****************Bag O Tron Footer**************************
    //************************************************************
    public WebElement getBagOTronDisplayBagPriceButton(){
        return btn_BagOTronDisplayBagPrice;
    }

    public WebElement getBagOTronUpdateBagPriceButton(){
        return btn_BagOTronUpdateBagPrice;
    }

    public WebElement getBagOTronStartOverButton(){
        return btn_BagOTronStartOver;
    }

    public WebElement getBagOTronBookTripButton(){
        return btn_BagOTronBookTrip;
    }

    //************************************************************
    //*****************Bag O Tron Bag Price***********************
    //************************************************************
    public WebElement getBagPriceDifferentPriceLink(){
        return lnk_BagPriceDifferentPrice;
    }

    public WebElement getBagPriceDifferentPriceVerbiageText(){
        return txt_BagPriceDifferentPriceVerbiage;
    }

    //************************************************************
    //**************Overweight or Oversized Baggage***************
    //************************************************************
    public WebElement getOverweightOrOversizedLink(){
        return lnk_OverweightOrOversized;
    }

    public WebElement getOverweightOrOversizedText() {
        return txt_OverweightOrOversized;
    }

    public WebElement get41SizeText() {
        return txt_41Size;
    }

    public List<WebElement> getInfoTooltipIcon() {
        return icn_infoTooltipIcon;
    }

    public WebElement getNtificationIcon() {
        return icn_Notificationicon;
    }

    public WebElement get41NotificationText() {
        return txt_41Notification;
    }

    public WebElement get41NotificationMoreInfoText() {
        return txt_41NotificationMoreInfo;
    }

    public WebElement getPerBagText() {
        return txt_PerBag;
    }

    public WebElement get30DollarText() {
        return txt_30Dollar;
    }

    public WebElement get51SizeText() {
        return txt_51Size;
    }

    public WebElement get51NtificationText() {
        return txt_51Notification;
    }

    public WebElement get51Notification2Text() {
        return txt_51Notification2;
    }

    public WebElement get51NotificationMoreInfoText() {
        return txt_51NotificationMoreInfo;
    }

    public WebElement get55DollarText() {
        return txt_55Dollar;
    }

    public WebElement get71SizeText() {
        return txt_71Size;
    }

    public WebElement get71NotificationText() {
        return txt_71Notification;
    }

    public WebElement get71Notification2Text() {
        return txt_71Notification2;
    }

    public WebElement get71NotificationMoreInfo() {
        return txt_71NotificationMoreInfo;
    }

    public WebElement get100Dollar71LbsText() {
        return txt_100Dollar71Lbs;
    }

    public WebElement get63SizeText() {
        return txt_63Size;
    }

    public WebElement get63NotificationText() {
        return txt_63Notification;
    }

    public WebElement get63Notification2Text() {
        return txt_63Notification2;
    }

    public WebElement get63NotificationMoreInfoText() {
        return txt_63NotificationMoreInfo;
    }

    public WebElement get100DollarLinearInchesText() {
        return txt_100DollarLinearInches;
    }

    public WebElement getSpecialItemsText() {
        return txt_SpecialItems;
    }

    public WebElement getSpecialItemsNotificationText() {
        return txt_SpecialItemsNotification;
    }

    public WebElement getSpecialItemsNotificationMoreInfoText() {
        return txt_SpecialItemsNotificationMoreInfo;
    }

    public WebElement get150SpecialItemText() {
        return txt_150SpecialItem;
    }

    //************************************************************
    //*********************Sporting Equipment*********************
    //************************************************************
    public WebElement getSportingEquipmentLink(){
        return lnk_SportingEquipment;
    }

    public WebElement getSportingEquipmentText() {
        return txt_SportingEquipment;
    }

    public WebElement getBicycleText() {
        return txt_Bicycle;
    }

    public WebElement getNotificationiconBicycleIcon() {
        return icn_NotificationiconBicycle;
    }

    public WebElement getBringYourBicycleText() {
        return txt_BringYourBicycle;
    }

    public WebElement get75DollarText() {
        return txt_75Dollar;
    }

    public WebElement getSurfBoardText() {
        return txt_SurfBoard;
    }

    public WebElement getBringYourSurfBoardText() {
        return txt_BringYourSurfBoard;
    }

    public WebElement get100DollarText() {
        return txt_100Dollar;
    }

    public WebElement getGolfClubsText() {
        return txt_GolfClubs;
    }

    public WebElement getBringYourClubsText() {
        return txt_BringYourClubs;
    }

    public WebElement getGolfClubsBagPriceText() {
        return txt_GolfClubsBagPrice;
    }

    public WebElement getSkisText() {
        return txt_Skis;
    }

    public WebElement getBringYourSkisText() {
        return txt_BringYourSkis;
    }

    public WebElement getSkisBagPriceText() {
        return txt_SkisBagPrice;
    }

    public WebElement getSportingEquipmentFullListText() {
        return txt_SportingEquipmentFullList;
    }

    //************************************************************
    //*********************OPTIONAL SERVICES-SEATS****************
    //************************************************************
    public WebElement getSeatLabel() {
        return lbl_Seat;
    }

    public WebElement getEveryGuestText() {
        return txt_EveryGuest;
    }

    public WebElement getSpiritAssignedtext() {
        return txt_SpiritAssigned;
    }

    public List<WebElement> getSpiritAssignedNotificationt() {
        return icn_SpiritAssignedNotification;
    }

    public WebElement getWeWillAssignText() {
        return txt_WeWillAssign;
    }

    public WebElement getPerSeatText() {
        return txt_PerSeat;
    }

    public WebElement getFreeText() {
        return txt_Free;
    }

    public WebElement getCustomerRequestedText() {
        return txt_CustomerRequested;
    }

    public WebElement getSeatAssignmentsText() {
        return txt_SeatAssignments;
    }

    public WebElement get1To50Text() {
        return txt_1To50;
    }

    public WebElement getBigFrontSeatText() {
        return txt_BigFrontSeat;
    }

    public WebElement getWidestSeatsText() {
        return txt_WidestSeats;
    }

    public WebElement get12To150Text() {
        return txt_12To150;
    }

    public WebElement getBigFrontSeatOnBoardUpgradesText() {
        return txt_BigFrontSeatOnBoardUpgrades;
    }

    public WebElement getBFSOnBoardUpgradesNotificationText() {
        return txt_BFSOnBoardUpgradesNotification;
    }

    public WebElement get25To175Text() {
        return txt_25To175;
    }

    //************************************************************
    //*********************OPTIONAL SERVICES-MEMBERSHIPS**********
    //************************************************************
    public WebElement getMembershipsText() {
        return txt_Memberships;
    }

    public WebElement getThereNoBetterText() {
        return txt_ThereNoBetter;
    }

    public WebElement getFreeSpiritMembershipText() {
        return txt_FreeSpiritMembership;
    }

    public WebElement getFSMembershipNotificatioText() {
        return txt_FSMembershipNotification;
    }

    public WebElement getOurFrequentFlyerText() {
        return txt_OurFrequentFlyer;
    }

    public WebElement getPerMemberText() {
        return txt_PerMember;
    }

    public WebElement getFreeMembershipsText() {
        return txt_FreeMemberships;
    }

    public WebElement getFairClubTrialMembershipText() {
        return txt_FairClubTrialMembership;
    }

    public WebElement getUnlikeAnnualMembershipText() {
        return txt_UnlikeAnnualMembership;
    }

    public WebElement get19DollarText() {
        return txt_19Dollar;
    }

    public WebElement getFareClubAnnualMembershipText() {
        return txt_FareClubAnnualMembership;
    }

    public WebElement getFareClubGiveExclusiveText() {
        return txt_FareClubGiveExclusive;
    }

    public WebElement get59DollarText() {
        return txt_59Dollar;
    }

    public WebElement getFairClubAnnualRenewalText() {
        return txt_FairClubAnnualRenewal;
    }

    public WebElement getForyourConvenienceText() {
        return txt_ForyourConvenience;
    }

    public WebElement get69DollarText() {
        return txt_69Dollar;
    }

    //************************************************************
    //*********************OPTIONAL SERVICES-OTHER****************
    //************************************************************
    public WebElement getOtherLabel() {
        return lbl_Other;
    }

    public WebElement getOtherHeaderText() {
        return txt_OtherHeader;
    }

    public WebElement getBoardingPassPrintedAtHomeText() {
        return txt_BoardingPassPrintedAtHome;
    }

    public WebElement getBoardingPassAtHomeNotificationText() {
        return txt_BoardingPassAtHomeNotification;
    }

    public WebElement getPBPAtHomeText() {
        return txt_PBPAtHome;
    }

    public WebElement getBPPrintedAtHomePriceText() {
        return txt_BPPrintedAtHomePrice;
    }

    public WebElement getBoardingPassByAirportKioskText() {
        return txt_BoardingPassByAirportKiosk;
    }

    public WebElement getPBPAtHomeToSaveText() {
        return txt_PBPAtHomeToSave;
    }

    public WebElement getBPPrintedAtKioskPriceText() {
        return txt_BPPrintedAtKioskPrice;
    }

    public WebElement getBoardingPassAtAirportAgentText() {
        return txt_BoardingPassAtAirportAgent;
    }

    public WebElement getPBPAtAirportAgentHommeToSaveText() {
        return txt_PBPAtAirportAgentHommeToSave;
    }

    public WebElement getBPPrintedByAgentPriceText() {
        return txt_BPPrintedByAgentPrice;
    }

    public WebElement getUnaccompaniedMinorsText() {
        return txt_UnaccompaniedMinors;
    }

    public WebElement getUNMRTooltipText() {
        return txt_UNMRTooltip;
    }

    public WebElement getUMNRPriceText() {
        return txt_UMNRPrice;
    }

    public WebElement getInfantText() {
        return txt_Infant;
    }

    public WebElement getInfantTooltipText() {
        return txt_InfantTooltip;
    }

    public WebElement getInfantFeeText() {
        return txt_InfantFee;
    }

    public WebElement getPetTransportationText() {
        return txt_PetTransportation;
    }

    public WebElement getPetTransportTooltipText() {
        return txt_PetTransportTooltip;
    }

    public WebElement getPetContainerPriceText() {
        return txt_PetContainerPrice;
    }

    public WebElement getTGInsuranceDomesticText() {
        return txt_TGInsuranceDomestic;
    }

    public WebElement getTGDomesticTooltipText() {
        return txt_TGDomesticTooltip;
    }

    public WebElement getTGIDomesticTravelGuardLink() {
        return lnk_TGIDomesticTravelGuard;
    }

    public WebElement getTGIDOmesticFeeText() {
        return Txt_TGIDOmesticFee;
    }

    public WebElement getTGInsuranceInternationalText() {
        return txt_TGInsuranceInternational;
    }

    public WebElement getTGTripInternationalTooltipText() {
        return txt_TGTripInternationalTooltip;
    }

    public WebElement getTGIInternationalTravelGuardLink() {
        return lnk_TGIInternationalTravelGuard;
    }

    public WebElement getTGIInternationalFeeText() {
        return Txt_TGIInternationalFee;
    }

    public WebElement getTGInsuranceVacationText() {
        return txt_TGInsuranceVacation;
    }

    public WebElement getTGVacationTooltipText() {
        return txt_TGVacationTooltip;
    }

    public WebElement getTGIVacationTravelGuardLink() {
        return lnk_TGIVacationTravelGuard;
    }

    public WebElement getTGIVacationFeeText() {
        return Txt_TGIVacationFee;
    }

    //************************************************************
    //***********OPTIONAL SERVICES-OTHER-ONBOARD SNACKS***********
    //************************************************************
    public WebElement getOnboardSnacksAndDrinksChevronLink() {
        return lnk_OnboardSnacksAndDrinksChevron;
    }

    public WebElement getOnboardSnacksAndDrinksLabel() {
        return lbl_OnboardSnacksAndDrinks;
    }

    public WebElement getSnacksText() {
        return txt_Snacks;
    }

    public WebElement getOtherOnBoardSnacksText() {
        return txt_OtherOnBoardSnacks;
    }

    public WebElement getSnacksTooltipText() {
        return txt_SnacksTooltip;
    }

    public WebElement getSnacksPriceText() {
        return txt_SnacksPrice;
    }

    public WebElement getDrinksText() {
        return txt_Drinks;
    }

    public WebElement getDrinksTooltipText() {
        return txt_DrinksTooltip;
    }

    public WebElement getDrinksPriceText() {
        return txt_DrinksPrice;
    }

    //************************************************************
    //***********OPTIONAL SERVICES-OTHER-BOOKING RELATED**********
    //************************************************************
    public WebElement getBookingRelatedChevronLink() {
        return lnk_BookingRelatedChevron;
    }


    public WebElement getBookingRelatedLabel() {
        return lbl_BookingRelated;
    }

    public WebElement getOtherBookingRelatedIcon() {
        return icn_OtherBookingRelated;
    }

    public WebElement getReservationCenterText() {
        return txt_ReservationCenter;
    }

    public WebElement getReservationCenterTooltipText() {
        return txt_ReservationCenterTooltip;
    }

    public WebElement getReservationCenterBookingFeeText() {
        return txt_ReservationCenterBookingFee;
    }

    public WebElement getGroupBookingText() {
        return txt_GroupBooking;
    }

    public WebElement getGroupBookingTooltipText() {
        return txt_GroupBookingTooltip;
    }

    public WebElement getGroupBookingFeeText() {
        return txt_GroupBookingFee;
    }

    public WebElement getColombiaAdministrativeChargeText() {
        return txt_ColombiaAdministrativeCharge;
    }

    public WebElement getColombiaChargeTooltipText() {
        return txt_ColombiaChargeTooltip;
    }

    public WebElement getColombiaAdministrativeFeeText() {
        return txt_ColombiaAdministrativeFee;
    }

    public WebElement getStandbyForEarlierFlightText() {
        return txt_StandbyForEarlierFlight;
    }

    public WebElement getStandbyTooltipText() {
        return txt_StandbyTooltip;
    }

    public WebElement getStandbyFlightFeeText() {
        return txt_StandbyFlightFee;
    }

    public WebElement getPassengerUsageChargeText() {
        return txt_PassengerUsageCharge;
    }

    public WebElement getPassengerChargeTooltipText() {
        return txt_PassengerChargeTooltip;
    }

    public WebElement getPassengerUsageFeeText() {
        return txt_PassengerUsageFee;
    }

    public WebElement getRegulatoryComplianceChargeText() {
        return RegulatoryComplianceCharge;
    }

    public WebElement getRegulatoryChargeTooltipText() {
        return txt_RegulatoryChargeTooltip;
    }

    public WebElement getRegulatoryComplianceChargeFeeText() {
        return txt_RegulatoryComplianceChargeFee;
    }

    public WebElement getFuelChargeText() {
        return txt_FuelCharge;
    }

    public WebElement getFuelChargeTooltipText() {
        return txt_FuelChargeTooltip;
    }

    public WebElement getFuelChargeFeeText() {
        return txt_FuelChargeFee;
    }

    //************************************************************
    //*****OOPTIONAL SERVICES-OTHER-Free Spirit Award Booking*****
    //************************************************************
    public WebElement getFreeSpiritAwardBookingChevronLink() {
        return lnk_FreeSpiritAwardBookingChevron;
    }

    public WebElement getFREESPIRITAwardBookingLabel() {
        return lbl_FREESPIRITAwardBooking;
    }

    public WebElement getAgentTransactionReservationCenterText() {
        return txt_AgentTransactionReservationCenter;
    }

    public WebElement getOtherFSAwardBookingIcon() {
        return icn_OtherFSAwardBooking;
    }

    public WebElement getAgentTransactionTooltipText() {
        return txt_AgentTransactionTooltip;
    }

    public WebElement getPerCustomerPerBookingText() {
        return txt_PerCustomerPerBooking;
    }

    public WebElement getAgentTransactionFeeText() {
        return txt_AgentTransactionFee;
    }

    public WebElement getAwardRedemptionText() {
        return txt_AwardRedemption;
    }

    public WebElement getAwardRedemptionTooltipText() {
        return txt_AwardRedemptionTooltip;
    }

    public WebElement getAwardRedemptionMoreInfoLink() {
        return lnk_AwardRedemptionMoreInfo;
    }

    public WebElement getAwardRedemptionFeeText() {
        return txt_AwardRedemptionFee;
    }

    public WebElement getAwardModificationText() {
        return txt_AwardModification;
    }

    public WebElement getAwardModificationTooltipText() {
        return txt_AwardModificationTooltip;
    }

    public WebElement getAwardModificationMoreInfoLink() {
        return lnk_AwardModificationMoreInfo;
    }

    public WebElement getAwardModificationFeeText() {
        return txt_AwardModificationFee;
    }

    public WebElement getMileageRedepositText() {
        return txt_MileageRedeposit;
    }
    public WebElement getMileageRedepositTooltipText() {
        return txt_MileageRedepositTooltip;
    }

    public WebElement getMileageRedepositFeeText() {
        return txt_MileageRedepositFee;
    }

    //************************************************************
    //***OPTIONAL SERVICES-OTHER- Modification Or Cancellation****
    //************************************************************
    public WebElement getModificationOrCancellationChevronLink() {
        return lnk_ModificationOrCancellationChevron;
    }


    public WebElement getModificationOrCancellationLabel() {
        return lbl_ModificationOrCancellation;
    }

    public WebElement getWebModificationText() {
        return txt_WebModification;
    }

    public WebElement getOtherModificationIcon() {
        return icn_OtherModification;
    }

    public WebElement getWebModificationCancellationTooltipText() {
        return txt_WebModificationCancellationTooltip;
    }

    public WebElement getModificationPerCustomerPerBookingText() {
        return txt_ModificationPerCustomerPerBooking;
    }

    public WebElement getWebModificationFeeText() {
        return txt_WebModificationFee;
    }

    public WebElement getReservationModificationCancellationText() {
        return txt_ReservationModificationCancellation;
    }

    public WebElement getReservationsModificationTooltipText() {
        return txt_ReservationsModificationTooltip;
    }

    public WebElement getReservationModificationCancellationMoreInfoLink() {
        return lnk_ReservationModificationCancellationMoreInfo;
    }

    public WebElement getReservationsModificationFeeText() {
        return txt_ReservationsModificationFee;
    }

    public WebElement getGroupBookingCancellationModificationText() {
        return txt_GroupBookingCancellationModification;
    }

    public WebElement getGroupBookingModificationsTooltipText() {
        return txt_GroupBookingModificationsTooltip;
    }

    public WebElement getGroupBookingCancellationModificationMoreInfoLink() {
        return lnk_GroupBookingCancellationModificationMoreInfo;
    }

    public WebElement getGroupBookingCancellationModificationFeeText() {
        return txt_GroupBookingCancellationModificationFee;
    }

    //************************************************************
    //***************OPTIONAL SERVICES-OTHER-EXTRAS***************
    //************************************************************
    public WebElement getExtrasChevronLink() {
        return lnk_ExtrasChevron;
    }

    public WebElement getExtrasLabel() {
        return lbl_Extras;
    }

    public WebElement getFlightFlexText() {
        return txt_FlightFlex;
    }

    public WebElement getOtherExtrasIcon() {
        return icn_OtherExtras;
    }

    public WebElement getFlightFlexTooltipText() {
        return txt_FlightFlexTooltip;
    }

    public WebElement getPerCustomerText() {
        return txt_PerCustomer;
    }

    public WebElement getFlightFlexFeeText() {
        return txt_FlightFlexFee;
    }

    public WebElement getShortcutBoardingText() {
        return txt_ShortcutBoarding;
    }

    public WebElement getShortcutBoardingTooltipText() {
        return txt_ShortcutBoardingTooltip;
    }

    public WebElement getShortcutBoardingFeeText() {
        return txt_ShortcutBoardingFee;
    }

    public WebElement getShortcutSecurityText() {
        return txt_ShortcutSecurity;
    }

    public WebElement getShortcutSecurityFeeText() {
        return txt_ShortcutSecurityFee;
    }



    //************************************************************
    //*****************Your Confirmation Code Popup***************
    //************************************************************
    public WebElement getConfirmationCodePopUpHeader(){
        return txt_ConfirmationCodePopUpHeader;
    }

    public WebElement getConfirmationCodePopUpCrossButton(){
        return btn_ConfirmationCodePopUpCross;
    }

    //************************************************************
    //*****************Reservation Not Found Popup****************
    //************************************************************
    public WebElement getReservationNotFoundPopUpHeader(){
        return txt_ReservationNotFoundPopUpHeader;
    }

    public WebElement getReservationNotFoundPopUpCrossButton(){
        return btn_ReservationNotFoundPopUpCross;
    }

    public WebElement getReservationNotFoundPopUpVerbiageText(){
        return txt_ReservationNotFoundPopUpVerbiage;
    }

    public WebElement getReservationNotFoundPopUpCloseButton(){
        return btn_ReservationNotFoundPopUpClose;
    }


    //table[1]/tbody//tr[1]/td[2]/p




}
