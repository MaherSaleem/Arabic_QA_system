package com.AQAS.passages_segmentation;

import com.AQAS.question_processessing.ConfigP;

import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {

        ArrayList<String> segments = PassageSegmentation.getDocumentSegments(5, "ما هي اعراض مرض السكري", null);
        System.out.println("***************Starting printing the segments*************");
        for (String sentence : segments) {
            System.out.println(sentence);
            System.out.println("=============================================");
        }
    }
}
