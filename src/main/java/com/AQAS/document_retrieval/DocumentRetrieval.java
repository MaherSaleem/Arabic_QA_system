package com.AQAS.document_retrieval;

import com.AQAS.Database.Document;
import com.AQAS.keyphrase_extraction.HelpersKE;
import com.AQAS.main.ConfigM;
import com.AQAS.main.Logger;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
            } catch (IOException e) {
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
    public static String retrieveDocumentText(String URL , String contentSelector) throws IOException {
        try {
            driver.get(URL);
        }catch (Exception e){
            openWebDriver();
            driver.get(URL);
        }
        try {
            System.out.println("Doc");
        } catch (Exception e) {
        }
        try {
            JavascriptExecutor js;
            if (driver instanceof JavascriptExecutor) {
                js = (JavascriptExecutor) driver;
                js.executeScript("var element = document.querySelector(\".article-top\");if (element)\n" + "    element.parentNode.removeChild(element);");
                js.executeScript("var element = document.querySelector(\".article-relations\");if (element)\n" + "    element.parentNode.removeChild(element);");
                js.executeScript("var element = document.querySelector(\".article-bottom\");if (element)\n" + "    element.parentNode.removeChild(element);");
                js.executeScript("var element = document.querySelector(\".jump-links\");if (element)\n" + "    element.parentNode.parentNode.removeChild(element.parentNode);");
                js.executeScript("var element = document.querySelector(\"table\");if (element)\n" + "    element.parentNode.parentNode.removeChild(element.parentNode);");


                js.executeScript("var element = document.querySelector(\".toc\");if (element)\n" + "    element.parentNode.removeChild(element);");
                js.executeScript("var element = document.querySelector(\".ltr\");if (element)\n" + "    element.parentNode.removeChild(element);");
                js.executeScript("var element = document.querySelector(\".references\");if (element)\n" + "    element.parentNode.removeChild(element);");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Not Worked");
        }


        WebElement we;
        try {
             we = driver.findElement(By.cssSelector(contentSelector));
        }
        catch (Exception e){
            we = driver.findElement(By.tagName("body"));
        }


        String documentText = we.getText();
        documentText = QuestionPreprocessing.preProcessDocument(documentText).get(ConfigP.Keys.NormalizedText_WithStoppingWords_WithAlT3reef_WithPunctuation);
        return documentText;

    }

    public static ArrayList<Document> getDocumentsByQuery(String preprocessed_query_string) throws IOException {

        ArrayList<Document> documents = new ArrayList<>();
        //Specify the number of search pages result to be used.
        ArrayList<Website_Document> website_documents = DocumentRetrieval.getLinksOfAllWebsitesByQuery(preprocessed_query_string, ConfigM.searchNumOfPages);
        //printing the links
        if (ConfigM.VERBOSE_LOG) {
            for (Website_Document website_document : website_documents) {
                for (String url : website_document.DocumentLinks) {
                    Logger.getInstance().log(ConfigM.LogFolders.DOC_RETRIEVAL + "/all_documents_links.log", url);
                }
                Logger.getInstance().log(ConfigM.LogFolders.DOC_RETRIEVAL + "/all_documents_links.log", "===================================================");

            }
        }
        if (ConfigM.VERBOS) {
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
                String text = DocumentRetrieval.retrieveDocumentText(url, website_document.websiteContentSelector);
                if (ConfigM.VERBOS) {
                    System.out.println("Link is :" + url);
                    System.out.println(text);
                }
                Document document = new Document(url, text, urlOrder);
                document.setKeyPhases( HelpersKE.getKeyPhrases(text));
                document.setDocName(website_document.getSourceWebsite()+"_"+urlOrder++);
                documents.add(document);
            }
        }
        return documents;
    }



}
