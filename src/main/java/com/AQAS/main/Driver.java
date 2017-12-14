package com.AQAS.main;


import com.AQAS.Database.Form;
import com.AQAS.Database.Segment;

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



//        ArrayList<Segment> segments = new ArrayList<>();
//        segments.add(new Segment("مرحيا\n" +
//                "انا اسمي \n" +
//                "ماهر سليم\n", 3.5, 2));
//        segments.add(new Segment("مرحيا", 4.5, 1));
//        segments.add(new Segment("مرحيا", 6.5, 5));
//        segments.add(new Segment( "مرحيا", 3.500000, 2 ));
//        for (Segment segment:  segments) {
//            System.out.printf("segments.add(new Segment( \"%s\", %f, %d ));\n",  segment.getText().replaceAll("[\r\n]+", "\" + \r\n \" "), segment.getRank(), segment.getSegmentOrder());
//        }


        intializeProb();
        openWebDriver();

        if (ConfigM.BUILD_DB) {
            storeTestingData();
        }
//        getQuestionTypeUsingSVM(new ArrayList<>(Arrays.asList(ConfigM.query)));



//        Form form = retrieveDocuments(ConfigM.query);

        /*
        *
        * This function will get the doucments, and will save the rank for each of them
         */
        Form form = retrieveDocuments(ConfigM.query);//


        ArrayList<Integer> result = getQuestionTypeUsingSVM(new ArrayList<>(Arrays.asList(ConfigM.query)));
        int questionType = result.size()>0 ? result.get(0) : -1;
        form.setQuestion_type(questionType);



        System.out.println("Before sorting Documents: " + form);

        Collections.sort(form.documents);// uses CompareTo in order to sort the document according to their contentRank

//        System.out.println("After sorting Documents: " + form);

        form.removeIrrelevantDocuments();

        form.generateFormDocumentsSegments();
//        for (Segment segment:  form.getTopSegments()) {
//            System.out.printf("segments.add(new Segment( \"%s\", %f, %d ));\n",  segment.getText().replaceAll("[\r\n]+", "\" + \r\n \" "), segment.getRank(), segment.getSegmentOrder());
//        }
        System.out.println(form.question_type);
        form.extractAnswer();
//        System.out.println("After Remove irrelevant: " + form);

        closeWebDriver();
    }
}
