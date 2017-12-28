package com.AQAS.main;

import com.AQAS.Database.Answer;
import com.AQAS.Database.Document;
import com.AQAS.Database.Form;
import com.AQAS.Database.HelpersDB;
import com.AQAS.document_retrieval.DocumentRetrieval;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static com.AQAS.Database.HelpersDB.intializeProb;
import static com.AQAS.Database.HelpersDB.storeTestingData;
import static com.AQAS.document_retrieval.HelpersD.closeWebDriver;
import static com.AQAS.document_retrieval.HelpersD.openWebDriver;
import static com.AQAS.question_type.HelpersQT.getQuestionTypeUsingSVM;

public class ServerDriver {
    public static void main(String[] args) throws IOException {


        String questionQuery = args[0];
        String fileName = args[1];
        BufferedWriter outJsonFile = null;
        Logger logger = new Logger(fileName);
        logger.log(ConfigM.LogFolders.PREPROCESSING+"/file.log", "تجربة مسج جديدة");

        //reading query
        File fileDir = new File(fileName);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileDir), StandardCharsets.UTF_8));
        questionQuery = in.readLine();
        in.close();



        //our system code
        intializeProb();
        Form form = Form.getInstance();
        if (!ConfigM.DATABASE_QUERY) {
            openWebDriver();
            form.setText(questionQuery);
            HashMap<String, String> out = QuestionPreprocessing.preProcessInput(form.getText());
            String normalizedQuery = out.get(ConfigP.Keys.NormalizedText); //TODO check to user normalized_stemmed or only normalized
            ArrayList<Document> retrievedDocuments = DocumentRetrieval.getDocumentsByQuery(normalizedQuery);
            form.setNormalizedText(normalizedQuery);
            form.setDocuments(retrievedDocuments);
        }else{
            form = HelpersDB.getFormById(ConfigM.questionId);
            HashMap<String, String> out = QuestionPreprocessing.preProcessInput(form.getText());
            String normalizedQuery = out.get(ConfigP.Keys.NormalizedText); //TODO check to user normalized_stemmed or only normalized
            form.setNormalizedText(normalizedQuery);
            form.getDocuments();
        }

        System.out.println("***1");

        form.calculateDocumentsRanks();
//        Form form = retrieveDocuments(ConfigM.query);//
        System.out.println("***2");

        //TODO use the saved model of machine learning
        ArrayList<Integer> result = getQuestionTypeUsingSVM(new ArrayList<>(Arrays.asList(form.getNormalizedText())));
        System.out.println("***3");
        int questionType = result.size() > 0 ? result.get(0) : -1;
        form.setQuestion_type(questionType);
        System.out.println(form.question_type);


//        System.out.println("Before sorting Documents: " + form);

        System.out.println("***4");
        Collections.sort(form.documents);// uses CompareTo in order to sort the document according to their contentRank

        System.out.println("After sorting Documents: " + form);

        System.out.println("***5");
        form.removeIrrelevantDocuments();

        System.out.println("***6");
        form.generateFormDocumentsSegments();
        System.out.println("***7");
        form.extractAnswer();
//        System.out.println("After Remove irrelevant: " + form);
        closeWebDriver();
        System.out.println("***8");

        //end of our  system code



        try {
            //writing json to file
            outJsonFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));
            outJsonFile.write(getJson(questionQuery));
//            out.write(questionQuery);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (outJsonFile != null) {
                outJsonFile.close();
            }
        }
    }

    public static String getJson(String query) {

        ArrayList<Answer> answers = Form.getInstance().getAnswers();

//        String[] answers = new String[]{"maher", "saleem", "abd", "khdeir"};
        String ret = "{\"answers\":[";

        for (int i = 0; i < answers.size(); i++) {

            if (i == answers.size() - 1) {
                ret += String.format("\"%s\"", answers.get(i).text.replace("\"", "'"));
            } else {
                ret += String.format("\"%s\",", answers.get(i).text.replace("\"", "'"));
            }
        }
        ret += String.format("],\"query\":\"%s\"}", query);

        System.out.println(ret);
        return ret;
    }
}
