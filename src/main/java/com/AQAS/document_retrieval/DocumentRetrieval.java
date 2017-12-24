package com.AQAS.document_retrieval;

import com.AQAS.Database.Document;
import com.AQAS.main.ConfigM;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.AQAS.document_retrieval.HelpersD.driver;
import static com.AQAS.document_retrieval.HelpersD.openWebDriver;
import static com.AQAS.document_retrieval.HelpersD.removeDuplicatesFromDocumentLinks;

public class DocumentRetrieval {

    public static ArrayList<Website_Document> getLinksOfAllWebsitesByQuery(String query, int searchNumOfPages) {
        ArrayList<Website_Document> DocumentSLinksWithContentSelector = new ArrayList<Website_Document>();
        HashMap<String, Object> searchAttr = new HashMap<String, Object>();
        searchAttr.put(ConfigD.Keys.searchQuery, query);
        searchAttr.put(ConfigD.Keys.searchNumOfPages, searchNumOfPages);
        for (Website website : ConfigD.webSites) {
            try {
                DocumentSLinksWithContentSelector.add(website.extractDocumentsLinksForAllPages(searchAttr));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return removeDuplicatesFromDocumentLinks(DocumentSLinksWithContentSelector,ConfigD.websitesToremoveDuplicatesFrom);
    }


    /**
     * @param URL
     */
    public static String retrieveDocumentText(String URL , String contentSelector) {
        try {
            driver.get(URL);
        }catch (Exception e){
            openWebDriver();
            driver.get(URL);
        }
        try {
            WebElement showMoreButton = driver.findElement(By.className("showMore"));
            showMoreButton.click();
        } catch (Exception e) {
        }
        WebElement we;
        try {
             we = driver.findElement(By.cssSelector(contentSelector));
        }
        catch (Exception e){
            we = driver.findElement(By.tagName("body"));
        }


        String documentText = we.getText();
        return documentText;

    }

    public static ArrayList<Document> getDocumentsByQuery(String preprocessed_query_string) throws IOException {

        ArrayList<Document> documents = new ArrayList<>();
        //Specify the number of search pages result to be used.
        ArrayList<Website_Document> website_documents = DocumentRetrieval.getLinksOfAllWebsitesByQuery(preprocessed_query_string, ConfigM.searchNumOfPages);
        //printing the links
        if (ConfigD.VERBOS) {
            System.out.println("All Links:");
            for (Website_Document website_document : website_documents) {
                for (String url : website_document.DocumentLinks) {
                    System.out.println(url);
                }
            }
        }

        for (Website_Document website_document : website_documents) {
            int urlOrder = 1;
            for (String url : website_document.DocumentLinks) {
                if (ConfigM.VERBOS) {
                    System.out.println("Link is :" + url);
                }
                String text = DocumentRetrieval.retrieveDocumentText(url, website_document.websiteContentSelector);
                System.out.println(text);
                documents.add(new Document(url, text, urlOrder++));
            }
        }
        return documents;
    }



}
