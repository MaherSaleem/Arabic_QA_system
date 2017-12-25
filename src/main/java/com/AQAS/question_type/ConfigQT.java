package com.AQAS.question_type;

import com.AQAS.Database.ConfigDB;
import com.AQAS.Database.Question;
import com.AQAS.question_processessing.ConfigP;

import java.util.ArrayList;

public final class ConfigQT {


    public static final boolean VERBOS = false;

    public static final String packagePath = ".\\src\\main\\java\\com\\AQAS\\question_type\\";

    public final class Keys {

    }


    public static final int QT_NUMERIC = 0;
    public static final int QT_LIST = 1;
    public static final int QT_PARAGRAPH = 2;

    //IF YOU WANNA ADD NEW QUESTION TYPE,PLEASE ADD ITS INDEX AS CONSTANT ABOVE :)
    public static final String[] QT_texts = {"Numeric", "List", "Paragraph"};

    public static final int CSV_FILE = 0;
    public static final int ARFF_FILE = 1;
    public static final int MANUALLY_DATA = 2;
    public static final int FILE_TYPE = ConfigQT.MANUALLY_DATA;

    //Classifier model parameters
    public static final boolean BUILD_MODEL = false;
    public static final String MODEL_FILE_NAME = "machine_learning_model\\smo.model";
    public static final String DATA_FILE_NAME = "machine_learning_model\\instances.model";


    //FeatureVector filter parameters
    public static final int MIN_NGRAM = 1;
    public static final int MAX_NGRAM = 2;
    public static final String TRAINING_INPUT_FILE = ConfigQT.packagePath + "test_files\\training.arff";

    public static final boolean save_filtered_data_to_csv = false;
    public static final String FILTERED_DATA_FILE = ConfigQT.packagePath + "filtered_data.csv";

    //config to choose which dataset to use for training
    public static  final ArrayList<Question> trainingQuestions = ConfigDB.normalArabicDataSet;

    public static  final String textInputPreprcessedKey = ConfigP.Keys.NormalizedText;

}
