package com.AQAS.Database;

import com.AQAS.Document_ranking.DocumentRanking;
import com.AQAS.document_retrieval.DocumentRetrieval;
import com.AQAS.document_retrieval.Website_Document;
import com.AQAS.main.ConfigM;
import com.AQAS.main.HelpersM;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import static com.AQAS.document_retrieval.DocumentRetrieval.retrieveDocumentText;


public class HelpersDB {

    public static Properties props = null;

    public static void storeTestingData() throws IOException {
        intializeProb();
        for (Question question : ConfigDB.trainingQuestions) {
            int question_id = question.store();
            for (Form form : question.forms) {
                form.text = QuestionPreprocessing.preProcessInput(form.text).get(ConfigP.Keys.NormalizedText);

                form.question_id = question_id;
                int form_id = form.store();
                ArrayList<Website_Document> website_documents = DocumentRetrieval.getLinksOfAllWebsitesByQuery(form.text, ConfigM.searchNumOfPages);
                for (Website_Document website_document : website_documents) {
                    int urlOrder = 1;

                    for (String url : website_document.DocumentLinks) {
                        String documentText = retrieveDocumentText(url , website_document.websiteContentSelector);
                        double contentRank = DocumentRanking.getDocumentRank(documentText,form.text);

                        Document newDoc = new Document(url, documentText,urlOrder++,contentRank);
                        newDoc.form_id = form_id;
                        newDoc.store();
                    }
                }


            }


        }

    }

    public static void intializeProb() {
        props = new Properties();
        InputStream fis = null;
        try {

            fis = HelpersM.getInputStreamFromResrcFile("db.properties");
            props.load(fis);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Form getFormById(int formId){
        Form form = Form.getInstance();
        try {
            String json = Jsoup.connect(props.getProperty("LOCAL_SERVER_IP") + "/forms/" + formId).ignoreContentType(true).execute().body();
            JSONParser parser = new JSONParser();
            Object obj = null;
            obj = parser.parse(json.toString());

            JSONObject jsonObject = (JSONObject) obj;
            String text = (String) jsonObject.get("text");
            form.setText(text);
            form.setId(formId);
            return form;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


}
