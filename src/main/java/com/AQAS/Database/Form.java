package com.AQAS.Database;

import com.AQAS.Document_ranking.ConfigDR;
import com.AQAS.document_retrieval.ConfigD;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static com.AQAS.Database.HelpersDB.props;

public class Form {

    public int id;
    public int question_id;
    public String text;
    public ArrayList<Document> documents = new ArrayList<Document>();


    public Form() {
    }

    public Form(int id) {
        this.id = id;
    }

    public Form(String text) {
        this.text = text;
    }


    public int store() {
        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(props.getProperty("LOCAL_SERVER_IP") + "forms/" + this.question_id)
                    .data("text", this.text)
                    .userAgent("Mozilla")
                    .post();
            return Integer.parseInt(doc.text());

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Form getFormById(int id) {
        return null;
    }

    public ArrayList<Document> getDocuments() {
        try {
            String json = Jsoup.connect(props.getProperty("LOCAL_SERVER_IP") + "/forms/document/" + this.id).ignoreContentType(true).execute().body();
            JSONParser parser = new JSONParser();
            Object obj = null;
            obj = parser.parse(json);

            JSONArray jsonArray = (JSONArray) obj;
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject tmp = iterator.next();
                String link = (String) tmp.get("link");
                String text = StringEscapeUtils.unescapeJava((String) tmp.get("text"));
                int document_id = Integer.parseInt(tmp.get("id") + "");
                this.documents.add(new Document(document_id, link, text, this.id));
            }

            return this.documents;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeIrrelevantDocuments() {
        double relevancyThreshold = getRelevancyThreshold();
        System.out.println("Choosen Threshold is :" + relevancyThreshold);
        for (Iterator<Document> document = documents.iterator(); document.hasNext(); ) {
            if (document.next().overAllRank() < relevancyThreshold) {
                document.remove();
            }
        }
    }

    private double getRelevancyThreshold(){

        if (ConfigDR.THRESHOLD_SOURCE == ConfigDR.STATIC_THRESHOLD){
            return ConfigDR.RELEVANCY_THRESHOLD;
        }
        else if(ConfigDR.THRESHOLD_SOURCE == ConfigDR.STATISTICAL_THRESHOLD){
            double avg = getDocumentsRanksAvg();
            double standardDeviation = getDocumentsRankStandardDeviation();
            if (avg <= standardDeviation){
                return  avg ;
            }
            else{
                return avg - standardDeviation;
            }
        }
        return 1;//TODO
    }


    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", question_id=" + question_id +
                ", text='" + text + '\'' +
                ", documents=" + documents +
                '}';
    }

    public double getDocumentsRanksAvg() {
        double sum = 0;
        for (Document document: this.documents) {
            sum += document.overAllRank();
        }
        return sum / this.documents.size();
    }
    public double getDocumentsRankStandardDeviation() {
        double mean = getDocumentsRanksAvg();
        double sum = 0;
        for (Document document: this.documents) {
            sum += Math.pow((document.overAllRank()-mean),2);
        }
        return Math.sqrt(sum / this.documents.size());
    }
}
