package daoimpl;

import po.Template;
import utils.Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import dao.*;


/*
*
* 模板类的实现
*
* */
public class TemplateDaoimpl implements  TemplateDao {
    //模板
    @Override
    public int addModel(Template t) {
        try {
            Tools.write("template", t.toString(), true);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Template> getAllTemplate() {
        List<Template> listtemplate = Tools.getTemplateList("template");
        return listtemplate;
    }

    @Override
    public List<Template> getAlltemplateByType(String type) {
        List<Template> questions = new ArrayList<>();
        List<Template> list1 = getAllTemplate();
        for (Template t : list1) {
            if (type.equals(t.getType())) {
                questions.add(t);
            }
        }
        return questions;
    }

    @Override
    public Template getTemplateById(int id) {
        List<Template> listtemplate2 = getAllTemplate();
        for (Template t : listtemplate2) {
            if (id == t.getId()) {
                return t;
            }
        }
        return null;
    }

    @Override
    public int deleteTemplate(Template t) {
        List<Template> list = Tools.getTemplateList("template");
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == (t.getId())) {
                flag = i;
            }
        }
        list.remove(flag);
        File file = new File("template.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            Tools.write("template", list.get(i).toString(), true);
        }
        return 0;
    }

    @Override
    public int modifyTemplate() {
        return 0;
    }
}
