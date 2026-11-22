package com.example.taskhub;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class Calculator extends AppCompatActivity {

    EditText input;
    TextView solution;
    boolean isOpenBracket = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_calculator), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        input = findViewById(R.id.inputValue);
        solution = findViewById(R.id.solutionValue);

        input.setShowSoftInputOnFocus(false);
        input.setCursorVisible(true);

//      Importing all buttons

        MaterialButton btn_0 = findViewById(R.id.zeroButton);
        MaterialButton btn_1 = findViewById(R.id.oneButton);
        MaterialButton btn_2 = findViewById(R.id.twoButton);
        MaterialButton btn_3 = findViewById(R.id.threeButton);
        MaterialButton btn_4 = findViewById(R.id.fourButton);
        MaterialButton btn_5 = findViewById(R.id.fiveButton);
        MaterialButton btn_6 = findViewById(R.id.sixButton);
        MaterialButton btn_7 = findViewById(R.id.sevenButton);
        MaterialButton btn_8 = findViewById(R.id.eightButton);
        MaterialButton btn_9 = findViewById(R.id.nineButton);
        MaterialButton btn_dot = findViewById(R.id.dotButton);
        MaterialButton btn_add = findViewById(R.id.addButton);
        MaterialButton btn_sub = findViewById(R.id.subtractButton);
        MaterialButton btn_div = findViewById(R.id.divideButton);
        MaterialButton btn_mul = findViewById(R.id.multiplyButton);
        MaterialButton btn_percent = findViewById(R.id.percentButton);

        MaterialButton btn_allClear = findViewById(R.id.allClearButton);
        MaterialButton btn_bracket = findViewById(R.id.bracketButton);
        MaterialButton btn_backButton = findViewById(R.id.backButton);

        MaterialButton btn_equal = findViewById(R.id.equalButton);

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append("0");
                updateLiveResult();
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append("1");
                updateLiveResult();
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append("2");
                updateLiveResult();
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append("3");
                updateLiveResult();
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append("4");
                updateLiveResult();
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append("5");
                updateLiveResult();
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append("6");
                updateLiveResult();
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append("7");
                updateLiveResult();
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append("8");
                updateLiveResult();
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append("9");
                updateLiveResult();
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.append(".");
                updateLiveResult();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator(" + ");
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator(" - ");
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator(" x ");
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator(" / ");
            }
        });

        btn_percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendOperator(" % ");
            }
        });

        btn_allClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                solution.setText("");
                isOpenBracket = true;
            }
        });

        btn_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                if(!text.isEmpty()){
                    char lastChar = text.charAt(text.length() - 1);

                    if(text.endsWith(" ")){
                        text = text.substring(0, text.length() - 3);
                    }else{
                        text = text.substring(0,text.length() - 1);
                    }

                    if(lastChar == '(' || lastChar == ')'){
                        isOpenBracket = !isOpenBracket;
                    }
                    input.setText(text);
                    updateLiveResult();
                }
            }
        });

        btn_bracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpenBracket) {
                    input.append("(");
                    isOpenBracket = false;
                } else {
                    input.append(")");
                    isOpenBracket = true;
                }
                updateLiveResult();
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentInput = input.getText().toString().trim();

                if(currentInput.isEmpty()) {
                    return;
                }

                String input_str = currentInput
                        .replace("(", "")
                        .replace(")", "")
                        .trim();

                String[] input_list = input_str.split(" ");
                if(input_list.length < 3 || input_list[2].isEmpty()){
                    Toast.makeText(Calculator.this, "Enter All Field", Toast.LENGTH_SHORT).show();
                    return;
                }

                String calculated = calculateResult(currentInput);
                if(calculated == null) {
                    Toast.makeText(Calculator.this, "Enter Value", Toast.LENGTH_SHORT).show();
                    return;
                }

                input.setText(calculated);
                solution.setText("");
                isOpenBracket = true;
            }
        });

    }

    private void updateLiveResult() {
        String preview = calculateResult(input.getText().toString());
        if(preview != null) {
            solution.setText(preview);
        }
        else {
            solution.setText("");
        }
    }
    private String calculateResult(String rawInput) {

        String[] input_list = rawInput
                .replace("(", "")
                .replace(")", "")
                .trim()
                .split(" ");

        try {
            double num1 = Double.parseDouble(input_list[0]);
            double num2 = Double.parseDouble(input_list[2]);
            char op = input_list[1].charAt(0);

            double result = 0;

            switch (op) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case 'x':
                    result = num1 * num2;
                    break;
                case '/':
                    result = (num2 != 0) ? num1 / num2 : 0;
                    break;
                case '%':
                    result = (num2 != 0) ? (num1 * num2) / 100.0 : 0;
                    break;
                default:
                    return null;
            }

            if (result == (int) result) {
                return String.valueOf((int) result);
            } else {
                return String.valueOf(result);
            }
        } catch (Exception e) {
            return  null;
        }
    }

    private void appendOperator(String newOp) {
        String text = input.getText().toString();
        if (text.isEmpty()) {
            return;
        }

        if (text.matches(".*\\s[+\\-x/%]\\s$")) {
            text = text.replaceAll("\\s[+\\-x/%]\\s$", newOp);
            input.setText(text);
            input.setSelection(input.getText().length());
            updateLiveResult();
            return;
        }

        if (text.matches(".*\\s[+\\-x/%]\\s.*")) {
            text = text.replaceFirst("\\s[+\\-x/%]\\s", newOp);
            input.setText(text);
            input.setSelection(input.getText().length());
            updateLiveResult();
            return;
        }

        input.append(newOp);
        updateLiveResult();
    }
}