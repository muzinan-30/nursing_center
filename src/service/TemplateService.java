package service;

import po.Template;

import java.util.List;
/*
* 模板管理服务层
* */
public interface TemplateService {
    //模板管理
    //添加模板
    public int addModel(Template t);
    //通过模板的类型惊醒获取
    public List<Template> getAlltemplateByType(String type);
    //通过模板的ID进行获取
    public Template getTemplateById(int id);
    //获取所有的模板
    public List<Template> getAllTemplate();
    //删除
    public int deleteTemplate(Template t);
    //修改
    public int modifyTemplate();
}
