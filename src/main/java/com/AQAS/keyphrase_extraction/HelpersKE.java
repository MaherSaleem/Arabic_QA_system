package com.AQAS.keyphrase_extraction;

import KPminer.Extractor;
import com.AQAS.question_processessing.ArabicStemmer;
import com.AQAS.question_processessing.utilities.TrainedTokenizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HelpersKE {

    public static Extractor extractor = null;

    public static String[] getKeyPhrases(String query) {

        if (ConfigKE.KEYPHRASE_STRATEGY == ConfigKE.KPMINER) {
            return kpMiner(query);
        } else {
            return kpAlternative(query);
        }

    }

    //TODO: implement this
    private static String[] kpAlternative(String query) {

        TrainedTokenizer tok = new TrainedTokenizer();
        String[] tokens = new String[]{};
        try {
            tokens = tok.tokenize(query);
        } catch (IOException e) {
            e.printStackTrace();
            return tokens;
        }
        ArrayList<String> KPs = new ArrayList<>(Arrays.asList(tokens));

        for (int i = 0; i < tokens.length; i++) {
            if (i < tokens.length - 1) {
                String kp = tokens[i] + " " + tokens[i + 1];
                KPs.add(kp);
            }
        }

        return KPs.toArray(new String[0]);
    }


    private static String[] kpMiner(String query) {
        if (extractor == null) {
            extractor = new Extractor();
            extractor.init();
        }

        String[] topKeys = extractor.getTopN(ConfigKE.KP_NUMBER, query + " و ك", true);

        return topKeys;
    }


}
