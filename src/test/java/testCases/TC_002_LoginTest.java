package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest  extends BaseClass {

	
	@Test(groups = {"sanity","master"})
	public void verify_Login_Test()
	{
		
		logger.info("****** Starting of TC_002_LoginTest ******* ");
		
	  try
	  {

		// homepage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("click on My account link on the home page");
		
		hp.clickLogin();
		
		logger.info("Click on login and move to the login page ");
		
		// LoginPage
		LoginPage lp = new LoginPage(driver);
				
		logger.info("Entering the email and password to the input boxes ");
		lp.setEmailaddress(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		
		lp.submitlogin();
		logger.info("click on login submit button ");
		
		// Myaccount page
		MyAccountPage mp = new MyAccountPage(driver);
		
	    boolean	targetpage = mp.isMyAccountPageExists();
		
	    if(targetpage == true)
	    {
	        logger.info("Login Test passed");
	    	Assert.assertTrue(true);
	    }
	    else
	    {
	    	logger.error("Login Test failed");
	    	Assert.fail();
	    }
	  }
	  catch(Exception e)
	  {
		  logger.error("Test failed");
		  Assert.fail();
	  }
	  
		logger.info("Finished of TC_002_LoginTest ******* ");

	}
	
}
