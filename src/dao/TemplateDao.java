package dao;

import po.Template;

import java.util.List;
/*
 * 模板管理操作接口
 *
 * */
public interface TemplateDao {
    //对于模板
    //添加
    public int addModel(Template t);
    //通过类型来获取所有的模板
    public List<Template> getAlltemplateByType(String type);
    //获取所有的模板
    public List<Template> getAllTemplate();
    //获取模板通过ID
    public Template getTemplateById(int id);
    //删除模板
    public int deleteTemplate(Template t);
    //修改模板
    public int modifyTemplate();
}
