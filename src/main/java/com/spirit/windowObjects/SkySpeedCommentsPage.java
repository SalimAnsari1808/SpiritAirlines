package com.spirit.windowObjects;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SkySpeedCommentsPage {

    private WindowsDriver driver;

    public SkySpeedCommentsPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //SkySpeedFillCommentWindow
    public final String xpath_CommentAddCommentsButton = "//*[@AutomationId = '_buttonAdd']";
    @FindBy(xpath=xpath_CommentAddCommentsButton)
    private WebElement btn_CommentAddComments;

    public final String xpath_CommentsaveCommentButton = "//*[@AutomationId = '_buttonSave']";
    @FindBy(xpath=xpath_CommentsaveCommentButton)
    private WebElement btn_CommentSaveComment;

    public final String xpath_CommentTypeDropDown = "//*[@AutomationId = '_comboBoxCommentType']";
    @FindBy(xpath=xpath_CommentTypeDropDown)
    private WebElement drpdwn_CommentType;

    public final String xpath_CommentInputTextBox = "//*[@AutomationId = '_textBoxCommentText']";
    @FindBy(xpath=xpath_CommentInputTextBox)
    private WebElement txtbx_CommentInput;

    public final String xpath_MandatoryCommentsTextBox = "//*[@AutomationId = '_textBoxDefaultComment']";
    @FindBy(xpath=xpath_MandatoryCommentsTextBox)
    private WebElement txtbx_MandatoryComments;

    public final String xpath_CommentList = "//*[@AutomationId = '_listBoxComment']";
    @FindBy(xpath=xpath_CommentList)
    private WebElement CommentList;

    public final String xpath_CommentNextButton = "//*[@AutomationId = '_buttonContinue']";
    @FindBy(xpath=xpath_CommentNextButton)
    private WebElement btn_CommentNext;

    ////////////////////////////Start getter Methods/////////////////////////////////////////////

    public WebElement getCommentAddCommentsButton() {
        return btn_CommentAddComments;
    }

    public WebElement getCommentNextButton() {
        return btn_CommentNext;
    }

    public WebElement getCommentSaveCommentButton() {
        return btn_CommentSaveComment;
    }

    public WebElement getCommentTypeDropDown() {
        return drpdwn_CommentType;
    }

    public WebElement getCommentInputTextBox() {
        return txtbx_CommentInput;
    }

    public WebElement getMandatoryCommentsTextBox() {
        return txtbx_MandatoryComments;
    }

    public WebElement get_CommentList() {
        return CommentList;
    }
}
