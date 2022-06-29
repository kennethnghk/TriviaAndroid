package im.tobe.triviaandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import im.tobe.triviaandroid.data.Repository;
import im.tobe.triviaandroid.databinding.ActivityMainBinding;
import im.tobe.triviaandroid.model.Question;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        questions = new Repository().getQuestions(questions -> {
            binding.questionText.setText(questions.get(currentQuestionIndex).getAnswer());
            Log.d("Main", "questions in interface: "+ questions);

            updateCounter(questions);
        });

        binding.nextBtn.setOnClickListener(this::onNextBtnClicked);
        binding.trueBtn.setOnClickListener(view -> checkAnswer(true));
        binding.falseBtn.setOnClickListener(view -> checkAnswer(false));
    }

    private void checkAnswer(boolean userAnswer) {
        boolean answer = questions.get(currentQuestionIndex).isAnswerTrue();
        int snackMsgId = 0;
        if (userAnswer == answer) {
            snackMsgId = R.string.correct_answer;
        } else {
            snackMsgId = R.string.incorrect_answer;
        }

        Snackbar.make(binding.cardView, snackMsgId, Snackbar.LENGTH_SHORT).show();
    }

    private void updateCounter(ArrayList<Question> questions) {
        binding.questionCounter.setText(String.format(getString(R.string.question_counter), currentQuestionIndex, questions.size()));
    }

    public void onNextBtnClicked(View view) {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
        updateQuestion();
    }

    private void updateQuestion() {
        String question = questions.get(currentQuestionIndex).getAnswer();
        binding.questionText.setText(question);
        updateCounter((ArrayList<Question>) questions);
    }
}