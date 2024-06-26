package com.example.drivingtest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class ExamActivity extends AppCompatActivity {
    public static int score = 20;
    private ArrayList<Question> questions = new ArrayList<>();
    ViewPager2 viewPager;

    TextView prev, con, cur, total,cDown;
    CountDownTimer countDownTimer;

    CountDownTimer countDownTimerTotal;


    int timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        viewPager = (ViewPager2) findViewById(R.id.viewpager);
        prev = (TextView) findViewById(R.id.prev);
        con = (TextView) findViewById(R.id.countinous);
        cur = (TextView) findViewById(R.id.current);
        total = (TextView) findViewById(R.id.total);
        cDown = (TextView) findViewById(R.id.countDown);

        timeLeftInMillis = 5000;
        countDownTimerTotal = new CountDownTimer(100000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent();
                intent.putExtra("result",score);
                setResult(RESULT_OK,intent);
                ExamActivity.this.finish();
            }
        }.start();
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
                viewPager.setUserInputEnabled(false);
                ViewPagerAdapter adapter  = new ViewPagerAdapter(ExamActivity.this ,questions);
                viewPager.setAdapter(adapter);
                cur.setText("1");
                total.setText(questions.size()+"");

                viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        startCountDown();
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
                Toast.makeText(ExamActivity.this, "!ok", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
               timeLeftInMillis = (int) millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis =5000;
                updateCountDownText();
                viewPager.setUserInputEnabled(false);
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }.start();
    }



    private void updateCountDownText() {
        int minutes =(int) ((timeLeftInMillis/1000)/60);
        int seconds = (int) ((timeLeftInMillis/1000)%60);
        String timeLeftFormatted = String.format("%02d:%02d",minutes,seconds);
        cDown.setText(timeLeftFormatted);

        if(timeLeftInMillis<10000){
            cDown.setTextColor(Color.RED);

        }
        else{
            cDown.setTextColor(Color.BLACK);
        }

    }
}