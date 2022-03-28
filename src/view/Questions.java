package view;

import po.PreviewTemplate;
import po.Question;

import service.PreviewTemplateService;
import service.QuestionService;
import serviceimpl.PreviewTemplateServiceimpl;
import serviceimpl.QuestionServiceimpl;
import utils.Tools;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/*
问题管理界面
* */
public class Questions extends javax.swing.JFrame {
    {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JComboBox jComboBox1;
    private JButton jButton1;
    private JButton jButton4;
    JTable jTable1;
    private JButton jButton3;
    private JButton jButton2;
    private JScrollPane questions;
    Vector<Vector> datas = new Vector<>();
    Questions que;
    List<Question> list;
    private List<Question> questionlist = null;
    private Vector<Vector> searchbyType = new Vector<>();
    Vector cols = new Vector();
    private DefaultTableModel tableModel = null;
    PreviewJFrame pre;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Questions inst = new Questions();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public Questions() {
        super();
        initGUI();
    }

    public Questions(PreviewJFrame p) {
        super();
        this.pre = p;
        initGUI();
    }

    private void initGUI() {
        Object titles[] = {" ", "ID", "题目", "类型"};
        for (int i = 0; i < titles.length; i++) {
            cols.add(titles[i]);
        }
        QuestionService stservice = new QuestionServiceimpl();
        list = stservice.getAllQuestion();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Vector vector = new Vector();
                vector.add(new Boolean(false));
                vector.add(list.get(i).getId());
                vector.add(list.get(i).getContent());
                vector.add(list.get(i).getType());
                datas.add(vector);
            }
        }
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            //筛选
            {
                ComboBoxModel jComboBox1Model =
                        new DefaultComboBoxModel(
                                new String[]{"所有", "A类型", "B类型", "C类型", "D类型"});
                jComboBox1 = new JComboBox();
                getContentPane().add(jComboBox1);
                jComboBox1.setModel(jComboBox1Model);
                jComboBox1.setBounds(17, 16, 102, 27);
                jComboBox1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        datas.clear();
                        QuestionService service = new QuestionServiceimpl();
                        String type = jComboBox1.getSelectedItem().toString();
                        if (type.equals("所有")) {
                            list = service.getAllQuestion();
                            if (list != null) {
                                for (int i = 0; i < list.size(); i++) {
                                    Vector vector = new Vector();
                                    vector.add(new Boolean(false));
                                    vector.add(list.get(i).getId());
                                    vector.add(list.get(i).getContent());
                                    vector.add(list.get(i).getType());
                                    datas.add(vector);
                                }
                            }
                            TableModel se = new DefaultTableModel(datas, cols);
                            jTable1.setModel(se);
                            Tools.setbox(jTable1);
                            jTable1.repaint();
                        } else {
                            QuestionService stservice = new QuestionServiceimpl();
                            questionlist = stservice.getAllquestionByType(type);
                            for (int i = 0; i < questionlist.size(); i++) {
                                Vector vector = new Vector();
                                vector.add(new Boolean(false));
                                vector.add(questionlist.get(i).getId());
                                vector.add(questionlist.get(i).getContent());
                                vector.add(questionlist.get(i).getType());
                                datas.add(vector);
                            }
                            TableModel se1 = new DefaultTableModel(datas, cols);
                            jTable1.setModel(se1);
                            Tools.setbox(jTable1);
                            jTable1.repaint();
                        }
                    }

                });
            }
            //删除
            {
                jButton1 = new JButton();
                getContentPane().add(jButton1);
                jButton1.setText("\u5220\u9664");
                jButton1.setBounds(17, 86, 86, 27);
                jButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuestionService service = new QuestionServiceimpl();
                        for (int i = 0; i < jTable1.getRowCount(); ) {
                            if ((Boolean) jTable1.getValueAt(i, 0)) {
                                Question q = service.searchQuestionById((int) jTable1.getValueAt(i, 1));
                                service.deleteQuestion(q);
                                System.out.println(jTable1.getRowCount());
                                tableModel.removeRow(i);
                                i = 0;
                                continue;
                            }
                            i++;
                        }
                        tableModel.fireTableDataChanged();
                        jTable1.updateUI();
                    }
                });
            }
            //详情
            {
                jButton2 = new JButton();
                getContentPane().add(jButton2);
                jButton2.setText("\u8be6\u60c5");
                jButton2.setBounds(373, 86, 85, 27);
                jButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index = jTable1.getSelectedRow();
                        int id = (int) datas.get(index).get(1);
                        QuestionService service = new QuestionServiceimpl();
                        Question question = service.searchQuestionById(id);
                        QuestionContent questionContent = new QuestionContent(question);
                        questionContent.setVisible(true);
                        questionContent.setLocationRelativeTo(null);
                    }
                });
            }
            //新增
            {
                jButton3 = new JButton();
                getContentPane().add(jButton3);
                jButton3.setText("\u65b0\u589e");
                jButton3.setBounds(475, 86, 90, 27);
                que = this;
                jButton3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        AddNewQuestion add = new AddNewQuestion(que);
                        add.setVisible(true);
                        add.setLocationRelativeTo(null);
                    }
                });
            }
            //table
            {
                questions = new JScrollPane();
                getContentPane().add(questions);
                questions.setBounds(17, 124, 548, 289);
                tableModel = new DefaultTableModel(datas, cols);
                jTable1 = new JTable();
                jTable1.setModel(tableModel);
                Tools.setbox(jTable1);
                questions.setViewportView(jTable1);
                jTable1.setRowHeight(30);
                jTable1.setBounds(17, 124, 548, 289);
            }

            {
                jButton4 = new JButton();
                getContentPane().add(jButton4);
                jButton4.setText("添加");
                jButton4.setBounds(150, 436, 92, 27);
                jButton4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PreviewTemplateService service = new PreviewTemplateServiceimpl();
                        QuestionService service1 = new QuestionServiceimpl();
                        for (int i = 0; i < jTable1.getRowCount(); ) {
                            if ((Boolean) jTable1.getValueAt(i, 0)) {
                                int id = (int) jTable1.getValueAt(i, 1);
                                Question q = service1.searchQuestionById(id);
                                Vector<Object> vector1 = new Vector<>();
                                vector1.add(new Boolean(false));
                                vector1.add(q.getId());
                                vector1.add(q.getContent());
                                pre.quedata.add(vector1);
                                //向PreviewTemplate中添加问题
                                PreviewTemplate pt = new PreviewTemplate();
                                pt.setId(pre.template.getId());
                                pt.setQid(q.getId());
                                pt.setQcontent(q.getContent());
                                service.addPreviewTemplate(pt);
                            }
                            i++;
                        }
                        TableModel tableModel = new DefaultTableModel(pre.quedata, pre.cols);
                        pre.previewTest.setModel(tableModel);
                        Tools.setbox(pre.previewTest);
                        pre.previewTest.repaint();
                        dispose();
                    }
                });
            }
            {
                jButton4 = new JButton();
                getContentPane().add(jButton4);
                jButton4.setText("返回");
                jButton4.setBounds(300, 436, 92, 27);
                jButton4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
            }
            pack();
            this.setSize(600, 551);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
