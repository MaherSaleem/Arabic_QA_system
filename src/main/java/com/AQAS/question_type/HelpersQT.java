package com.AQAS.question_type;

import com.AQAS.main.ConfigM;
import com.AQAS.main.Logger;
import weka.core.Instances;

import java.io.IOException;
import java.util.ArrayList;

public class HelpersQT {

    //TODO: make it return list of questions with their type
    public static ArrayList<Integer> getQuestionTypeUsingSVM(ArrayList<String> testingQuestions) throws IOException {
        Instances ngrammedVectors = null;
        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            ngrammedVectors = FeatureVector.applyFilterOnData(testingQuestions);
            result = MachineLearning.buildClassifier(ngrammedVectors);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ConfigM.VERBOS) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println("question is:" + testingQuestions.get(i));
                System.out.println("question type is:" + ConfigQT.QT_texts[result.get(i)]);
                System.out.println("=================================");
            }
        }
        if (ConfigM.VERBOSE_LOG) {
            for (int i = 0; i < result.size(); i++) {
                Logger.getInstance().log(ConfigM.LogFolders.PREPROCESSING + "/question_types.log", "question is:" + testingQuestions.get(i));
                Logger.getInstance().log(ConfigM.LogFolders.PREPROCESSING + "/question_types.log", "question type is:" + ConfigQT.QT_texts[result.get(i)]);
                Logger.getInstance().log(ConfigM.LogFolders.PREPROCESSING + "/question_types.log", "=================================");
            }
        }
        return result;
    }

}
