package org.duckdns.daisyburn.teragram;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView question;
    EditText answer;
    TextView response;
    int level = 1;
    int maxLevel = 10;

    Random rand = new Random();
    int operand1 = rand.nextInt(50 * level);
    int operand2 = rand.nextInt(50 * level);



    int correctCount = 0;
    int wrongCount = 0;

    String operation = "+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        question = (TextView) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);
        response = (TextView) findViewById(R.id.response);

        question.setText("" + operand1 + " " + operation + operand2 + " =");


        Button submitAnswer = (Button) findViewById(R.id.submit);
        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int correctAnswer = 0;

                if (operation == "+") correctAnswer = operand1 + operand2;
                if (operation == "-") correctAnswer = operand1 - operand2;
                int submittedAnswer = Integer.parseInt(answer.getText().toString());

                if (submittedAnswer == correctAnswer) {
                    response.setText("correct!");
                    correctCount++;
                    wrongCount = 0;
                    if (correctCount == 3) {
                        level++;
                        correctCount = 0;
                    }
                } else {
                    response.setText("try again");
                    wrongCount++;
                    correctCount = 0;
                    if (wrongCount == 2) {
                        level--;
                        wrongCount = 0;
                    }
                }


            }
        });

        Button newQuestion = (Button) findViewById(R.id.newQuestion);
        newQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operand1 = rand.nextInt(level * 50);
                operand2 = rand.nextInt(level * 50);
                question.setText("" + operand1 + operation + operand2 + " =");

            }
        });

        Button tooEasy = (Button) findViewById(R.id.tooEasy);
        tooEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level++;
                if (level > maxLevel) level = maxLevel;
                operand1 = rand.nextInt(level * 50);
                operand2 = rand.nextInt(level * 50);
                question.setText("" + operand1 + operation + operand2 + " =");

            }
        });

        Button tooHard = (Button) findViewById(R.id.tooHard);
        tooHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level--;
                if (level < 1) level = 1;
                operand1 = rand.nextInt(level * 50);
                operand2 = rand.nextInt(level * 50);
                question.setText("" + operand1 + operation + operand2 + " =");
            }
        });


        Button plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operation = "+";
            }
        });

        Button minus = (Button) findViewById(R.id.minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operation = "-";
                operand1 = rand.nextInt(50 * level);
                operand2 = rand.nextInt(50 * level);
                if (operand2 > operand1) {
                    int temp = operand1;
                    operand1 = operand2;
                    operand2 = temp;
                }
                question.setText("" + operand1 + operation + operand2 + " =");
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
