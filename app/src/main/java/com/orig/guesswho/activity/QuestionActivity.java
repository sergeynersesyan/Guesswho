package com.orig.guesswho.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.orig.guesswho.AppConstants;
import com.orig.guesswho.block.Package;
import com.orig.guesswho.PreferenceController;
import com.orig.guesswho.block.Question;
import com.orig.guesswho.R;

public class QuestionActivity extends AppCompatActivity {

    TextView[] answer = new TextView[4];
    TextView[] part = new TextView[3];
    TextView mainQuestion;
    TextView notSureButton;
    TextView questionCost;
    TextView questionTitle;
    TextView plusCoins;
    TextView totalCoins;
    Package currentPackage;
    int currentPartNo = 0;
    Question currentQuestion;
    private final int answerTextSize = 16;
    int currentCost = AppConstants.QUESTION_COST;
    SharedPreferences sPref;
    private int totalCoinsValue;
    boolean finished = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        part[0] = (TextView) findViewById(R.id.part0_textView);
        part[1] = (TextView) findViewById(R.id.part1_textView);
        part[2] = (TextView) findViewById(R.id.part2_textView);
        answer[0] = (TextView) findViewById(R.id.answer_1);
        answer[1] = (TextView) findViewById(R.id.answer_2);
        answer[2] = (TextView) findViewById(R.id.answer_3);
        answer[3] = (TextView) findViewById(R.id.answer_4);
        mainQuestion = (TextView) findViewById(R.id.main_question_textView);
        questionCost = (TextView) findViewById(R.id.question_cost_textview);
        questionTitle = (TextView) findViewById(R.id.story_title_textView);
        notSureButton = (TextView) findViewById(R.id.not_sure_button_textview);
        currentPackage = (Package) getIntent().getSerializableExtra(AppConstants.PACKAGE_EXTRA);
        plusCoins = (TextView) findViewById(R.id.plus_coins_text);
        totalCoins = (TextView) findViewById(R.id.total_coins_textView);
        loadState();
        initNewQuestion();
    }

    private void initNewQuestion(){
        currentQuestion = currentPackage.questions.get(currentPackage.currentQuestionNo);
        if (finished) {
            next();
        } else {
            notSureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentCost = Math.max(currentCost - AppConstants.NOT_SURE_COST, 0);
                    currentPartNo++;
                    initCommons();
                }
            });
            notSureButton.setText(R.string.not_sure_text);
        }

        initAnswers(currentQuestion);
        initCommons();

        for (int i = 0; i < part.length; i++) {
            part[i].setText(currentQuestion.getPart(i));
        }
        questionTitle.setText(currentPackage.title + " " + (currentPackage.currentQuestionNo + 1) + "/" + currentPackage.questionCount);
        String x = Integer.toString(totalCoinsValue);
        totalCoins.setText(x);
        for (int i = 0; i< currentPartNo; i++) {
            part[i].setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        findViewById(R.id.questions_linearLayout).setPadding(0,0,0,findViewById(R.id.answers_relativelayout).getHeight());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveState();
    }

    private void initAnswers(final Question currentQuestion) {

        View.OnClickListener onAnsweredListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(v instanceof TextView)) {
                    throw new UnsupportedOperationException("This listener should be set only to answer Textview");
                }
                TextView selectedButton = (TextView) v;
                if (selectedButton.getText().toString().equals(currentQuestion.getRightAnswer())) {
                    while (currentPartNo < part.length-1) {
                        currentPartNo++;
                        part[currentPartNo].setTextColor(getResources().getColor(R.color.color_right));
                        part[currentPartNo].setVisibility(View.VISIBLE);
                    }
                    selectedButton.setBackgroundResource(R.drawable.answer_right_button);
                    next();
                    totalCoinsValue += currentCost;
                    String newTotalCoins = totalCoinsValue + "";
                    totalCoins.setText(newTotalCoins);
                    String plusText = "+" + currentCost;
                    plusCoins.setText(plusText);
                    plusCoins.setVisibility(View.VISIBLE);
                    Animation anim = AnimationUtils.loadAnimation(QuestionActivity.this, R.anim.anim_size);
                    Animation animPlus = AnimationUtils.loadAnimation(QuestionActivity.this, R.anim.move_up);
                    animPlus.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            plusCoins.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    plusCoins.startAnimation(animPlus);
                    findViewById(R.id.coins_icon_imageView).startAnimation(anim);
//
                } else {
                    selectedButton.setBackgroundResource(R.drawable.answer_error_button);
                    currentCost = Math.max(currentCost - AppConstants.ERROR_COST, 0);
                    currentPartNo++;
                    initCommons();
                }
            }
        };


        for (int i = 0; i < 4; i++) {
            answer[i].setText(currentQuestion.getAnswer(i));
            answer[i].setBackgroundResource(R.drawable.answer_options_button);
            fitInButton(answer[i]);
            if (!finished) {
                answer[i].setOnClickListener(onAnsweredListener);
            }
        }
    }

    private void fitInButton(TextView textView) {
        int charCount = textView.getText().length();
        if (charCount > 15) {
            textView.setTextSize(answerTextSize - 4);
        } else if (charCount > 11) {
            textView.setTextSize(answerTextSize - 2);
        }
    }

    private void initCommons() {
        if (currentPartNo < part.length) {
            part[currentPartNo].setVisibility(View.VISIBLE);
            String s = currentCost + "";
            questionCost.setText(s);
            if ("0".equals(s)) {
                next();
                part[currentPartNo].setTextColor(getResources().getColor(R.color.color_error));
            }
        } else {
            next();
        }
    }

    private void next() {
        for (TextView a : answer) {
            a.setOnClickListener(null);
        }
        notSureButton.setText("NEXT");
        finished = true;
        notSureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPackage.currentQuestionNo++;
                currentCost = AppConstants.QUESTION_COST;
                currentPartNo = 0;
                finished = false;
                for (TextView p:part) {
                    p.setVisibility(View.GONE);
                    p.setTextColor(getResources().getColor(R.color.color_question_text));
                }
                initNewQuestion();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveState();
    }

    void saveState() {
        sPref = getSharedPreferences(AppConstants.PREF_FOLDER,MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(currentPackage.packageUID + AppConstants.PREF_PACK_INTS, codeState(currentPackage.currentQuestionNo, currentCost, currentPartNo));
        ed.putInt(AppConstants.PREF_TOTAL_COINS, totalCoinsValue);
        ed.putBoolean(currentPackage.packageUID + AppConstants.PREF_BOOL_FINISHED, finished);
        ed.apply();
    }

    void loadState() {
        sPref = getSharedPreferences(AppConstants.PREF_FOLDER,MODE_PRIVATE);
        int codedInt = sPref.getInt(currentPackage.packageUID + AppConstants.PREF_PACK_INTS, 0);
        totalCoinsValue = PreferenceController.getInstance(getApplicationContext()).getTotalCoins();
        if (codedInt <= 0) {
            currentCost = AppConstants.QUESTION_COST;
            currentPartNo = currentPackage.currentQuestionNo = 0;
        } else {
            decodeState(codedInt);
        }
        finished = sPref.getBoolean(currentPackage.packageUID + AppConstants.PREF_BOOL_FINISHED, false);
    }

    private int codeState(int cQuestion, int cCost, int cPartNo) {
        if (cQuestion >= currentPackage.questionCount || cCost > AppConstants.QUESTION_COST || cPartNo > 3) {
            return -1;
        } else return 100 * cQuestion + 10 * cCost + cPartNo;
    }

    private void decodeState(int codedInt) {
        currentPackage.currentQuestionNo = codedInt / 100;
        currentCost = (codedInt % 100) / 10;
        currentPartNo = codedInt % 10;

    }
}
