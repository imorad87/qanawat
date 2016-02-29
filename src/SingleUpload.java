import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import net.sourceforge.htmlunit.corejs.javascript.tools.shell.JSConsole;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SingleUpload {

	// ------------- CAT 1 start ------------------//
	private static final String BOLLYWOOD_SONGS = "selectItem(MainList,\"Bollywood Songs\",\"20021\",fatherdirname,fatherdirindex,\"\")";
	private static final String WORLD_MUSIC = "selectItem(MainList,\"World Music\",\"20022\",fatherdirname,fatherdirindex,\"\")";
	
	// ------------- CAT 1 end -------------------//

	

	// ------------- DAM CAT 1 start ------------------//
	private static final String DAM_WORLD_MUSIC = "World Music";
	private static final String DAM_BOLLYWOOD_SONGS = "Bollywood Songs";

	// ------------- DAM CAT 1 end ------------------//

	

	private static FirefoxDriver driver;

	public static final String URL = "http://61.5.195.27/sp";
	public static final String USERNAME_TAGE = "username";
	public static final String PASSWORD_TAGE = "password";
	public static final String USER_NAME = "601014";
	public static final String PASSWORD = "4WcCQana4at";

	public static void main(String[] args) throws IOException,
			InterruptedException {

		/*
		 * System.setProperty("webdriver.ie.driver",
		 * "C:\\Users\\islam.morad\\Desktop\\IEDriverServer.exe");
		 * 
		 * DesiredCapabilities cap = new DesiredCapabilities();
		 * cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		 * cap.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,
		 * "https://secure.nedjma.dz");
		 */
		driver = new FirefoxDriver();

		login(URL, USER_NAME, PASSWORD);
		upload();
	}

	private static boolean login(String url, String userName, String password)
			throws InterruptedException {
		driver.get(url);

		Thread.sleep(10000);
		WebElement element = driver.findElement(By.name(USERNAME_TAGE));
		element.sendKeys(userName);
		element = driver.findElement(By.name(PASSWORD_TAGE));
		element.sendKeys(password);

		try {
			Thread.sleep(28000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * WebElement login = driver.findElement(By.name("btnSubmit"));
		 * login.click();
		 */

		WebElement interface1 = driver
				.findElement(By
						.cssSelector("#webSystemDiv > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(1)"));
		interface1.click();

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.navigate()
				.to("http://61.5.195.27/sp/uploadtone.screen");
		
		return false;

	}

	private static void upload() throws InterruptedException, IOException {

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream("input.csv"),
						StandardCharsets.UTF_8));
		Scanner s = new Scanner(bufferedReader);

		FileOutputStream out = new FileOutputStream("uploading status.txt");
		OutputStreamWriter r = new OutputStreamWriter(out);

		String artist = null;
		String title = null;
		String cat1 = null;
		String gender = null;
		String path10 = null;

		while (s.hasNext()) {
			String line = s.nextLine();
			StringTokenizer to = new StringTokenizer(line, ",");

			title = to.nextToken();
			artist = to.nextToken();
			cat1 = to.nextToken();
			gender = to.nextToken();
			path10 = to.nextToken();
			

			WebElement titleField = driver.findElementById("toneName");
			titleField.sendKeys(title);

			driver.findElementById("singerName").sendKeys(artist);
			
			selectSex(gender);
			
			selectCat1(cat1);
						
			setDate();

			driver.findElementById("webToneFileName").sendKeys(path10);
			driver.findElementById("aipToneFileName").sendKeys(path10);
			
			/*driver.findElementById("ivrPromptToneFileName").sendKeys(path10);
			driver.findElementByCssSelector(
					"tr.sptr2:nth-child(16) > td:nth-child(2) > input:nth-child(1)")
					.sendKeys(path10);
			new Select(driver.findElement(By.id("relativelyTime")))
					.selectByVisibleText("60");

			FileInputStream in = new FileInputStream(
					"C:\\Users\\islam.morad\\Desktop\\script.js");

			Scanner ss = new Scanner(in);

			StringBuilder x = new StringBuilder();
			while (ss.hasNext()) {
				String next = ss.nextLine();
				x.append(next);
				x.append(System.lineSeparator());

			}

			js.executeScript(x.toString());
			System.out.println();
			// ((JavascriptExecutor)
			// driver).executeScript("javascript:checkForm()");
*/
			driver.findElementByCssSelector("#okLink > a:nth-child(1)").click();
			//Thread.sleep(15000);

			// driver.findElementByCssSelector("#okLink > a:nth-child(1)").click();

			String text = new WebDriverWait(driver, 150)
					.until(ExpectedConditions.visibilityOfElementLocated(By
							.cssSelector(".sptd1"))).getText().trim();

			if ("Success prompt : : :".equalsIgnoreCase(text)) {
				r.append(artist + " " + title + "done");
				r.append(System.lineSeparator());
				r.flush();
			} else {
				r.append(artist + " " + title + "failed, " + text);
				r.append(System.lineSeparator());
				r.flush();
			}

			driver.navigate()
					.to("http://61.5.195.27/sp/uploadtone.screen");

		}

		r.close();

	}

	private static void selectCat1(String cat1) {

		switch (cat1) {
		case DAM_BOLLYWOOD_SONGS:
			

			((JavascriptExecutor) driver).executeScript(BOLLYWOOD_SONGS);
			break;

		case DAM_WORLD_MUSIC:
			

			((JavascriptExecutor) driver).executeScript(WORLD_MUSIC);
			break;

		
		default:
			JOptionPane.showMessageDialog(null,
					"Cat is not identified. Check cat spelling.");
		}
	}

	

	public static void switchwindow(String data) {
		try {

			String winHandleBefore = driver.getWindowHandle();

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(data);
			}
		} catch (Exception e) {
			/*
			 * return Constants.KEYWORD_FAIL + "Unable to Switch Window" +
			 * e.getMessage();
			 */
		}
		// return Constants.KEYWORD_PASS;
	}

	private static void setDate() {
		
		
		((JavascriptExecutor) driver).executeScript("dateField = document.getElementById('formattedtoneValidDay'); r = d.getAttribute('readonly'); r = false; dateField.value = '12/31/2020'; ");

		
		
		
		/*driver.findElementById("calimg1").click();

		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				new Select(driver.findElement(By
						.cssSelector("select.cpYearNavigation")))
						.selectByVisibleText("2025");

				new Select(driver.findElement(By
						.cssSelector("select.cpMonthNavigation")))
						.selectByVisibleText("December");

				((JavascriptExecutor) driver).executeScript(
						"window.opener.CP_tmpReturnFunction(2025,12,31)",
						"window.opener.CP_hideCalendar(\'0\')");

				// driver.findElementByCssSelector("body > center:nth-child(2) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(4) > a:nth-child(1)").click();

				driver.switchTo().window(parentWindow); // cntrl to parent
														// window
			}
		}*/
	}

	private static void selectSex(String sex) {
		switch (sex) {
		case "Male":
			driver.findElementByCssSelector(
					"tr.sptr1:nth-child(7) > td:nth-child(2) > input:nth-child(1)")
					.click();

			break;
		case "Female":
			driver.findElementByCssSelector(
					"tr.sptr1:nth-child(7) > td:nth-child(2) > input:nth-child(2)")
					.click();
			break;

		case "Group":
			driver.findElementByCssSelector(
					"tr.sptr1:nth-child(7) > td:nth-child(2) > input:nth-child(3)")
					.click();
			break;
		}
	}

}