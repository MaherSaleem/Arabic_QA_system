package com.AQAS.Database;

import com.AQAS.question_type.ConfigQT;

import java.util.ArrayList;
import java.util.Arrays;

public class tempDataSet {

    public static final ArrayList<Question> trainingQuestions = new ArrayList<Question>(Arrays.asList(


            new Question("مدة علاج الزكام",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كم  مدة علاج الزكام")
                    ))
                    , ConfigQT.QT_NUMERIC
            ),
            new Question("أعراض الانفلونزا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي أعراض الانفلونزا")
                    ))
                    , ConfigQT.QT_LIST
            ),
               new Question("تعريف مرض السكري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو مرض السكري")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            )
    ));
}
