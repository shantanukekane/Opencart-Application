package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	static public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups = {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		// loading properties file
		
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
	     
		// Loading log4j2 file 
		
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			// os 
			
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linex"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.print("No Matching operating System");
				return;
			}
			
			// browser 
			
		    switch(br.toLowerCase())
			{
		    
		    case "chrome":  cap.setBrowserName("chrome");break;
		    case "edge": cap.setBrowserName("edge");break;
		    default:System.out.print("No matching browsers");
		    
			}
			
		    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		
		else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			// launching the browser in local machine
			
			switch(br)
			{
			case "chrome":driver = new ChromeDriver();break;
			case "edge":driver = new EdgeDriver();break;
			default:System.out.print("No matching browser");
			return;
			}
			
		}
		
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		
	}

	@AfterClass(groups = {"sanity","regression","master"})
	void teardown()
	{
		driver.quit();
	}
	
	// This will return the Alphabetic random data
		public String randomeString()
		{
			String generatedString = RandomStringUtils.randomAlphabetic(5);
		    return generatedString;
		}
		
		// This will return the Random Numeric data
		public String randomeNumber()
		{
			String generatedString = RandomStringUtils.randomAlphanumeric(10);
			return generatedString;
		}
		
		// This will return the combination of alphabetic + Numerica random data
		public String randomeAlphaNumeric()
		{
			String str = RandomStringUtils.randomAlphabetic(5);
			String num = RandomStringUtils.randomAlphanumeric(5);
			
			return (str+"@"+num);
		}
		
		public String captureScreen(String tname)
		{
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		    
		    String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile = new File(targetFilePath);
		    
		    sourceFile.renameTo(targetFile);
		    
		    return targetFilePath;
		
		}
		
		
		
		
		
		
		
		
		
		
		
}
