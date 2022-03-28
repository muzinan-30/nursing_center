package dao;

import po.PreviewTemplate;
import po.Question;
import po.Template;

import java.util.List;
/*
 * 预览模板管理操作接口
 *
 * */
public interface PreviewTemplateDao {
    //预览模板
    //获取对应模板的所有的问题
    public List<PreviewTemplate> getAllQue();
    //添加
    public int addPreviewTemplate(PreviewTemplate pt);
    //删除
    public int deletePreviewTemplate(Template t, Question q);
    //通过ID获取
    public List<Question> getAllqueBytID(int Tid);
}
