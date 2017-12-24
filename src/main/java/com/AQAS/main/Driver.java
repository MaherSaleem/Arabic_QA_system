package com.AQAS.main;


import com.AQAS.Database.Document;
import com.AQAS.Database.Form;
import com.AQAS.document_retrieval.DocumentRetrieval;
import com.AQAS.document_retrieval.HelpersD;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;

import java.io.IOException;
import java.util.*;

import static com.AQAS.Database.HelpersDB.intializeProb;
import static com.AQAS.Database.HelpersDB.storeTestingData;
import static com.AQAS.document_retrieval.HelpersD.closeWebDriver;
import static com.AQAS.document_retrieval.HelpersD.openWebDriver;
import static com.AQAS.question_type.HelpersQT.getQuestionTypeUsingSVM;

public class Driver {

    public static void main(String[] args) throws IOException {

        intializeProb();
        openWebDriver();

        if (ConfigM.BUILD_DB) {
            storeTestingData();
        }

        /*
         *
         * This function will get the doucments, and will save the rank for each of them
         */
        HashMap<String, String> out = QuestionPreprocessing.preProcessInput(ConfigM.query);
        String normalizedQuery = out.get(ConfigP.Keys.NormalizedText); //TODO check to user normalized_stemmed or only normalized
        Form form = new Form(ConfigM.query);
        form.setNormalizedText(normalizedQuery);

        ArrayList<Document> retrievedDocuments = DocumentRetrieval.getDocumentsByQuery(normalizedQuery);
        form.setDocuments(retrievedDocuments);
        form.calculateDocumentsRanks();
//        Form form = retrieveDocuments(ConfigM.query);//

        ArrayList<Integer> result = getQuestionTypeUsingSVM(new ArrayList<>(Arrays.asList(form.getNormalizedText())));
        int questionType = result.size() > 0 ? result.get(0) : -1;
        form.setQuestion_type(questionType);
        System.out.println(form.question_type);


//        System.out.println("Before sorting Documents: " + form);

        Collections.sort(form.documents);// uses CompareTo in order to sort the document according to their contentRank

        System.out.println("After sorting Documents: " + form);

        form.removeIrrelevantDocuments();

        form.generateFormDocumentsSegments();
        form.extractAnswer();
//        System.out.println("After Remove irrelevant: " + form);

        closeWebDriver();
    }
}
