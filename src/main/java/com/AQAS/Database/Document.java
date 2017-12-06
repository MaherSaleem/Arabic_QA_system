package com.AQAS.Database;

import com.AQAS.passages_segmentation.ConfigPS;
import com.AQAS.passages_segmentation.PassageSegmentation;
import org.jsoup.Jsoup;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;

import static com.AQAS.Database.HelpersDB.props;

public class Document implements Comparable<Document> {

    public int id;
    String link;
    String text;
    int form_id;
    double urlRank; // according to the search engine [the order of it]
    double contentRank;
    ArrayList<String> segments = new ArrayList<>();

    public Document(String link, String text) {
        this.link = link;
        this.text = text;
    }

    public Document(String link, String text, int form_id) {
        this.link = link;
        this.text = text;
        this.form_id = form_id;
    }

    public Document(int id, int form_id, double urlRank, double contentRank) {
        this.id = id;
        this.form_id = form_id;
        this.urlRank = urlRank;
        this.contentRank = contentRank;
    }

    public Document(String link, String text, double urlRank, double contentRank) {
        this.link = link;
        this.text = text;
        this.urlRank = urlRank;
        this.contentRank = contentRank;
    }

    public Document(int id, String link, String text, int form_id, double urlRank, double contentRank) {
        this.id = id;
        this.link = link;
        this.text = text;
        this.form_id = form_id;
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
                    .data("contentRank", String.valueOf(this.contentRank))
                    .data("urlRank", String.valueOf(this.urlRank))
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

    public void setSegments(ArrayList<String> segments) {
        this.segments = segments;
    }

    public ArrayList<String> getSegments() {
        return segments;
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

    public  void generateDocumentSegments( String[] questionKeyPhrases, String splitRegex) {

        splitRegex = splitRegex != null ? splitRegex : "[؟?!.]";


        String documentText = this.text;
        String documentSentences[] = documentText.split(splitRegex);


        //just printing
        for (String sentence : documentSentences) {
            System.out.println(sentence);
            System.out.println("=============================================");

        }
        System.out.println("started dividing.");
        int state = ConfigPS.STATE_END;
        int sentencesSize = documentSentences.length;
        int startIndex = 0;
        int endIndex = 0;
        ArrayList<String> segments = new ArrayList<>();
        for (int i = 0; i < sentencesSize; i++) {
            String si = documentSentences[i];
            if (PassageSegmentation.hasKeyPhrases(si, questionKeyPhrases) || i == sentencesSize - 1) {
                if (i == sentencesSize - 1) {// case of last sentence in document
                    state = ConfigPS.STATE_LAST_SENTENCE;
                }
                if (state == ConfigPS.STATE_END || state == ConfigPS.STATE_LAST_SENTENCE) {
                    startIndex = endIndex;
                    endIndex = i;
                    if (!PassageSegmentation.isEnoughSegment(startIndex, endIndex) && state != ConfigPS.STATE_LAST_SENTENCE) {
                        state = ConfigPS.STATE_NOT_ENOUGH;
                        continue;
                    }

                } else if (state == ConfigPS.STATE_NOT_ENOUGH) {
                    endIndex = i;
                    state = ConfigPS.STATE_END;
                }
                String segmentString = "";
                for (int j = startIndex; j < endIndex; j++) {
                    segmentString += documentSentences[j] + ". ";
                }

                if (state == ConfigPS.STATE_LAST_SENTENCE) {
                    int LastSegmentIndex = segments.size() - 1;
                    segmentString += documentSentences[sentencesSize - 1];
                    segments.set(LastSegmentIndex, segments.get(LastSegmentIndex) + ". " + segmentString);
                } else {
                    System.out.println(segments.size());
                    segments.add(segmentString);
                }
            }

        }
        this.segments = segments;
    }

}
