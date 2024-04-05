package com.example.drivingtest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    private View view;
    Question ques;
    private boolean isTimeUp = false;


    private CountDownTimer countDownTimer;

    TextView question;
    RadioGroup rdoG;
    RadioButton idA,idB,idC,idD;

    Button next, prev;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_question, container, false);

        question = view.findViewById(R.id.ques);
        rdoG = view.findViewById(R.id.rdoGR);
        idA = view.findViewById(R.id.idea1);
        idB = view.findViewById(R.id.idea2);
        idC = view.findViewById(R.id.idea3);
        idD = view.findViewById(R.id.idea4);
        next = view.findViewById(R.id.next);
        prev = view.findViewById(R.id.previous);


        Bundle bundle = getArguments();

        if(bundle != null){
            ques = (Question) bundle.getSerializable("question");
            if(ques != null){
                question.setText(ques.getQuestion());
                idA.setText(ques.getIdeaA());
                idB.setText(ques.getIdeaB());
                idC.setText(ques.getIdeaC());
                idD.setText(ques.getIdeaD());
            }
        }

        rdoG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Get the selected radio button
                RadioButton checkedRadioButton = group.findViewById(checkedId);

                // Get the text of the selected radio button
                String selectedText = checkedRadioButton.getText().toString();

                int count;

                if(selectedText.equals(idA.getText())){
                    count =1;
                    Log.i("rsrsrsrsrsrs",count+"");
                }else if (selectedText.equals(idB.getText())){
                    count =2;
                    Log.i("rsrsrsrsrsrs",count+"");
                }else if (selectedText.equals(idC.getText())){
                    count =3;
                    Log.i("rsrsrsrsrsrs",count+"");
                }else{
                    count =4;
                    Log.i("rsrsrsrsrsrs",count+"");
                }
                
                if(count == ques.getAnswer()){
                    Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (countDownTimer != null) {
//            countDownTimer.cancel();
//        }
//    }

    public void countDown(){
        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the UI with the remaining time

                Toast.makeText(getContext(), millisUntilFinished / 1000+"", Toast.LENGTH_SHORT).show();            }

            @Override
            public void onFinish() {
                isTimeUp = true;

                // Navigate to the next fragment
                ViewPager2 viewPager = (ViewPager2) getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        };

        // Start the countdown timer
        countDownTimer.start();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (isTimeUp) {
//            ViewPager2 viewPager = (ViewPager2) getActivity().findViewById(R.id.viewpager);
//            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//        }
//    }
}