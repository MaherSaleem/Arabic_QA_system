package com.AQAS.question_processessing;

import com.AQAS.question_processessing.utilities.AraNormalizer;
import com.AQAS.question_processessing.utilities.DiacriticsRemover;
import com.AQAS.question_processessing.utilities.PunctuationsRemover;

import java.util.HashMap;

public class QuestionPreprocessing {

    public static HashMap<String ,String> preProcessInput(String query){


        HashMap <String, String> results = new HashMap<String, String>();

        AraNormalizer arn = new AraNormalizer();
        DiacriticsRemover diacriticsRemover = new DiacriticsRemover();
        PunctuationsRemover punctuationsRemover = new PunctuationsRemover();
        String normalizedText = arn.normalize(query);
        normalizedText = diacriticsRemover.removeDiacritics(normalizedText);
        normalizedText = punctuationsRemover.removePunctuations(normalizedText);

        // System.out.println("Text: " + query);
        //System.out.println("Normalized text: " + normalizedText);

        ArabicStemmer arabicStemmer = new ArabicStemmer(normalizedText);
        String normlized_Sttemed_Query = arabicStemmer.outputFilePanelStemButtonActionPerformed();
//        System.out.println(arabicStemmer.stemmedTextLists.toString());


        results.put(ConfigP.Keys.NormalizedText , normalizedText);
        results.put(ConfigP.Keys.StemmedText , normlized_Sttemed_Query); // TODO Aseel  please do this
        return results;
    }
}
