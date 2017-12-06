package com.AQAS.main;

import com.AQAS.Database.Document;
import com.AQAS.Database.Form;
import com.AQAS.Document_ranking.DocumentRanking;
import com.AQAS.document_retrieval.DocumentRetrieval;
import com.AQAS.document_retrieval.Website_Document;
import com.AQAS.question_processessing.ConfigP;
import com.AQAS.question_processessing.QuestionPreprocessing;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

public class HelpersM {

    public static Form retrieveDocuments(int form_id) {
        Form form = new Form(form_id);
        form.getDocuments();
        return form;
    }


    public static Form retrieveDocuments(String query_string) throws IOException {
        HashMap<String, String> out = QuestionPreprocessing.preProcessInput(query_string);

        String normalizedQuery = out.get(ConfigP.Keys.NormalizedText);
        Form form = new Form(query_string);
        ArrayList<Website_Document> website_documents = DocumentRetrieval.getLinksOfAllWebsitesByQuery(form.text, ConfigM.searchNumOfPages);

        //printing the links
        if (ConfigM.VERBOS) {
            System.out.println("All Links:");
        }

        for (Website_Document website_document : website_documents) {
            int urlOrder = 1;
            for (String url : website_document.DocumentLinks) {
                if (ConfigM.VERBOS) {
                    System.out.println("Link is :" + url);
                }
                String text = DocumentRetrieval.retrieveDocumentText(url , website_document.websiteContentSelector);
                double contentRank = DocumentRanking.getDocumentRank(text,normalizedQuery);
                System.out.println("CR=>"+contentRank);
                //new Logger("log");
                form.documents.add(new Document(url, text,urlOrder++,contentRank));

            }
        }
        return form;
    }

    public static String[] removeStringDuplicates(String [] a){
        return new HashSet<String>(Arrays.asList(a)).toArray(new String[0]);
    }
    public static String[] removeStringDuplicates(ArrayList<String>  a){
        return new HashSet<String>(a).toArray(new String[0]);
    }


}
