package com.AQAS.Database;

import com.AQAS.passages_segmentation.ConfigPS;
import com.AQAS.passages_segmentation.PassageSegmentation;
import org.jsoup.Jsoup;

import javax.print.Doc;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

import static com.AQAS.Database.HelpersDB.props;

public class Document implements Comparable<Document> {

    public int id;
    String link;
    String text;
    int form_id;
    double urlRank; // according to the search engine [the order of it]
    double contentRank;
    ArrayList<Segment> segments = new ArrayList<Segment>();
    String[] keyPhases ;

    public Document(String link, String text) {
        this.link = link;
        this.text = text;
    }

    public Document(String link, String text, double urlRank) {
        this.link = link;
        this.text = text;
        this.urlRank = urlRank;
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
    public Document(int id, String link, String text, int form_id, double urlRank) {
        this.id = id;
        this.link = link;
        this.text = text;
        this.form_id = form_id;
        this.urlRank = urlRank;
    }

    public Document(int id, String link, String text, int form_id) {
        this.id = id;
        this.link = link;
        this.text = text;
        this.form_id = form_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public double getUrlRank() {
        return urlRank;
    }

    public void setUrlRank(double urlRank) {
        this.urlRank = urlRank;
    }

    public double getContentRank() {
        return contentRank;
    }

    public void setContentRank(double contentRank) {
        this.contentRank = contentRank;
    }

    public String[] getKeyPhases() {
        return keyPhases;
    }

    public void setKeyPhases(String[] keyPhases) {
        this.keyPhases = keyPhases;
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
        return this.contentRank / (Math.log(this.urlRank + 1) / Math.log(2));
    }


    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
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

    public  void generateDocumentSegments( String[] questionKeyPhrases, String splitRegex)  {

        splitRegex = splitRegex != null ? splitRegex : "[؟?!.]";


        String documentText = this.text;
        String documentSentences[] = documentText.split(splitRegex);


        //just printing
        for (String sentence : documentSentences) {
            System.out.println(sentence);
            System.out.println("=============================================");
        }
        int state = ConfigPS.STATE_END;
        int sentencesSize = documentSentences.length;
        int startIndex = 0;
        int endIndex = 0;
        ArrayList<Segment> segments = new ArrayList<>();
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
                    segments.set(LastSegmentIndex, new Segment(segments.get(LastSegmentIndex).text + ". " + segmentString));
                } else {
                    System.out.println(segments.size());
                    segments.add(new Segment(segmentString));
                }
            }

        }
        this.segments = segments;

//        //just printing
//        System.out.println("*************After segmentation process*************");
//        PrintWriter writer = null;
//        try {
//            writer = new PrintWriter(new FileOutputStream(
//                    new File("out.txt"),
//                    true /* append = true */));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        for (Segment segment: this.segments) {
//            writer.println(segment.text);
//            writer.println("==================================================");
//        }
//        writer.println("Finshed the current File");
//        writer.close();
    }


    public void calculateSegmentsRanks(Form form) throws IOException {

        for (Segment segment:this.segments) {
            segment.calculateRank(form, this);
        }
    }

    public void removeIrrelevantSegments() {
        for (Iterator<Segment> segment = this.segments.iterator(); segment.hasNext(); ) {
            if (segment.next().getRank() <= ConfigPS.SEGMENT_THRESHOLD) {
                segment.remove();
            }
        }
    }


    public void setSegmentsOrder(){
        int i=1;
        for (Segment segment:this.segments) {
            segment.setSegmentOrder(i++);
        }
    }
}
