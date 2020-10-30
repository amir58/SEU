package com.example.seu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BloodTypeInfoActivity extends AppCompatActivity {

    TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_type_info);

        textViewInfo = findViewById(R.id.blood_type_info_tv);

        textViewInfo.setText("- نبدأ بفصيلة +A والتي تُعد سابع فصيله من حيث الندره و اصحاب هذه الفصيله بامكانهم التبرع بالدم لـ +A و +AB وبامكانكم التبرع بالصفائح لـ +A و +O");

        textViewInfo.append("\n -  يليها فصيلة +B والتي تعتبر الفصيله السادسه من حيث الندره و بإمكان حامل هذه الفصيله التبرع بالدم لـ +B و +AB و بامكانهم التبرع بالصفائح الدمويه لـ +B و +O");

        textViewInfo.append("\n - فصيلة +AB\n" + "و اصحاب هذه الفصيله يستطيعون التبرع بالدم لانفسهم فقط لكنهم يستطيعون استقبال الدم من جميع الفصائل");

        textViewInfo.append("\n ننتقل للاخر فصيله موجبه وهي فصيله +O وتعتبر اهم فصيله لان اصحاب هذه الفصيله باستطاعتهم التبرع لجميع الفصائل الموجبه ");

        textViewInfo.append("\n A+ و B+ و AB+ و O+");

        textViewInfo.append("\n ولهذا يطلق عليهم اسم \"الفصيلة الكريمة\"");
    }


    public void back(View view) {
        finish();
    }


}