package service;

import po.Question;

import java.util.List;
/*
* 问题管理服务层
* */
public interface QuestionService {
    //问题管理
    //添加问题
    public int addQuestion(Question q);
    //获取所有的问题
    public List<Question> getAllQuestion();
    //通过类型获取所有的问题
    public List<Question> getAllquestionByType(String type);
    //通过问题的ID来获取问题
    public Question searchQuestionById(int id);
    //删除问题
    public int deleteQuestion(Question q);
    public int modifyQuestion();
}
