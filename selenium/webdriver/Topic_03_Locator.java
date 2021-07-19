package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

public class Topic_03_Locator {
	// WebDriver la 1 interface
	// Khai báo 1 biến đại diện cho Selenium WebDrive. WebDriver là những hàm tương
	// tác với browser
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox lên
		driver = new FirefoxDriver();

		// Set timeout để tìm Element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Mở application lên (AUT = Application Under Testing/SUT = System Under
		// Testing)
		driver.get("https://live.demoguru99.com/index.php/customer/account/login/");
	}
	public void TC_001_FindElement() {
		// Muốn tương tác element (Email, Password,...) thì cần phải tìm được nó
		// Có thể dùng 2 hàm để tìm Element: Tìm 1 Element và tìm nhiều Element

		// Single element: WebElement
		// driver.findElement(By.className("")).click();
		// nếu ko thao tác trực tiếp như trên thì có thể làm như bên dưới: lưu trước
		// thao tác sau
		WebElement loginButton = driver.findElement(By.className(""));
		loginButton.click();

		// findElement: tìm Element
		// By.xxx: với locator nào
		// Action gì lên element đó: click/sendkey/getText/...

		// Multiple element: List<WebElement
		// WebElement là những hàm tương tác với element
		List<WebElement> buttons = driver.findElements(By.className(""));
		buttons.get(0).click();
	}
	public void TC_002_ID() {
		// Selenium locator
		driver.findElement(By.id("send2")).click();

		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}

	public void TC_003_Class() {
		// Refresh trang
		driver.navigate().refresh();

		driver.findElement(By.className("button")).click();

		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
		
		//cách 2: validate password
		//className của ô password:"input-text required-entry validate-password" 
		//nhưng cũng có thể lấy đoạn nào có 1 node duy nhất trong này
		driver.findElement(By.className("validate-password")).sendKeys("123456789");
	}

	public void TC_004_Name() {
		// Refresh trang
		driver.navigate().refresh();

		driver.findElement(By.name("send")).click();

		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}

	public void TC_005_Tagname() {
		/// Refresh trang
		driver.navigate().refresh();

		// Hiển thị hết tất cả đường link tại màn hình này sau đó getText ra
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));

		// show console (show all link trong console)
		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
	}

	public void TC_006_LinkText() {
		/// Refresh trang
		driver.navigate().refresh();

		// lấy hết toàn bộ chuỗi của link
		driver.findElement(By.linkText("Forgot Your Password?")).click();

		// Verify ô input nhập số dt xuất hiện
		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());
	}

	public void TC_007_PartialLinkText() {

		// lấy hết toàn bộ chuỗi của link
		driver.findElement(By.partialLinkText("Back to")).click();

		// Nếu hủy thành công, quay về màn hình login
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
	}
	
	@Test
	public void TC_008_Css() {
		//#email là id email
		driver.findElement(By.cssSelector("#email")).sendKeys("thanhtuyen291198@gmail.com");
		driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("123456789");
	}
	
	@Test
	public void TC_009_Xpath() {
		//refresh trang
		driver.navigate().refresh();
		
		//#email là id email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thanhtuyen291198@gmail.com");
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123456789");
		//cách 2: cho trường password
		//driver.findElement(By.xpath("//label[contains(text(),'Password')]/following-sibling::div/input")).sendKeys("123456789");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
