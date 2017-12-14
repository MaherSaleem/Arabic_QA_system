package com.AQAS.passages_segmentation;

import com.AQAS.main.HelpersM;

public class PassageSegmentation {


    public static boolean hasKeyPhrases(String sentence, String[] keyPhrases) {
        return HelpersM.stringHasAnyOfArray(sentence, keyPhrases);
    }

    public static boolean isEnoughSegment(int startIndex, int endIndex) {
        return startIndex - endIndex > ConfigPS.ENOUGH_THRESHOLD;
    }

}
