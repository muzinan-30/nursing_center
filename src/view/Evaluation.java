package view;
/*
 * 评估界面
 * */

import po.Record;
import po.Template;
import service.RecordService;
import service.TemplateService;
import serviceimpl.RecordServiceimpl;
import serviceimpl.TemplateServiceimpl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;
/*
*
* 评估界面
*
* */
public class Evaluation extends javax.swing.JFrame {

    {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel jPanel1;
    private JButton jButton1;
    private JTable record;
    private JButton jButton2;
    private JScrollPane recording;
    private JComboBox model;
    private Vector<String> vector = new Vector<>();
    private Vector<Vector> vectors = new Vector<>();
    private Template template1 = null;
    private String name;
    private String gender;
    private String ename;
    String template[];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Evaluation inst = new Evaluation();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public Evaluation() {
        super();
        initGUI();
    }

    public Evaluation(String name, String gender, String ename) {
        super();
        this.name = name;
        this.gender = gender;
        this.ename = ename;
        initGUI();
    }

    private void initGUI() {
        vector.add("姓名");
        vector.add("性别");
        vector.add("模板名称");
        vector.add("模板类型");
        vector.add("时间");
        vector.add("评估人");
        vector.add("建议");
        RecordService service = new RecordServiceimpl();
        vectors = service.getAllRecord();
        TemplateService service1=new TemplateServiceimpl();
        template=new String[service1.getAllTemplate().size()];
        for(int i=0;i<template.length;i++){
            template[i]=service1.getAllTemplate().get(i).getTname();
        }
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                //添加下拉选框
                jPanel1 = new JPanel();
                getContentPane().add(jPanel1, BorderLayout.CENTER);
                jPanel1.setLayout(null);
                {
                    ComboBoxModel modelModel =
                            new DefaultComboBoxModel(template);
                    model = new JComboBox();
                    jPanel1.add(model);
                    model.setModel(modelModel);
                    model.setBounds(591, 61, 191, 31);

                    model.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            TemplateService service = new TemplateServiceimpl();
                            List<Template> list = service.getAllTemplate();
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getTname().equals(model.getSelectedItem().toString())) {
                                    template1 = list.get(i);
                                    break;
                                }
                            }
                        }
                    });
                }
                //评估按钮
                {
                    jButton1 = new JButton();
                    jPanel1.add(jButton1);
                    jButton1.setText("评估");
                    jButton1.setBounds(30, 61, 137, 31);
                    jButton1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EvaluationView evaluationView =
                                    new EvaluationView(template1,name,gender,ename);
                            evaluationView.setVisible(true);
                            evaluationView.setLocationRelativeTo(null);
                            dispose();
                        }
                    });
                }
                //table
                {
                    recording = new JScrollPane();
                    jPanel1.add(recording);
                    recording.setBounds(30, 122, 752, 239);
                    {
                        TableModel recordModel =
                                new DefaultTableModel(vectors, vector);
                        record = new JTable();
                        recording.setViewportView(record);
                        record.setModel(recordModel);
                        record.setRowHeight(30);
                    }
                }
                {
                    jButton2 = new JButton();
                    jPanel1.add(jButton2);
                    jButton2.setText("退出");
                    jButton2.setBounds(672, 378, 110, 31);
                    jButton2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            dispose();
                        }
                    });
                }
            }
            pack();
            this.setSize(839, 498);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
