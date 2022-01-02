package com.spirit.utility;

import com.spirit.enums.Context;
import com.spirit.managers.FileReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailUtil {


    //**********************************************************************************************
    //Method Name:sendEmailWithAttachReport
    //Description: Method is used to send the Email with attch report in it
    //Input Arguments:1.File->reportFile
    //                2.String->currSuite
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 20-May-2019
    //Reviewed By: Kartik Chauhan
    //Reviewed On: 20-May-2019
    //Updated By: Anthony Cardona
    //Update date: 27-Jun-2019
    //**********************************************************************************************
    public static void sendEmailWithAttachReport(File reportFile, String currSuite) {

        //Setting Properties and Session for message
        Properties props = new Properties();

        try //try to send through 365 email
        {
            props.put("mail.smtp.host", FileReaderManager.getInstance().getEmailReader().getEmailHost());
            props.put("mail.smtp.starttls.enable", FileReaderManager.getInstance().getEmailReader().getEmailAuthentication());
            props.put("mail.smtp.auth", FileReaderManager.getInstance().getEmailReader().getEmailAuthentication());
            props.put("mail.smtp.port", FileReaderManager.getInstance().getEmailReader().getEmailPort());
            props.put("mail.smtp.connectiontimeout", "20000"); //set timeout, Exception if Connection to server is not made
            props.put("mail.smtp.timeout", "20000"); //set timeout, Exception if handshake is not made

            sendMessage(reportFile, currSuite, props); //Email With Content

        } catch (MessagingException eMessage) { // if send message through 365 fails, send via mailrelay which does not need authentication
            try {

                props.clear();//clear properties that were already added
                props = System.getProperties();
                props.put("mail.smtp.host", "mailrelay.spirit.com");
                props.put("mail.smtp.port", "25");

                sendMessage(reportFile, currSuite, props); //Email With Content

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //**********************************************************************************************
    //Method Name:sendEmailWithSnapshot
    //Description: Method is used to send the Email with attch report in it
    //Input Arguments:1.File->reportFile
    //                2.String->currSuite
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 20-May-2019
    //Reviewed By: Kartik Chauhan
    //Reviewed On: 20-May-2019
    //Updated By: Anthony Cardona
    //Update date: 27-Jun-2019
    //**********************************************************************************************
    public static void sendEmailWithSnapshot(File reportFile, String currSuite) {

        //Setting Properties and Session for message
        Properties props = new Properties();

        try //try to send through 365 email
        {
            props.put("mail.smtp.host", FileReaderManager.getInstance().getEmailReader().getEmailHost());
            props.put("mail.smtp.starttls.enable", FileReaderManager.getInstance().getEmailReader().getEmailAuthentication());
            props.put("mail.smtp.auth", FileReaderManager.getInstance().getEmailReader().getEmailAuthentication());
            props.put("mail.smtp.port", FileReaderManager.getInstance().getEmailReader().getEmailPort());
            props.put("mail.smtp.connectiontimeout", "20000"); //set timeout, Exception if Connection to server is not made
            props.put("mail.smtp.timeout", "20000"); //set timeout, Exception if handshake is not made

            sendMessage(reportFile, currSuite, props); //Email With Content

        } catch (MessagingException eMessage) { // if send message through 365 fails, send via mailrelay which does not need authentication
            try {

                props.clear();//clear properties that were already added
                props = System.getProperties();
                props.put("mail.smtp.host", "mailrelay.spirit.com");
                props.put("mail.smtp.port", "25");

                sendMessage(reportFile, currSuite, props); //Email With Content

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void sendMessage(File reportFile, String currSuite, Properties props) throws MessagingException
    {
        Session session;

        if (props.get("mail.smtp.host").equals("smtp.office365.com")) //depending on properties smtp.host, set authentication.
        {
            session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(FileReaderManager.getInstance().getEmailReader().getOutLookUserName(), FileReaderManager.getInstance().getEmailReader().getOutLookPassword());
                        }
                    });
        }
        else{
            session  = Session.getInstance(props);
        }

        //Output for email , debugging purposes
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        // Output for session on Console, debugging purposes
        session.setDebug(true);
        session.setDebugOut(ps);

        // Create object of MimeMessage class
        Message message = new MimeMessage(session);

        // Set the from address
        message.setFrom(new InternetAddress(FileReaderManager.getInstance().getEmailReader().getOutLookUserName()));

        // Set the recipient address
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(FileReaderManager.getInstance().getEmailReader().getRecipientType_TO()));
        //message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("Alex.Rodriguez2@spirit.com"));


        if(currSuite.equalsIgnoreCase("HeartBeat")){
            // Add the subject link
            message.setSubject("Failure Observed on Heart Beat at: " + reportFile.getName().replace(".png",""));
        }else{
            // Add the subject link
            message.setSubject("EPIC Automation Report Currently Completed "+ currSuite);
        }

        // Create object to add multimedia type content
        BodyPart messageBodyPart1 = new MimeBodyPart();

        // Set the body of email
        messageBodyPart1.setText("This is message body");

        // Create another object to add another content
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();

        // Create data source and pass the filename
        DataSource source = new FileDataSource(reportFile);

        // set the handler
        messageBodyPart2.setDataHandler(new DataHandler(source));

        // set the file
        messageBodyPart2.setFileName(reportFile.getName());

        // Create object of MimeMultipart class
        Multipart multipart = new MimeMultipart();

        // add body part 1
        multipart.addBodyPart(messageBodyPart2);

        // add body part 2
        multipart.addBodyPart(messageBodyPart1);

        // set the content
        message.setContent(multipart);

        // finally send the email
        Transport.send(message);
    }

    public static void sendEmailForVacationBooking(File reportFile, String currSuite) {

        //Setting Properties and Session for message
        Properties props = new Properties();

        try //try to send through 365 email
        {
            props.put("mail.smtp.host", FileReaderManager.getInstance().getEmailReader().getEmailHost());
            props.put("mail.smtp.starttls.enable", FileReaderManager.getInstance().getEmailReader().getEmailAuthentication());
            props.put("mail.smtp.auth", FileReaderManager.getInstance().getEmailReader().getEmailAuthentication());
            props.put("mail.smtp.port", FileReaderManager.getInstance().getEmailReader().getEmailPort());
            props.put("mail.smtp.connectiontimeout", "20000"); //set timeout, Exception if Connection to server is not made
            props.put("mail.smtp.timeout", "20000"); //set timeout, Exception if handshake is not made

            sendMessageForVacationBooking(reportFile, currSuite, props); //Email With Content

        } catch (MessagingException eMessage) { // if send message through 365 fails, send via mailrelay which does not need authentication
            try {

                props.clear();//clear properties that were already added
                props = System.getProperties();
                props.put("mail.smtp.host", "mailrelay.spirit.com");
                props.put("mail.smtp.port", "25");

                sendMessageForVacationBooking(reportFile, currSuite, props); //Email With Content

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void sendMessageForVacationBooking(File reportFile, String currSuite, Properties props) throws MessagingException {

        Session session;

        if (props.get("mail.smtp.host").equals("smtp.office365.com")) //depending on properties smtp.host, set authentication.
        {
            session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(FileReaderManager.getInstance().getEmailReader().getOutLookUserName(), FileReaderManager.getInstance().getEmailReader().getOutLookPassword());
                        }
                    });
        }
        else{
            session  = Session.getInstance(props);
        }

        //Output for email , debugging purposes
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        // Output for session on Console, debugging purposes
        session.setDebug(true);
        session.setDebugOut(ps);

        // Create object of MimeMessage class
        Message message = new MimeMessage(session);

        // Set the from address
        message.setFrom(new InternetAddress(FileReaderManager.getInstance().getEmailReader().getOutLookUserName()));

        // Set the recipient address
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("Alex.Rodriguez2@spirit.com,anthony.cardona@spirit.com"));

        // Add the subject link
        message.setSubject("Cancel Vacation Booking Hotel or Car");

        // Create object to add multimedia type content
        BodyPart messageBodyPart1 = new MimeBodyPart();

        // Set the body of email
        messageBodyPart1.setText("This is message body");

        // Create another object to add another content
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();

        // Create data source and pass the filename
        DataSource source = new FileDataSource(reportFile);

        // set the handler
        messageBodyPart2.setDataHandler(new DataHandler(source));

        // set the file
        messageBodyPart2.setFileName(reportFile.getName());

        // Create object of MimeMultipart class
        Multipart multipart = new MimeMultipart();

        // add body part 1
        multipart.addBodyPart(messageBodyPart2);

        // add body part 2
        multipart.addBodyPart(messageBodyPart1);

        // set the content
        message.setContent(multipart);

        // finally send the email
        Transport.send(message);
    }

    //**********************************************************************************************
    //Method Name:openOutlookInNewTab
    //Description: Method is used to open out look in new tab
    //Input Arguments:1.WebDriver->driver
    //                2.String->nameOfMember
    //Return: NA
    //Created By : Anthony C
    //Created On : 20-May-2019
    //Reviewed By: Kartik Chauhan
    //Reviewed On: 20-May-2019
    //**********************************************************************************************
    public static String openOutlookInNewTab(WebDriver driver, String nameOfMember, String startTime, String callingMethod) {

        String resertPassword;

        WebDriverWait explicitWait = new WebDriverWait(driver,20);

        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');"); //open new tab with JS executor

        WaitUtil.untilTimeCompleted(2000);

        //TestUtil.switchToWindow(driver,1);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles()); //create a list of tabs
        driver.switchTo().window(tabs.get(1)); //switch to tab at postition 1

        driver.get(FileReaderManager.getInstance().getEmailReader().getOutLookURL()); // open this URL so that we can access email

        WaitUtil.untilTimeCompleted(5000);

        WaitUtil.untilAngularLoadComplete(driver);

        WaitUtil.untilJqueryIsDone(driver);

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));

        driver.findElement(By.id("i0116")).sendKeys(FileReaderManager.getInstance().getEmailReader().getOutLookUserName()); //input mail value

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));

        driver.findElement(By.id("idSIButton9")).click(); //click on the Next button

        WaitUtil.untilTimeCompleted(7000);

        WaitUtil.untilJavaScriptPageLoadComplete(driver);

        WaitUtil.untilAngularLoadComplete(driver);

        WaitUtil.untilJqueryIsDone(driver);

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("passwordInput")));

        driver.findElement(By.id("passwordInput")).sendKeys(FileReaderManager.getInstance().getEmailReader().getOutLookPassword()); //send password keys

        driver.findElement(By.id("submitButton")).click(); //click on the log in button

        WaitUtil.untilPageURLVisible(driver, "https://login.microsoftonline.com/login.srf"); //wait until page URL is visible

        WaitUtil.untilTimeCompleted(7000);

        WaitUtil.untilJavaScriptPageLoadComplete(driver);

        WaitUtil.untilAngularLoadComplete(driver);

        WaitUtil.untilJqueryIsDone(driver);

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("idBtn_Back")));

        WaitUtil.untilTimeCompleted(7000);

        WaitUtil.untilJavaScriptPageLoadComplete(driver);

        WaitUtil.untilAngularLoadComplete(driver);

        WaitUtil.untilJqueryIsDone(driver);

        driver.findElement(By.id("idBtn_Back")).click(); //click no to save password

        WaitUtil.untilTimeCompleted(5000);

        if(callingMethod.equalsIgnoreCase("TemporaryPasswordFromEmail")){
            resertPassword = getTemporaryPasswordFromEmail(driver, nameOfMember, startTime);
        }else if(callingMethod.equalsIgnoreCase("BoardingPassEmail")){
            resertPassword = "";
            checkBoardingPassEmail(driver, nameOfMember, startTime);
        }else{
            resertPassword = "";
        }

        driver.close(); //close the current tab

        driver.switchTo().window(tabs.get(0));

        return resertPassword;

    }

    //**********************************************************************************************
    //Method Name:getTemporaryPasswordFromEmail
    //Description: Method is used to get the temperary password from email
    //Input Arguments:1.WebDriver->driver
    //                2.String->nameOfMember
    //Return: NA
    //Created By : Anthony
    //Created On : 20-May-2019
    //Reviewed By: Kartik Chauhan
    //Reviewed On: 20-May-2019
    //**********************************************************************************************
    public static String getTemporaryPasswordFromEmail(WebDriver driver, String memberFN, String firstTime) {
        String resetPWFromEmail = "";
        List<WebElement> allWebEmails = null;
        WaitUtil.untilTimeCompleted(5000);

        //get time when password is reset
        Date startTime = TestUtil.convertStringToDate(firstTime,"yyyy-MM-dd HH:mm:ss");

        //get the first name with first alphabet as capital
        String nameOfMember = memberFN.substring(0, 1).toUpperCase() + memberFN.substring(1).toLowerCase();//make first letter of name upper cased

        //System.out.println(nameOfMember);

        //loop through for 7 mins
        for(int mailcounter=0;mailcounter<=500;mailcounter++){
            try{
                //get all the mail list
                allWebEmails = driver.findElements(By.xpath("//div[@aria-label='conversation']//span[contains(text(),'Hi " + nameOfMember + "')]"));
                //System.out.println(allWebEmails.size());

                //check the mail size
                if(allWebEmails.size()>0){
                    //click on reset password email
                    driver.findElement(By.xpath("(//div[@aria-label='conversation']//span[contains(text(),'Hi " + nameOfMember + "')]//..//..//..//div[@role = 'option'])")).click();//click on the next email reset email

                    //get the temp password
                    resetPWFromEmail = driver.findElement(By.xpath("//div[@id='Item.MessageUniqueBody']//strong//span/..")).getText(); //store text to resetPWFromEmail

                    //break from loop
                    break;
                }
            }catch (Exception e){
                //do nothing
            }

            //get current time
            Date endTime = TestUtil.convertStringToDate(TestUtil.getStringDateFormat("0","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");

            //System.out.println(endTime.getTime()-startTime.getTime());
            if(endTime.getTime()-startTime.getTime() > 7*60*1000){
                ValidationUtil.validateTestStep("test execution took longer than 7 minutes and did not recieve email", false);
            }else{
                //driver.navigate().refresh();

                WaitUtil.untilTimeCompleted(4000);
            }
        }

        //return temp password
        return resetPWFromEmail;
    }

    //**********************************************************************************************
    //Method Name:checkBoardingPassEmail
    //Description: Method is used to verify Boarding Pass in Email
    //Input Arguments:1.WebDriver->driver
    //                2.String->nameOfMember
    //Return: NA
    //Created By : Anthony
    //Created On : 20-May-2019
    //Reviewed By: Kartik Chauhan
    //Reviewed On: 20-May-2019
    //**********************************************************************************************
    public static void checkBoardingPassEmail(WebDriver driver, String memberFN, String firstTime) {

        String EMAIL_BP_SUBJECT_LINE   = "//div[@aria-label='Message list']//span[contains(text(),'Spirit Airlines Email Boarding Pass: " + memberFN + "')]";

        String resetPWFromEmail = "";
        List<WebElement> allWebEmails = null;
        WaitUtil.untilTimeCompleted(5000);

        //get time when password is reset
        Date startTime = TestUtil.convertStringToDate(firstTime,"yyyy-MM-dd HH:mm:ss");

        //get the first name with first alphabet as capital
        String nameOfMember = memberFN.substring(0, 1).toUpperCase() + memberFN.substring(1).toLowerCase();//make first letter of name upper cased

        //loop through for 7 mins
        for(int mailcounter=0;mailcounter<=500;mailcounter++) {
            try {
                //get all the mail list
                allWebEmails = driver.findElements(By.xpath(EMAIL_BP_SUBJECT_LINE));

                //check the mail size
                if (allWebEmails.size() > 0) {
                    //click on reset password email
                   driver.findElement(By.xpath(EMAIL_BP_SUBJECT_LINE)).click();

                    ValidationUtil.validateTestStep("Boarding pass email has at least 1 QR code", 0 < driver.findElements(By.xpath("//img[@data-imagetype='DataUri']")).size() );

                    //get the temp password
                    //resetPWFromEmail = Integer.toString(driver.findElements(By.xpath("//img[@data-imagetype='DataUri']")).size());

                    //break from loop
                    break;
                }
            } catch (Exception e) {
                //do nothing
            }

            //get current time
            Date endTime = TestUtil.convertStringToDate(TestUtil.getStringDateFormat("0","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");

            //System.out.println(endTime.getTime()-startTime.getTime());
            if(endTime.getTime()-startTime.getTime() > 7*60*1000){
                ValidationUtil.validateTestStep("test execution took longer than 7 minutes and did not recieve email", false);
            }else{
                driver.navigate().refresh();

                WaitUtil.untilTimeCompleted(4000);
            }
        }
    }


    //**********************************************************************************************
    //Method Name:verifyPackageBookingEmails
    //Description: Method is used to open out look in new tab
    //Input Arguments:1.WebDriver->driver
    //                2.String->nameOfMember
    //Return: NA
    //Created By : Anthony C
    //Created On : 20-May-2019
    //Reviewed By: Kartik Chauhan
    //Reviewed On: 20-May-2019
    //**********************************************************************************************
    public static void verifyPackageBookingEmails(WebDriver driver, String carDetails, String hotelDetails, String spiritCinformationCode, String carConfirmationNumber, String hotelConfirmationNumber, String redEyeFlight, String depDate, String retDate, String depFlightDetails, String retFlightDetails) {

        WebDriverWait explicitWait = new WebDriverWait(driver,20);

        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');"); //open new tab with JS executor

        WaitUtil.untilTimeCompleted(2000);

        //TestUtil.switchToWindow(driver,1);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles()); //create a list of tabs
        driver.switchTo().window(tabs.get(1)); //switch to tab at postition 1

        driver.get(FileReaderManager.getInstance().getEmailReader().getOutLookURL()); // open this URL so that we can access email

        WaitUtil.untilTimeCompleted(5000);

        WaitUtil.untilAngularLoadComplete(driver);

        WaitUtil.untilJqueryIsDone(driver);

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("i0116")));

        driver.findElement(By.id("i0116")).sendKeys(FileReaderManager.getInstance().getEmailReader().getOutLookUserName()); //input mail value

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));

        driver.findElement(By.id("idSIButton9")).click(); //click on the Next button

        WaitUtil.untilTimeCompleted(7000);

        WaitUtil.untilJavaScriptPageLoadComplete(driver);

        WaitUtil.untilAngularLoadComplete(driver);

        WaitUtil.untilJqueryIsDone(driver);

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("passwordInput")));

        driver.findElement(By.id("passwordInput")).sendKeys(FileReaderManager.getInstance().getEmailReader().getOutLookPassword()); //send password keys

        driver.findElement(By.id("submitButton")).click(); //click on the log in button

        while(true){

            WaitUtil.untilPageURLVisible(driver, "https://login.microsoftonline.com/login.srf");

            if(driver.findElements(By.xpath("//div[contains(text(),'An error occurred')]")).size()>0){
                JSExecuteUtil.refreshBrowser(driver);
            }else{

                break;
            }
        }

        WaitUtil.untilPageURLVisible(driver, "https://login.microsoftonline.com/login.srf"); //wait until page URL is visible

        WaitUtil.untilTimeCompleted(7000);

        WaitUtil.untilJavaScriptPageLoadComplete(driver);

        WaitUtil.untilAngularLoadComplete(driver);

        WaitUtil.untilJqueryIsDone(driver);

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("idBtn_Back")));

        WaitUtil.untilTimeCompleted(7000);

        WaitUtil.untilJavaScriptPageLoadComplete(driver);

        WaitUtil.untilAngularLoadComplete(driver);

        WaitUtil.untilJqueryIsDone(driver);

        driver.findElement(By.id("idBtn_Back")).click(); //click no to save password

        WaitUtil.untilTimeCompleted(5000);

        checkPackageBookingConfirmation(driver, carDetails, hotelDetails, spiritCinformationCode, carConfirmationNumber, hotelConfirmationNumber,
                                        redEyeFlight, depDate, retDate, depFlightDetails, retFlightDetails);

        driver.close(); //close the current tab

        driver.switchTo().window(tabs.get(0));
    }

    //**********************************************************************************************
    //Method Name:checkPackageBookingConfirmation
    //Description: Method is used to verify Package Booking in Email
    //Input Arguments:1.WebDriver->driver
    //                2.String->PNR
    //Return: NA
    //Created By : Anthony
    //Created On : 19-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public static void checkPackageBookingConfirmation(WebDriver driver, String carDetails, String hotelDetails, String spiritCinformationCode, String carConfirmationNumber, String hotelConfirmationNumber, String redEyeFlight, String depDate, String retDate, String depFlightDetails, String retFlightDetails) {

        //declare xpath used in methods
        final String xpath_EmailSubjectLineText  = "//div[@aria-label='Message list']//span[contains(text(),'Spirit Airlines Vacation Package Confirmation: " + spiritCinformationCode + "')]";

        List<WebElement> allWebEmails = null;

        //get time when password is reset
        Date startTime = TestUtil.convertStringToDate(TestUtil.getStringDateFormat("0", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");

        //loop through for 7 mins
        for (int mailCounter = 0; mailCounter <= 500; mailCounter++) {
            //try {
                //get all the mail list
            WaitUtil.untilTimeCompleted(5000);

                allWebEmails = driver.findElements(By.xpath(xpath_EmailSubjectLineText));
            WaitUtil.untilTimeCompleted(5000);
                //check the mail size
                if (allWebEmails.size() > 0) {
                    //click on reset password email
                    driver.findElement(By.xpath(xpath_EmailSubjectLineText)).click();
                    WaitUtil.untilTimeCompleted(5000);

                    if(!carConfirmationNumber.equalsIgnoreCase("NA")){
                        //verify Car details
                        verifyCarDetails(driver, carDetails, carConfirmationNumber, redEyeFlight, depDate, retDate, depFlightDetails, retFlightDetails);
                    }

                    if(!hotelConfirmationNumber.equalsIgnoreCase("NA")){
                        //verify Hotel details
                        verifyHotelDetails(driver, hotelDetails, hotelConfirmationNumber, redEyeFlight, depDate, retDate);
                    }

                    //break from loop
                    break;
                }
            //} catch (Exception e) {
             //   System.out.println();
           // }

            //get current time
            Date endTime = TestUtil.convertStringToDate(TestUtil.getStringDateFormat("0", "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");

            //System.out.println(endTime.getTime()-startTime.getTime());
            if (endTime.getTime() - startTime.getTime() > 7 * 60 * 1000) {
                ValidationUtil.validateTestStep("test execution took longer than 7 minutes and did not receive email", false);
            } else {
                driver.navigate().refresh();

                WaitUtil.untilTimeCompleted(4000);
            }
        }
    }

    //**********************************************************************************************
    //Method Name:verifyHotelDetails
    //Description: Method is used to verify Selected Hotel Details on Email
    //Input Arguments:  1.WebDriver->driver
    //                  2.String->hotelDetails
    //                  3.String->hotelConfirmationNumber
    //                  4.String->redEyeFlight
    //                  5.String->depDate
    //                  6.String->retDate
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    private static void verifyHotelDetails(WebDriver driver, String hotelDetails, String hotelConfirmationNumber, String redEyeFlight, String depDate, String retDate){

        //declare constant used in method
        final String SINGLE_SEPARATOR                               = "\\|";
        final String DOUBLE_SEPARATOR                               = "\\|\\|";

        //declare Hotel Xpath used in method
        final String xpath_SelectedHBGHotelHeaderText               = "//td[contains(text(),'Hotel')]";
        final String xpath_SelectedHBGHotelConfirmationLabel        = "//*[@id='x_HotelInfoElement']/tbody/tr[1]/td/table/tbody/tr/td[1]";
        final String xpath_SelectedHBGHotelConfirmationValueText    = "//div[@id='x_HotelInfo']//td//span";
        final String xpath_SelectedHBGHotelAddressLocationText      = "//table[@id='x_HotelAddress']//tr";//list
        final String xpath_SelectedHBGHotelRoomTypeCountText        = "//td[contains(text(),'Room(s):')]";
        final String xpath_SelectedHBGHotelCheckInText              = "//td[contains(text(),'Check in:')]/following-sibling::td";
        final String xpath_SelectedHBGHotelCheckOutText             = "//td[contains(text(),'Check out:')]/following-sibling::td";

        //declare variable used in method
        String hotelName=null, roomType=null, roomCount =null, hotelAddress = null;

        System.out.println(hotelDetails);

        for(String hotelDetail : hotelDetails.split(SINGLE_SEPARATOR)){

            switch(hotelDetail.split(":")[0]){
                case "HotelName":
                    hotelName = hotelDetail.replace("HotelName:","");
                    break;
                case "HotelAddress":
                    hotelAddress = hotelDetail.replace("HotelAddress:","");
                    break;
                case "HotelRoomType":
                    roomType = hotelDetail.replace("HotelRoomType:","");
                    break;
                case "HotelRoomCount":
                    roomCount = hotelDetail.replace("HotelRoomCount:","");
                    break;
            }
        }

        //hotel header
        ValidationUtil.validateTestStep("Verify Selected Hotel Header appear on Package Booking Email",
                driver.findElement(By.xpath(xpath_SelectedHBGHotelHeaderText)).isDisplayed());

//        System.out.println(hotelName);
//        System.out.println(driver.findElement(By.xpath(xpath_SelectedHBGHotelConfirmationLabel)).getText());
        //hotel name
        ValidationUtil.validateTestStep("Verify Selected Hotel Name appear on Package Booking Email",
                driver.findElement(By.xpath(xpath_SelectedHBGHotelConfirmationLabel)).getText(),(hotelName));

        //hotel confirmation number
        ValidationUtil.validateTestStep("Verify Selected Hotel Confirmation Code appear on Package Booking Email",
                driver.findElement(By.xpath(xpath_SelectedHBGHotelConfirmationValueText)).getText(),hotelConfirmationNumber);

        //hotel address location
        boolean addressFlag = true;
        //System.out.println(hotelAddress);
        //System.out.println(driver.findElements(By.xpath(xpath_SelectedHBGHotelAddressLocationText)).get(0).getText());
        for(String address : driver.findElements(By.xpath(xpath_SelectedHBGHotelAddressLocationText)).get(0).getText().toString().split(",")[0].split(" ")){
            if(!hotelAddress.toUpperCase().contains(address.toUpperCase())){
                addressFlag = false;
            }
        }
        ValidationUtil.validateTestStep("Verify Selected Hotel Address appear on Package Booking Email", addressFlag);


        //hotel Room Count
        ValidationUtil.validateTestStep("Verify Selected Hotel Room Count appear on Package Booking Email",
                driver.findElement(By.xpath(xpath_SelectedHBGHotelRoomTypeCountText)).getText(),roomCount);

        //hotel Room type
        ValidationUtil.validateTestStep("Verify Selected Hotel Room Type appear on Package Booking Email",
                driver.findElement(By.xpath(xpath_SelectedHBGHotelRoomTypeCountText)).getText(),roomType);

        //check in Date
        String dateDays = null;

        //verify check-In date
        if(redEyeFlight.split(DOUBLE_SEPARATOR)[0].equals("1")){
            dateDays = Integer.toString(Integer.parseInt(depDate)+1);
        }else{
            dateDays = depDate;
        }

        //get Check-In Date
        String checkInDate = TestUtil.getStringDateFormat(dateDays, "EEEE, MMMM dd, yyyy");

        ValidationUtil.validateTestStep("Verify Selected Hotel Check-In date appear on Package Booking Email",
                driver.findElement(By.xpath(xpath_SelectedHBGHotelCheckInText)).getText(),checkInDate);

        //check out Date
        dateDays = retDate.toString();

        //get arribval time
        String checkOutDate = TestUtil.getStringDateFormat(dateDays, "EEEE, MMMM dd, yyyy");

        ValidationUtil.validateTestStep("Verify Selected Hotel Check-In date appear on Package Booking Email",
                driver.findElement(By.xpath(xpath_SelectedHBGHotelCheckOutText)).getText(),checkOutDate);
    }

    //**********************************************************************************************
    //Method Name:verifyCarDetails
    //Description: Method is used to verify Selected Car Details on Email
    //Input Arguments:  1.WebDriver->driver
    //                  2.String->hotelDetails
    //                  3.String->carConfirmationNumber
    //                  4.String->redEyeFlight
    //                  5.String->depDate
    //                  6.String->retDate
    //Return: NA
    //Created By : Salim Ansari
    //Created On : 28-Nov-2019
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public static void verifyCarDetails(WebDriver driver, String carDetails, String carConfirmationNumber, String redEyeFlight, String depDate, String retDate, String depFlightDetails, String retFlightDetails){

        //declare constant used in method
        final String SINGLE_SEPARATOR                               = "\\|";
        final String DOUBLE_SEPARATOR                               = "\\|\\|";

        //declare Car Xpaths used in methods
        final String xpath_SelectedCarnetCarHeaderText              = "//td[contains(text(),'Car')]";
        final String xpath_SelectedCarnetCarConfirmationLabel       = "//tr[@id='x_hertzConfirmRow']//td[contains(text(),'Confirmation Number')]";
        final String xpath_SelectedCarnetCarConfimrationValueText   = "//tr[@id='x_hertzConfirmRow']//td/span";
        final String xpath_SelectedCarnetCarAddressLocationText     = "//div[@id='x_CarInfo']//tr";//list
        final String xpath_SelectedCarnetCarPickUpText              = "//td[contains(text(),'Pick up:')]/following-sibling::td";
        final String xpath_SelectedCarnetCarDropOffText             = "//td[contains(text(),'Drop off:')]/following-sibling::td";

        //declare variable used in method
        String carModel=null,carType=null,carAddress=null;

        //CarType:THRIFTY ECONOMY CAR|CarModel:CHEVROLET SPARK or similar|Seats:4 Seats|Bags:3 Bags|Options:3 Bags|Miles:Unlimited Miles|Price:278.45|PickUpPoint:7135 GILESPIE STREET LAS VEGAS AP NV 89119
        for (String carDetail : carDetails.split(SINGLE_SEPARATOR)) {
            switch (carDetail.split(":")[0]) {
                case "CarModel":
                    carModel = carDetail.replace("CarModel:","").split("or")[0].trim();
                    break;
                case "CarType":
                    carType = carDetail.replace("CarType:","").split(" ")[0].trim();
                    break;
                case "PickUpPoint":
                    carAddress = carDetail.replace("PickUpPoint:","");
            }
        }


        //car header
        ValidationUtil.validateTestStep("Verify Selected Car Header appear on Package Booking Email",
                driver.findElement(By.xpath(xpath_SelectedCarnetCarHeaderText)).isDisplayed());

        //car model in confirmation number
        ValidationUtil.validateTestStep("Verify Selected Car Rental Agency Name appear in Confirmation Number on Package Booking Email",
                driver.findElement(By.xpath(xpath_SelectedCarnetCarConfirmationLabel)).getText(),carType);

        //car Confirmation code
        ValidationUtil.validateTestStep("Verify Selected Car Confirmation Number appear on Package Booking Email",
                driver.findElement(By.xpath(xpath_SelectedCarnetCarConfimrationValueText)).getText(),carConfirmationNumber);

        //car model and type
        ValidationUtil.validateTestStep("Verify Selected Car Model appear in Address section on Package Booking Email",
                driver.findElements(By.xpath(xpath_SelectedCarnetCarAddressLocationText)).get(1).getText(),carModel);

        ValidationUtil.validateTestStep("Verify Selected Car Type appear in Address section on Package Booking Email",
                driver.findElements(By.xpath(xpath_SelectedCarnetCarAddressLocationText)).get(1).getText(),carType);

        //car address
        int carAddressLength = driver.findElements(By.xpath(xpath_SelectedCarnetCarAddressLocationText)).size();
        String carAddressdetail = driver.findElements(By.xpath(xpath_SelectedCarnetCarAddressLocationText)).get(carAddressLength-5).getText();
        System.out.println(carAddressdetail);
        boolean carFlag = true;
        for(String carAddressValue : carAddressdetail.split(" ")){
            if(!carAddress.toUpperCase().contains(carAddressValue.toUpperCase())){
                carFlag = false;
            }
        }

        ValidationUtil.validateTestStep("Verify Selected Car Address section on Package Booking Email",carFlag);

        //car pick up date and time
        //get last leg departure flight information
        int lastLegDetails = depFlightDetails.split(DOUBLE_SEPARATOR).length;
        String dateDays = null;

        //loop through all departure flight details of last leg
        for (String arrivalTime : depFlightDetails.split(DOUBLE_SEPARATOR)[lastLegDetails - 1].split(SINGLE_SEPARATOR)) {
            //check for arrival time
            if (arrivalTime.contains("ArrivalTime")) {
                //get arribval time

                //System.out.println(scenarioContext.getContext(Context.AVAILABILITY_RED_EYE).toString().split(DOUBLE_SEPARATOR)[0]);
                if (redEyeFlight.split(DOUBLE_SEPARATOR)[0].equals("1")) {
                    dateDays = Integer.toString(Integer.parseInt(depDate) + 1);
                } else {
                    dateDays = depDate;
                }

                //String pickUp = TestUtil.getStringDateFormat(dateDays, "EEEE, MMMM dd, yyyy") + " " + arrivalTime.replace("ArrivalTime:","");
                String pickUp = TestUtil.getStringDateFormat(dateDays, "EEEE, MMMM dd, yyyy");

                //verify pick up date and time
                ValidationUtil.validateTestStep("Verify Selected Car Pick Up Date and Time on Package Booking Email",
                        driver.findElement(By.xpath(xpath_SelectedCarnetCarPickUpText)).getText(),pickUp);

                break;
            }
        }

        //car drop off date and time
        //loop through all flight details of first leg
        for (String departureTime : retFlightDetails.split(DOUBLE_SEPARATOR)[0].split(SINGLE_SEPARATOR)) {
            //check for arrival time
            if (departureTime.contains("DepartureTime")) {

                dateDays = retDate;

                //get arrival time
                //String dropOff = TestUtil.getStringDateFormat(dateDays, "EEEE, MMMM dd, yyyy") + " " + departureTime.replace("DepartureTime:", "");
                String dropOff = TestUtil.getStringDateFormat(dateDays, "EEEE, MMMM dd, yyyy");

                //validate drop off point of Car
                ValidationUtil.validateTestStep("Verify Selected Car Drop Date and Time on Package Booking Email",
                        driver.findElement(By.xpath(xpath_SelectedCarnetCarDropOffText)).getText(),dropOff);
                break;
            }
        }

    }

}
