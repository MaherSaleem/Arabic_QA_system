package com.AQAS.main;

public final class ConfigM {

    public static final String packagePath = ".\\src\\main\\java\\com\\AQAS\\main\\";

    public final class Keys {
    }

    public interface LogFolders {
        String PREPROCESSING = "preprocessing";
        String DOC_RETRIEVAL = "docRetrieval";
        String PASSAGE_EXTRACTION = "passageExtraction";
        String ANSWER_EXTRACTION = "answerExtraction";

    }

    public static final boolean VERBOS = false;
    public static final boolean VERBOSE_LOG = true;

    public static final boolean BUILD_DB = false;
    public static final boolean DATABASE_QUERY = false;
    public final static int questionId = 2;



    public final static String query = "كم مدة علاج الانفلونزا؟";
    public final static int searchNumOfPages = 1;
}
