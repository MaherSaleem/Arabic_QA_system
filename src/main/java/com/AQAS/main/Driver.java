package com.AQAS.main;


import com.AQAS.document_retrieval.HelpersD;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.HelpersP;
import com.AQAS.question_processessing.QuestionPreprocessing;

import java.util.ArrayList;
import java.util.HashMap;

public class Driver {

    public static void main(String[] args) {


        HashMap<String, String> out = QuestionPreprocessing.preProcessInput(ConfigM.query);

        ArrayList<String> searchResultURLs = HelpersD.getLinks(out.get(ConfigP.Keys.NormalizedText), ConfigM.searchNumOfPages);

        //printing the links
        if (ConfigM.VERBOS) {
            System.out.println("All Links:");
            for (String s : searchResultURLs) {
                System.out.println(s);
            }
        }

    }
}
