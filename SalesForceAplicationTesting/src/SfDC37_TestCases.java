

//import java.util.concurrent.TimeUnit;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



//import com.relevantcodes.extentreports.LogStatus;

//import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SfDC37_TestCases extends ReusableMethodsTestNG {

	@BeforeTest
	public void Intializer() {
		System.out.println("Initializer");
		// WebDriverManager.chromedriver().setup();
		InitializeDriver();
	}

	@Test
	public void TC1_LoginErrorMessage() throws InterruptedException {
		// CreateReport();
		// logger = report.startTest("TC1_LoginErrorMessage");
		OpenUrl("https://login.salesforce.com/");
		// logger.log(LogStatus.INFO, "URL opened sucessfully");
		WebElement Uname = driver.findElement(By.id("username"));
		// Uname.sendKeys("himabindu@sbn.com");
		EnterText(Uname, "himabindu@sbn.com", "username");
		WebElement pwd = driver.findElement(By.id("password"));
		pwd.clear();
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"Login\"]"));
		Click(loginBtn, "loginbutton");
		Thread.sleep(2000);
		String errormessage = driver.findElement(By.id("error")).getText();
		String msg = "Please enter your password.";
		Assert.assertEquals(errormessage, msg, "Login error message test case failed");
	}

	@Test
	public void TC2_LoginToSalesForce() throws InterruptedException {
		// CreateReport();
		// logger = report.startTest("TC2_LoginToSalesForce");
		// logger = report.startTest("TC2_LoginToSalesForce");
		OpenUrl("https://login.salesforce.com/");
		// logger.log(LogStatus.INFO, "URL opened successfully");
		WebElement Uname = driver.findElement(By.id("username"));
		EnterText(Uname, "himabindu@sbn.com", "username");
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "nishu123@123", "password");
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"Login\"]"));
		Click(loginBtn, "loginbutton");
		Thread.sleep(2000);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle, "Login to SalesForce failed");
	}

	@Test
	public void TC3_CheckRememberMe() throws InterruptedException {
		// CreateReport();
		// logger = report.startTest("TC3_CheckRememberMe()");
		OpenUrl("https://login.salesforce.com/");
		// logger.log(LogStatus.INFO, "URL opened successfully");
		WebElement Uname = driver.findElement(By.id("username"));
		EnterText(Uname, "himabindu@sbn.com", "username");
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "nishu123@123", "password");
		Thread.sleep(2000);
		WebElement Checkbox = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[3]/label"));
		CheckButton(Checkbox, "Checkbox");
		// logger.log(LogStatus.INFO, "Remembercheck box checked");
		Thread.sleep(3000);
		WebElement loginBtn = driver.findElement(By.id("Login"));
		Click(loginBtn, "loginbutton");
		Thread.sleep(2000);
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, expectedTitle, " Salesforce home page is not displayed");
		Thread.sleep(2000);
		UserMenu();
		LogOut();
		Thread.sleep(2000);
		String userNameText = driver.findElement(By.id("username")).getAttribute("value");
		String str = "himabindu@sbn.com";
		Assert.assertEquals(userNameText, str, "UserName textbox is not displayed with username");
	}

	@Test
	public void Forgotpasword_4A() throws InterruptedException {
		// CreateReport();
		// logger = report.startTest("TC_ForgotPassword_A");
		OpenUrl("https://login.salesforce.com/");

		WebElement forgot_pwd = driver.findElement(By.xpath("//*[@id=\"forgot_password_link\"]"));
		Click(forgot_pwd, "forgot_pwd");
		// logger.log(LogStatus.PASS, "forgotpassword checked");
		Thread.sleep(2000);
		String actualTitle = "Forgot Your Password | Salesforce";
		String expectedTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, " forgot password page not displayed");
		WebElement Uname = driver.findElement(By.id("un"));
		EnterText(Uname, "himabindu@sbn.com", "username");
		Thread.sleep(2000);
		WebElement ContinueBtn = driver.findElement(By.id("continue"));
		Click(ContinueBtn, "continue");
		Thread.sleep(2000);
		String actualmsg = "Check Your Email";
		String expected = driver.findElement(By.id("header")).getText();
		Assert.assertEquals(actualmsg, expected, "email not sent");
	}

	@Test
	public void Forgotpassword_4B() throws InterruptedException {
		// CreateReport();
		// logger = report.startTest("TC_ForgotPassword");
		OpenUrl("https://login.salesforce.com/");
		WebElement Uname = driver.findElement(By.id("username"));
		EnterText(Uname, "abc@dfg", "username");
		// logger.log(LogStatus.PASS, "wrong user name entered");
		WebElement pwd = driver.findElement(By.id("password"));
		EnterText(pwd, "123", "password");
		// logger.log(LogStatus.PASS, "wrong password entered");
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"Login\"]"));
		Click(loginBtn, "loginbutton");
		String ActualError = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		String expectedError = driver.findElement(By.id("error")).getText();
		Assert.assertEquals(ActualError, expectedError, "Username and password correct");
		Thread.sleep(2000);

	}

	@Test
	public void TC_05() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		UserMenu();
		// to checck user dropdown elements
		WebElement userMenuOptn = driver.findElement(By.id("userNavMenu"));
		String menuStr = userMenuOptn.getText();
		String[] expectedArray = menuStr.split("\n");

		String str = "My Profile;My Settings;Developer Console;Switch to Lightning Experience;Logout";
		String[] ActualArray = str.split(";");
		CompareStringArrays(expectedArray, ActualArray);

		Thread.sleep(2000);
	}

	@Test
	public void TC_06() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		UserMenu();
		Thread.sleep(2000);
		WebElement userMenuOptn = driver.findElement(By.id("userNavMenu"));
		String menuStr = userMenuOptn.getText();
		String[] expectedArray = menuStr.split("\n");

		String str = "My Profile;My Settings;Developer Console;Switch to Lightning Experience;Logout";
		String[] ActualArray = str.split(";");
		CompareStringArrays(expectedArray, ActualArray);
		Thread.sleep(2000);

		WebElement myprofileBtn = driver.findElement(By.xpath("//a[@title='My Profile']"));
		Click(myprofileBtn, "myprofile ");
