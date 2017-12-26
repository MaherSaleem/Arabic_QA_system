package com.AQAS.synonyms;

import com.AQAS.main.HelpersM;

import java.util.List;

public class awnMain {
    public static void main(String[] args)
    {
        AWN awn=new AWN (HelpersM.getInputStreamFromResrcFile("awn.xml"),false);
        List<String> ItemID= awn.Get_Item_Id_From_Name("سبب");
        for(int i = 0; i< ItemID.size();i++){
            System.out.println("Item ID: " + ItemID.get(i) +" Item Name: " + awn.Get_Name_Of_Item_From_Item_Id(ItemID.get(i)));
        }
        List<String> listWordId= awn.Get_List_Word_Id_From_Value("سبب");
        for(int i = 0; i< listWordId.size();i++){
            System.out.println("Word ID: " + listWordId.get(i) +" Word Value: " + awn.Get_Word_Value_From_Word_Id(listWordId.get(i)));
//            List<List<String>> listOfForm=awn.Get_List_Of_Forms_From_Word_Id(listWordId.get(i));
//            for(int j = 0; j<listOfForm.size();j++){
//                String formVlue = listOfForm.get(j).get(0);
//                String formType = listOfForm.get(j).get(1);
//                System.out.println("Form value: " + formVlue+ ". Form type:" + formType);
//            }
            String SynsetID= awn.Get_Synset_ID_From_Word_Id(listWordId.get(i));
            List<String> listWordIdFromSynsent=awn.Get_List_Word_Id_From_Synset_ID(SynsetID);
            for(int j = 0; j< listWordIdFromSynsent.size();j++) {
            String wordValue = awn.Get_Word_Value_From_Word_Id(listWordIdFromSynsent.get(j));
             System.out.println("Synset List: " + wordValue);
        }


        }
    }
}
    
