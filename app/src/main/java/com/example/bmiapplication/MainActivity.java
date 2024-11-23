package com.example.bmiapplication;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edt1 = findViewById(R.id.edt1);
        EditText edt2 = findViewById(R.id.edt2);
        EditText edt3 = findViewById(R.id.edt3);
        Button btn1 = findViewById(R.id.btn1);
        TextView txt1 = findViewById(R.id.txt1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String weightText = edt1.getText().toString();
                    String heightFeetText = edt2.getText().toString();
                    String heightInchesText = edt3.getText().toString();

                    // Validate that all fields are filled
                    if (weightText.isEmpty() || heightFeetText.isEmpty() || heightInchesText.isEmpty()) {
                        txt1.setText(R.string.warning_detail);
                        txt1.setTextColor(Color.RED);
                        return;
                    }

                    int wgt = Integer.parseInt(weightText);
                    int hgt1 = Integer.parseInt(heightFeetText);
                    int hgt2 = Integer.parseInt(heightInchesText);
                    int hgt = (hgt1 * 12) + hgt2;
                    double cm = hgt * 2.54;
                    double m = cm / 100;
                    double bmi = wgt / (m * m);

                    if (bmi < 18.5) {
                        Intent intent = new Intent(MainActivity.this, under.class);
                        startActivity(intent);
                    } else if (bmi >= 18.5 && bmi <= 24.9) {
                        Intent intent = new Intent(MainActivity.this, normal.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, over.class);
                        startActivity(intent);
                    }
                } catch (NumberFormatException e) {
                    txt1.setText("Invalid Input");
                    txt1.setTextColor(Color.RED);
                }
            }
        });

    }
}
