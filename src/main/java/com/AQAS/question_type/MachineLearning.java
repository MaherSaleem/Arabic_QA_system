package com.AQAS.question_type;

import com.AQAS.main.HelpersM;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.SMO;
import weka.core.Instances;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;


/**
 * https://weka.wikispaces.com/Use+WEKA+in+your+Java+code
 */
public class MachineLearning {



    public static ArrayList<Integer> buildClassifier(Instances data) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        try {
            SMO classifier = new SMO();
            data.setClassIndex(0);// set the questionType as the class index for machine learning

            if (ConfigQT.BUILD_MODEL) {
                classifier.buildClassifier(data);
                weka.core.SerializationHelper.write(ConfigQT.packagePath + ConfigQT.MODEL_FILE_NAME, classifier);
            } else {
                InputStream inputStream = HelpersM.getInputStreamFromResrcFile(ConfigQT.MODEL_FILE_NAME);
                classifier = (SMO) weka.core.SerializationHelper.read(inputStream);
                inputStream.close();
//                classifier = (SMO) weka.core.SerializationHelper.read(ConfigQT.packagePath +ConfigQT.MODEL_FILE_NAME);
            }
            for (int i = FeatureVector.originalTrainingSize; i < data.size(); i++) {
                int questionTypeClass = (int)classifier.classifyInstance(data.get(i));
                System.out.println("Classification Class is:" + ConfigQT.QT_texts[questionTypeClass]);
                result.add(questionTypeClass);
            }

            Evaluation eval = new Evaluation(data);
            eval.crossValidateModel(classifier, data, 10, new Random(System.currentTimeMillis()));

            System.out.println("\n(k-folds results):" + eval.toSummaryString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;


    }
}
