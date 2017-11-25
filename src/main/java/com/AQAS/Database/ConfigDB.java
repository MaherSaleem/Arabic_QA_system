package com.AQAS.Database;

import java.util.ArrayList;

public class ConfigDB {

    public static final String packagePath = ".\\src\\main\\java\\com\\AQAS\\Database\\";

    public static final ArrayList<Question> normalArabicDataSet = NormalArabicDataset.trainingQuestions;
    public static final ArrayList<Question> palestinianArabicDataset = PalestinianArabicDataset.trainingQuestions;
    public static final ArrayList<Question> tempDataSet = com.AQAS.Database.tempDataSet.trainingQuestions;


    //configuration to choose which dataset to use for training
    public static final ArrayList<Question> trainingQuestions = ConfigDB.tempDataSet;

}
