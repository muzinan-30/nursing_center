package serviceimpl;

import dao.QuestionDao;
import daoimpl.QuestionDaoimpl;
import po.Question;

import java.util.List;
import service.*;
/*
 * 问题管理服务层的实现类
 * */
public class QuestionServiceimpl implements QuestionService{
    QuestionDao Dao=new QuestionDaoimpl();
    @Override
    public int addQuestion(Question q) {
        return Dao.addQuestion(q);
    }


    @Override
    public List<Question> getAllQuestion() {
        return Dao.getAllQuestion();
    }

    @Override
    public List<Question> getAllquestionByType(String type) {
        return Dao.getAllquestionByType(type);
    }
    @Override
    public Question searchQuestionById(int id){
        return Dao.searchQuestionById(id);
    }
    @Override
    public int deleteQuestion(Question q) {
        return Dao.deleteQuestion(q);
    }

    @Override
    public int modifyQuestion() {
        return Dao.modifyQuestion();
    }

}
