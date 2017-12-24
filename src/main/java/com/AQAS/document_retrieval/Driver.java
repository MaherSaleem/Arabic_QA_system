package com.AQAS.document_retrieval;


import com.AQAS.Database.Document;
import com.AQAS.main.ConfigM;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import static com.AQAS.document_retrieval.HelpersD.closeWebDriver;
import static com.AQAS.document_retrieval.HelpersD.openWebDriver;

public class Driver {

    public static void main(String[] args) throws IOException {


        openWebDriver();

        HashMap<String, String> out = QuestionPreprocessing.preProcessInput(ConfigM.query);
        String normalizedQuery = out.get(ConfigP.Keys.NormalizedText);

        ArrayList<Document> documents  = DocumentRetrieval.getDocumentsByQuery(normalizedQuery);

        closeWebDriver();

    }
}
