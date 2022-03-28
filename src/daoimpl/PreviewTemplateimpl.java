package daoimpl;

import po.PreviewTemplate;
import po.Question;
import po.Template;
import utils.Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import dao.*;
/*
* 预览模板方法的实现
*
* */
public class PreviewTemplateimpl implements PreviewTemplateDao {
    @Override
    public List<PreviewTemplate> getAllQue() {
        List<PreviewTemplate> listque = Tools.getPreviewTemplate("previewTemplate");
        return listque;
    }

    @Override
    public int addPreviewTemplate(PreviewTemplate pt) {
        try {
            Tools.write("previewTemplate", pt.toString(), true);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deletePreviewTemplate(Template t, Question q) {
        List<PreviewTemplate> list = Tools.getPreviewTemplate("previewTemplate");
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == (t.getId())) {
                if (list.get(i).getQid() == q.getId()) {
                    flag = i;
                }
            }
        }
        list.remove(flag);
        File file = new File("previewTemplate.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            Tools.write("previewTemplate", list.get(i).toString(), true);
        }
        return 0;
    }

    public List<Question> getAllqueBytID(int Tid) {
        List<Question> list = new ArrayList<>();
        List<PreviewTemplate> list1 = getAllQue();
        QuestionDao question=new QuestionDaoimpl();
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getId() == Tid) {
                list.add(question.searchQuestionById(list1.get(i).getQid()));
            }
        }
        return list;
    }

}


