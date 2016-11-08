package com.orig.guesswho.block;

/*
 * Created by Mr Nersesyan on 22/10/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question implements Serializable {
    private ArrayList<String> parts;
    private ArrayList<String> answers;
    private String rightAnswer;

    public Question (ArrayList<String> parts, ArrayList<String> answers, String rightAnswer) {
        if (parts.size() == 3 && answers.size() == 4) {
            this.parts = parts;
            this.answers = answers;
            this.rightAnswer = rightAnswer;
        }
    }

    public String getPart(int i) {
        if (0<=i && i<3){
            return parts.get(i);
        } else {
            return null;
        }
    }

    public String getAnswer(int i) {
        if (0<=i && i<4){
            return answers.get(i);
        } else {
            return null;
        }
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeStringList(parts);
//        dest.writeStringList(answers);
//        dest.writeString(rightAnswer);
//    }
//
//    private Question (Parcel in) {
//        parts = in.readString();
//    }
//
//    public static final Creator<Question> CREATOR = new Creator<Question>() {
//        @Override
//        public Question createFromParcel(Parcel in) {
//            return new Question(in);
//        }
//
//        @Override
//        public Question[] newArray(int size) {
//            return new Question[size];
//        }
//    };
}
