package com.AQAS.keyphrase_extraction;

import KPminer.Extractor;

public class HelpersKE {

    public static Extractor extractor = null;

    public static String[] getKeyPhrases(String query){

        if(ConfigKE.KEYPHRASE_STRATEGY == ConfigKE.KPMINER){
            return kpMiner(query);
        }
        else{
            return kpAlternative(query);
        }

    }

    //TODO: implement this
    private static String[] kpAlternative(String query) {

        return null;
    }


    private static String[] kpMiner(String query){
        if(extractor == null){
            extractor = new Extractor();
            extractor.init();
        }

        String [] topKeys = extractor.getTopN(ConfigKE.KP_NUMBER,query + " و ك",true);

        return topKeys;
    }


}