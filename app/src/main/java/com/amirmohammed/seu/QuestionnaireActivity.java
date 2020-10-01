package com.amirmohammed.seu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amirmohammed.seu.deprecated.DonorData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            uploadQuestions();
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
        for (int i = 0; i < 10; i++) {
            Questionnaire questionOne = new Questionnaire("هل انت بصحة جيدة اليوم", "");
            questions.add(questionOne);
        }
    }


    private void setQuestionData() {
        textViewQuestion.setText(questions.get(questionCounter).getQuestion());

        String questionNumber =  "السؤال " +(questionCounter + 1) + " / " + questions.size() ;


        textViewQuestionNumber.setText(questionNumber);

    }

    private void uploadQuestions() {
        DonorData donorData = new DonorData(questions);

        firestore.collection("donors")
                .document("donors101")
                .set(donorData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(QuestionnaireActivity.this, "Done", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
