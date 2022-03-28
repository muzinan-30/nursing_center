package serviceimpl;

import dao.PreviewTemplateDao;
import daoimpl.PreviewTemplateimpl;
import po.PreviewTemplate;
import po.Question;
import po.Template;

import java.util.List;
import service.*;
/*
 * 预览模板管理服务层的实现类
 * */
public class PreviewTemplateServiceimpl implements PreviewTemplateService {
    PreviewTemplateDao Dao=new PreviewTemplateimpl();
    @Override
    public List<PreviewTemplate> getAllQue() {
        return Dao.getAllQue();
    }
    public int addPreviewTemplate(PreviewTemplate pt){
        return Dao.addPreviewTemplate(pt);
    }
    public int deletePreviewTemplate(Template t, Question q){
        return Dao.deletePreviewTemplate(t,q);
    }
    public List<Question> getAllqueBytID(int Tid){return Dao.getAllqueBytID(Tid);}

}
