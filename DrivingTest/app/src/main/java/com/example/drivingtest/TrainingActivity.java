package com.example.drivingtest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.drivingtest.models.Question;
import com.example.drivingtest.services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainingActivity extends AppCompatActivity {

    private ArrayList<Question> questions = new ArrayList<>();
    TextView prev, con, cur, total,cDown;

    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        viewPager = (ViewPager2) findViewById(R.id.viewpager);
        prev = (TextView) findViewById(R.id.prev);
        con = (TextView) findViewById(R.id.countinous);
        cur = (TextView) findViewById(R.id.current);
        total = (TextView) findViewById(R.id.total);

        getQuestions();

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }

        });
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });



    }

    private void getQuestions() {
        ApiService.apiService.questionCall().enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                List<Question> questions = response.body();
                for(Question question : questions){
                    Log.i("aaaaaaaaaaaa",question.toString());
                }
//                viewPager.setUserInputEnabled(false);
                ViewPagerAdapter adapter  = new ViewPagerAdapter(TrainingActivity.this ,questions);
                viewPager.setAdapter(adapter);
                cur.setText("1");
                total.setText(questions.size()+"");

                viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        cur.setText(String.valueOf(position + 1));
                        if(position ==0){
                            prev.setVisibility(View.GONE);
                            con.setVisibility(View.VISIBLE);
                        }else if(position == questions.size()-1){
                            con.setVisibility(View.GONE);
                            prev.setVisibility(View.VISIBLE);
                        }
                        else{
                            prev.setVisibility(View.VISIBLE);
                            con.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(TrainingActivity.this, "!ok", Toast.LENGTH_SHORT).show();
            }
        });
    }
}