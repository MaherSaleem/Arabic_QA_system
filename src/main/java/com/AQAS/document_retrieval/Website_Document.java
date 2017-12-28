package com.AQAS.document_retrieval;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Website_Document {

    public String websiteContentSelector;
    public ArrayList<String> DocumentLinks;
    public String sourceWebsite;

    public void removeDuplicatedDocumentLinks(ArrayList<String> a){
//        ArrayList<String> al = a;
//        // add elements to al, including duplicates
//        Set<String> hs = new HashSet<String>();
//        hs.addAll(al);
//        al.clear();
//        al.addAll(hs);
        this.DocumentLinks.removeAll(a);
    }

    public String getWebsiteContentSelector() {
        return websiteContentSelector;
    }

    public void setWebsiteContentSelector(String websiteContentSelector) {
        this.websiteContentSelector = websiteContentSelector;
    }

    public ArrayList<String> getDocumentLinks() {
        return DocumentLinks;
    }

    public void setDocumentLinks(ArrayList<String> documentLinks) {
        DocumentLinks = documentLinks;
    }

    public String getSourceWebsite() {
        return sourceWebsite;
    }

    public void setSourceWebsite(String sourceWebsite) {
        this.sourceWebsite = sourceWebsite;
    }
}
