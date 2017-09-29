package com.AQAS.document_retrieval;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Iterator;
import java.util.List;

public class SeleniumTests {


    public static void main(String[] args) {


        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY ,System.getProperty("user.dir") + "/exec/phantomjs.exe" );
        WebDriver driver = new PhantomJSDriver(caps);

        // And now use this to visit Google
        driver.get("https://www.webteb.com/search?q=%D8%A7%D9%84%D8%B3%D9%83%D8%B1%D9%8A");

        try {
            WebElement showMoreButton = driver.findElement(By.className("showMore"));
            showMoreButton.click();
        }
        catch (Exception e){
            ;
        }

        WebElement we = driver.findElement(By.tagName("body"));

        System.out.println(we.getText());
        //to remove null (elements that have the selector but doesn't have href)
        driver.quit();

    }
}
