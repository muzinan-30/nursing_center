package view;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import po.PreviewTemplate;
import po.Question;
import po.Record;
import po.Template;
import service.PreviewTemplateService;
import service.RecordService;
import serviceimpl.PreviewTemplateServiceimpl;
import serviceimpl.RecordServiceimpl;
import utils.Tools;
/*
* 评估问题界面
* */
public class EvaluationView extends javax.swing.JFrame {
    private static final long serialVersionUID = 8122995752132636267L;
    private JButton btnSubmit;
    private Template template=null;
    private String name;
    private String gender;
    private String ename;
    private String suggest;
    private JScrollPane jScrollPane=new JScrollPane();
    PreviewTemplateService service=new PreviewTemplateServiceimpl();
    RecordService service1=new RecordServiceimpl();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EvaluationView inst = new EvaluationView();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }
    public EvaluationView() {
        initGUI();
    }
    public EvaluationView(Template tt,String name,String gender,String ename) {
        this.template = tt;
        this.name=name;
        this.gender=gender;
        this.ename=ename;
        initGUI();
    }
    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            pack();
            this.setSize(430, 700);
            //初始化数据
            qs = getData();
            //初始化标签，单选按钮组
            init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Question> getData() {
        List<Question> qs=service.getAllqueBytID(template.getId());
        return qs;
    }
    int L = 0;
    ButtonGroup[] bgs;
    JRadioButton[] jrbs;
    List<Question> qs;
    Integer[] scores={5,3,1};//定义A:5分，B:3分，C：1分
    private void init(){
        L = qs.size();
        bgs = new ButtonGroup[L];
        JLabel[] jbs = new JLabel[L];
        jrbs = new JRadioButton[3];//定义三个单选
        Container c = getContentPane();
        c.add(getBtnSubmit());
        int x = 50;// 动态控件组距离左边框距离
        int w = 350;// 动态标题及单选按钮宽
        int h = 45;// 动态单选按钮高
        for (int i = 0; i < L; i++) {
            Question q = qs.get(i);
            bgs[i] = new ButtonGroup();
            jbs[i] = new JLabel("第"+q.getId() + "题:" + q.getContent());
            jbs[i].setForeground(Color.red);
            jbs[i].setBounds(x, 5 + i * 120, w, h);
            c.add(jbs[i]);
            jbs[i].revalidate();

            jrbs[0] = new JRadioButton("A." + q.getAnswer1());
            jrbs[0].setBounds(x, 35 + i * 120, w, h);
            jrbs[0].setActionCommand("q"+i);
            jrbs[0].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    firstActionPerformed(ae);
                }});
            bgs[i].add(jrbs[0]);
            c.add(jrbs[0]);

            jrbs[1] = new JRadioButton("B." + q.getAnswer2());
            jrbs[1].setBounds(x, 65 + i * 120, w, h);
            jrbs[1].setActionCommand("q"+i);
            jrbs[1].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    secondActionPerformed(ae);

                }});
            bgs[i].add(jrbs[1]);
            c.add(jrbs[1]);

            jrbs[2] = new JRadioButton("C." + q.getAnswer3());
            jrbs[2].setBounds(x, 95 + i * 120, w, h);
            jrbs[2].setActionCommand("q"+i);
            jrbs[2].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    thirdActionPerformed(ae);
                }});
            bgs[i].add(jrbs[2]);
            c.add(jrbs[2]);
        }
        repaint();
    }
    Map<String,Integer> rs=new HashMap<String,Integer>();
    public void firstActionPerformed(ActionEvent arg0) {
        JRadioButton jb=(JRadioButton) arg0.getSource();
        String ac=jb.getActionCommand();
        rs.put(ac,scores[0]);
    }
    public void secondActionPerformed(ActionEvent arg0) {
        JRadioButton jb=(JRadioButton) arg0.getSource();
        String ac=jb.getActionCommand();
        rs.put(ac,scores[1]);
    }
    public void thirdActionPerformed(ActionEvent arg0) {
        JRadioButton jb=(JRadioButton) arg0.getSource();
        String ac=jb.getActionCommand();
        rs.put(ac,scores[2]);
    }
    private JButton getBtnSubmit() {
        if(btnSubmit == null) {
            btnSubmit = new JButton();
            btnSubmit.setText("提交");
            btnSubmit.setBounds(300, 600, 80, 24);
            btnSubmit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    btnSubmitActionPerformed(ae);
                }
            });
        }
        return btnSubmit;
    }
    private void btnSubmitActionPerformed(ActionEvent ae) {
        int r=0;
        for(Integer value:rs.values())
        {
            r+=value;
        }
        if(r<=qs.size()*1){
            suggest="bad";
        }
        else if(r>qs.size()*1&&r<qs.size()*4){
            suggest="good";
        }else{
            suggest="nice";
        }
        String time= Tools.getTime();

        Record record=
                new Record(name,gender,template.getTname(),template.getType(),time,ename,suggest);
        service1.addRecord(record);
        Evaluation evaluation=new Evaluation();
        evaluation.setVisible(true);
        evaluation.setLocationRelativeTo(null);
        dispose();
    }

}
