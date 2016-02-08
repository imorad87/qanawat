import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SingleUpload {

	// ------------- CAT 1 start ------------------//
	private static final int ORIENTAL = 36;
	private static final int OCCIDENTAL = 33;
	private static final int ALGERIENNE = 27;
	private static final int ISLAMIQUE = 46;
	// ------------- CAT 1 end -------------------//

	// ------------- CAT 2 start ----------------//
	private static final int KHALIDJI = 40212;
	private static final int POP_ROCK = 40206;
	private static final int EGYPTIEN = 40210;
	private static final int DOUAA = 40214;
	private static final int LIBANAIS = 40211;
	private static final int VARIETE = 40197;
	private static final int ANACHID = 40215;
	// ------------- CAT 2 end -----------------//

	// ------------- DAM CAT 1 start ------------------//
	private static final String DAM_ORIENTAL = "Oriental";
	private static final String DAM_OCCIDENTAL = "Occidental";
	private static final String DAM_ISLAMIQUE = "Islamique";
	private static final String DAM_ALGERIENNE = "Algérienne";
	// ------------- DAM CAT 1 end ------------------//

	// ------------- DAM CAT 2 start ------------------//
	private static final String DAM_KHALIDJI = "Khalidji";
	private static final String DAM_POP_ROCK = "POP/Rock";
	private static final String DAM_DOUAA = "Douaa";
	private static final String DAM_LIBANAIS = "Libanais";
	private static final String DAM_VARIETE = "Variété";
	private static final String DAM_ANACHID = "Anachid";
	private static final String DAM_EGYPTIEN = "Egyptien";
	// ------------- DAM CAT 2 end ------------------//

	private static FirefoxDriver driver;

	public static final String URL = "https://secure.nedjma.dz";
	public static final String USERNAME_TAGE = "username";
	public static final String PASSWORD_TAGE = "password";
	public static final String USER_NAME = "qanawat";
	public static final String PASSWORD = "wta$123*nedjma";

	public static void main(String[] args) throws IOException,
			InterruptedException {
		driver = new FirefoxDriver();
		login(URL, USER_NAME, PASSWORD);
		upload();
	}

	private static boolean login(String url, String userName, String password) {
		driver.get(url);

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
				.to("https://secure.nedjma.dz/sp/,DanaInfo=10.39.1.40+tonemaintenanceindex.screen");
		driver.navigate()
				.to("https://secure.nedjma.dz/sp/,DanaInfo=10.39.1.40+spqueryselftone.do?qryType=self&uploadType=1&approveType=2&status=1&language=4");
		driver.navigate()
				.to("https://secure.nedjma.dz/sp/,DanaInfo=10.39.1.40+uploadtone.screen");

		return false;

	}

	private static void upload() throws InterruptedException,
			FileNotFoundException {

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream("input2.csv"),
						StandardCharsets.UTF_8));
		Scanner s = new Scanner(bufferedReader);

		FileOutputStream out = new FileOutputStream("uploading status.txt");
		OutputStreamWriter r = new OutputStreamWriter(out);

		String artist = null;
		String title = null;
		String album = null;
		String ID = "600004";
		String language = null;
		String cat1 = null;
		String cat2 = null;
		String gender = null;
		String path10 = null;
		String path35 = null;

		while (s.hasNext()) {
			String line = s.nextLine();
			StringTokenizer to = new StringTokenizer(line, ">");

			title = to.nextToken();
			artist = to.nextToken();
			album = to.nextToken();
			cat1 = to.nextToken();
			cat2 = to.nextToken();
			gender = to.nextToken();
			path10 = to.nextToken();
			path35 = to.nextToken();

			driver.findElementById("toneName").sendKeys(title);
			driver.findElementById("singerName").sendKeys(artist);
			driver.findElementById("movieName").sendKeys(album);
			driver.findElementById("subCpId").sendKeys(ID);
			selectCat1(cat1);
			selectCat2(cat2);
			driver.findElementById("calimg1").click();

			String parentWindow = driver.getWindowHandle();
			Set<String> handles =  driver.getWindowHandles();
			   for(String windowHandle  : handles)
			       {
			       if(!windowHandle.equals(parentWindow))
			          {
			          driver.switchTo().window(windowHandle);
			          /*new Select(driver.findElement(By
								.cssSelector("select.cpYearNavigation")))
								.selectByVisibleText("2025");
						
						new Select(driver.findElement(By
								.cssSelector("select.cpMonthNavigation")))
								.selectByVisibleText("December");
*/
						/*((JavascriptExecutor) driver)
								.executeScript("javascript:DanaDeferEval('window.opener.CP_tmpReturnFunction(2025,12,31);"
										+ "window.opener.CP_hideCalendar(\'0\');')");
*/
			          driver.findElementByCssSelector("body > center:nth-child(2) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(4) > a:nth-child(1)").click();
			         driver.switchTo().window(parentWindow); //cntrl to parent window
			          }
			       }

			
			//driver.switchTo().window(parentWindow);

		}

	}

	private static void selectCat1(String cat1) {

		switch (cat1) {
		case DAM_ORIENTAL:
			driver.findElementById("dir1list").click();

			((JavascriptExecutor) driver).executeScript("selectItem(MainList,'"
					+ DAM_ORIENTAL + "','" + ORIENTAL
					+ "',fatherdirname,fatherdirindexlevel1, '0','')");
			break;

		case DAM_ISLAMIQUE:
			driver.findElementById("dir1list").click();

			((JavascriptExecutor) driver).executeScript("selectItem(MainList,'"
					+ DAM_ISLAMIQUE + "','" + ISLAMIQUE
					+ "',fatherdirname,fatherdirindexlevel1, '0','')");
			break;

		case DAM_ALGERIENNE:
			driver.findElementById("dir1list").click();

			((JavascriptExecutor) driver).executeScript("selectItem(MainList,'"
					+ DAM_ALGERIENNE + "','" + ALGERIENNE
					+ "',fatherdirname,fatherdirindexlevel1, '0','')");
			break;

		case DAM_OCCIDENTAL:
			driver.findElementById("dir1list").click();

			((JavascriptExecutor) driver).executeScript("selectItem(MainList,'"
					+ DAM_OCCIDENTAL + "','" + OCCIDENTAL
					+ "',fatherdirname,fatherdirindexlevel1, '0','')");
			break;
		default:
			JOptionPane.showMessageDialog(null,
					"Cat is not identified. Check cat spelling.");
		}
	}

	private static void selectCat2(String cat2) throws InterruptedException {
		Thread.sleep(5000);

		switch (cat2) {
		case DAM_ANACHID:
			driver.findElementById("dir2list").click();

			((JavascriptExecutor) driver)
					.executeScript("selectChildItem(ChildList,'"
							+ DAM_ANACHID
							+ "','"
							+ ANACHID
							+ "',document.getElementById('categoryname1'),document.getElementById('fatherdirindex1'),1,1,'')");

			break;

		case DAM_DOUAA:
			driver.findElementById("dir2list").click();

			((JavascriptExecutor) driver)
					.executeScript("selectChildItem(ChildList,'"
							+ DAM_DOUAA
							+ "','"
							+ DOUAA
							+ "',document.getElementById('categoryname1'),document.getElementById('fatherdirindex1'),1,1,'')");

			break;

		case DAM_EGYPTIEN:
			driver.findElementById("dir2list").click();

			((JavascriptExecutor) driver)
					.executeScript("selectChildItem(ChildList,'"
							+ DAM_EGYPTIEN
							+ "','"
							+ EGYPTIEN
							+ "',document.getElementById('categoryname1'),document.getElementById('fatherdirindex1'),1,1,'')");

			break;

		case DAM_KHALIDJI:
			driver.findElementById("dir2list").click();

			((JavascriptExecutor) driver)
					.executeScript("selectChildItem(ChildList,'"
							+ DAM_KHALIDJI
							+ "','"
							+ KHALIDJI
							+ "',document.getElementById('categoryname1'),document.getElementById('fatherdirindex1'),1,1,'')");

			break;

		case DAM_LIBANAIS:
			driver.findElementById("dir2list").click();

			((JavascriptExecutor) driver)
					.executeScript("selectChildItem(ChildList,'"
							+ DAM_LIBANAIS
							+ "','"
							+ LIBANAIS
							+ "',document.getElementById('categoryname1'),document.getElementById('fatherdirindex1'),1,1,'')");

			break;

		case DAM_VARIETE:
			driver.findElementById("dir2list").click();

			((JavascriptExecutor) driver)
					.executeScript("selectChildItem(ChildList,'"
							+ DAM_VARIETE
							+ "','"
							+ VARIETE
							+ "',document.getElementById('categoryname1'),document.getElementById('fatherdirindex1'),1,1,'')");

			break;

		case DAM_POP_ROCK:
			driver.findElementById("dir2list").click();

			((JavascriptExecutor) driver)
					.executeScript("selectChildItem(ChildList,'"
							+ DAM_POP_ROCK
							+ "','"
							+ POP_ROCK
							+ "',document.getElementById('categoryname1'),document.getElementById('fatherdirindex1'),1,1,'')");

			break;

		default:
			JOptionPane.showMessageDialog(null,
					"Cat 2 is not identified. Check cat spelling.");
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

}