//		String ActualTitleProfile = driver.getTitle();
//		System.out.println(ActualTitleProfile +"123");
//		String expectedTitleProfile = "User: himabindu gundarapu ~ Salesforce - Developer Edition";
		// System.out.println();
		// Assert.assertEquals(ActualTitleProfile,expectedTitleProfile,"Profile page is
		// not displayed");
		System.out.println(" user is on my profile");
		WebElement editprofile = driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']//img"));
		Click(editprofile, "Edit ");
		Thread.sleep(2000);
		SwitchFrame("contactInfoContentId");
		WebElement AboutTab = driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		Click(AboutTab, "AboutTab ");
		WebElement lastName = driver.findElement(By.id("lastName"));
		lastName.clear();
		EnterText(lastName, "chandula", "LastName");
		WebElement SaveAll = driver.findElement(By.xpath("//input[@class='zen-btn zen-primaryBtn zen-pas']"));
		Click(SaveAll, "SaveAll");
		Thread.sleep(2000);
		String actualTitle = "User: himabindu chandula ~ Salesforce - Developer Edition";
		String expectedTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, " my profile page not displayed");
		WebElement PostLink = driver.findElement(By.id("publisherAttachTextPost"));
		Click(PostLink, "post");
		WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@class,'cke_wysiwyg_frame cke_reset')]"));
		SwitchFrame(iframe);
		System.out.println("Switch to frame");
		WebElement postBody = driver.findElement(By.xpath("//html/body[1]"));
		Click(postBody, "  postBody");
		EnterText(postBody, "Hi am hima", "post");
		Thread.sleep(10000);
		SwtitchFrame();
		WebElement Share = driver.findElement(By.id("publishersharebutton"));
		Click(Share, " Share");
		Thread.sleep(3000);
		String postText = driver.findElement(By.xpath("//div[@class='cxfeeditemtextadditional']")).getText();
		String actualText = "Hi am hima";
		Assert.assertEquals(postText, actualText);
		WebElement fileLink = driver.findElement(By.id("publisherAttachContentPost"));
		Click(fileLink, " File");
		WebElement uploadFile = driver.findElement(By.id("chatterUploadFileActionPanel"));
		Click(uploadFile, " upload a file from computer");
		WebElement chooseFile = driver.findElement(By.id("chatterFile"));
		EnterText(chooseFile, " /Users/sayanna/Downloads/Readathon.docx", "choose file");
		Thread.sleep(5000);
		WebElement ShareBtn = driver.findElement(By.id("publishersharebutton"));
		Click(ShareBtn, " Share");
		Thread.sleep(2000);
		String ExpectedText = driver
				.findElement(By.xpath("//div[@class='contentFileTitle']//a[@class='contentActionLink']")).getText();
		String ActualText = "Readathon";
		Assert.assertEquals(ActualText, ExpectedText, "file is not uploaded");
	}

	@Test
	public void TC_07() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
//			CreateReport();
		// logger = report.startTest("TC7");

		UserMenu();
		Thread.sleep(2000);
		WebElement mySettings = driver.findElement(By.xpath("//a[contains(text(),'My Settings')]"));
		Click(mySettings, "MySettings");
		WebElement Personal = driver.findElement(By.id("PersonalInfo_font"));
		Click(Personal, "Personal");
		Thread.sleep(2000);
		WebElement logInHistory = driver.findElement(By.id("LoginHistory_font"));
		Click(logInHistory, "LogInHistory");
		Thread.sleep(2000);
		String Expected = driver.findElement(By.xpath("	//h1[@class='noSecondHeader pageType'] ")).getText();
		String actual = "Login History";
		Assert.assertEquals(actual, Expected, "The login history page is not displayed");
		WebElement DownloadHistory = driver
				.findElement(By.xpath("//a[contains(text(),'Download login history for last six months, includ')]"));
		Click(DownloadHistory, "DownloadHistory");

		WebElement display = driver.findElement(By.id("DisplayAndLayout_font"));
		Click(display, "display");
		WebElement customize = driver.findElement(By.xpath("//*[@id=\"CustomizeTabs_font\"]"));
		Click(customize, "customize");
		Thread.sleep(3000);
		WebElement customApp = driver.findElement(By.id("p4"));
		Select element = new Select(customApp);
		element.selectByVisibleText("Salesforce Chatter");
		Thread.sleep(2000);
		WebElement availableTabs = driver.findElement(By.id("duel_select_0"));
		Select sel = new Select(availableTabs);
		sel.selectByVisibleText("Reports");
		WebElement add = driver.findElement(By.xpath("//*[@id=\"duel_select_0_right\"]/img"));
		Click(add, "Add");
		WebElement SaveBtn = driver.findElement(By.xpath("//input[@name='save']"));
		Click(SaveBtn, "SaveBtn");
