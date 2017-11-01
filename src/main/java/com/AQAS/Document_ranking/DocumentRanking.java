package com.AQAS.Document_ranking;

import com.AQAS.keyphrase_extraction.HelpersKE;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import com.AQAS.POS.*;
import javafx.util.Pair;

public class DocumentRanking {

    public static double getDocumentRank(String document, String query) {

        double rank = 0;

        String[] keyPhrases = HelpersKE.getKeyPhrases(query + " و ك");

        HashMap<String, Double> keyPhrasesFrequencies = HelpersDR.getWordsFreqInDoc(keyPhrases, document);
        double cosineSimilarity = HelpersDR.cosineSimilarity(query, document);


        double keyPhrasesScore = 0;
        for (String keyPhrase : keyPhrases) {
            double keyPhraseScore=0;
            double keyPhraseFreq = keyPhrasesFrequencies.get(keyPhrase);
            int keyPhraseLength = HelpersDR.getSentenceWordsCount(keyPhrase);
            double properNameScore = getProperNameScore(keyPhrase);
            keyPhraseScore = keyPhraseFreq * Math.sqrt(keyPhraseLength) * properNameScore;
//            System.out.println(keyPhrase + " " + keyPhraseFreq + " " +  Math.sqrt(keyPhraseLength)  + " " +  properNameScore);
            keyPhrasesScore += keyPhraseScore;
        }
        if(keyPhrasesScore == 0){
            return  cosineSimilarity;
        }

        return keyPhrasesScore * cosineSimilarity;
    }
    //TODO: @Aseel, POS tagger thing
    public static double getProperNameScore(String keyPrase){
        StanfordPOS pos = new StanfordPOS(keyPrase);
        ArrayList<Pair<String,String>> returnPOS = pos.tokenPOS();

        if(returnPOS.size() > 1){
            int flag = 2;
            for(int i =0; i< returnPOS.size(); i++){
                Pair<String,String> POS_pair = returnPOS.get(i);
                String word = POS_pair.getKey();
                String word_pos = POS_pair.getValue();
                if(! word_pos.contains("NN"))
                    flag = 1;
//                System.out.println("POS for word "+ word +" is " + word_pos);
            }
            return flag;
        }
        else{
            Pair<String,String> POS_pair = returnPOS.get(0);
            String word = POS_pair.getKey();
            String word_pos = POS_pair.getValue();
//            System.out.println("POS for word "+ word +" is " + word_pos);
            if(POS_pair.getValue().contains("NN")){
                return 2;
            }
            else return 1;
        }
    }
}
