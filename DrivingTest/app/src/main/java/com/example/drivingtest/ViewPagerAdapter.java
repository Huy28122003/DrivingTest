package com.example.drivingtest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter  extends FragmentStateAdapter{

    private List<Question> questions;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Question> questions) {
        super(fragmentActivity);
        this.questions = questions;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(questions  == null){
            return null;
        }
        Question question = questions.get(position);
        QuestionFragment questionFragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("question",question);
        questionFragment.setArguments(bundle);
       //  questionFragment.countDown();
        return questionFragment;
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
