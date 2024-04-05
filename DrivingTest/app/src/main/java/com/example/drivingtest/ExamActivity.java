package com.example.drivingtest;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.drivingtest.services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamActivity extends AppCompatActivity {
    private ArrayList<Question> questions = new ArrayList<>();
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        viewPager = (ViewPager2) findViewById(R.id.viewpager);

        getQuestions();


    }


    private void getQuestions() {
        ApiService.apiService.questionCall().enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                List<Question> questions = response.body();
                for(Question question : questions){
                    Log.i("aaaaaaaaaaaa",question.toString());
                }
                //  viewPager.setUserInputEnabled(false);
                ViewPagerAdapter adapter  = new ViewPagerAdapter(ExamActivity.this ,questions);
                viewPager.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(ExamActivity.this, "!ok", Toast.LENGTH_SHORT).show();
            }
        });
    }
}