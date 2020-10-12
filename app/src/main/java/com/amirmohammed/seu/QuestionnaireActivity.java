package com.amirmohammed.seu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireActivity extends AppCompatActivity {
    TextView textViewQuestionNumber, textViewQuestion;
    RadioGroup radioGroup;
    List<Questionnaire> questions = new ArrayList<>();

    int questionCounter = 0;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        radioGroup = findViewById(R.id.questionnaire_rg);
        textViewQuestion = findViewById(R.id.questionnaire_tv_question);
        textViewQuestionNumber = findViewById(R.id.questionnaire_tv_question_number);

        questionsData();

        setQuestionData();

    }

    public void nextQuestion(View view) {
        if (questionCounter >= questions.size() - 1) {
            Toast.makeText(this, "انتهت الاسئلة", Toast.LENGTH_SHORT).show();

            MainActivity.donorData.setQuestionnaires(questions);

            startActivity(new Intent(this, DonorTypeActivity.class));
            finish();
            return;
        }

        String answer = getAnswer();

        if (answer.isEmpty()) {
            Toast.makeText(this, "برجاء اختيار اجابة", Toast.LENGTH_SHORT).show();
            return;
        }

        questions.get(questionCounter).setAnswer(answer);

        questionCounter++;

        setQuestionData();

    }

    private String getAnswer() {
        int id = radioGroup.getCheckedRadioButtonId();
        if (id == -1) {
            return "";
        }
        RadioButton radioButton = findViewById(id);
        return radioButton.getText().toString();
    }

    private void questionsData() {
        Questionnaire question1 = new Questionnaire("هل انت بصحة جيدة اليوم ؟", "");
        questions.add(question1);

        Questionnaire question2 = new Questionnaire("هل تتناول مضاد حيوى ؟", "");
        questions.add(question2);

        Questionnaire question3 = new Questionnaire("هل تتناول اى عقاقير اخرى مضادة للامراض المعدية ؟", "");
        questions.add(question3);

        Questionnaire question4 = new Questionnaire("خلال 3 ايام سابقة : هل تناولت الاسبرين او اى دواء يحتوى على الاسبرين ؟", "");
        questions.add(question4);

        Questionnaire question5 = new Questionnaire("للنساء خلال 6 اسابيع ماضية : هل انت حامل الان او كنت حامل خلال الاسابيع الماضيه", "");
        questions.add(question5);

        Questionnaire question6 = new Questionnaire("خلال 8 اسابيع سابقة : هل شربت لبن حليب ابقار او اغنام او جمال غير مبستر او تعاملت بشكل مباشر مع سوائل هذه الحيوانات", "");
        questions.add(question6);

        Questionnaire question7 = new Questionnaire("خلال 8 اسابيع سابقة : هل تلقيت اى تطعيمات", "");
        questions.add(question7);

        Questionnaire question8 = new Questionnaire("خلال 8 اسابيع سابقة : هل كنت على اتصال مباشر مع شخص تلقى لقاح الجدرى المائى( العنقز )", "");
        questions.add(question8);

        Questionnaire question9 = new Questionnaire("خلال 8 اسابيع ماضية : هل تبرعت بالدم او البلازما او صافئح الدم", "");
        questions.add(question9);

        Questionnaire question10 = new Questionnaire("خلال 4 اشهر ماضيه : هل تبرعت بوحدتى كريات دم حمراء باستخدام جهاز فصل مكونات الدم", "");
        questions.add(question10);

        Questionnaire question11 = new Questionnaire("خلال 12 شهر ماضى : هل كنت خارج المملكة", "");
        questions.add(question11);

        Questionnaire question12 = new Questionnaire("خلال 12 شهر ماضى : هل نقل لك دم", "");
        questions.add(question12);

        Questionnaire question13 = new Questionnaire("خلال 12 شهر ماضى : هل اجرى لك عملية زراعة للاعضاء او نخاع العظام", "");
        questions.add(question13);

        Questionnaire question14 = new Questionnaire("خلال 12 شهر ماضى : هل تعرضت عن طريق الجلد او العين او الانسجة المخاطية لدم شخص اخر", "");
        questions.add(question14);

        Questionnaire question15 = new Questionnaire("خلال 12 شهر ماضى : هل تعرضت لوخز غير مقصود بابره ملوثه بدم شخص اخر", "");
        questions.add(question15);

        Questionnaire question16 = new Questionnaire("خلال 12 شهر ماضى : هل اتصلت جنسيا بشخص مصاب بمرض نقص المناعة المكتسب ( الايدز )", "");
        questions.add(question16);

        Questionnaire question17 = new Questionnaire("خلال 12 شهر ماضى : هل اتصلت جنسيا مع شخص ياخذ المال او المخدرات مقابل الجنس", "");
        questions.add(question17);

        Questionnaire question18 = new Questionnaire("هل اصبت بالايدز او كان فخص الايدز ايجابى", "");
        questions.add(question18);

        Questionnaire question19 = new Questionnaire("هل سبق لك ان استخدمت ابره لتناول عقاقير او مخدر او منشطات لم توصف لك من قبل الطبيب", "");
        questions.add(question19);

        Questionnaire question20 = new Questionnaire("هل سبق لك ان استخدمت عوامل تخثر الدم المركزة", "");
        questions.add(question20);

        Questionnaire question21 = new Questionnaire("هل سبق ان اصبت بالتهاب كبد فيروسى او باى امراض اخرى بالكبد", "");
        questions.add(question21);

        Questionnaire question22 = new Questionnaire("هل اصبت بمرض شاغاس او داء البابسية او داء اللشمانيا", "");
        questions.add(question22);

        Questionnaire question23 = new Questionnaire("هل اصبت باى نوع من امراض الدم او الحمى", "");
        questions.add(question23);

        Questionnaire question24 = new Questionnaire("هل سبق ان اجرى لك عملية فى الدماغ او زراعة لانسجة الدماع او زراعه انسجة او اعضاء من مصدر غير بشرى", "");
        questions.add(question24);

        Questionnaire question25 = new Questionnaire("هل سبق ان اصبت بالسرطات او باى مرض خبيث", "");
        questions.add(question25);

        Questionnaire question26 = new Questionnaire("هل سبق ان عانيت من مشاكل فى القلب او الرئة", "");
        questions.add(question26);

        Questionnaire question27 = new Questionnaire("هل تعانى من مشاكل نزف الدم او امراض الدم", "");
        questions.add(question27);

        Questionnaire question28 = new Questionnaire("هل سبق ان تلقيت علاج هرمونى ( هرمون النمو او هرمون بالحقن )", "");
        questions.add(question28);

        Questionnaire question29 = new Questionnaire("هل سبق لك او لاحد اقاربك الاصابه بمرض جنون البقر", "");
        questions.add(question29);

        Questionnaire question30 = new Questionnaire("هل سبق ان اصبت بالحمى المالطية", "");
        questions.add(question30);

        Questionnaire question31 = new Questionnaire("هل سبق وان اصبت بامراض مزمنة او وراثية", "");
        questions.add(question31);

    }

    private void setQuestionData() {
        textViewQuestion.setText(questions.get(questionCounter).getQuestion());

        String questionNumber = "السؤال " + (questionCounter + 1) + " / " + questions.size();

        textViewQuestionNumber.setText(questionNumber);

    }

}
