package com.example.drivingtest;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drivingtest.models.Question;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    private View view;
    Question ques;
    private boolean isTimeUp = false;

    TextView question;
    RadioGroup rdoG;
    RadioButton idA,idB,idC,idD;



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
                    rdoG.setEnabled(false);
                    idA.setEnabled(false);
                    idB.setEnabled(false);
                    idC.setEnabled(false);
                    idD.setEnabled(false);
                    Log.i("scoreeeeeeeeeeee",ExamActivity.score+" ");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.yes);
                    mediaPlayer.start();
                }
                else{
                    Toast.makeText(getContext(), "!ok", Toast.LENGTH_SHORT).show();
                    rdoG.setEnabled(false);
                    idA.setEnabled(false);
                    idB.setEnabled(false);
                    idC.setEnabled(false);
                    idD.setEnabled(false);
                    ExamActivity.score -= 1;
                    Log.i("scoreeeeeeeeeeee",ExamActivity.score+" ");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.no);
                    mediaPlayer.start();
                }

                if (checkedId == -1) {
                    Toast.makeText(getContext(), "No answer selected, point deducted!", Toast.LENGTH_SHORT).show();
                    ExamActivity.score -= 1;
                    Log.i("scoreeeeeeeeeeee", ExamActivity.score + " ");
                }
            }
        });
        return view;
    }
}