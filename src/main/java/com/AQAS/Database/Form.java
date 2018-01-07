package com.AQAS.Database;

import com.AQAS.Document_ranking.ConfigDR;
import com.AQAS.Document_ranking.DocumentRanking;
import com.AQAS.Document_ranking.HelpersDR;
import com.AQAS.answer_extraction.ConfigAE;
import com.AQAS.keyphrase_extraction.ConfigKE;
import com.AQAS.keyphrase_extraction.HelpersKE;
import com.AQAS.main.ConfigM;
import com.AQAS.main.HelpersM;
import com.AQAS.main.Logger;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;
import com.AQAS.question_type.ConfigQT;
import com.AQAS.synonyms.FindSynonyms;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;

import javax.print.Doc;
import java.io.*;
import java.util.*;

import static com.AQAS.Database.HelpersDB.props;

public class Form {

    public int id;
    public int question_id;
    public String text;
    public int question_type;
    public String normalizedText;//NormalizedText_WithoutStoppingWords_WithoutALT3reef
    public ArrayList<Document> documents = new ArrayList<Document>();
    private String[] keyPhrases = null;
    public ArrayList<Segment> topSegmentsByRank = new ArrayList<>();// top segments by rank
    public ArrayList<Segment> topSegmentsByOrder = new ArrayList<>();//top segments by order of document
    public ArrayList<Answer> answers = new ArrayList<Answer>();// the segments ordered by Rank
    HashMap<String, String> preprocessed_query;

    private static Form singletonForm;

    public HashMap<String, String> getPreprocessed_query() {
        return preprocessed_query;
    }

    public void setPreprocessed_query(HashMap<String, String> preprocessed_query) {
        this.preprocessed_query = preprocessed_query;
    }

    public static Form getInstance() {
        if (singletonForm == null) {
            singletonForm = new Form();
        }
        return singletonForm;
    }


    public Form() {
    }

    public ArrayList<Segment> getTopSegmentsByOrder() {
        return topSegmentsByOrder;
    }

    public void setTopSegmentsByOrder(ArrayList<Segment> topSegmentsByOrder) {
        this.topSegmentsByOrder = topSegmentsByOrder;
    }

    public Form(int id) {
        this.id = id;
    }

