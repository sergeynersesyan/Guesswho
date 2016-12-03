package com.orig.guesswho;

import android.content.Context;

import com.orig.guesswho.block.Package;
import com.orig.guesswho.block.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * Created by Mr Nersesyan on 22/10/2016.
 */

public class QuestionHelper {
    private static QuestionHelper thisInstance;
    private HashMap<String, Integer> questionStringIDs = new HashMap<>();
    private HashMap<String, Integer> packageResourceIDs = new HashMap<>();

    private QuestionHelper() {
    }

    public static QuestionHelper getInstance() {
        if (thisInstance == null) {
            thisInstance = new QuestionHelper();
        }
        return thisInstance;
    }

    public Question getQuestion(String packageName, int number, Context context) {
        if (questionStringIDs.size() == 0) {

            throw new IllegalStateException("Question String IDs are not initialised yet");
        }
        int partsID = questionStringIDs.get(packageName + "Question" + number);
        int answersID = questionStringIDs.get(packageName + "Answers" + number);
        int rightAnswerID = questionStringIDs.get(packageName + "Answer" + number);

        String[] partsArray = context.getResources().getStringArray(partsID);
        String[] answersArray = context.getResources().getStringArray(answersID);
        String rightAnswer = context.getString(rightAnswerID);

        ArrayList<String> parts = new ArrayList<>(Arrays.asList(partsArray));
        ArrayList<String> answers = new ArrayList<>(Arrays.asList(answersArray));

        return new Question(parts, answers, rightAnswer);
    }

    public ArrayList<Package> getAllPackages(Context context) {
        ArrayList<Package> allPackages = new ArrayList<>(AppConstants.PACKAGE_COUNT);
        PreferenceController pref = PreferenceController.getInstance(context.getApplicationContext());
        for (int i = 1; i <= AppConstants.PACKAGE_COUNT; i++) {
            Package p = getPackage(i, context);
            pref.loadLock(p);
            allPackages.add(p);
        }
        return allPackages;
    }

    public Package getPackage(int number, Context context) {
        if (packageResourceIDs.size() == 0) {
            throw new IllegalStateException("package String IDs are not initialised yet");
        }

        int uidID = packageResourceIDs.get("package" + number + "UID");
        int titleID = packageResourceIDs.get("package" + number + "title");
        int descriptionID = packageResourceIDs.get("package" + number + "description");
        int questionCountID = packageResourceIDs.get("package" + number + "questionCount");


        return new Package(context.getString(uidID), context.getString(titleID), context.getString(descriptionID),
                context.getResources().getInteger(questionCountID), context);
    }

