package com.AQAS.main;


import com.AQAS.Database.Form;
import com.AQAS.Database.Segment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static com.AQAS.Database.HelpersDB.intializeProb;
import static com.AQAS.Database.HelpersDB.storeTestingData;
import static com.AQAS.document_retrieval.HelpersD.closeWebDriver;
import static com.AQAS.document_retrieval.HelpersD.openWebDriver;
import static com.AQAS.main.HelpersM.retrieveDocuments;
import static com.AQAS.question_type.HelpersQT.getQuestionTypeUsingSVM;

public class Driver {

    public static void main(String[] args) throws IOException {

//        Segment segment = new Segment("نمط الحياة الخاطئ: أي اتباع نظام غذائي غير صحي وعدم ممارسة النشاط البدني. \n" +
//                "السمنة: والوزن الزائد. \n" +
//                "ما هي أعراض مرض السكر. \n" +
//                "تتشابه أعراض مرض السكر بنوعيه كثيراً، الا ان بعضها قد يكون مميز أكثر لدى فئة معينة دون الأخرى. \n" +
//                "تتمثل أهم أعراض مرض السكر في:\n" +
//                " 1.الجوع الشديد\n" +
//                "2 .العطش\n" +
//                "كثرة التبول\n" +
//                "فقدان الوزن\n" +
//                "عدم وضوح الرؤية\n" +
//                "التعب والوهن\n" +
//                "شفاء الجروح ببطء. ");
//
//        System.out.println("List: "+segment.isList());
//        System.out.println("Numeric: "+segment.isNumeric());
//        System.out.println("short sentences: "+segment.containsShortSentences());
//
//        System.exit(1);



        intializeProb();
        openWebDriver();

        if (ConfigM.BUILD_DB) {
            storeTestingData();
        }
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


        System.out.println("Before sorting: " + form);

        Collections.sort(form.documents);// uses CompareTo in order to sort the document according to their contentRank

        System.out.println("After sorting: " + form);

        form.removeIrrelevantDocuments();

        System.out.println("After Remove irrelevant: " + form);

        closeWebDriver();
    }
}
