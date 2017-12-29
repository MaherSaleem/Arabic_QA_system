package com.AQAS.Database;

import com.AQAS.question_type.ConfigQT;

import java.util.ArrayList;
import java.util.Arrays;

public class NormalArabicDataset {

    public static final ArrayList<Question> trainingQuestions = new ArrayList<Question>(Arrays.asList(


            //copy from this part

            /*******
             *
             *
             *marad el sokaree
             *
             */
            new Question("مرض السكري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو مرض السكري"),
                            new Form("ماذا يعني مرض السكري"),
                            new Form("مرض السكري"),
                            new Form("تعريف السكري")

                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("مدة علاج السكري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كم  مدة علاج السكري"),
                            new Form("كم  فترة علاج السكري"),
                            new Form("كم هي فترة علاج السكري"),
                            new Form("ما هي فترة العلاج اللازمة لمرض السكري؟"),
                            new Form("ما هي المدة اللازمة للشفاء من مرض السكري"),
                            new Form("كم تبلغ فنرة علاج مرض السكري"),
                            new Form("ما هي الفترة التي يحتاجها مريض السكري للعلاج"),
                            new Form("كم تمتد فترة العلاج لمرض السكري؟"),
                            new Form("كم يلزم من الوقت للعلاج من مرض السكري؟"),
                            new Form("كم تستمر فترة علاج مرض السكري؟"),
                            new Form("كم يحتاج مرض السكري للعلاح؟"),
                            new Form("كم يستغرق مرض السكري للعلاح؟")
                    ))
                    , ConfigQT.QT_NUMERIC
            ),
            new Question("أعراض السكري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي أعراض السكري"),
                            new Form("اذكر أعراض السكري"),
                            new Form("ما هي الأعراض الجانبية للسكري"),
                            new Form("ما هي الأعراض الجانبية لمرض  السكري"),
                            new Form("على ماذا يؤثر السكري"),
                            new Form("آثار مرض السكري"),
                            new Form("ما هي  الآثار الجانبية لمرض السكري؟")

                    ))
                    , ConfigQT.QT_LIST
            ),

            new Question("سبب مرض السكري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي مسببات مرض السكري"),
                            new Form("ما هو سبب  مرض السكري"),
                            new Form("سبب  مرض السكري"),
                            new Form("ما هو سبب الإصابة بمرض السكري"),
                            new Form("ما هي أسباب الإصابة بمرض السكري"),
                            new Form("ما هي الأسباب المؤدية إلى مرض السكري"),
                            new Form("ماالمسبب الرئيسي لمرض السكري؟"),
                            new Form("كيف يصاب الانسان بمرض السكري؟")


                    ))
                    , ConfigQT.QT_PARAGRAPH//TODO check this
            ),
            new Question("علاج السكري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو علاج مرض السكري"),
                            new Form("ما هو دواء السكري"),
                            new Form("كيفية علاج مرض السكري"),
                            new Form("كيف يمكن الشفاء من مرض السكري"),
                            new Form("أدوية لعلاج مرض السكري")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("الوقاية من مرض السكري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي طريقة الوقاية من مرض السكري"),
                            new Form("كيف نستطيع تجنب الإصابة بالسكري؟"),
                            new Form("كيفية  تجنب الإصابة بمرض السكري"),
                            new Form("ما هي طرق الوقاية من مرض السكري؟"),
                            new Form("كيف تحمي نفسك من مرض السكري"),
                            new Form("الوقاية من مرض السكري"),
                            new Form("ما هي طرق الحماية من مرض السكري"),
                            new Form("وسائل الوقاية من مرض السكري")

                    ))
                    , ConfigQT.QT_LIST
            ),
            new Question("تشخيص مرض السكري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كيف نكشف عن مرض السكري"),
                            new Form("كيف نشخص مرض السكري"),
                            new Form("كيف يتم الكشف عن الإصابة  بمرض السكري"),
                            new Form("ما هي الطرق المتبعة للكشف عن الإصابة  بمرض السكري"),
                            new Form("كيف يمكن تشخيص مرض السكري"),
                            new Form("كيف يتم تشخيص مرض السكري"),
                            new Form("كيف أعرف اني مصاب بمرض السكري"),
                            new Form("ما هي الآلية المتبعة في الكشف عن مرض السكري")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("الفحوصات الطبية لمرض السكري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي الفحوصات اللازمة لمرض السكري"),
                            new Form("ما هي الفحوصات اللازمة لمريض السكري"),
                            new Form("ما هي الفحوصات  الدورية اللازمة لمرض السكري"),
                            new Form("ما هي الفحوصات  الروتينية لمرض السكري"),
                            new Form("ما هي الفحوصات التي يخضع لها الشخص المصاب بمرض السكري"),
                            new Form("الفحوصات التي يجريها مريض السكري"),
                            new Form(" ما هي الفحوصات الطبية للأشحاص المصابين بمرض السكري")

                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("امكانية علاج مرض السكري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ها هناك علاج لمرض السكري"),
                            new Form("هل يوجد علاج لمرض السكري؟"),
                            new Form("احتمالية الشفاء من مرض السكري"),
                            new Form(" ما هي احتمالية وجود علاج لمرض السكري"),
                            new Form(" ما هي امكانية وجود علاج لمرض السكري"),
                            new Form("هل يمكن الشفاء من مرض السكري"),
                            new Form("هل من علاج لمرض السكري")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),

            //copy until this part

            new Question("مرض ضغط الدم",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو مرض ضغط الدم"),
                            new Form("ماذا يعني مرض ضغط الدم"),
                            new Form("مرض ضغط الدم"),
                            new Form("تعريف ضغط الدم")

                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("مدة علاج ضغط الدم",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كم  مدة علاج ضغط الدم"),
                            new Form("كم  فترة علاج ضغط الدم"),
                            new Form("كم هي فترة علاج ضغط الدم"),
                            new Form("ما هي فترة العلاج اللازمة لمرض ضغط الدم؟"),
                            new Form("ما هي المدة اللازمة للشفاء من مرض ضغط الدم"),
                            new Form("كم تبلغ فنرة علاج مرض ضغط الدم"),
                            new Form("ما هي الفترة التي يحتاجها مريض ضغط الدم للعلاج"),
                            new Form("كم تمتد فترة العلاج لمرض ضغط الدم؟"),
                            new Form("كم يلزم من الوقت للعلاج من مرض ضغط الدم؟"),
                            new Form("كم تستمر فترة علاج مرض ضغط الدم؟"),
                            new Form("كم يحتاج مرض ضغط الدم للعلاح؟"),
                            new Form("كم يستغرق مرض ضغط الدم للعلاح؟")
                    ))
                    , ConfigQT.QT_NUMERIC
            ),
            new Question("أعراض ضغط الدم",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي أعراض ضغط الدم"),
                            new Form("اذكر أعراض ضغط الدم"),
                            new Form("ما هي الأعراض الجانبية للسكري"),
                            new Form("ما هي الأعراض الجانبية لمرض  ضغط الدم"),
                            new Form("على ماذا يؤثر ضغط الدم"),
                            new Form("آثار مرض ضغط الدم"),
                            new Form("ما هي  الآثار الجانبية لمرض ضغط الدم؟")

                    ))
                    , ConfigQT.QT_LIST
            ),

            new Question("سبب مرض ضغط الدم",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي مسببات مرض ضغط الدم"),
                            new Form("ما هو سبب  مرض ضغط الدم"),
                            new Form("سبب  مرض ضغط الدم"),
                            new Form("ما هو سبب الإصابة بمرض ضغط الدم"),
                            new Form("ما هي أسباب الإصابة بمرض ضغط الدم"),
                            new Form("ما هي الأسباب المؤدية إلى مرض ضغط الدم"),
                            new Form("ماالمسبب الرئيسي لمرض ضغط الدم؟"),
                            new Form("كيف يصاب الانسان بمرض ضغط الدم؟")


                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("علاج ضغط الدم",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو علاج مرض ضغط الدم"),
                            new Form("ما هو دواء ضغط الدم"),
                            new Form("كيفية علاج مرض ضغط الدم"),
                            new Form("كيف يمكن الشفاء من مرض ضغط الدم"),
                            new Form("أدوية لعلاج مرض ضغط الدم")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("الوقاية من مرض ضغط الدم",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي طريقة الوقاية من مرض ضغط الدم"),
                            new Form("كيف نستطيع تجنب الإصابة بضغط الدم؟"),
                            new Form("كيفية  تجنب الإصابة بمرض ضغط الدم"),
                            new Form("ما هي طرق الوقاية من مرض ضغط الدم؟"),
                            new Form("كيف تحمي نفسك من مرض ضغط الدم"),
                            new Form("الوقاية من مرض ضغط الدم"),
                            new Form("ما هي طرق الحماية من مرض ضغط الدم"),
                            new Form("وسائل الوقاية من مرض ضغط الدم")

                    ))
                    , ConfigQT.QT_LIST
            ),
            new Question("تشخيص مرض ضغط الدم",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كيف نكشف عن مرض ضغط الدم"),
                            new Form("كيف نشخص مرض ضغط الدم"),
                            new Form("كيف يتم الكشف عن الإصابة  بمرض ضغط الدم"),
                            new Form("ما هي الطرق المتبعة للكشف عن الإصابة  بمرض ضغط الدم"),
                            new Form("كيف يمكن تشخيص مرض ضغط الدم"),
                            new Form("كيف يتم تشخيص مرض ضغط الدم"),
                            new Form("كيف أعرف اني مصاب بمرض ضغط الدم"),
                            new Form("ما هي الآلية المتبعة في الكشف عن مرض ضغط الدم")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("الفحوصات الطبية لمرض ضغط الدم",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي الفحوصات اللازمة لمرض ضغط الدم"),
                            new Form("ما هي الفحوصات اللازمة لمريض ضغط الدم"),
                            new Form("ما هي الفحوصات  الدورية اللازمة لمرض ضغط الدم"),
                            new Form("ما هي الفحوصات  الروتينية لمرض ضغط الدم"),
                            new Form("ما هي الفحوصات التي يخضع لها الشخص المصاب بمرض ضغط الدم"),
                            new Form("الفحوصات التي يجريها مريض ضغط الدم"),
                            new Form(" ما هي الفحوصات الطبية للأشحاص المصابين بمرض ضغط الدم")

                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("امكانية علاج مرض ضغط الدم",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ها هناك علاج لمرض ضغط الدم"),
                            new Form("هل يوجد علاج لمرض ضغط الدم؟"),
                            new Form("احتمالية الشفاء من مرض ضغط الدم"),
                            new Form(" ما هي احتمالية وجود علاج لمرض ضغط الدم"),
                            new Form(" ما هي امكانية وجود علاج لمرض ضغط الدم"),
                            new Form("هل يمكن الشفاء من مرض ضغط الدم"),
                            new Form("هل من علاج لمرض ضغط الدم")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),

            /*******
             *
             *
             *marad el sokaree
             *
             */
            new Question("مرض الجدري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو مرض الجدري"),
                            new Form("ماذا يعني مرض الجدري"),
                            new Form("مرض الجدري"),
                            new Form("تعريف الجدري")

                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("مدة علاج الجدري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كم  مدة علاج الجدري"),
                            new Form("كم  فترة علاج الجدري"),
                            new Form("كم هي فترة علاج الجدري"),
                            new Form("ما هي فترة العلاج اللازمة لمرض الجدري؟"),
                            new Form("ما هي المدة اللازمة للشفاء من مرض الجدري"),
                            new Form("كم تبلغ فنرة علاج مرض الجدري"),
                            new Form("ما هي الفترة التي يحتاجها مريض الجدري للعلاج"),
                            new Form("كم تمتد فترة العلاج لمرض الجدري؟"),
                            new Form("كم يلزم من الوقت للعلاج من مرض الجدري؟"),
                            new Form("كم تستمر فترة علاج مرض الجدري؟"),
                            new Form("كم يحتاج مرض الجدري للعلاح؟"),
                            new Form("كم يستغرق مرض الجدري للعلاح؟")
                    ))
                    , ConfigQT.QT_NUMERIC
            ),
            new Question("أعراض الجدري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي أعراض الجدري"),
                            new Form("اذكر أعراض الجدري"),
                            new Form("ما هي الأعراض الجانبية للسكري"),
                            new Form("ما هي الأعراض الجانبية لمرض  الجدري"),
                            new Form("على ماذا يؤثر الجدري"),
                            new Form("آثار مرض الجدري"),
                            new Form("ما هي  الآثار الجانبية لمرض الجدري؟")

                    ))
                    , ConfigQT.QT_LIST
            ),

            new Question("سبب مرض الجدري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي مسببات مرض الجدري"),
                            new Form("ما هو سبب  مرض الجدري"),
                            new Form("سبب  مرض الجدري"),
                            new Form("ما هو سبب الإصابة بمرض الجدري"),
                            new Form("ما هي أسباب الإصابة بمرض الجدري"),
                            new Form("ما هي الأسباب المؤدية إلى مرض الجدري"),
                            new Form("ماالمسبب الرئيسي لمرض الجدري؟"),
                            new Form("كيف يصاب الانسان بمرض الجدري؟")


                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("علاج الجدري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو علاج مرض الجدري"),
                            new Form("ما هو دواء الجدري"),
                            new Form("كيفية علاج مرض الجدري"),
                            new Form("كيف يمكن الشفاء من مرض الجدري"),
                            new Form("أدوية لعلاج مرض الجدري")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("الوقاية من مرض الجدري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي طريقة الوقاية من مرض الجدري"),
                            new Form("كيف نستطيع تجنب الإصابة بالجدري؟"),
                            new Form("كيفية  تجنب الإصابة بمرض الجدري"),
                            new Form("ما هي طرق الوقاية من مرض الجدري؟"),
                            new Form("كيف تحمي نفسك من مرض الجدري"),
                            new Form("الوقاية من مرض الجدري"),
                            new Form("ما هي طرق الحماية من مرض الجدري"),
                            new Form("وسائل الوقاية من مرض الجدري")

                    ))
                    , ConfigQT.QT_LIST
            ),
            new Question("تشخيص مرض الجدري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كيف نكشف عن مرض الجدري"),
                            new Form("كيف نشخص مرض الجدري"),
                            new Form("كيف يتم الكشف عن الإصابة  بمرض الجدري"),
                            new Form("ما هي الطرق المتبعة للكشف عن الإصابة  بمرض الجدري"),
                            new Form("كيف يمكن تشخيص مرض الجدري"),
                            new Form("كيف يتم تشخيص مرض الجدري"),
                            new Form("كيف أعرف اني مصاب بمرض الجدري"),
                            new Form("ما هي الآلية المتبعة في الكشف عن مرض الجدري")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("الفحوصات الطبية لمرض الجدري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي الفحوصات اللازمة لمرض الجدري"),
                            new Form("ما هي الفحوصات اللازمة لمريض الجدري"),
                            new Form("ما هي الفحوصات  الدورية اللازمة لمرض الجدري"),
                            new Form("ما هي الفحوصات  الروتينية لمرض الجدري"),
                            new Form("ما هي الفحوصات التي يخضع لها الشخص المصاب بمرض الجدري"),
                            new Form("الفحوصات التي يجريها مريض الجدري"),
                            new Form(" ما هي الفحوصات الطبية للأشحاص المصابين بمرض الجدري")

                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("امكانية علاج مرض الجدري",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ها هناك علاج لمرض الجدري"),
                            new Form("هل يوجد علاج لمرض الجدري؟"),
                            new Form("احتمالية الشفاء من مرض الجدري"),
                            new Form(" ما هي احتمالية وجود علاج لمرض الجدري"),
                            new Form(" ما هي امكانية وجود علاج لمرض الجدري"),
                            new Form("هل يمكن الشفاء من مرض الجدري"),
                            new Form("هل من علاج لمرض الجدري")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),

            /*******
             *
             *
             *marad el sokaree
             *
             */
            new Question("مرض الانفلونزا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو مرض الانفلونزا"),
                            new Form("ماذا يعني مرض الانفلونزا"),
                            new Form("مرض الانفلونزا"),
                            new Form("تعريف الانفلونزا")

                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("مدة علاج الانفلونزا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كم  مدة علاج الانفلونزا"),
                            new Form("كم  فترة علاج الانفلونزا"),
                            new Form("كم هي فترة علاج الانفلونزا"),
                            new Form("ما هي فترة العلاج اللازمة لمرض الانفلونزا؟"),
                            new Form("ما هي المدة اللازمة للشفاء من مرض الانفلونزا"),
                            new Form("كم تبلغ فنرة علاج مرض الانفلونزا"),
                            new Form("ما هي الفترة التي يحتاجها مريض الانفلونزا للعلاج"),
                            new Form("كم تمتد فترة العلاج لمرض الانفلونزا؟"),
                            new Form("كم يلزم من الوقت للعلاج من مرض الانفلونزا؟"),
                            new Form("كم تستمر فترة علاج مرض الانفلونزا؟"),
                            new Form("كم يحتاج مرض الانفلونزا للعلاح؟"),
                            new Form("كم يستغرق مرض الانفلونزا للعلاح؟")
                    ))
                    , ConfigQT.QT_NUMERIC
            ),
            new Question("أعراض الانفلونزا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي أعراض الانفلونزا"),
                            new Form("اذكر أعراض الانفلونزا"),
                            new Form("ما هي الأعراض الجانبية للسكري"),
                            new Form("ما هي الأعراض الجانبية لمرض  الانفلونزا"),
                            new Form("على ماذا يؤثر الانفلونزا"),
                            new Form("آثار مرض الانفلونزا"),
                            new Form("ما هي  الآثار الجانبية لمرض الانفلونزا؟")

                    ))
                    , ConfigQT.QT_LIST
            ),

            new Question("سبب مرض الانفلونزا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي مسببات مرض الانفلونزا"),
                            new Form("ما هو سبب  مرض الانفلونزا"),
                            new Form("سبب  مرض الانفلونزا"),
                            new Form("ما هو سبب الإصابة بمرض الانفلونزا"),
                            new Form("ما هي أسباب الإصابة بمرض الانفلونزا"),
                            new Form("ما هي الأسباب المؤدية إلى مرض الانفلونزا"),
                            new Form("ماالمسبب الرئيسي لمرض الانفلونزا؟"),
                            new Form("كيف يصاب الانسان بمرض الانفلونزا؟")


                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("علاج الانفلونزا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو علاج مرض الانفلونزا"),
                            new Form("ما هو دواء الانفلونزا"),
                            new Form("كيفية علاج مرض الانفلونزا"),
                            new Form("كيف يمكن الشفاء من مرض الانفلونزا"),
                            new Form("أدوية لعلاج مرض الانفلونزا")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("الوقاية من مرض الانفلونزا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي طريقة الوقاية من مرض الانفلونزا"),
                            new Form("كيف نستطيع تجنب الإصابة بالانفلونزا؟"),
                            new Form("كيفية  تجنب الإصابة بمرض الانفلونزا"),
                            new Form("ما هي طرق الوقاية من مرض الانفلونزا؟"),
                            new Form("كيف تحمي نفسك من مرض الانفلونزا"),
                            new Form("الوقاية من مرض الانفلونزا"),
                            new Form("ما هي طرق الحماية من مرض الانفلونزا"),
                            new Form("وسائل الوقاية من مرض الانفلونزا")

                    ))
                    , ConfigQT.QT_LIST
            ),
            new Question("تشخيص مرض الانفلونزا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كيف نكشف عن مرض الانفلونزا"),
                            new Form("كيف نشخص مرض الانفلونزا"),
                            new Form("كيف يتم الكشف عن الإصابة  بمرض الانفلونزا"),
                            new Form("ما هي الطرق المتبعة للكشف عن الإصابة  بمرض الانفلونزا"),
                            new Form("كيف يمكن تشخيص مرض الانفلونزا"),
                            new Form("كيف يتم تشخيص مرض الانفلونزا"),
                            new Form("كيف أعرف اني مصاب بمرض الانفلونزا"),
                            new Form("ما هي الآلية المتبعة في الكشف عن مرض الانفلونزا")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("الفحوصات الطبية لمرض الانفلونزا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي الفحوصات اللازمة لمرض الانفلونزا"),
                            new Form("ما هي الفحوصات اللازمة لمريض الانفلونزا"),
                            new Form("ما هي الفحوصات  الدورية اللازمة لمرض الانفلونزا"),
                            new Form("ما هي الفحوصات  الروتينية لمرض الانفلونزا"),
                            new Form("ما هي الفحوصات التي يخضع لها الشخص المصاب بمرض الانفلونزا"),
                            new Form("الفحوصات التي يجريها مريض الانفلونزا"),
                            new Form(" ما هي الفحوصات الطبية للأشحاص المصابين بمرض الانفلونزا")

                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("امكانية علاج مرض الانفلونزا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ها هناك علاج لمرض الانفلونزا"),
                            new Form("هل يوجد علاج لمرض الانفلونزا؟"),
                            new Form("احتمالية الشفاء من مرض الانفلونزا"),
                            new Form(" ما هي احتمالية وجود علاج لمرض الانفلونزا"),
                            new Form(" ما هي امكانية وجود علاج لمرض الانفلونزا"),
                            new Form("هل يمكن الشفاء من مرض الانفلونزا"),
                            new Form("هل من علاج لمرض الانفلونزا")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),

            /*******
             *
             *
             *marad el sokaree
             *
             */
            new Question("مرض الملاريا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو مرض الملاريا"),
                            new Form("ماذا يعني مرض الملاريا"),
                            new Form("مرض الملاريا"),
                            new Form("تعريف الملاريا")

                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("مدة علاج الملاريا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كم  مدة علاج الملاريا"),
                            new Form("كم  فترة علاج الملاريا"),
                            new Form("كم هي فترة علاج الملاريا"),
                            new Form("ما هي فترة العلاج اللازمة لمرض الملاريا؟"),
                            new Form("ما هي المدة اللازمة للشفاء من مرض الملاريا"),
                            new Form("كم تبلغ فنرة علاج مرض الملاريا"),
                            new Form("ما هي الفترة التي يحتاجها مريض الملاريا للعلاج"),
                            new Form("كم تمتد فترة العلاج لمرض الملاريا؟"),
                            new Form("كم يلزم من الوقت للعلاج من مرض الملاريا؟"),
                            new Form("كم تستمر فترة علاج مرض الملاريا؟"),
                            new Form("كم يحتاج مرض الملاريا للعلاح؟"),
                            new Form("كم يستغرق مرض الملاريا للعلاح؟")
                    ))
                    , ConfigQT.QT_NUMERIC
            ),
            new Question("أعراض الملاريا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي أعراض الملاريا"),
                            new Form("اذكر أعراض الملاريا"),
                            new Form("ما هي الأعراض الجانبية للسكري"),
                            new Form("ما هي الأعراض الجانبية لمرض  الملاريا"),
                            new Form("على ماذا يؤثر الملاريا"),
                            new Form("آثار مرض الملاريا"),
                            new Form("ما هي  الآثار الجانبية لمرض الملاريا؟")

                    ))
                    , ConfigQT.QT_LIST
            ),

            new Question("سبب مرض الملاريا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي مسببات مرض الملاريا"),
                            new Form("ما هو سبب  مرض الملاريا"),
                            new Form("سبب  مرض الملاريا"),
                            new Form("ما هو سبب الإصابة بمرض الملاريا"),
                            new Form("ما هي أسباب الإصابة بمرض الملاريا"),
                            new Form("ما هي الأسباب المؤدية إلى مرض الملاريا"),
                            new Form("ماالمسبب الرئيسي لمرض الملاريا؟"),
                            new Form("كيف يصاب الانسان بمرض الملاريا؟")


                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("علاج الملاريا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هو علاج مرض الملاريا"),
                            new Form("ما هو دواء الملاريا"),
                            new Form("كيفية علاج مرض الملاريا"),
                            new Form("كيف يمكن الشفاء من مرض الملاريا"),
                            new Form("أدوية لعلاج مرض الملاريا")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("الوقاية من مرض الملاريا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي طريقة الوقاية من مرض الملاريا"),
                            new Form("كيف نستطيع تجنب الإصابة بالملاريا؟"),
                            new Form("كيفية  تجنب الإصابة بمرض الملاريا"),
                            new Form("ما هي طرق الوقاية من مرض الملاريا؟"),
                            new Form("كيف تحمي نفسك من مرض الملاريا"),
                            new Form("الوقاية من مرض الملاريا"),
                            new Form("ما هي طرق الحماية من مرض الملاريا"),
                            new Form("وسائل الوقاية من مرض الملاريا")

                    ))
                    , ConfigQT.QT_LIST
            ),
            new Question("تشخيص مرض الملاريا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("كيف نكشف عن مرض الملاريا"),
                            new Form("كيف نشخص مرض الملاريا"),
                            new Form("كيف يتم الكشف عن الإصابة  بمرض الملاريا"),
                            new Form("ما هي الطرق المتبعة للكشف عن الإصابة  بمرض الملاريا"),
                            new Form("كيف يمكن تشخيص مرض الملاريا"),
                            new Form("كيف يتم تشخيص مرض الملاريا"),
                            new Form("كيف أعرف اني مصاب بمرض الملاريا"),
                            new Form("ما هي الآلية المتبعة في الكشف عن مرض الملاريا")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("الفحوصات الطبية لمرض الملاريا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ما هي الفحوصات اللازمة لمرض الملاريا"),
                            new Form("ما هي الفحوصات اللازمة لمريض الملاريا"),
                            new Form("ما هي الفحوصات  الدورية اللازمة لمرض الملاريا"),
                            new Form("ما هي الفحوصات  الروتينية لمرض الملاريا"),
                            new Form("ما هي الفحوصات التي يخضع لها الشخص المصاب بمرض الملاريا"),
                            new Form("الفحوصات التي يجريها مريض الملاريا"),
                            new Form(" ما هي الفحوصات الطبية للأشحاص المصابين بمرض الملاريا")

                    ))
                    , ConfigQT.QT_PARAGRAPH
            ),
            new Question("امكانية علاج مرض الملاريا",
                    new ArrayList<Form>(Arrays.asList(
                            new Form("ها هناك علاج لمرض الملاريا"),
                            new Form("هل يوجد علاج لمرض الملاريا؟"),
                            new Form("احتمالية الشفاء من مرض الملاريا"),
                            new Form(" ما هي احتمالية وجود علاج لمرض الملاريا"),
                            new Form(" ما هي امكانية وجود علاج لمرض الملاريا"),
                            new Form("هل يمكن الشفاء من مرض الملاريا"),
                            new Form("هل من علاج لمرض الملاريا")
                    ))
                    , ConfigQT.QT_PARAGRAPH
            )
    ));
}
