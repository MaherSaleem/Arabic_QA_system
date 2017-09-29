package com.AQAS.document_retrieval;



import java.io.IOException;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) throws IOException {

        String query = "ما هي أعراض مرض السكري؟";
        //Specify the number of search pages result to be used.
        int searchNumOfPages = 2;

        ArrayList<String> searchResultURLs = HelpersD.getLinks(query , searchNumOfPages);


        //printing the links
        if (ConfigD.VERBOS) {
            System.out.println("All Links:");
            for (String s : searchResultURLs) {
                System.out.println(s);
            }
        }


    }
}
