package com.orig.guesswho.block;

import android.content.Context;

import com.orig.guesswho.AppConstants;
import com.orig.guesswho.QuestionHelper;

import java.io.Serializable;
import java.util.ArrayList;


/*
 * Created by Mr Nersesyan on 23/10/2016.
 */

public class Package implements Serializable{
    public ArrayList<Question> questions;
    public String title;
    public String packageUID;
    public String description;
    public boolean locked;
    public int questionCount;
    public int currentQuestionNo;
    public int price;
    public boolean passed;
    public int earnedCoins = 0;
    Context context;


    public Package(String uid, String title, String description, int questionCount, Context context) {
        packageUID = uid;
        this.title = title;

        this.description = description;
        locked = true;
        this.questionCount = questionCount;
        currentQuestionNo = 0;
        questions = new ArrayList<>(questionCount);
        Question newQuestion;
        for (int i = 1; i <= questionCount; i++) {
            newQuestion = QuestionHelper.getInstance().getQuestion(packageUID, i, context);
            questions.add(newQuestion);
        }
        price = questionCount* AppConstants.QUESTION_COST;
    }
}