//		WebElement SelectedTabs =driver.findElement(By.id("duel_select_1"));
//		String str = "Reports";
//		Select selectedTabList = new Select(SelectedTabs);
//		List<WebElement> list = selectedTabList.getOptions();
//		boolean foundIt=false;
//		for(WebElement e : list) {
//			if (e.getText() == str) {
//				foundIt = true;
//			}		
//			if (foundIt) { break; }
//		}

		// Assert.assertTrue(foundIt,"Selected tab is not displayed in selected tab
		// list");

		Thread.sleep(2000);
		// logger.log(LogStatus.INFO, "Report field is added and displayed on saleforce
		// charted page");
		WebElement Email = driver.findElement(By.xpath("//*[@id=\"EmailSetup\"]/a"));
		Click(Email, "Email");
		Thread.sleep(2000);
		WebElement EmailSettings = driver.findElement(By.xpath("//*[@id=\"EmailSettings_font\"]"));
		Click(EmailSettings, "EmailSettings");
		Thread.sleep(2000);
		WebElement emailName = driver.findElement(By.id("sender_name"));
		EnterText(emailName, "Salesforce", "EmailName");
		WebElement emailAddress = driver.findElement(By.id("sender_name"));
		EnterText(emailAddress, "Salesforce@123.com", " emailAddress");
		WebElement automatic = driver.findElement(By.id("auto_bcc1"));
		automatic.click();
		Thread.sleep(2000);
		WebElement save2 = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		Click(save2, " save2");
		String ExpectedMysettingPageTitle = driver.getTitle();
		String actualMysetTitle = "My Email Settings ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualMysetTitle, ExpectedMysettingPageTitle, "My Setting page is not displayed");
		Thread.sleep(2000);
		String expectedTitle = driver.getTitle();
		String ActualTitle = "My Email Settings ~ Salesforce - Developer Edition";
		Assert.assertEquals(ActualTitle, expectedTitle, "MySettings page is not dispalyed");
		WebElement CalenderReminders = driver.findElement(By.id("CalendarAndReminders_font"));
		Click(CalenderReminders, " CalenderReminders");
		WebElement ActivityReminder = driver.findElement(By.id("Reminders_font"));
		Click(ActivityReminder, " ActivityReminder");
		WebElement openTestReminder = driver.findElement(By.id("testbtn"));
		Click(openTestReminder, " openTestReminder");

