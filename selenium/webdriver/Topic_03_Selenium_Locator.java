package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Selenium_Locator {
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
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_FindElement() {
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

	@Test
	public void TC_02_ID() {
		// Selenium locator
		driver.findElement(By.id("login_password_step_element")).click();

		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("login_error")).isDisplayed());
	}

	@Test
	public void TC_03_Class() {
		// Refresh trang
		driver.navigate().refresh();

		driver.findElement(By.className("_54k8 _52jh _56bs _56b_ _28lf _9cow _56bw _56bu")).click();

		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("login_error")).isDisplayed());
	}

	@Test
	public void TC_04_Name() {
		// Refresh trang
		driver.navigate().refresh();

		driver.findElement(By.name("login")).click();

		// Verify email error message xuất hiện
		Assert.assertTrue(driver.findElement(By.id("login_error")).isDisplayed());

	}

	@Test
	public void TC_05_Tagname() {
		/// Refresh trang
		driver.navigate().refresh();

		// Hiển thị hết tất cả đường link tại màn hình này sau đó getText ra
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));

		// show console (show all link trong console)
		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
	}

	@Test
	public void TC_06_LinkText() {
		/// Refresh trang
		driver.navigate().refresh();

		// lấy hết toàn bộ chuỗi của link
		driver.findElement(By.linkText("Quên mật khẩu?")).click();

		// Verify ô input nhập số dt xuất hiện
		Assert.assertTrue(driver.findElement(By.id("identify_search_text_input")).isDisplayed());
	}

	@Test
	public void TC_07_PartialLinkText() {

		// lấy hết toàn bộ chuỗi của link
		driver.findElement(By.partialLinkText("Hủy")).click();

		// Nếu hủy thành công, quay về màn hình login
		Assert.assertTrue(driver.findElement(By.id("m_login_email")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
