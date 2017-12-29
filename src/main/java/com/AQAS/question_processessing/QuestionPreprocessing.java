package com.AQAS.question_processessing;

import java.io.IOException;

import com.AQAS.main.ConfigM;
import com.AQAS.main.Logger;
import com.AQAS.question_processessing.utilities.AraNormalizer;
import com.AQAS.question_processessing.utilities.DiacriticsRemover;
import com.AQAS.question_processessing.utilities.LightStemmer8;
import com.AQAS.question_processessing.utilities.PunctuationsRemover;
import com.AQAS.question_processessing.utilities.TrainedTokenizer;

import java.util.HashMap;

public class QuestionPreprocessing {

    public static HashMap<String, String> preProcessInput(String query) throws IOException {


        HashMap<String, String> results = new HashMap<String, String>();


        //Initializations
        TrainedTokenizer trainedTokenizer = new TrainedTokenizer();
        LightStemmer8 lightStemmer = new LightStemmer8();
        ArabicStemmer stoppingWordsRemoval = new ArabicStemmer();

        //normalization
        AraNormalizer arn = new AraNormalizer();
        String normalizedText = arn.normalize(query);

        //remove Diacritic (7arakat)
        DiacriticsRemover diacriticsRemover = new DiacriticsRemover();
        normalizedText = diacriticsRemover.removeDiacritics(normalizedText);


        //remove Punctuations and save normalized
        PunctuationsRemover punctuationsRemover = new PunctuationsRemover();
        normalizedText = punctuationsRemover.removePunctuations(normalizedText);


        String normalizedText_WithoutStoppingWords = stoppingWordsRemoval.removeStopWords(normalizedText);

        String normalized_WithoutStoppingWords_WithoutAlfT3reef = "";
        //remove al alt3reef
        String[] normalizedText_WithoutStoppingWords_Tokens = trainedTokenizer.tokenize(normalizedText_WithoutStoppingWords);
        for (String token : normalizedText_WithoutStoppingWords_Tokens) {
            normalized_WithoutStoppingWords_WithoutAlfT3reef += lightStemmer.removeAlfAlt3reef(token) + " ";
        }


        //find stemmed version for every word of normalizedText(after stopping words removed)
        normalizedText_WithoutStoppingWords_Tokens = trainedTokenizer.tokenize(normalizedText_WithoutStoppingWords);
        String normalized_WithoutStoppingWords_Stemmed = "";
        for (String token : normalizedText_WithoutStoppingWords_Tokens) {
            normalized_WithoutStoppingWords_Stemmed += lightStemmer.findStem(token) + " ";
        }

        results.put(ConfigP.Keys.NormalizedText_WithoutStoppingWords_WithALT3reef, normalizedText_WithoutStoppingWords);
        results.put(ConfigP.Keys.NormalizedText_WithoutStoppingWords_WithoutALT3reef, normalized_WithoutStoppingWords_WithoutAlfT3reef);//without Al alt3reef, without stopping words

        results.put(ConfigP.Keys.NormalizedText_WithStoppingWords_WithAlT3reef, normalizedText);// with Al alt3reef,
        results.put(ConfigP.Keys.StemmedText, normalized_WithoutStoppingWords_Stemmed);

        /*
        * Machine Learning Training:
        * Search in websites
        */
        if (ConfigM.VERBOSE_LOG) {
            Logger.getInstance().log(ConfigM.LogFolders.PREPROCESSING + "/normalization_and stemming.log", "Normalized with stopping Words with AL:\n" + results.get(ConfigP.Keys.NormalizedText_WithStoppingWords_WithAlT3reef));
            Logger.getInstance().log(ConfigM.LogFolders.PREPROCESSING + "/normalization_and stemming.log", "Normalized without stopping Words with AL:\n" + results.get(ConfigP.Keys.NormalizedText_WithoutStoppingWords_WithALT3reef));
            Logger.getInstance().log(ConfigM.LogFolders.PREPROCESSING + "/normalization_and stemming.log", "Normalized without stopping Words without AL:\n" + results.get(ConfigP.Keys.NormalizedText_WithoutStoppingWords_WithoutALT3reef));
            Logger.getInstance().log(ConfigM.LogFolders.PREPROCESSING + "/normalization_and stemming.log", "stemmed without stopping Words:\n" + results.get(ConfigP.Keys.StemmedText));
        }
        if (ConfigM.VERBOS) {
            System.out.println("Normalized with stopping Words with AL:\n" + results.get(ConfigP.Keys.NormalizedText_WithStoppingWords_WithAlT3reef));
            System.out.println("Normalized without stopping Words with AL:\n" + results.get(ConfigP.Keys.NormalizedText_WithoutStoppingWords_WithALT3reef));
            System.out.println("Normalized without stopping Words without AL:\n" + results.get(ConfigP.Keys.NormalizedText_WithoutStoppingWords_WithoutALT3reef));
            System.out.println("stemmed without stopping Words:\n" + results.get(ConfigP.Keys.StemmedText));
        }
        return results;
    }

    public static HashMap<String, String> preProcessDocument(String query) throws IOException {

        HashMap<String, String> results = new HashMap<String, String>();


        //normalization
        AraNormalizer arn = new AraNormalizer();
        String normalizedText = arn.normalize(query, false);

        //remove Diacritic (7arakat)
        DiacriticsRemover diacriticsRemover = new DiacriticsRemover();
        normalizedText = diacriticsRemover.removeDiacritics(normalizedText, false);

        results.put(ConfigP.Keys.NormalizedText_WithStoppingWords_WithAlT3reef_WithPunctuation, normalizedText);
        return results;
    }
}