//		//logger.log(LogStatus.INFO, "Given details are saved as default emailoptions and mysetting page is displayed  ");
//		WebElement CalenderReminders = driver.findElement(By.id("CalendarAndReminders_font"));
//		Click(CalenderReminders, " Calender&Reminders");
//		Thread.sleep(2000);
//		WebElement ActivityReminder = driver.findElement(By.id("Reminders_font"));
//		Click(ActivityReminder, " ActivityReminder");
//		Thread.sleep(2000);
//		WebElement OpenTestReminder = driver.findElement(By.xpath("//*[@id=\"testbtn\"]"));
//		Click(OpenTestReminder, " OpenTestReminder");
//		Thread.sleep(2000);
	}

	@Test
	public void TC_08() throws InterruptedException {
		// CreateReport();
		// logger = report.startTest("TC8");
		OpenUrl("https://login.salesforce.com/");
		login();
		UserMenu();
		Thread.sleep(2000);

		WebElement userMenuOptn = driver.findElement(By.id("userNavMenu"));
		String menuStr = userMenuOptn.getText();
		System.out.println("menuStr=" + menuStr);
		String[] expectedArray = menuStr.split("\n");
		String str = "My Profile;My Settings;Developer Console;Switch to Lightning Experience;Logout";
		String[] ActualArray = str.split(";");
		CompareStringArrays(expectedArray, ActualArray);
		WebElement devConsole = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[3]"));
		Click(devConsole, "DevloperConsole");
		Thread.sleep(2000);
//		logger.log(LogStatus.INFO, "devloper console window displayed");
		String oldwindow = driver.getWindowHandle();
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		// System.out.println(driver.getCurrentUrl());
		driver.switchTo().window(getWindow[1]);
		System.out.println(driver.getCurrentUrl());
		driver.get("https://na174.salesforce.com/setup/forcecomHomepage.apexp?setupid=ForceCom");
		driver.manage().window().maximize();

		System.out.println(driver.getCurrentUrl());
		driver.switchTo().window(oldwindow);
		driver.close();
		Thread.sleep(2000);

		// String ExpectedUrl =driver.getCurrentUrl();

//		logger.log(LogStatus.INFO, "The DeveloperConsole window is closed");
		Thread.sleep(3000);
	}

	@Test
	public void TC_09() throws InterruptedException {

		OpenUrl("https://login.salesforce.com/");
		login();
		UserMenu();
		Thread.sleep(2000);

		WebElement userMenuOptn = driver.findElement(By.id("userNavMenu"));
		String menuStr = userMenuOptn.getText();
		System.out.println("menuStr=" + menuStr);
		String[] expectedArray = menuStr.split("\n");
		String str = "My Profile;My Settings;Developer Console;Switch to Lightning Experience;Logout";
		String[] ActualArray = str.split(";");
		CompareStringArrays(expectedArray, ActualArray);
		Thread.sleep(2000);
		WebElement logOutBtn = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		Click(logOutBtn, "logout");
		// logger.log(LogStatus.INFO, "the page is log out and salesforce login page
		// displayed");
		Thread.sleep(3000);
		String ActualTitle = driver.getTitle();
		String expectedTitle = "Login | Salesforce";
		Assert.assertEquals(ActualTitle, expectedTitle, "SalesForce login page is not displayed");

	}

	@Test
	public void TC_10() throws InterruptedException, AWTException {
		OpenUrl("https://login.salesforce.com/");
		login();
		// logger = report.startTest("TC_10");
		Thread.sleep(2000);
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement Accounts = driver.findElement(By.xpath("//a[@class='listRelatedObject accountBlock title']"));
		Accounts.click();
		String actualText = driver.getTitle();
		String ExpectedText = "Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualText, ExpectedText, "Accounts home is not displayed");
		Thread.sleep(2000);
		WebElement newBtn = driver.findElement(By.xpath("//input[@name='new']"));
		Click(newBtn, "New");
		Thread.sleep(3000);
		WebElement accntName = driver.findElement(By.id("acc2"));
		EnterText(accntName, "TekArch", "Accountname");
		Thread.sleep(2000);
		WebElement typeDropDown = driver.findElement(By.xpath("//select[contains(@name,'acc6')]"));
		Select sel = new Select(typeDropDown);
		sel.selectByVisibleText("Technology Partner");
		WebElement cstmrPriority = driver.findElement(By.id("00N6g00000MR0ue"));
		Select sel1 = new Select(cstmrPriority);
		sel1.selectByVisibleText("High");
		WebElement save = driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		Click(save, "Save");
		Thread.sleep(5000);
		String actualstrng = driver.findElement(By.xpath("//h2[@class='topName']")).getText();
		String str = "TekArch";
		Assert.assertEquals(actualstrng, str, "New Account page is not displ;ayed");

		driver.close();
	}

	@Test
	public void TC_11CreateNewView() throws InterruptedException {

		OpenUrl("https://login.salesforce.com/");
		login();
		// logger = report.startTest("TC_10");
		Thread.sleep(2000);
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement Accounts = driver.findElement(By.xpath("//a[@class='listRelatedObject accountBlock title']"));
		Accounts.click();
		String actualText = driver.getTitle();
		String ExpectedText = "Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualText, ExpectedText, "Accounts home is not displayed");
		// logger.log(LogStatus.INFO, "Accounts home page is dispalyed with username");
		WebElement createNewViewBtn = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		createNewViewBtn.click();
		WebElement viewName = driver.findElement(By.id("fname"));
		EnterText(viewName, "Ghbsbn43215", "viewname");
		WebElement viewUniqueName = driver.findElement(By.id("devname"));
		viewUniqueName.click();
		WebElement saveBtn = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]"));
		Click(saveBtn, "Save");
		WebElement viewlist = driver.findElement(By.xpath("//select[@name='fcf']"));
		Select viewelement = new Select(viewlist);
		String str = viewelement.getFirstSelectedOption().getText();
		System.out.println(str);
		String expected = "Ghbsbn43215";
		Assert.assertEquals(str, expected, "view drop down is displayed with new view name");
		WebElement deleteView = driver.findElement(By.xpath("//a[contains(text(),'Delete')]"));
		deleteView.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
