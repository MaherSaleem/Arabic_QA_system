package com.AQAS.Database;

public class Segment {
    public String text;
    public double rank;


    public Segment(String text) {
        this.text = text;
    }

    public Segment(String text, double rank) {
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

    @Override
    public String toString() {
        return "Segment{" +
                "text='" + text + '\'' +
                ", rank=" + rank +
                '}';
    }
}
