package com.AQAS.synonyms;


public class Driver {

    // execution starts here
    public static void main(String[] args) {
        String[] synonyms = FindSynonyms.getWordSynonyms("وقاية");

        for (String synonym: synonyms) {
            System.out.println(synonym);
        }
    }
}