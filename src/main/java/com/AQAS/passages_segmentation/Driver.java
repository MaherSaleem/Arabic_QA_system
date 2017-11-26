package com.AQAS.passages_segmentation;

import com.AQAS.question_processessing.ConfigP;

import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {


        int state = ConfigPS.STATE_INITIAL;
        String documentText = PassageSegmentation.getDocumentText(5);
        String[] keyPhrases = PassageSegmentation.getQueryKeyPhrases("ما هو مرض السكري");

        String documentSentences[] = documentText.split("[؟?!.]");


        //just printing
        for (String sentence : documentSentences) {
            System.out.println(sentence);
            System.out.println("=============================================");

        }

        int sentencesSize = documentSentences.length;
        int startIndex = -1;
        int endIndex = -1;
        ArrayList<String> segments = new ArrayList<>();
        for (int i = 0; i < sentencesSize; i++) {
            String si = documentSentences[i];
            if (PassageSegmentation.hasKeyPhrases(si, keyPhrases) || i == sentencesSize - 1) {
                if (i == sentencesSize - 1){// case of last sentence in document
                    state = ConfigPS.STATE_LAST_SENTENCE;
                }
                if (state == ConfigPS.STATE_INITIAL) {
                    startIndex = i;
                    endIndex = i;
                    state = ConfigPS.STATE_END;
                }
                else {
                    if (state == ConfigPS.STATE_END || state == ConfigPS.STATE_LAST_SENTENCE) {
                        startIndex = endIndex;
                        endIndex = i;
                        if (!PassageSegmentation.isEnoughSegment(startIndex, endIndex) && state != ConfigPS.STATE_LAST_SENTENCE) {
                            state = ConfigPS.STATE_NOT_ENOUGH;
                            continue;
                        }

                    }
                    else if (state == ConfigPS.STATE_NOT_ENOUGH) {
                        endIndex = i;
                    }
                    String segmentString = "";
                    for (int j = startIndex; j < endIndex ; j++) {
                        segmentString += documentSentences[j] + ". " ;
                    }

                    if (state == ConfigPS.STATE_LAST_SENTENCE) {
                        int LastSegmentIndex = segments.size() - 1;
                        segments.set(LastSegmentIndex, segments.get(LastSegmentIndex) + segmentString);
                    }else{
                        segments.add(segmentString);
                    }
                }
            }

        }

        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        for (String sentence : segments) {
            System.out.println(sentence);
            System.out.println("=============================================");

        }
    }
}
