package com.example.hw1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText ed_number1, ed_number2;
    TextView tv_result;
    Spinner s_operator;
    Button b_calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ed_number1 = findViewById(R.id.ed_number1);
        ed_number2 = findViewById(R.id.ed_number2);
        tv_result = findViewById(R.id.tv_result);
        s_operator =  (Spinner) findViewById(R.id.s_operator);
        b_calculate = findViewById(R.id.b_calculate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.operators,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_operator.setAdapter(adapter);

        b_calculate.setOnClickListener(btnClick());

    }

    View.OnClickListener btnClick () {
        return v->{
            try {
                double number1 = Integer.parseInt(ed_number1.getText().toString());
                double number2 = Integer.parseInt(ed_number2.getText().toString());
                String operation = s_operator.getSelectedItem().toString();
                double result = 0;

                switch (operation) {
                    case "+":
                        result = number1 + number2;
                        break;
                    case "-":
                        result = number1 - number2;
                        break;
                    case "*":
                        result = number1 * number2;
                        break;
                    case "/":
                        if(number2 == 0)
                            throw new Exception("division by zero");
                        result = number1 / number2;
                        break;
                    case "^":
                        result = Math.pow(number1, number2);
                        break;
                }

                tv_result.setText(String.valueOf(result));
            }
            catch (Exception e) {
                tv_result.setText("Error: " + e.getMessage());
            }
        };
    };


}