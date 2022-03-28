package dao;

import po.Question;

import java.util.List;
/*
 * 问题管理操作接口
 *
 * */
public interface QuestionDao {
    //对于问题
    //添加
    public int addQuestion(Question q);
    //获取所有的问题
    public List<Question> getAllQuestion();
    //通过问题的类型来获取所有的问题
    public List<Question> getAllquestionByType(String type);
    //通过问题的ID来获取问题
    public Question searchQuestionById(int id);
    //删除问题
    public int deleteQuestion(Question q);
    //修改问题
    public int modifyQuestion();
}
