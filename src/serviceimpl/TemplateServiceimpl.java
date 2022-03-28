package serviceimpl;

import dao.TemplateDao;
import daoimpl.TemplateDaoimpl;
import po.Template;
import service.TemplateService;

import java.util.List;
/*
 * 模板管理服务层的实现类
 * */
public class TemplateServiceimpl implements TemplateService {
    TemplateDao Dao=new TemplateDaoimpl();
    @Override
    public int addModel(Template t) {
        return Dao.addModel(t);
    }

    @Override
    public List<Template> getAlltemplateByType(String type) {
        return Dao.getAlltemplateByType(type);
    }
    public List<Template> getAllTemplate(){
        return Dao.getAllTemplate();
    }
    @Override
    public Template getTemplateById(int id) {
        return Dao.getTemplateById(id);
    }

    @Override
    public int deleteTemplate(Template t) {
        return Dao.deleteTemplate(t);
    }

    @Override
    public int modifyTemplate() {
        return Dao.modifyTemplate();
    }
}