//
		Thread.sleep(2000);
	}

	@Test
	public void TC_12EditView() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		Thread.sleep(2000);
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement Accounts = driver.findElement(By.xpath("//a[@class='listRelatedObject accountBlock title']"));
		Accounts.click();
		String actualText = driver.getTitle();
		String ExpectedText = "Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualText, ExpectedText, "Accounts home is not displayed");
		// logger.log(LogStatus.INFO, "Accounts home page is dispalyed with username");
		WebElement viewmenu = driver.findElement(By.xpath("//select[@id='fcf']"));
		Select sel = new Select(viewmenu);
		List<WebElement> Dropdownlist = sel.getOptions();
		int size = Dropdownlist.size();
		Random r = new Random();
		int x = r.nextInt(size - 1);
		String selectedOption = Dropdownlist.get(x).getText();
		System.out.println(selectedOption);
		WebElement editBtn = driver.findElement(By.xpath("//span[@class='fFooter']//a[contains(text(),'Edit')]"));
		Click(editBtn, "Edit");
		Thread.sleep(2000);
		String actual = driver.getTitle();
		String expected = "Accounts: Edit View ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, " EditView page not Displayed");
		WebElement viewName = driver.findElement(By.xpath("//input[@id='fname']"));
		viewName.clear();
		EnterText(viewName, "mostvisited1", "viewname");
		WebElement viewUniqueName = driver.findElement(By.xpath("//input[@id='devname']"));
		viewUniqueName.clear();
		EnterText(viewUniqueName, "mostvisited1", "viewUniqueName");

		// viewUniqueName.click();
		WebElement field = driver.findElement(By.xpath("//*[@id=\"fcol1\"]"));
		Select sel2 = new Select(field);
		sel2.selectByVisibleText("Account Name");
		Select sel3 = new Select(driver.findElement(By.id("fop1")));
		sel3.selectByVisibleText("contains");
		WebElement value = driver.findElement(By.id("fval1"));
		EnterText(value, "a", "Value");
		WebElement saveBtn = driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]"));
		Click(saveBtn, "save");
		WebElement viewlist = driver.findElement(By.id("00B6g00000Bmm1Z_listSelect"));
		Select viewelement = new Select(viewlist);
		String str = viewelement.getFirstSelectedOption().getText();
		String ActualStr = "mostvisited1";
		System.out.println(str);
		Assert.assertEquals(ActualStr, str, "Edit view page is not displayed with new created view");
		WebElement deleteView = driver.findElement(By.xpath("//a[contains(text(),'Delete')]"));
		deleteView.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
	}

	@Test
	public void TC_13() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		Thread.sleep(2000);
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement Accounts = driver.findElement(By.xpath("//a[@class='listRelatedObject accountBlock title']"));
		Accounts.click();
		String actualText = driver.getTitle();
		String ExpectedText = "Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualText, ExpectedText, "Accounts home is not displayed");
		WebElement tools = driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/h3"));
		Click(tools, "Tools");
		Thread.sleep(2000);
		WebElement mergeAccnts = driver
				.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a"));
		Click(mergeAccnts, "MergeAccounts");
		WebElement textBox = driver.findElement(By.id("srch"));
		EnterText(textBox, "tecArch", "TextBox");
		WebElement find = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[4]/input[2]"));
		Click(find, "FindAccounts");
		Thread.sleep(2000);
	}

	@Test
	public void TC_14() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		Thread.sleep(2000);
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement Accounts = driver.findElement(By.xpath("//a[@class='listRelatedObject accountBlock title']"));
		Accounts.click();
		String actualText = driver.getTitle();
		String ExpectedText = "Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualText, ExpectedText, "Accounts home is not displayed");
		// logger.log(LogStatus.INFO, "Accounts home page is dispalyed with username");
		WebElement activity = driver
				.findElement(By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]"));
		Click(activity, "accountactivity<30days");
		Thread.sleep(2000);
		WebElement DateField = driver.findElement(By.xpath("//input[@id='ext-gen20']"));
		DateField.click();
		WebElement CreatedDate = driver.findElement(By.xpath("//div[contains(text(),'Created Date')]"));
		CreatedDate.click();
