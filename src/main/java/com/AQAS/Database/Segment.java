package com.AQAS.Database;

import com.AQAS.Document_ranking.ConfigDR;
import com.AQAS.Document_ranking.DocumentRanking;
import com.AQAS.Document_ranking.HelpersDR;
import com.AQAS.main.ConfigM;
import com.AQAS.main.HelpersM;
import com.AQAS.main.Logger;
import com.AQAS.passages_segmentation.ConfigPS;
import com.AQAS.question_type.ConfigQT;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Segment implements Comparable<Segment> {
    public String text;
    public double rank;
    public int segmentOrder;
    public String serialNum;


    public Segment(String text) {
        this.text = text;
    }

    public Segment(String text, double rank) {
        this.text = text;
        this.rank = rank;
    }

    public Segment(String text, double rank, int segmentOrder) {
        this.text = text;
        this.rank = rank;
        this.segmentOrder = segmentOrder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public int getSegmentOrder() {
        return segmentOrder;
    }

    public void setSegmentOrder(int segmentOrder) {
        this.segmentOrder = segmentOrder;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "text='" + text + '\'' +
                ", rank=" + rank +
                '}';
    }

    public void calculateRank(Form form, Document document) throws IOException {

        int questionType = form.question_type;
        ArrayList<Integer> segmentTypes = this.findSegmentTypes();

        double questionTypeScore = segmentTypes.contains(questionType) ? 1 : 0;
        double keyPhrasesScore = 0;
        double documentRankScore = 0;
        //if segment doesnt contain the question type => rank = 0
        double rank;
//        if (questionTypeScore == 0) {
//            rank = 0;
//        } else {
//            keyPhrasesScore = findKeyPhrasesScore(form.text);
//            documentRankScore = document.overAllRank();
//            rank = ConfigPS.weights.A * questionTypeScore + ConfigPS.weights.B * keyPhrasesScore + ConfigPS.weights.C * documentRankScore;
//        }

        keyPhrasesScore = findKeyPhrasesScore(form.text);
        documentRankScore = document.overAllRank();
        rank = ConfigPS.weights.A * questionTypeScore + ConfigPS.weights.B * keyPhrasesScore + ConfigPS.weights.C * documentRankScore;




        if(ConfigM.VERBOS){
            System.out.println("Quuestion type Score: " + questionTypeScore);
            System.out.println("keyphrase  Score: " + keyPhrasesScore);
            System.out.println("documentRank  Score: " + documentRankScore);
        }


        this.setRank(rank);
    }

    public double findKeyPhrasesScore(String query) throws IOException {

        String[] queryKeyPhrasesWithSynonyms = Form.getInstance().getKeyPhrases();

        HashMap<String, Double> keyPhrasesFrequencies = HelpersDR.getWordsFreqInDoc(queryKeyPhrasesWithSynonyms, this.text);
        double cosineSimilarity = HelpersDR.cosineSimilarity(query, this.text);

        double keyPhrasesScore = 0;
        for (String keyPhrase : queryKeyPhrasesWithSynonyms) {

            double keyPhraseScore = 0;
            double keyPhraseFreq = keyPhrasesFrequencies.get(keyPhrase);
            if(keyPhraseFreq > 0){
                int keyPhraseLength = HelpersM.getSentenceWordsCount(keyPhrase);
                double properNameScore = DocumentRanking.getProperNameScore(keyPhrase);
                keyPhraseScore =  Math.sqrt(keyPhraseLength) * properNameScore;
            }
            keyPhrasesScore += keyPhraseScore;
        }

        return ConfigDR.a * keyPhrasesScore + ConfigDR.b * cosineSimilarity;
    }

    //QT_NUMERIC = 0;
    //QT_LIST = 1;
    //QT_PARAGRAPH = 2;
    public ArrayList<Integer> findSegmentTypes() {
        ArrayList<Integer> types = new ArrayList<Integer>();
        boolean isList = isList();
        if (isList) {
            types.add(ConfigQT.QT_LIST);
        } else if (isNumeric()) {
            types.add(ConfigQT.QT_NUMERIC);
            types.add(ConfigQT.QT_PARAGRAPH);
        } else {
            types.add(ConfigQT.QT_PARAGRAPH);
        }


        return types;
    }

//contains ':\n'
//small sentences
//numbering[1.,2.,3.,...]

    //    Regex to match number then dots
//            (\\d+\\.)
//    " : " then new line
//            (:\\n)
    public boolean isList() {
        int numberThenDotCount = HelpersM.regexCount("\\d+\\s*\\.", this.text);
        int columnThenLineCount = HelpersM.regexCount(":\\s*\\n", this.text);

        return numberThenDotCount > 2 || this.containsShortSentences() || columnThenLineCount >= 1;

    }


//contains number
//TODO:number-to-text[abdul tool] FUTURE_WORK

    //    Regex to match number
//            (\\d+)
    public boolean isNumeric() {

        int numberCount = HelpersM.regexCount("\\d+", this.text);

        return numberCount > 1;
    }

    public boolean containsShortSentences() {

        String sentences[] = this.text.split(".");
        int shortSentencesCount = 0;
        int longestSequence = 0;

        for (String sentence : sentences) {
            int wordsCount = sentence.split("\\s+").length;
            if (3 <= wordsCount && wordsCount <= 5) {
                shortSentencesCount++;
                longestSequence++;
                if (longestSequence == 3) {
                    return true;
                }
            } else {
                longestSequence = 0;
            }
        }
//        return shortSentencesCount > 4;
        return false;

    }

    /*
return ordered document depending on content rank, Sorting descending
 */
    @Override
    public int compareTo(Segment s) {
        double diff = (this.getRank() - s.getRank());
        return diff != 0 ? (diff > 0 ? -1 : 1) : 0;
    }

    public void log(String fileName) {
//        String fileName = filePath+ "/" + this.getSerialNum() + ".log";

        try {
            Logger.getInstance().log(fileName, "Segment Types: " + this.getSegmentTypes());
            Logger.getInstance().log(fileName, "Rank: " + this.rank);
            Logger.getInstance().log(fileName, "Segment Order: " + this.segmentOrder);
            Logger.getInstance().log(fileName, "Text: \n" + this.text);
            Logger.getInstance().log(fileName, "===================================================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSegmentTypes() {
        String STRtypes = "";
        ArrayList<Integer> types = this.findSegmentTypes();
        for (int type : types) {
            switch (type) {
                case 0:
                    STRtypes += "NUMERIC, ";
                    break;
                case 1:
                    STRtypes+="LIST, ";
                    break;

                case 2:
                    STRtypes+="PARAGRAPH";
                    break;
            }
        }
        return STRtypes;
    }


}
