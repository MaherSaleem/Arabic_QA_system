package com.AQAS.document_retrieval;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Helpers {

    public static ArrayList<String> removeDuplicates(ArrayList<String> a){
        ArrayList<String> al = a;
        // add elements to al, including duplicates
        Set<String> hs = new HashSet<String>();
        hs.addAll(al);
        al.clear();
        al.addAll(hs);

        return al;
    }

    public static WebDriver openWebDriver(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY ,System.getProperty("user.dir") + "/exec/phantomjs.exe" );
        WebDriver driver = new PhantomJSDriver(caps);

        return driver;
    }

    public static void closeWebDriver(WebDriver driver){
        driver.close();
    }


}