//		Select sel = new Select(DateField);
//		sel.selectByVisibleText("Created Date");
		WebElement todaysDateFrom = driver.findElement(By.xpath("//img[@id='ext-gen152']"));
		Click(todaysDateFrom, "From");
		WebElement todayDate = driver.findElement(By.xpath("//button[contains(text(),'Today')]"));
		todayDate.click();
		WebElement to = driver.findElement(By.id("ext-gen154"));
		to.click();
		Thread.sleep(2000);
		WebElement todayFromTo = driver.findElement(
				By.xpath("//img[contains(@class,'x-form-trigger x-form-date-trigger x-form-trigger-click')]"));
		todayFromTo.click();
		WebElement saveBtn = driver.findElement(By.xpath("//*[@id=\"ext-gen49\"]']"));
		saveBtn.click();
		Thread.sleep(2000);
	}

	@Test
	public void TC_15() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement opportunities = driver
				.findElement(By.xpath("//a[@class='listRelatedObject opportunityBlock title']"));
		Click(opportunities, "opportunities");
		String actual = driver.getTitle();
		String expected = "Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "Opportunities page not displayed");
		WebElement homeOpprt = driver.findElement(By.id("fcf"));
		Assert.assertTrue((homeOpprt != null) && (homeOpprt.isDisplayed()), "Home Opportunities not displayed");
		String str = homeOpprt.getText();
		// Select dropDownElements = new Select(homeOpprt);
		// List<WebElement> listofElements = dropDownElements.getOptions();
		String[] expectedArray = str.split("\n");
		String OpprtDrowDownList = "All Opportunities;Closing Next Month;Closing This Month;My Opportunities;New Last Week;New This Week;Opportunity Pipeline;Private;Recently Viewed Opportunities;Won";
		String[] actualArray = OpprtDrowDownList.split(";");
		for (String s : actualArray) {
			System.out.println(s);
		}
		CompareStringArrays(expectedArray, actualArray);

	}
	@Test
	public void TC_16() throws InterruptedException {
		
		OpenUrl("https://login.salesforce.com/");
		login();
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement opportunities = driver.findElement(By.xpath("//a[@class='listRelatedObject opportunityBlock title']"));
		Click(opportunities, "opportunities");
		String actual = driver.getTitle();
		String expected = "Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "Opportunities page not displayed");
		WebElement newBtn = driver.findElement(By.xpath("//input[@name='new']"));
		Click(newBtn, "New");
		Thread.sleep(2000);
		
		System.out.println("NEW BUTTON CLICKED");
		WebElement opportunityName = driver.findElement(By.xpath("//input[@name='opp3']"));
		EnterText(opportunityName, "tecArch2021", "opportunityName");
		WebElement accName = driver.findElement(By.xpath("//input[@name='opp4']"));
		WebElement AccountSearch = driver.findElement(By.xpath("//td[@class='dataCol col02']//img[@class='lookupIcon']"));
		Click(AccountSearch, "AccountName");
		/*To go to pop up window */
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		// System.out.println(driver.getCurrentUrl());
		driver.switchTo().window(getWindow[1]);
		System.out.println(driver.getCurrentUrl());
		//driver.get("Url");
		driver.manage().window().maximize();
//		WebElement Text = driver.findElement(By.id("lksrch"));
//		EnterText(Text, "tecArch", "Text");
		WebElement GoBtn = driver.findElement(By.xpath("//input[@name='go']"));
		Click( GoBtn, " Go");
		driver.close();

		System.out.println(driver.getCurrentUrl());
		driver.switchTo().defaultContent();

		
//		EnterText(accName, "tecArch", "AccountName");
//		WebElement closeDate = driver.findElement(By.xpath("//input[@name='opp9']"));
//		EnterText(closeDate, "2/19/2020", "closeDate");
//		// Click(closeDate,"closeDate");
//		WebElement stage = driver.findElement(By.xpath("//select[@name='opp11']"));
//		Select sel = new Select(stage);
//		sel.selectByVisibleText("Closed Won");
//		List<WebElement> list = sel.getOptions();
//		for (WebElement e : list) {
//			System.out.println(e.getText());
//		}
////		WebElement probability = driver.findElement(By.xpath("//input[@name='opp12']"));
////		EnterText( probability, "60", " probability");
//		WebElement lead = driver.findElement(By.xpath("//select[@name='opp6']"));
//		Select leadElement = new Select(lead);
//		leadElement.selectByVisibleText("Web");
//		WebElement primaryCampaign = driver.findElement(By.xpath("//input[@name='opp17']"));
//		EnterText(primaryCampaign, "marketing", " primaryCampaign");
//		WebElement save = driver.findElement(By.xpath("//td[@class='pbButtonb']//input[@name='save']"));
//		Click(save, "save");
//		String str = driver.findElement(By.xpath("//h2[@class='pageDescription']")).getText();
//
//		if (str.contains("tecArch2020")) {
//			logger.log(LogStatus.PASS, "New opportunities page displayed with opportunity details");
//		} else {
//			logger.log(LogStatus.FAIL, "New opportunities page not displayed with opportunity details");
//		}
//
//		driver.close();

	}
	@Test
	public void TC_17() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement opportunities = driver.findElement(By.xpath("//a[@class='listRelatedObject opportunityBlock title']"));
		Click(opportunities, "opportunities");
		String actual = driver.getTitle();
		String expected = "Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "Opportunities page not displayed");
		Thread.sleep(3000);
		//logger.log(LogStatus.INFO, "Opportunities home page dispalyed");
		WebElement optyPipeline = driver.findElement(By.xpath("//a[contains(text(),'Opportunity Pipeline')]"));
		Click(optyPipeline, "optyPipeline");
		Thread.sleep(2000);
		String actualstr = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
		String expectedStr = "Opportunity Pipeline";
		Assert.assertEquals(actualstr, expectedStr,"Opportunity pipeline page displayed with Report");
		}
	@Test
	public void TC_18() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement opportunities = driver.findElement(By.xpath("//a[@class='listRelatedObject opportunityBlock title']"));
		Click(opportunities, "opportunities");
		String actual = driver.getTitle();
		String expected = "Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "Opportunities page not displayed");
		Thread.sleep(3000);
		//logger.log(LogStatus.INFO, "Opportunities home page dispalyed");
		WebElement stuckOpty = driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
		Click(stuckOpty, "StuckOpportunities");
		Thread.sleep(2000);
		String Actualstr = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']")).getText();
		String Expected = "Stuck Opportunities";
		Assert.assertEquals(Actualstr, Expected,"Report page with the Opportunities are stuck will not displayed");
		}
	@Test
	public  void TC_19() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement opportunities = driver.findElement(By.xpath("//a[@class='listRelatedObject opportunityBlock title']"));
		Click(opportunities, "opportunities");
		String actual = driver.getTitle();
		String expected = "Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "Opportunities page not displayed");
		Thread.sleep(3000);
		WebElement QtrSummeryInterval = driver.findElement(By.xpath("//select[@id='quarter_q']"));
		Select listOfIntervals = new Select(QtrSummeryInterval);
		List<WebElement> DropDownList = listOfIntervals.getOptions();
		Random r = new Random();
		int sz = DropDownList.size();
		int boundRandomNum = r.nextInt(sz);
		String str = DropDownList.get(boundRandomNum).getText();
		listOfIntervals.selectByVisibleText(str);
		Thread.sleep(3000);
		System.out.println(str);
		WebElement QtrSummeryInclude = driver.findElement(By.id("open"));
		Select IncludeList = new Select(QtrSummeryInclude );
		
		List<WebElement> DropdownInclude = IncludeList.getOptions();
		
		System.out.println("DropdownInclude: ");
		for (WebElement e: DropdownInclude) {
			System.out.println(e.getText());
		}
		
		int size = DropdownInclude.size();
		int boundRandomNum2= r.nextInt(size);
		System.out.println("boundRandomNum2="+boundRandomNum2);
		String str1 = DropdownInclude.get(boundRandomNum2).getText();
		System.out.println(str1);
		IncludeList .selectByVisibleText(str1);
		Thread.sleep(2000);
		WebElement RunReportBtn = driver.findElement(By.xpath("//input[@title='Run Report']"));
		Click(RunReportBtn, "RunReport");
		String Actual = driver.getTitle();
		String expectedTitle = "Opportunity Report ~ Salesforce - Developer Edition";
		Assert.assertEquals(Actual, expectedTitle);
	}
	@Test
	public  void TC_20LeadTab() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		//logger = report.startTest("TC_20LeadTab");
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement Lead = driver.findElement(By.xpath("//a[@class='listRelatedObject leadBlock title']"));
		Click(Lead, " Lead");
		Thread.sleep(2000);
		
		String actualTitle = driver.findElement(By.xpath("//h1[@class='pageType']")).getText();
		String expected ="Leads";
		Assert.assertEquals(actualTitle, expected,"Leads home page is displayed");