    public Form(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getQuestion_type() {
        return question_type;
    }

    public void setKeyPhrases(String[] keyPhrases) {
        this.keyPhrases = keyPhrases;
    }

    public void setKeyPhrases() throws IOException {
        String[] queryKeyPhrases = HelpersKE.getKeyPhrases(this.normalizedText);
        String[] stemmedWords = this.getPreprocessed_query().get(ConfigP.Keys.StemmedText).split("\\s");
        if (ConfigM.VERBOSE_LOG) {
            Logger.getInstance().log(ConfigM.LogFolders.PREPROCESSING + "/keyphrases.log", "Keyphrases List" + Arrays.toString(queryKeyPhrases) + "\n====================================");
        }
        if (ConfigM.VERBOS) {
            System.out.println("Keyphrases List is :" + Arrays.toString(queryKeyPhrases));
        }
        ArrayList<String> queryStemmedWordsWithKeyphrasesSynonyms = new ArrayList<String>();

        //getting synonyms for key phrases
        for (String queryKeyPhrase : queryKeyPhrases) {
            String[] keyPhraseSynonyms = FindSynonyms.getWordSynonyms(queryKeyPhrase);
            if (ConfigM.VERBOS) {
                System.out.println("Synonyms for keyphrase \"" + queryKeyPhrase + "\" are: " + Arrays.asList(keyPhraseSynonyms));
            }
            if (ConfigM.VERBOSE_LOG) {
                Logger.getInstance().log(ConfigM.LogFolders.PREPROCESSING + "/keyphrases.log", "Synonyms for keyphrase \"" + queryKeyPhrase + "\" are: " + Arrays.asList(keyPhraseSynonyms));

            }
            if (HelpersM.getSentenceWordsCount(queryKeyPhrase) > 1) {
                queryStemmedWordsWithKeyphrasesSynonyms.add(queryKeyPhrase);
            }
            queryStemmedWordsWithKeyphrasesSynonyms.addAll(Arrays.asList(keyPhraseSynonyms));
        }
        queryStemmedWordsWithKeyphrasesSynonyms.addAll((Arrays.asList(stemmedWords)));
        queryKeyPhrases = HelpersM.removeStringDuplicates(queryStemmedWordsWithKeyphrasesSynonyms.toArray(new String[queryStemmedWordsWithKeyphrasesSynonyms.size()]));
        if (ConfigM.VERBOSE_LOG) {
            Logger.getInstance().log(ConfigM.LogFolders.PREPROCESSING + "/keyphrases.log", "==================================\nKey phrases with synonyms" + Arrays.toString(queryKeyPhrases));
        }
        if (ConfigM.VERBOS) {
            System.out.println("==================================\nKey phrases with synonyms" + Arrays.toString(queryKeyPhrases));
        }
        this.setKeyPhrases(queryKeyPhrases);
    }

    public String[] getKeyPhrases() throws IOException {
        if (this.keyPhrases == null) {
            this.setKeyPhrases();
        }
        return this.keyPhrases;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
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

    public ArrayList<Document> getDocuments() {//from database
        try {
            String json = Jsoup.connect(props.getProperty("LOCAL_SERVER_IP") + "/forms/document/" + this.id).ignoreContentType(true).execute().body();
            // System.out.println("JSON: "+json);
            JSONParser parser = new JSONParser();
            Object obj = null;
            obj = parser.parse(json.toString());

            JSONArray jsonArray = (JSONArray) obj;
            Iterator<JSONObject> iterator = jsonArray.iterator();
            int count = 1;
            while (iterator.hasNext()) {
                JSONObject tmp = iterator.next();
                String link = (String) tmp.get("link");
                String text = StringEscapeUtils.unescapeJava((String) tmp.get("text"));
                int document_id = Integer.parseInt(tmp.get("id") + "");
                JSONObject pivot = (JSONObject) tmp.get("pivot");

                double urltRank = Double.parseDouble(pivot.get("urlRank") + "");
                Document document = new Document(document_id, link, text, this.id, urltRank);
                document.setDocName("" + count++);
                this.documents.add(document);

            }

            return this.documents;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Document> getDocumentsForRank() {
        try {
            String json = Jsoup.connect(props.getProperty("LOCAL_SERVER_IP") + "/forms/documentRank/" + this.id).ignoreContentType(true).execute().body();
            // System.out.println("JSON: "+json);
            JSONParser parser = new JSONParser();
            Object obj = null;
            obj = parser.parse(json.toString());

            JSONArray jsonArray = (JSONArray) obj;
            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {

                JSONObject tmp = iterator.next();
                int document_id = Integer.parseInt(tmp.get("id") + "");
                double contentRank = Double.parseDouble(tmp.get("contentRank") + "");
                double urlRank = Double.parseDouble(tmp.get("urlRank") + "");
                this.documents.add(new Document(document_id, this.id, urlRank, contentRank));

            }

            return this.documents;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeIrrelevantDocuments() {
        double relevancyThreshold = getRelevancyThreshold();
        if (ConfigM.VERBOSE_LOG) {
            try {
                Logger.getInstance().log(ConfigM.LogFolders.DOC_RETRIEVAL + "/threshold.log", "Threshold:\n" + relevancyThreshold);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (ConfigM.VERBOS) {
            System.out.println("Choosen Threshold is :" + relevancyThreshold);
        }
        for (Iterator<Document> document = documents.iterator(); document.hasNext(); ) {
            if (document.next().overAllRank() < relevancyThreshold) {
                document.remove();
            }
        }
    }

    private double getRelevancyThreshold() {

        if (ConfigDR.THRESHOLD_SOURCE == ConfigDR.STATIC_THRESHOLD) {
            return ConfigDR.RELEVANCY_THRESHOLD;
        } else if (ConfigDR.THRESHOLD_SOURCE == ConfigDR.STATISTICAL_THRESHOLD) {
            double avg = getDocumentsRanksAvg();
            double standardDeviation = getDocumentsRankStandardDeviation();
            if (avg <= standardDeviation) {
                return avg;
            } else {
                return avg - standardDeviation;
            }
        }
        return 1;
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
        for (Document document : this.documents) {
            sum += document.overAllRank();
        }
        return sum / this.documents.size();
    }

    public double getDocumentsRankStandardDeviation() {
        double mean = getDocumentsRanksAvg();
        double sum = 0;
        for (Document document : this.documents) {
            sum += Math.pow((document.overAllRank() - mean), 2);
        }
        return Math.sqrt(sum / this.documents.size());
    }


    public void setQuestion_type(int question_type) {
        this.question_type = question_type;
    }


    /*
   *
   * this will generate the segments for each document in a form
    */
    public void generateFormDocumentsSegments() throws IOException {

        ArrayList<Segment> tempTopSegmentsByOrder = new ArrayList<>();
        String[] questionKeyPhrases = this.getKeyPhrases();
        int orderSegmentsToTake = (int) Math.ceil((double) ConfigAE.topN.DEFINITION / (double) this.documents.size());
        for (Document document : this.documents) {
            document.generateDocumentSegments(questionKeyPhrases, null);
            document.calculateSegmentsRanks(this);
            document.setSegmentsOrder();

            if (ConfigM.VERBOSE_LOG) {
                document.log(ConfigM.LogFolders.PASSAGE_EXTRACTION + "/" + document.getDocName() + "/" + document.getDocName() + ".log");
                document.logSegments(ConfigM.LogFolders.PASSAGE_EXTRACTION + "/" + document.getDocName() + "/" + document.getDocName() + "_segments_BEFORE_FILTRATION");
            }


            document.removeIrrelevantSegments();

            if (ConfigM.VERBOSE_LOG) {
                document.logSegments(ConfigM.LogFolders.PASSAGE_EXTRACTION + "/" + document.getDocName() + "/" + document.getDocName() + "_segments_AFTER_FILTRATION");
            }

            //Get top N segments according to their order in the document
            Collections.sort(document.segments, (o1, o2) -> {
                double diff = (o2.getSegmentOrder() - o1.getSegmentOrder());
                return diff != 0 ? (diff > 0 ? -1 : 1) : 0;
            });

//            tempTopSegmentsByOrder.addAll(document.segments.subList(0, ConfigAE.TOP_SEGMENTS_BY_ORDER));
            if (document.segments.size() > orderSegmentsToTake) {
                tempTopSegmentsByOrder.addAll(document.segments.subList(0, orderSegmentsToTake));
            } else {
                tempTopSegmentsByOrder.addAll(document.segments);
            }

//            //just printing
//            System.out.println("*************After segmentation process*************");
//            PrintWriter writer = null;
//            try {
//                writer = new PrintWriter(new FileOutputStream(
//                        new File("out.txt"),
//                        true /* append = true */));
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            for (Segment segment: document.segments) {
//                writer.println(segment.text);
//                writer.println("Rank is :" + segment.getRank());
//                writer.println("types are is :" + segment.findSegmentTypes());
//
//
//                writer.println("==================================================");
//            }
//            writer.println("*****************Finshed the current Document************");
//            writer.close();
        }

        //filling documents in form.topPassages ArrayList
        ArrayList<Segment> tempTopSegments = new ArrayList<Segment>();
        for (Document document : this.documents) {
            tempTopSegments.addAll(document.getSegments());
        }
        Collections.sort(tempTopSegments);
        this.setTopSegmentsByRank(tempTopSegments);//best segments in all documents

        if (ConfigM.VERBOSE_LOG) {
            for (Segment segment : this.topSegmentsByRank) {
                segment.log(ConfigM.LogFolders.PASSAGE_EXTRACTION + "/TOP_SEGMENTS_BY_RANK/" + segment.getSerialNum() + ".log");
            }
        }


        Collections.sort(tempTopSegmentsByOrder);
        this.setTopSegmentsByOrder(tempTopSegmentsByOrder);

        if (ConfigM.VERBOSE_LOG) {
            for (Segment segment : this.topSegmentsByOrder) {
                segment.log(ConfigM.LogFolders.PASSAGE_EXTRACTION + "/TOP_SEGMENTS_BY_ORDER/" + segment.getSerialNum() + ".log");
            }
        }

    }

    public void setNormalizedText(String normalizedText) {
        this.normalizedText = normalizedText;
    }

    public String getNormalizedText() {
        return normalizedText;
    }

    public ArrayList<Segment> getTopSegmentsByRank() {
        return topSegmentsByRank;
    }

    public void setTopSegmentsByRank(ArrayList<Segment> topSegmentsByRank) {
        this.topSegmentsByRank = topSegmentsByRank;
    }


    public void extractAnswer() {
        int topSegmentsSize = this.topSegmentsByRank.size();
        switch (this.question_type) {

            case ConfigQT.QT_LIST:
                for (int i = 0; i < ConfigAE.topN.LIST; i++) {
                    try {
                        Segment segment = this.topSegmentsByRank.get(i);
                        this.answers.add(new Answer(segment.getText(), segment.getRank()));
                    } catch (Exception e) {
                        break;
                    }
                }
                break;

            case ConfigQT.QT_NUMERIC:
                int topNNumeric = ConfigAE.topN.NUMERIC;
                for (int i = 0; i < topNNumeric; i++) {

                    try {
                        Segment segment = this.topSegmentsByRank.get(i);
                        String segmentText = segment.getText();
                        String[] segmentSentences = segmentText.trim().split("[:\\n\\.]");

                        Answer bestAnswer = new Answer();
                        double bestCosine = -1;
                        for (String sentence : segmentSentences) {
                            //Answer dummyAnswer = Answer(sentence);
                            if (HelpersM.regexCount("\\d+", sentence) > 1) {// the sentence has a number
                                double cosSim = HelpersDR.cosineSimilarity(QuestionPreprocessing.preProcessInput(sentence).get(ConfigP.Keys.NormalizedText_WithoutStoppingWords_WithoutALT3reef), String.join(" ", this.getKeyPhrases()));
                                if (cosSim > bestCosine) {
                                    bestAnswer.setText(sentence);
                                    bestAnswer.setRank(cosSim);
                                    bestCosine = cosSim;
                                }
                            }
                        }
                        if (bestCosine == -1) {
                            topNNumeric += 1;
                        } else {
                            this.answers.add(bestAnswer);
                        }

                    } catch (Exception e) {
                        break;
                    }

                }
                Collections.sort(this.answers);
                break;

            case ConfigQT.QT_PARAGRAPH:
                for (int i = 0; i < ConfigAE.topN.DEFINITION; i++) {
                    try {
                        Segment segment = this.topSegmentsByOrder.get(i);
                        this.answers.add(new Answer(segment.getText(), segment.getRank()));
                    } catch (Exception e) {
                        break;
                    }
                }
                break;
        }

        if (ConfigM.VERBOS) {
            this.printAnswers();
        }

    }

    public void printAnswers() {
        System.out.println(this.answers);
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public void calculateDocumentsRanks() throws IOException {
        for (Document document : this.documents) {
            double contentRank = DocumentRanking.getDocumentRank(document.text, this.normalizedText);
            document.setContentRank(contentRank);
        }
    }


    public void logDocuments(String folderPath) {
        for (Document document : this.documents) {
            document.log(folderPath + "/" + document.getDocName() + ".log");
        }

    }
}
