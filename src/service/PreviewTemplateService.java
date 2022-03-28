package service;

import po.PreviewTemplate;
import po.Question;
import po.Template;

import java.util.List;
/*
* 预览模板服务层
* */
public interface PreviewTemplateService {
    //预览界面
    //获取模板中的所有问题
    public List<PreviewTemplate> getAllQue();
    //添加
    public int addPreviewTemplate(PreviewTemplate pt);
    //删除
    public int deletePreviewTemplate(Template t, Question q);
    //获取问题
    public List<Question> getAllqueBytID(int Tid);
}
