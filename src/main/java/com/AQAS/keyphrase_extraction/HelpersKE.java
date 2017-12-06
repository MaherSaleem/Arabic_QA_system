package com.AQAS.keyphrase_extraction;

import KPminer.Extractor;

public class HelpersKE {

    public static String[] getKeyPhrases(String query){

        Extractor extractor = new Extractor();
        extractor.init();
        String [] topKeys = extractor.getTopN(ConfigKE.KP_NUMBER,query + " و ك",true);

        return topKeys;
    }


}
