package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups = {"regression","master"})
	public void verify_account_registration()
	{
		
		logger.info("Starting of the TC_001_Account Registration test ******* ");
		
		logger.debug("application logs.....");
		
	  try
	  {
			
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Click on My Account link");
		
		hp.clickRegister();
		logger.info("Click on Registration link");

		logger.info("Entering the customer info -: ");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
		regpage.setTelephone(randomeNumber());
		
		String password=randomeAlphaNumeric();
		
		regpage.setPassword(password);
		
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("clicked on continue");
		String confmsg=regpage.getConfirmationMsg();
		
		logger.info("Validating Expected message..");
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			logger.info("Test Passed");
			Assert.assertTrue(true);
		}
		else
		{
		  logger.error("Test Failed");
		  Assert.fail();
		}
		
	  }
	  catch(Exception e)
	  {

		  Assert.fail();
	  }
		
		
		logger.info("Ending of the TC_001_Account Registration test ******* ");

		
	}
		
}

