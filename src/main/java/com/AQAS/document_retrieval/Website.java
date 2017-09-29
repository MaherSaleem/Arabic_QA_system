package com.AQAS.document_retrieval;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class Website {

    public String websiteName;
    public String searchLink;
    public String pageVariableName;
    public String selector;
    public int searchPageOffset;
    public String contentSelector;


    /**
     * @param websiteName:the   website's name
     * @param searchLink:the    link that is used to make the search inside the website
     * @param selector:         used to get the div that contains the links of the search result
     * @param pageVariableName: The variable name that is used within the search to specify which page from the search result to use
     * @param searchPageOffset: is used to determine the start page of a website search results
     */
    public Website(String websiteName, String searchLink, String pageVariableName, String selector, int searchPageOffset, String contentSelector) {
        this.websiteName = websiteName;
        this.searchLink = searchLink;
        this.pageVariableName = pageVariableName;
        this.selector = selector;
        this.searchPageOffset = searchPageOffset;
        this.contentSelector = contentSelector;
    }


    public ArrayList<String> _getLinksOnePage(String generatedSearchURL, WebDriver driver) throws ParseException {

        //GET request
        driver.get(generatedSearchURL);

        List<WebElement> refList = driver.findElements(By.cssSelector(this.selector));
        //to remove null (elements that have the selector but doesn't have href)
        Iterator<WebElement> i = refList.iterator();
        while (i.hasNext()) {
            WebElement s = i.next(); // must be called before you can call i.remove()
            if (s.getAttribute("href") == null)
                i.remove();
        }
        //store results in arrayLists
        ArrayList<String> links = new ArrayList<String>();
        for (WebElement we : refList) {
            String link = we.getAttribute("href");
            links.add(link);
        }
        return links;
    }

    /*
        generic function to get documents links
     */
    public ArrayList<String> getLinks(HashMap<String, Object> searchAttr, WebDriver driver) throws UnsupportedEncodingException, ParseException {
        ArrayList<String> allLinks = new ArrayList<String>();

        int startPage = this.searchPageOffset;
        int endPage = (Integer) searchAttr.get("searchNumOfPages") + this.searchPageOffset;
        for (int i = startPage; i < endPage; i++) {
            System.out.println("Link Requested->" + this.generateSearchLink((String) searchAttr.get("searchQuery"), i));
            String generatedSearchURL = this.generateSearchLink((String) searchAttr.get("searchQuery"), i);
            allLinks.addAll(_getLinksOnePage(generatedSearchURL, driver));
        }
        return allLinks;
    }

    @Override
    public String toString() {
        return "Website{" +
                "websiteName='" + websiteName + '\'' +
                ", searchLink='" + searchLink + '\'' +
                ", pageVariableName='" + pageVariableName + '\'' +
                ", selector='" + selector + '\'' +
                ", searchPageOffset=" + searchPageOffset +
                ", contentSelector='" + contentSelector + '\'' +
                '}';
    }

    public String generateSearchLink(String searchQuery, int pageNumber) throws UnsupportedEncodingException {
        return String.format(this.searchLink, URLEncoder.encode(searchQuery, "UTF-8"), pageNumber);
    }
}

class DailyMedicalInfoWebsite extends Website {

    /**
     * @param websiteName      :the website's name
     * @param searchLink       :the link that is used to make the search inside the website
     * @param pageVariableName : The variable name that is used within the search to specify which page from the search result to use
     * @param selector         : used to get the div that contains the links of the search result
     * @param searchPageOffset :  is used to determine the start page of a website search results
     * @param contentSelector
     */
    public DailyMedicalInfoWebsite(String websiteName, String searchLink, String pageVariableName, String selector, int searchPageOffset, String contentSelector) {
        super(websiteName, searchLink, pageVariableName, selector, searchPageOffset, contentSelector);

    }

    public String generateSearchLink(String searchQuery, int pageNumber) throws UnsupportedEncodingException {
        return String.format(this.searchLink, pageNumber, URLEncoder.encode(searchQuery, "UTF-8"));
    }


}

class Google extends Website {

    /**
     * @param websiteName      :the website's name
     * @param searchLink       :the link that is used to make the search inside the website
     * @param pageVariableName : The variable name that is used within the search to specify which page from the search result to use
     * @param selector         : used to get the div that contains the links of the search result
     * @param searchPageOffset :  is used to determine the start page of a website search results
     * @param contentSelector
     */
    public Google(String websiteName, String searchLink, String pageVariableName, String selector, int searchPageOffset, String contentSelector) {
        super(websiteName, searchLink, pageVariableName, selector, searchPageOffset, contentSelector);

    }

    public String generateSearchLink(String searchQuery, int pageNumber) throws UnsupportedEncodingException {
        return String.format(this.searchLink, URLEncoder.encode(searchQuery, "UTF-8"), pageNumber * 10 + 1);
    }

    @Override
    public ArrayList<String> _getLinksOnePage(String generatedSearchURL, WebDriver driver) throws ParseException {

        ArrayList<String> links = new ArrayList<String>();

        //https://www.mkyong.com/java/json-simple-example-read-and-write-json/
        JSONParser parser = new JSONParser();
        driver.get(generatedSearchURL);


        //the json object is wrapped with <pre></pre> tag, so we get the content from it.
        String jsonResponse = driver.findElement(new By.ByTagName("pre")).getText();
        Object obj = parser.parse(jsonResponse);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray res = (JSONArray) jsonObject.get("items");

        Iterator<JSONObject> iterator = res.iterator();
        while (iterator.hasNext()) {
            JSONObject tmp = iterator.next();
            String link = (String) tmp.get("link");

            //use JAVA 8 to make this valid(press on error message to solve the problem)
            //remove unwanted websites
            if( Arrays.stream(ConfigD.unwantedWebsites).parallel().noneMatch(link::contains)){
                links.add(link);
            }
            //Another solution(using loop)
            //TODO add it if you want
        }
        return links;
    }
}