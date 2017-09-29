package com.AQAS.document_retrieval;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class DocumentRetrieval {


    /**
     *
     * @param searchAttr
     * @return Array list for all links in all websites
     */
    public ArrayList<String> getLinksFromAllWebsites(HashMap<String, Object> searchAttr) {

        WebDriver driver = HelpersD.openWebDriver();

        ArrayList<String> searchResultURLs = new ArrayList<String>();

        for (Website website : ConfigD.webSites) {
            try {
                searchResultURLs.addAll(website.getLinks(searchAttr, driver));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //Close the browser
        HelpersD.closeWebDriver(driver);

        return HelpersD.removeDuplicates(searchResultURLs);
    }

    /**
     *
     * @param URL
     */
    public void getDocument(String URL) {
        WebDriver driver = HelpersD.openWebDriver();
        driver.get(URL);
        try {
            WebElement showMoreButton = driver.findElement(By.className("showMore"));
            showMoreButton.click();
        } catch (Exception e) {
        }

        WebElement we = driver.findElement(By.tagName("body"));

        System.out.println(we.getText());

        HelpersD.closeWebDriver(driver);


    }


}