//		if (actualTitle.contains("Leads")) {
//			logger.log(LogStatus.PASS, "Leads home page displayed");
//		} else {
//			logger.log(LogStatus.PASS, "Leads home page not  displayed");
//		}
	}
	@Test
	public void TC_21LeadsSelectView() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		//logger = report.startTest("TC_20LeadTab");
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement Lead = driver.findElement(By.xpath("//a[@class='listRelatedObject leadBlock title']"));
		Click(Lead, " Lead");
		Thread.sleep(2000);	
		String actualTitle = driver.findElement(By.xpath("//h1[@class='pageType']")).getText();
		String expected ="Leads";
		Assert.assertEquals(actualTitle, expected,"Leads home page is displayed");
		WebElement LeadList = driver.findElement(By.xpath("//select[@id='fcf']"));
		String str1 = LeadList.getText();
		String[] ExpectedArray =str1.split("\n");
		//Select DropDownList = new Select(LeadList);
		//List<WebElement> DDList = DropDownList.getOptions();
		String str = "All Open Leads;My Unread Leads;Recently Viewed Leads;Today's Leads;View - Custom 1;View - Custom 2";
		String[] ActualArray= str.split(";");
		CompareStringArrays(ExpectedArray,ActualArray);
	
//		for (WebElement e : DDList) {
//			System.out.println(e.getText());
//		}
//		logger.log(LogStatus.INFO, "Leads list dropdown displayed");

	}
	@Test
	public void TC_22DefaultView() throws InterruptedException {
		OpenUrl("https://login.salesforce.com/");
		login();
		//logger = report.startTest("TC_20LeadTab");
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement Lead = driver.findElement(By.xpath("//a[@class='listRelatedObject leadBlock title']"));
		Click(Lead, " Lead");
		String actualTitle = driver.findElement(By.xpath("//h1[@class='pageType']")).getText();
		String expected ="Leads";
		Assert.assertEquals(actualTitle, expected,"Leads home page is displayed");
		WebElement LeadList = driver.findElement(By.xpath("//select[@id='fcf']"));
		Select DropDownList = new Select(LeadList);
		DropDownList.selectByVisibleText("Today's Leads");
		Thread.sleep(2000);
		String expectedstr = DropDownList.getFirstSelectedOption().getText();
		String actual = "Today's Leads";
		Assert.assertEquals(actual, expectedstr,"DropDown not dispalyed with selected value");
		UserMenu();
		WebElement LogOut = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		Click(LogOut, "LogOut");
		Thread.sleep(2000);
		String ActualTitle = driver.getTitle();
		System.out.println(ActualTitle);
		String expectedTitle = "Login | Salesforce";
		Assert.assertEquals(ActualTitle, expectedTitle, "SalesForce login page is not displayed");
		//logger.log(LogStatus.INFO, "logged out");
		login();
		WebElement addTabs1 = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs1, "AddTab");
		Thread.sleep(2000);
		WebElement Lead1 = driver.findElement(By.xpath("//a[@class='listRelatedObject leadBlock title']"));
		Click(Lead1, " Lead");
		Thread.sleep(2000);
		//Check dropdown default value
		WebElement LeadList1 = driver.findElement(By.xpath("//select[@id='fcf']"));
		Select DropDownList1 = new Select(LeadList1);
		String expectedstr1 = DropDownList1.getFirstSelectedOption().getText();
		System.out.println(expectedstr1);
		String actual1 = "Today's Leads";
		Assert.assertEquals(actual1, expectedstr1,"DropDown not dispalyed with selected value");
		
		WebElement GoBtn = driver.findElement(By.xpath("//span[@class='fBody']//input[@name='go']"));
		Click(GoBtn, "GoBtn");
		Thread.sleep(2000);
		// WebElement LeadList1 = driver.findElement(By.xpath("//select[@id='fcf']"));
//			
//		Select DropDownList1 = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));
//
//		String str = DropDownList1.getFirstSelectedOption().getText();
//		Thread.sleep(2000);
//		if (str.equals("Today's Leads")) {
//			logger.log(LogStatus.INFO, "The lead view displayed with the selcted view from last session");
//		} else {
//			logger.log(LogStatus.INFO, "The lead view didnot display selcted view from last session");
//		}
		String LeadActualTitle = "Leads ~ Salesforce - Developer Edition";
		String ExpectedTitleLeads = driver.getTitle();
		Assert.assertEquals(LeadActualTitle, ExpectedTitleLeads,"Leads view page not displayed");
		
