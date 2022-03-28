package daoimpl;

import po.Question;
import service.QuestionService;
import utils.Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import dao.*;
/*
* 问题管理的实现
*
* */
public class QuestionDaoimpl implements QuestionDao {
    //问题：
    @Override
    public int addQuestion(Question q) {
        try {
            Tools.write("question", q.toString(), true);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Question> getAllQuestion() {
        List<Question> listQuestion = Tools.getQuestionList("question");
        return listQuestion;
    }

    @Override
    public List<Question> getAllquestionByType(String type) {
        List<Question> questions = new ArrayList<>();
        List<Question> list1 = getAllQuestion();
        for (Question q : list1) {
            if (type.equals(q.getType())) {
                questions.add(q);
            }
        }
        return questions;
    }

    @Override
    public Question searchQuestionById(int id) {
        List<Question> listQuestion2 = getAllQuestion();
        for (Question q : listQuestion2) {
            if (id == q.getId()) {
                return q;
            }
        }
        return null;
    }

    @Override
    public int deleteQuestion(Question q) {
        List<Question> list = Tools.getQuestionList("question");
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == (q.getId())) {
                flag = i;
            }
        }
        list.remove(flag);
        File file = new File("question.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            Tools.write("question", list.get(i).toString(), true);
        }
        return 0;
    }

    @Override
    public int modifyQuestion() {
        return 0;
    }

}
