package com.AQAS.question_processessing;

import java.io.IOException;

import com.AQAS.question_processessing.utilities.AraNormalizer;
import com.AQAS.question_processessing.utilities.DiacriticsRemover;
import com.AQAS.question_processessing.utilities.LightStemmer8;
import com.AQAS.question_processessing.utilities.PunctuationsRemover;
import com.AQAS.question_processessing.utilities.TrainedTokenizer;

import java.util.HashMap;

public class QuestionPreprocessing {

    public static HashMap<String ,String> preProcessInput(String query)  throws IOException{


        HashMap <String, String> results = new HashMap<String, String>();

        AraNormalizer arn = new AraNormalizer();
        DiacriticsRemover diacriticsRemover = new DiacriticsRemover();
        PunctuationsRemover punctuationsRemover = new PunctuationsRemover();
        TrainedTokenizer tok=new TrainedTokenizer();
        LightStemmer8 stemmer8 = new LightStemmer8();
        String normalizedText = arn.normalize(query);
        normalizedText = diacriticsRemover.removeDiacritics(normalizedText);
        normalizedText = punctuationsRemover.removePunctuations(normalizedText);

        //System.out.println("Normalized text: " + normalizedText);

        ArabicStemmer arabicStemmer = new ArabicStemmer();
        String[] tokens=tok.tokenize(normalizedText);
        String removedStop = arabicStemmer.removeStopWords(tokens);
        tokens=tok.tokenize(removedStop);
        String normlized_Stemmed_Query="";
        for (int i=0;i<tokens.length;i++)
        {
            String stem=stemmer8.findStem(tokens[i]);
            normlized_Stemmed_Query=normlized_Stemmed_Query+stem+" ";
        }


        normalizedText= removedStop;
//        String normlized_Sttemed_Query = arabicStemmer.outputFilePanelStemButtonActionPerformed();
//        System.out.println(arabicStemmer.stemmedTextLists.toString());


        results.put(ConfigP.Keys.NormalizedText , normalizedText);
        results.put(ConfigP.Keys.StemmedText , normlized_Stemmed_Query);
        return results;
    }
}