//		if (driver.getCurrentUrl().equalsIgnoreCase(LeadUrl)) {
//			logger.log(LogStatus.PASS, "Todays Lead page is displayed");
//			System.out.println("Todays Lead page is displayed");
//		} else {
//			logger.log(LogStatus.PASS, "Todays Lead page is not displayed");
//			System.out.println("Todays Lead page is not displayed");
//		}
//
		Thread.sleep(2000);

	}
	@Test
	public void TC_23() throws InterruptedException {
		//logger = report.startTest("TC_23");
		OpenUrl("https://login.salesforce.com/");
		login();
		WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
		Click(addTabs, "AddTab");
		Thread.sleep(2000);
		WebElement Lead = driver.findElement(By.xpath("//a[@class='listRelatedObject leadBlock title']"));
		Click(Lead, " Lead");
		WebElement LeadList = driver.findElement(By.xpath("//select[@id='fcf']"));
		Select DropDownList = new Select(LeadList);
		DropDownList.selectByVisibleText("Today's Leads");
		WebElement GoBtn = driver.findElement(By.xpath("//span[@class='fBody']//input[@name='go']"));
		Click(GoBtn, "GoBtn");
		String ActualLeadTitle =driver.getTitle();
		String ExpectedTtile = "Leads ~ Salesforce - Developer Edition";
		Assert.assertEquals(ActualLeadTitle, ExpectedTtile,"Today's lead page not dispalyed");

//		if (driver.getCurrentUrl().equalsIgnoreCase(LeadUrl)) {
//			logger.log(LogStatus.PASS, "Todays Lead page is displayed");
//			System.out.println("Todays Lead page is displayed");
//		} else {
//			logger.log(LogStatus.PASS, "Todays Lead page is not displayed");
//			System.out.println("Todays Lead page is not displayed");
//		}
		Thread.sleep(2000);
	}
@Test
public  void TC_24() throws InterruptedException {
	//logger = report.startTest("TC_24");
	OpenUrl("https://login.salesforce.com/");
	login();
	WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
	Click(addTabs, "AddTab");
	Thread.sleep(2000);
	WebElement Lead = driver.findElement(By.xpath("//a[@class='listRelatedObject leadBlock title']"));
	Click(Lead, " Lead");
	WebElement newBtn = driver.findElement(By.xpath("//input[@name='new']"));
	Click(newBtn, "New");
	Thread.sleep(2000);
	WebElement lastName = driver.findElement(By.xpath("//input[@id='name_lastlea2']"));
	EnterText(lastName, "ABCD", "lastname");
	WebElement companyName = driver.findElement(By.xpath("//input[@id='lea3']"));
	EnterText(companyName, "ABCD", "companyName");
	WebElement save = driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save']"));
	Click(save, "save");
	String Expectedstr = "Lead: ABCD ~ Salesforce - Developer Edition";
	String actual = driver.getTitle();
	Assert.assertEquals(actual,Expectedstr,"Newly created Lead page not displayed");
	
//	if (driver.getCurrentUrl().equalsIgnoreCase(str)) {
//		logger.log(LogStatus.PASS, "Created Lead view page opened");
//	} else {
//		logger.log(LogStatus.PASS, "Created Lead view page not opened");
//	}
//	Thread.sleep(2000);

}
@Test
public void TC_25() throws InterruptedException {
	//logger = report.startTest("TC_24");
	OpenUrl("https://login.salesforce.com/");
	login();
	WebElement addTabs = driver.findElement(By.xpath("//img[@class='allTabsArrow']"));
	Click(addTabs, "AddTab");
	Thread.sleep(2000);
	WebElement contacts = driver.findElement(By.xpath("//a[@class='listRelatedObject contactBlock title']"));
	Click(contacts, "contacts");
	Thread.sleep(2000);
	String Actual = driver.getTitle();
	String Expected = "Contacts: Home ~ Salesforce - Developer Edition";
	Assert.assertEquals( Actual, Expected,"Contacts home page is displayed");
	//logger.log(LogStatus.INFO, "Contact home page displayed");
	WebElement newBtn = driver.findElement(By.xpath("//input[@name='new']"));
	Click(newBtn, "New");
	String Actual1=driver.getTitle();
	String Expected1 = "Contact Edit: New Contact ~ Salesforce - Developer Edition";
	Assert.assertEquals(Actual1, Expected1);
	WebElement lastname = driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
	EnterText(lastname, "macheal", "lastname");
	WebElement AccountName = driver.findElement(By.xpath("//input[@id='con4']"));
	EnterText(AccountName, "Foster123", " AccountName");
	WebElement searchImage = driver.findElement(By.xpath("//a[@id='con4_lkwgt']//img[@class='lookupIcon']"));
	Click(searchImage, "searchImage");
	String oldwindow = driver.getWindowHandle();
	Set<String> getAllWindows = driver.getWindowHandles();
	String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
	// System.out.println(driver.getCurrentUrl());
	driver.switchTo().window(getWindow[1]);
	System.out.println(driver.getCurrentUrl());
	driver.get("https://na174.salesforce.com/_ui/common/data/LookupPage?lkfm=editPage&lknm=con4&lktp=001&lksrch");
	driver.manage().window().maximize();
	Thread.sleep(2000);
//	WebElement searchtext = driver.findElement(By.id("lksrch"));
//	EnterText(searchtext, "Foster1234", "searchtext");
	WebElement Newbtn = driver.findElement(By.xpath("//input[@name='new']"));
	Newbtn.click();
	System.out.println(driver.getCurrentUrl());
	driver.switchTo().window(oldwindow);

}



	@AfterTest
	public void quitBrowser() {
		CloseBrowser();
	}
}

