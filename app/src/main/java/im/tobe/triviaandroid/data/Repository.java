package im.tobe.triviaandroid.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import im.tobe.triviaandroid.controller.AppController;
import im.tobe.triviaandroid.model.Question;

public class Repository {
    ArrayList<Question> questions = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> getQuestions(final RepositoryInterface callback) {

        // call in async
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, resp -> {
            for (int i = 0; i < resp.length(); i++) {
                try {
                    // print the questions
                    Log.d("Repo", String.valueOf(resp.getJSONArray(i).get(0)));


                    String answer = resp.getJSONArray(i).getString(0);
                    Boolean answerTrue = resp.getJSONArray(i).getBoolean(1);

                    Question question = new Question(answer, answerTrue);
                    questions.add(question);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (callback != null) {
                callback.processFinished(questions);
            }
        }, error -> {

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questions;
    }
}
