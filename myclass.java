import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;



public class myclass {
	
	

	
	WebDriver driver = new ChromeDriver();
	
	 String mywebsite = "https://magento.softwaretestingboard.com/";
	 String logoutpage = "https://magento.softwaretestingboard.com/customer/account/logout/";
	 Random rand = new Random();
	 String password = "iLoveMyMom!234k";


		String emailAddressToLogin = "";
		@BeforeTest
		public void mySetup() {
			driver.manage().window().maximize();
			driver.get(mywebsite);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); 

		}

		@Test(priority = 1, enabled = true)
		public void CreateAnAccount() {

			// xpath
//			WebElement createAccountPage = driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']"));

			// partialLinkText

//			WebElement createAccountPage = driver.findElement(By.partialLinkText("Account"));

			// linkText
//			WebElement createAccountPage = driver.findElement(By.linkText("Create an Account"));
	//

			WebElement createAccountPage = driver
					.findElement(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)"));
			createAccountPage.click();

			// example
//			String[] thearrayNameforExampleFirstNames = { "firstname", "firstname2", "firstname3" };

			// first names
			String[] first_Names = { "Alice", "Bob", "Charlie", "David", "Eve", "Fay", "Grace" };
			// last names
			String[] Last_Names = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia" };

			int randomIndexForTheFirstName = rand.nextInt(first_Names.length);
			int randomIndexForTheLastName = rand.nextInt(Last_Names.length);

			System.out.println(randomIndexForTheFirstName);
			System.out.println(randomIndexForTheLastName);

			WebElement firstNameInput = driver.findElement(By.id("firstname"));
			WebElement lastNameInput = driver.findElement(By.id("lastname"));
			WebElement emailAddressInput = driver.findElement(By.id("email_address"));
			WebElement passwordInput = driver.findElement(By.id("password"));
			WebElement confirmPassword = driver.findElement(By.id("password-confirmation"));
			WebElement createAccountButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));
			String firstname = first_Names[randomIndexForTheFirstName];

			String lastname = Last_Names[randomIndexForTheLastName];

			
			System.out.println(lastname);
			int randomnumber = rand.nextInt(9876);

			String domainName = "@gmail.com";

			firstNameInput.sendKeys(firstname);
			lastNameInput.sendKeys(lastname);
			emailAddressInput.sendKeys(firstname + lastname + randomnumber + domainName);
			passwordInput.sendKeys(password);
			confirmPassword.sendKeys(password);
			createAccountButton.click();
			emailAddressToLogin = firstname + lastname + randomnumber + domainName;
			
			WebElement MessageAsWebElement = driver.findElement(By.className("messages"));
			
			String TheActualMessage = MessageAsWebElement.getText(); 
			
			String ExpectedMessage = "Thank you for registering with Main Website Store.";
			
		
			Assert.assertSame(TheActualMessage, ExpectedMessage);

		}

		@Test(priority = 2)
		public void logOut() {
			driver.get(logoutpage);
			WebElement LogoutMessage = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
			
			String ActualMsg = LogoutMessage.getText();
			String ExpectedMsg = "You are signed out"; 
			
			Assert.assertEquals(ActualMsg, ExpectedMsg);

		}

		@Test(priority = 3 )
		public void loginTest() {
			WebElement LoginPage = driver.findElement(By.linkText("Sign In"));
			LoginPage.click();

			WebElement EmailLoginInput = driver.findElement(By.id("email"));
			WebElement passwordInput = driver.findElement(By.id("pass"));
			WebElement LoginButton = driver.findElement(By.cssSelector(".action.login.primary"));

			EmailLoginInput.sendKeys(emailAddressToLogin);
			passwordInput.sendKeys(password);
			LoginButton.click();
			
			String WelcomeMessage = driver.findElement(By.className("logged-in")).getText();
			
			boolean ActualValue = WelcomeMessage.contains("Welcome");
			boolean ExpectedValue = true ; 
			
			Assert.assertEquals(ActualValue, ExpectedValue);
		}

		@Test(priority = 4)

		public void addMenItem() throws InterruptedException {
			WebElement MenSection = driver.findElement(By.id("ui-id-5"));
			
			MenSection.click();
			
			; 
			
//			System.out.println(driver.findElements(By.className("product-item")).size());
			
			WebElement productITemsContainer = driver.findElement(By.className("product-items"));
			
//			System.out.println(productITemsContainer.findElements(By.className("product-item")).size());;
//			
//			; 
			
//			System.out.println(driver.findElements(By.tagName("li")).size());
			
			List<WebElement> AllItems = productITemsContainer.findElements(By.tagName("li"));
			
	int totalNumberOfItems = AllItems.size(); 
	int randomItem = rand.nextInt(totalNumberOfItems); 

	AllItems.get(randomItem).click();;

	WebElement theContainerOfSizes = driver.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));

	;

	String [] sizes = {"33","34","36","37"};
	// select any one for me i will select the first one 
	//System.out.println(theContainerOfSizes.findElements(By.className("swatch-option")).size());
	//System.out.println(theContainerOfSizes.findElements(By.tagName("div")).size());
	List<WebElement> ListOfSizes =theContainerOfSizes.findElements(By.className("swatch-option"));
	int numberofSizes = ListOfSizes.size();

	

}
		@Test(priority = 5)

		public void addWomanItem() throws InterruptedException{
			
			WebElement WomanSection = driver.findElement(By.id("ui-id-4")); 
			
			WomanSection.click();
			
			WebElement productITemsContainer = driver.findElement(By.className("product-items"));
			List<WebElement> AllItems = productITemsContainer.findElements(By.tagName("li"));
			
			int totalNumberOfItems = AllItems.size(); 
			int randomItem = rand.nextInt(totalNumberOfItems); 

			AllItems.get(randomItem).click();
			
			WebElement Review = driver.findElement(By.id("tab-label-reviews-title"));
			Review.click();
			WebElement NickName = driver.findElement(By.id("nickname_field"));
			NickName.sendKeys("Anas");
			NickName.click();
			
			WebElement Summary = driver.findElement(By.id("summary_field"));
			Summary.sendKeys("I love it ");
			
			
			WebElement Submite = driver.findElement(By.tagName("div"));
			Submite.click();
			
			
			
			
			
			
		}
}
