package com.AQAS.passages_segmentation;

public class Driver {

    public static void main(String[] args) {


        int state = ConfigPS.STATE_INITIAL;
        String documentText = PassageSegmentation.getDocumentText(5);
        String[] keyPhrases = PassageSegmentation.getQueryKeyPhrases("ما هو مرض السكري");

        String documentSentences[] = documentText.split("\\.");


        //just printing
        for (String sentence : documentSentences) {
            System.out.println(sentence);
            System.out.println("=============================================");

        }

        int sentencesSize = documentSentences.length;
        for (int i = 0; i < sentencesSize; i++) {
            String si = documentSentences[i];
            if (PassageSegmentation.hasKeyPhrases(si, keyPhrases)) {
//                if (state = ConfigPS.STATE_END){
//
//                }
            }

        }

    }
}
