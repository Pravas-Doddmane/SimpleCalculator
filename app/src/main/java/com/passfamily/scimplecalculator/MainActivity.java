package com.passfamily.scimplecalculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    String currentInput = "";
    double firstNumber = 0;
    String operator = "";
    boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText.setShowSoftInputOnFocus(false); // disable keyboard

        // Numbers
        int[] numberButtons = {R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button_dot};

        View.OnClickListener numberListener = v -> {
            if (isNewOp) {
                currentInput = "";
                isNewOp = false;
            }
            Button btn = (Button) v;
            currentInput += btn.getText().toString();
            editText.setText(currentInput);
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(numberListener);
        }

        // Operators
        int[] operatorButtons = {R.id.button_add, R.id.button_sub, R.id.button_mult, R.id.button_div};
        View.OnClickListener operatorListener = v -> {
            Button btn = (Button) v;
            firstNumber = Double.parseDouble(editText.getText().toString());
            operator = btn.getText().toString();
            isNewOp = true;
        };
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(operatorListener);
        }

        // Equals
        findViewById(R.id.button_eql).setOnClickListener(v -> {
            double secondNumber = Double.parseDouble(editText.getText().toString());
            double result = 0;

            switch (operator) {
                case "+": result = firstNumber + secondNumber; break;
                case "-": result = firstNumber - secondNumber; break;
                case "*": result = firstNumber * secondNumber; break;
                case "/":
                    if (secondNumber != 0) result = firstNumber / secondNumber;
                    else editText.setText("Error");
                    break;
            }
            editText.setText(String.valueOf(result));
            isNewOp = true;
        });

        // Clear
        findViewById(R.id.button_Cancel).setOnClickListener(v -> {
            currentInput = "";
            operator = "";
            firstNumber = 0;
            editText.setText("");
            isNewOp = true;
        });
    }
}
