package com.AQAS.Database;

public class Answer implements Comparable<Answer>{

    public String text;
    public double rank;

    public Answer() {
    }

    public Answer(String text) {
        this.text = text;
    }

    public Answer(String text, double rank) {
        this.text = text;
        this.rank = rank;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }



    /*
   return ordered answers depending on content rank, Sorting descending
    */
    @Override
    public int compareTo(Answer o) {
        double diff = (this.rank - o.rank);
        return diff != 0 ? (diff > 0 ? -1 : 1) : 0;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", rank=" + rank +
                '}';
    }
}
