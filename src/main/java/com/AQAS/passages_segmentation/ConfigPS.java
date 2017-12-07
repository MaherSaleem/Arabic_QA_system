package com.AQAS.passages_segmentation;

public class ConfigPS {


    public static final int ENOUGH_THRESHOLD = 2;
    public static final double SEGMENT_THRESHOLD = 1;
    public static int STATE_INITIAL = 1;
    public static int STATE_END = 2;
    public static int STATE_NOT_ENOUGH = 3;
    public static int STATE_LAST_SENTENCE = 4;

    public  interface weights{
        double A = 10;//questionTypeScore
        double B = 1;//keyPhrasesScore
        double C = 0.4;//documentRankScore
    }

}