    public void initStringIDs() {
        packageResourceIDs.put("package1UID", R.string.package1UID);
        packageResourceIDs.put("package1title", R.string.package1title);
        packageResourceIDs.put("package1description", R.string.package1description);
        packageResourceIDs.put("package1questionCount", R.integer.package1questionCount);
        questionStringIDs.put("trialQuestion1", R.array.trialQuestion1);
        questionStringIDs.put("trialAnswers1", R.array.trialAnswers1);
        questionStringIDs.put("trialAnswer1", R.string.trialAnswer1);
        questionStringIDs.put("trialQuestion2", R.array.trialQuestion2);
        questionStringIDs.put("trialAnswers2", R.array.trialAnswers2);
        questionStringIDs.put("trialAnswer2", R.string.trialAnswer2);
        questionStringIDs.put("trialQuestion3", R.array.trialQuestion3);
        questionStringIDs.put("trialAnswers3", R.array.trialAnswers3);
        questionStringIDs.put("trialAnswer3", R.string.trialAnswer3);

        packageResourceIDs.put("package2UID", R.string.package2UID);
        packageResourceIDs.put("package2title", R.string.package2title);
        packageResourceIDs.put("package2description", R.string.package2description);
        packageResourceIDs.put("package2questionCount", R.integer.package2questionCount);
        questionStringIDs.put("animalsQuestion1", R.array.animalsQuestion1);
        questionStringIDs.put("animalsAnswers1", R.array.animalsAnswers1);
        questionStringIDs.put("animalsAnswer1", R.string.animalsAnswer1);
        questionStringIDs.put("animalsQuestion2", R.array.animalsQuestion2);
        questionStringIDs.put("animalsAnswers2", R.array.animalsAnswers2);
        questionStringIDs.put("animalsAnswer2", R.string.animalsAnswer2);
        questionStringIDs.put("animalsQuestion3", R.array.animalsQuestion3);
        questionStringIDs.put("animalsAnswers3", R.array.animalsAnswers3);
        questionStringIDs.put("animalsAnswer3", R.string.animalsAnswer3);
        questionStringIDs.put("animalsQuestion4", R.array.animalsQuestion4);
        questionStringIDs.put("animalsAnswers4", R.array.animalsAnswers4);
        questionStringIDs.put("animalsAnswer4", R.string.animalsAnswer4);
        questionStringIDs.put("animalsQuestion5", R.array.animalsQuestion5);
        questionStringIDs.put("animalsAnswers5", R.array.animalsAnswers5);
        questionStringIDs.put("animalsAnswer5", R.string.animalsAnswer5);
        questionStringIDs.put("animalsQuestion6", R.array.animalsQuestion6);
        questionStringIDs.put("animalsAnswers6", R.array.animalsAnswers6);
        questionStringIDs.put("animalsAnswer6", R.string.animalsAnswer6);
        questionStringIDs.put("animalsQuestion7", R.array.animalsQuestion7);
        questionStringIDs.put("animalsAnswers7", R.array.animalsAnswers7);
        questionStringIDs.put("animalsAnswer7", R.string.animalsAnswer7);
        questionStringIDs.put("animalsQuestion8", R.array.animalsQuestion8);
        questionStringIDs.put("animalsAnswers8", R.array.animalsAnswers8);
        questionStringIDs.put("animalsAnswer8", R.string.animalsAnswer8);
        questionStringIDs.put("animalsQuestion9", R.array.animalsQuestion9);
        questionStringIDs.put("animalsAnswers9", R.array.animalsAnswers9);
        questionStringIDs.put("animalsAnswer9", R.string.animalsAnswer9);
        questionStringIDs.put("animalsQuestion10", R.array.animalsQuestion10);
        questionStringIDs.put("animalsAnswers10", R.array.animalsAnswers10);
        questionStringIDs.put("animalsAnswer10", R.string.animalsAnswer10);
        questionStringIDs.put("animalsQuestion11", R.array.animalsQuestion11);
        questionStringIDs.put("animalsAnswers11", R.array.animalsAnswers11);
        questionStringIDs.put("animalsAnswer11", R.string.animalsAnswer11);









        packageResourceIDs.put("package3UID", R.string.package3UID);
        packageResourceIDs.put("package3title", R.string.package3title);
        packageResourceIDs.put("package3description", R.string.package3description);
        packageResourceIDs.put("package3questionCount", R.integer.package3questionCount);
        questionStringIDs.put("thirdQuestion1", R.array.thirdQuestion1);
        questionStringIDs.put("thirdAnswers1", R.array.thirdAnswers1);
        questionStringIDs.put("thirdAnswer1", R.string.thirdAnswer1);
        questionStringIDs.put("thirdQuestion2", R.array.thirdQuestion2);
        questionStringIDs.put("thirdAnswers2", R.array.thirdAnswers2);
        questionStringIDs.put("thirdAnswer2", R.string.thirdAnswer2);
        questionStringIDs.put("thirdQuestion3", R.array.thirdQuestion3);
        questionStringIDs.put("thirdAnswers3", R.array.thirdAnswers3);
        questionStringIDs.put("thirdAnswer3", R.string.thirdAnswer3);
    }
}

