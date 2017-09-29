package com.AQAS.question_processessing;

import java.util.HashMap;

public class Driver {

    // execution starts here
    public static void main(String[] args) {
        // create the stemmer
        String query = "ما هي أعراض مرض السكري القوي ؟";
        HashMap<String, String> out = QuestionPreprocessing.preProcessInput(query);
        System.out.println("input query : " + query);
        System.out.println("normalized  query : " +out.get(ConfigP.Keys.NormalizedText));
        System.out.println("stemmed query : " +out.get(ConfigP.Keys.StemmedText));


    }
}
