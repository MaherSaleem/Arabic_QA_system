package com.AQAS.main;


import com.AQAS.Database.Form;

import java.io.IOException;
import java.util.*;

import static com.AQAS.Database.HelpersDB.intializeProb;
import static com.AQAS.Database.HelpersDB.storeTestingData;
import static com.AQAS.document_retrieval.HelpersD.closeWebDriver;
import static com.AQAS.document_retrieval.HelpersD.openWebDriver;
import static com.AQAS.main.HelpersM.retrieveDocuments;
import static com.AQAS.question_type.HelpersQT.getQuestionTypeUsingSVM;

public class Driver {

    public static void main(String[] args) throws IOException {

        intializeProb();
        openWebDriver();

        if (ConfigM.BUILD_DB) {
            storeTestingData();
        }
//        System.exit(1);
//        getQuestionTypeUsingSVM(new ArrayList<>(Arrays.asList(ConfigM.query)));


        ArrayList<Integer> result = getQuestionTypeUsingSVM(new ArrayList<>(Arrays.asList(ConfigM.query)));
        int questionType = result.size()>0 ? result.get(0) : -1;

//        Form form = retrieveDocuments(ConfigM.query);

        /*
        *
        * This function will get the doucments, and will save the rank for each of them
         */
        Form form = retrieveDocuments("ما هي اعراض مرض السكري");//
        form.setQuestion_type(questionType);
        form.generateFormDocumentsSegments();


        System.out.println("Before ranking: " + form);

        Collections.sort(form.documents);// uses CompareTo in order to sort the document according to their contentRank

        System.out.println("After ranking: " + form);

        form.removeIrrelevantDocuments();

        System.out.println("After Remove irrelevant: " + form);

        closeWebDriver();
    }
}
