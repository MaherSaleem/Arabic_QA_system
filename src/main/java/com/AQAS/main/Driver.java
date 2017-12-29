package com.AQAS.main;


import com.AQAS.Database.Document;
import com.AQAS.Database.Form;
import com.AQAS.Database.HelpersDB;
import com.AQAS.document_retrieval.DocumentRetrieval;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;

import static com.AQAS.Database.HelpersDB.intializeProb;
import static com.AQAS.Database.HelpersDB.storeTestingData;
import static com.AQAS.document_retrieval.HelpersD.closeWebDriver;
import static com.AQAS.document_retrieval.HelpersD.openWebDriver;
import static com.AQAS.question_type.HelpersQT.getQuestionTypeUsingSVM;

public class Driver {

    public static void main(String[] args) throws IOException {


        intializeProb();

        if (ConfigM.BUILD_DB) {
            storeTestingData();
        }

        /*
         *
         * This function will get the doucments, and will save the rank for each of them
         */

        Form form = Form.getInstance();
        if (!ConfigM.DATABASE_QUERY) {
            openWebDriver();
            form.setText(ConfigM.query);
            HashMap<String, String> out = QuestionPreprocessing.preProcessInput(form.getText());
            String normalizedQuery = out.get(ConfigP.Keys.NormalizedText_WithoutStoppingWords_WithoutALT3reef);
            ArrayList<Document> retrievedDocuments = DocumentRetrieval.getDocumentsByQuery(normalizedQuery);
            form.setNormalizedText(normalizedQuery);
            form.setDocuments(retrievedDocuments);
        }else{
            form = HelpersDB.getFormById(ConfigM.questionId);
            HashMap<String, String> out = QuestionPreprocessing.preProcessInput(form.getText());
            String normalizedQuery = out.get(ConfigP.Keys.NormalizedText_WithoutStoppingWords_WithoutALT3reef);
            form.setNormalizedText(normalizedQuery);
            form.getDocuments();
        }

        System.out.println("***1");

        form.calculateDocumentsRanks();
//        Form form = retrieveDocuments(ConfigM.query);//
        System.out.println("***2");

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
    }
}
