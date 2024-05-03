package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage  extends BasePage {

	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	// WebElements and Identifier
	
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement E_MailAddress;
	
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement email_password;
	
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginbtn;
	
	
	// Action methods 
	
	public void setEmailaddress(String email)
	{
		E_MailAddress.sendKeys(email);
	}
	
	public void setpassword(String pwd)
	{
		email_password.sendKeys(pwd);
	}
	
	public void submitlogin()
	{
		loginbtn.click();
	}
	

}
