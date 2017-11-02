package com.AQAS.synonyms;


import com.AQAS.keyphrase_extraction.keyphraseExtraction;
import com.AQAS.main.HelpersM;

import java.util.ArrayList;

public class Driver {

    // execution starts here
    public static void main(String[] args) {
        String[] synonyms = FindSynonyms.getWordSynonyms("وقاية");

        for (String synonym: synonyms) {
            System.out.println(synonym);
        }
    }
}