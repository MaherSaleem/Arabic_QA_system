package com.AQAS.Database;

import org.jsoup.Jsoup;

import javax.print.Doc;
import java.io.IOException;

import static com.AQAS.Database.HelpersDB.props;

public class Document implements Comparable<Document> {

    public int id;
    String link;
    String text;
    int form_id;
    double urlRank; // according to the search engine [the order of it]
    double contentRank;

    public Document(String link, String text) {
        this.link = link;
        this.text = text;
    }

    public Document(String link, String text, int form_id) {
        this.link = link;
        this.text = text;
        this.form_id = form_id;
    }

    public Document(String link, String text, double urlRank, double contentRank) {
        this.link = link;
        this.text = text;
        this.urlRank = urlRank;
        this.contentRank = contentRank;
    }

    public Document(int id, String link, String text, int form_id) {
        this.id = id;
        this.link = link;
        this.text = text;
        this.form_id = form_id;
    }

    public int store() {
        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(props.getProperty("LOCAL_SERVER_IP") + "forms/document/" + this.form_id)
                    .data("text", this.text)
                    .data("link", this.link)
                    .userAgent("Mozilla")
                    .post();
            return Integer.parseInt(doc.text());

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public double overAllRank(){
        return this.contentRank;
    }
    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
//                ", link='" + link + '\'' +
//                ", text='" + text + '\'' +
                ", form_id=" + form_id +
                ", urlRank=" + urlRank +
                ", contentRank=" + contentRank +
                '}' + '\n';
    }

    /*
    return ordered document depending on content rank, Sorting descending
     */
    @Override
    public int compareTo(Document o) {
        double diff = (this.contentRank- o.contentRank);
        return  diff != 0 ? ( diff >0 ? -1 : 1): 0;
    }
}
