package com.AQAS.question_type;

import weka.core.Instances;

import java.util.ArrayList;

public class HelpersQT {

//TODO: make it return list of questions with their type
    public static ArrayList<Integer> getQuestionTypeUsingSVM(ArrayList<String> testingQuestions){
        Instances ngrammedVectors = null;
        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            ngrammedVectors = FeatureVector.applyFilterOnData(testingQuestions);
            result = MachineLearning.buildClassifier(ngrammedVectors);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
