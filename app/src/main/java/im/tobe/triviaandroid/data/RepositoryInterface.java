package im.tobe.triviaandroid.data;

import java.util.ArrayList;

import im.tobe.triviaandroid.model.Question;

public interface RepositoryInterface {
    void processFinished(ArrayList<Question> questions);

}
