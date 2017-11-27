package com.AQAS.Document_ranking.Ranking_Evaluation;

public class Measurements {

    public int overAllCount;
    public int actualPositiveCount;//manual+related
    public int predictedPositiveCount;//Ours+related
    public int TP;//intersection


    public Measurements(int overAllCount, int actualPositiveCount, int predictedPositiveCount, int TP) {
        this.overAllCount = overAllCount;
        this.actualPositiveCount = actualPositiveCount;
        this.predictedPositiveCount = predictedPositiveCount;
        this.TP = TP;//number of intersection between related[manually related AND Ours related]
    }

    public int actualNegativeCount(){
        return this.overAllCount - this.actualPositiveCount;
    }

    public int predictedNegativeCount(){
        return this.overAllCount - this.predictedPositiveCount;
    }


    public int FN(){//actually related but our system found them unrelated [we lost data]
        return this.actualPositiveCount - this.TP;
    }

    public int FP(){//actually NOT related but our system found them related [data zyadih]
        return this.predictedPositiveCount - TP;
    }

    public int TN(){//actually not related AND our system found them unrelated[good]
        return actualNegativeCount() - FP();
    }


    public double precision(){
        return (double)TP/(double)(TP+FP());
    }

    public double recall(){
        return (double)TP/(double)(TP+FN());
    }

    public double accuracy(){
        return (double)(TP+TN())/(double)overAllCount;
    }

    @Override
    public String toString() {
        return "Measurements{" +
                "overAllCount=" + overAllCount +
                ", actualPositiveCount=" + actualPositiveCount +
                ", predictedPositiveCount=" + predictedPositiveCount +
                ", TP=" + TP +
                ", FN=" + FN() +
                ", TN=" + TN() +
                ", FP=" + FP() +
                '}';
    }

    public void summary(){
        System.out.println(toString());
        System.out.println("Precision: "+ precision());
        System.out.println("Recall: "+ recall());
        System.out.println("accuracy: "+ accuracy());
    }


}
