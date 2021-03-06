package com.test.appium.dryrun;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.test.util.BaseTest;

public class WordPressAndroidTest extends BaseTest {

	@Test
	public void testParallel() {
		waitForElement(By.id("com.android2.calculator3:id/cling_dismiss"), 30);
		driver.findElement(By.id("com.android2.calculator3:id/cling_dismiss")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit5")).click();
		driver.findElement(By.id("com.android2.calculator3:id/plus")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit9")).click();
		driver.findElement(By.id("com.android2.calculator3:id/equal_!")).click();
		waitForElement(By.xpath("com.android2.calculator3:id/cling_dismiss"), 30);
		String num = driver.findElement(By.xpath("//android.widget.EditText[@index=0]")).getText();
		System.out.println("Result : " + num);
		driver.findElement(By.id("com.android2.calculator3:id/digit5")).click();
		driver.findElement(By.id("com.android2.calculator3:id/plus")).click();
		driver.findElement(By.id("com.android2.calculator3:id/digit9")).click();
		driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
		driver.closeApp();

	}

	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			File file = new File("");
			Reporter.setCurrentTestResult(result);
			System.out.println(file.getAbsolutePath());
			Reporter.log(file.getAbsolutePath());
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(file.getAbsolutePath() + "/reports/"+ System.getProperty("deviceName")+"/"+ result.getName() + ".jpg"));
			Reporter.setCurrentTestResult(result);
			Reporter.log("<a href='" + file.getAbsolutePath() + "/reports/"+System.getProperty("deviceName")+"/"+result.getName()+".jpg'> <img src='"
					+ file.getAbsolutePath() + "/reports/"+System.getProperty("deviceName")+"/"+result.getName()
					+ ".jpg' height='100' width='100'/> </a>");
			Reporter.setCurrentTestResult(null);
		}
	}

}
