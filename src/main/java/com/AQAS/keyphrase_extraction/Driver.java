package com.AQAS.keyphrase_extraction;


import java.io.IOException;
import java.util.HashMap;

import KPminer.*;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;

public class Driver {

    // execution starts here
    public static void main(String[] args) {
        keyphraseExtraction kp = new keyphraseExtraction();
        String query = "ما هي أعراض مرض السكري و أسبابه و سبل الوقاية منه";
        String normalizedQuery = query;
        try{
            normalizedQuery = QuestionPreprocessing.preProcessInput(query).get(ConfigP.Keys.NormalizedText);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ffff");
        }
        System.out.println("NORM: "+normalizedQuery);

        String[] topKeys = HelpersKE.getKeyPhrases(normalizedQuery);

        for (int i = 0; i < topKeys.length; i++) {
            System.out.println(topKeys[i]);
        }


    }
}