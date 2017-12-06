package com.AQAS.Document_ranking;

import com.AQAS.keyphrase_extraction.ConfigKE;
import com.AQAS.keyphrase_extraction.HelpersKE;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.AQAS.POS.*;
import com.AQAS.synonyms.FindSynonyms;
import javafx.util.Pair;

public class DocumentRanking {

    public static double getDocumentRank(String document, String query) {



        String[] keyPhrases = HelpersKE.getKeyPhrases(query);

        if (ConfigKE.VERBOS) {
            System.out.println("Keyphrases List is :");
            System.out.println(Arrays.toString(keyPhrases));
        }
        ArrayList<String> keyPhraseArrayList = new ArrayList<String>(Arrays.asList(keyPhrases));
        for (String keyPhrase : keyPhrases) {
            String[] keyPhraseSynonyms = FindSynonyms.getWordSynonyms(keyPhrase);
            if (ConfigKE.VERBOS) {
                System.out.println("Synonyms for keyphrase \"" + keyPhrase + "\" are: ");
                System.out.println(Arrays.asList(keyPhraseSynonyms));
            }
            System.out.println("all keyphrases and synonyms");
            keyPhraseArrayList.addAll(Arrays.asList(keyPhraseSynonyms));
        }
        if (ConfigKE.VERBOS) {
            keyPhrases = keyPhraseArrayList.toArray(new String[keyPhraseArrayList.size()]);
        }
        HashMap<String, Double> keyPhrasesFrequencies = HelpersDR.getWordsFreqInDoc(keyPhrases, document);
        double cosineSimilarity = HelpersDR.cosineSimilarity(query, document);



        double keyPhrasesScore = 0;
        for (String keyPhrase : keyPhrases) {

            double keyPhraseScore = 0;
            double keyPhraseFreq = keyPhrasesFrequencies.get(keyPhrase);
            int keyPhraseLength = HelpersDR.getSentenceWordsCount(keyPhrase);
            double properNameScore = getProperNameScore(keyPhrase);
            System.out.println("KEYPHRASE: "+keyPhrase);
            System.out.println("keyphraseFreq: "+keyPhraseFreq);
            System.out.println("keyPhraseLength: "+keyPhraseLength);
            System.out.println("properNameScore: "+properNameScore);
            keyPhraseScore = keyPhraseFreq  * Math.sqrt(keyPhraseLength) * properNameScore;
            keyPhrasesScore += keyPhraseScore;
        }
        if (keyPhrasesScore == 0) {
            return cosineSimilarity;
        } else {
            return ConfigDR.a * keyPhrasesScore + ConfigDR.b * cosineSimilarity;
        }

    }

    public static double getProperNameScore(String keyPrase) {
        StanfordPOS pos = new StanfordPOS(keyPrase);
        ArrayList<Pair<String, String>> returnPOS = pos.tokenPOS();

        if (returnPOS.size() > 1) {
            int flag = 2;
            for (int i = 0; i < returnPOS.size(); i++) {
                Pair<String, String> POS_pair = returnPOS.get(i);
                String word = POS_pair.getKey();
                String word_pos = POS_pair.getValue();
                if (!word_pos.contains("NN"))
                    flag = 1;
//                System.out.println("POS for word "+ word +" is " + word_pos);
            }
            return flag;
        } else {
            Pair<String, String> POS_pair = returnPOS.get(0);
            String word = POS_pair.getKey();
            String word_pos = POS_pair.getValue();
//            System.out.println("POS for word "+ word +" is " + word_pos);
            if (POS_pair.getValue().contains("NN")) {
                return 2;
            } else return 1;
        }
    }
}
