package im.tobe.triviaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import im.tobe.triviaandroid.data.Repository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Repository().getQuestions(questions -> {
            Log.d("Main", "questions in interface: "+ questions);
        });
    }
}