package com.AQAS.Document_ranking.Ranking_Evaluation;

import com.AQAS.Database.Document;
import com.AQAS.Database.Form;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static com.AQAS.Database.HelpersDB.intializeProb;

public class RankingEvaluation {


    public static void main(String[] args) throws FileNotFoundException {
        intializeProb();

        //getting the data for each questionForm and storing it in a map as FormID=>[Docs]
        ArrayList<Map<Integer, ArrayList<Integer>>> folderFormsWithRankedDocs = readEvaluationDateFromFolder();
//        System.out.println(folderFormsWithRankedDocs);

        double averageForPercentOfRelated = 0;
        double averageForPercentOfTruelyRanked = 0;
        int numberOfForms = 0;

        //[{}, {}, {1=[5, 2, 7, 9, 1, 3], 2=[4, 2, 7, 9, 1, 3]}]
        for (Map<Integer, ArrayList<Integer>> formsWithRankedDocs:folderFormsWithRankedDocs) {//we take each map
            if(formsWithRankedDocs.size()<1){
                continue;
            }
            // {1=[5, 2, 7, 9, 1, 3], 2=[4, 2, 7, 9, 1, 3]}
            for (Map.Entry<Integer, ArrayList<Integer>> formWithRankedDocs : formsWithRankedDocs.entrySet()) {//we take each list
                //1=[5, 2, 7, 9, 1, 3]
                numberOfForms++;
//                System.out.println(formWithRankedDocs);
                int formID = formWithRankedDocs.getKey();
                double percentOfRelated;
                double percentOfTruelyRanked;
                ArrayList<Integer> manuallyRankedDocs = formWithRankedDocs.getValue();
                ArrayList<Integer> OurRankedDocs =new ArrayList<Integer>();

                Form form = retrieveDocumentsForRank(formID);
//                System.out.println(form.documents);

                int FormDocumentsCount = form.documents.size();
//                System.out.println("Before ranking: " + form);
                Collections.sort(form.documents);// uses CompareTo in order to sort the document according to their contentRank
//                System.out.println("After ranking: " + form);
                form.removeIrrelevantDocuments();
//                System.out.println("After Remove irrelevant: " + form);

                for (Document d : form.documents) {
                    OurRankedDocs.add(d.id);
                }

                int ourRankedDocsOriginalSize = OurRankedDocs.size();//related docs

                System.out.println("our ranked Docs: " + OurRankedDocs);
                System.out.println("Manually ranked Docs: " + manuallyRankedDocs);

                int margin = ConfigRE.EVALUATION_ERROR_MARGIN;
                int index = 0;
                int numberOfCorrectRanks = 0;
                for (int docID : OurRankedDocs) {
                    if(index == manuallyRankedDocs.size()){
                        break;
                    }
                    int startMargin = index - margin < 0 ? 0 : index - margin;
                    int endMargin = index + margin > manuallyRankedDocs.size() - 1 ? manuallyRankedDocs.size() - 1 : index + margin;
                    endMargin++;//since it is excluded.
//                    System.out.println(startMargin+" "+ endMargin);
                    List marginList = manuallyRankedDocs.subList(startMargin, endMargin);
//                    System.out.println(marginList);

                    if (marginList.contains(docID)) {
                        numberOfCorrectRanks++;
                    }
                    index++;
                }



                OurRankedDocs.retainAll(manuallyRankedDocs);
//                System.out.println(OurRankedDocs);
                int numberOfMatchedRelatedDocs = OurRankedDocs.size();//intersection
                int numberOfManuallyRelatedDocs = manuallyRankedDocs.size();
                double normalization = Math.max(ourRankedDocsOriginalSize,numberOfManuallyRelatedDocs);
                Measurements measurements = new Measurements(FormDocumentsCount,
                        numberOfManuallyRelatedDocs,ourRankedDocsOriginalSize,numberOfMatchedRelatedDocs);
                percentOfRelated = (double) numberOfMatchedRelatedDocs / normalization;
                percentOfTruelyRanked = (double) numberOfCorrectRanks / (double) ourRankedDocsOriginalSize;
                measurements.summary();
                averageForPercentOfRelated += percentOfRelated;
                averageForPercentOfTruelyRanked += percentOfTruelyRanked;

            }
        }

        averageForPercentOfRelated /=numberOfForms;
        averageForPercentOfTruelyRanked /=numberOfForms;


        System.out.println("accuracy of RELATED: "+averageForPercentOfRelated);
        System.out.println("accuracy of TruelyRanked: "+averageForPercentOfTruelyRanked);


    }

//[{}, {}, {1=[5, 2, 7, 9, 1, 3], 2=[4, 2, 7, 9, 1, 3]}]
    public static ArrayList<Map<Integer, ArrayList<Integer>>> readEvaluationDateFromFolder() throws FileNotFoundException {
        ArrayList<Map<Integer, ArrayList<Integer>>> foldersData = new ArrayList<Map<Integer, ArrayList<Integer>>>();//Ziad,Aseel,Maher
        String dataFolderPath = ConfigRE.packagePath + ConfigRE.dataFolder;
        final File fold = new File(dataFolderPath);
        for (final File fileEntry : fold.listFiles()) {//fileEntry.getName()
            String subFolderName = fileEntry.getName();
            System.out.println(subFolderName);
            Map<Integer, ArrayList<Integer>> formsWithRankedDocs = new HashMap<Integer, ArrayList<Integer>>();
            final File folder = new File(dataFolderPath+subFolderName);
            String folderFullPath = dataFolderPath+subFolderName;
            ArrayList<String> filesName = listFilesForFolder(folder);
            for (String fileName : filesName) {
                Scanner s = new Scanner(new File(folderFullPath+"/"+ fileName));
                ArrayList<Integer> relatedDocs = new ArrayList<Integer>();
                int questionFormID = Integer.parseInt(s.next());
//            System.out.println(questionFormID);
                while (s.hasNext()) {
                    relatedDocs.add(Integer.valueOf(s.next()));
                }
                s.close();
                formsWithRankedDocs.put(questionFormID, relatedDocs);
//            System.out.println(formsWithRankedDocs);
            }
            foldersData.add(formsWithRankedDocs);
        }


        return foldersData;
    }

    public static ArrayList<String> listFilesForFolder(final File folder) {
        ArrayList<String> filesName = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            filesName.add(fileEntry.getName());
        }
        return filesName;
    }

    private static Form retrieveDocumentsForRank(int form_id) {
        Form form = new Form(form_id);
        form.getDocumentsForRank();
        return form;
    }




}
