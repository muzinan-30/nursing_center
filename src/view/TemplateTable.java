package view;

import po.Template;
import service.TemplateService;
import serviceimpl.TemplateServiceimpl;
import utils.Tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/*
* 模板管理界面
*
*
* */
public class TemplateTable extends javax.swing.JFrame {

    {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel jPanel1;
    private JButton jButton1;
    JTable jTable1;
    private JButton jButton5;
    private JButton jButton4;
    private JScrollPane templates;
    private JButton jButton3;
    private JButton jButton2;
    private JComboBox leixing;
    Vector<Vector> datas = new Vector<>();
    Vector<Object> cols = new Vector<>();
    List<Template> list = new ArrayList<>();
    TemplateTable tt;
    Template template;
    private DefaultTableModel tableModel = null;
    private List<Template> Templatelist = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TemplateTable inst = new TemplateTable();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public TemplateTable() {
        super();
        initGUI();
    }

    private void initGUI() {
        Object titles[] = {"", "ID", "名称", "类型"};
        for (int i = 0; i < titles.length; i++) {
            cols.add(titles[i]);
        }
        TemplateService stservice = new TemplateServiceimpl();
        list = stservice.getAllTemplate();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Vector vector = new Vector();
                vector.add(new Boolean(false));
                vector.add(list.get(i).getId());
                vector.add(list.get(i).getTname());
                vector.add(list.get(i).getType());
                datas.add(vector);
            }
        }
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                jPanel1 = new JPanel();
                getContentPane().add(jPanel1, BorderLayout.CENTER);
                jPanel1.setPreferredSize(new java.awt.Dimension(783, 444));
                jPanel1.setLayout(null);
                {
                    ComboBoxModel leixingModel =
                            new DefaultComboBoxModel(
                                    new String[]{"所有", "A类型", "B类型", "C类型", "D类型"});
                    leixing = new JComboBox();
                    jPanel1.add(leixing);
                    leixing.setModel(leixingModel);
                    leixing.setBounds(38, 33, 105, 28);
                    leixing.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            datas.clear();
                            TemplateService stservice = new TemplateServiceimpl();
                            String type = leixing.getSelectedItem().toString();
                            if (type.equals("所有")) {
                                List<Template> list = stservice.getAllTemplate();
                                if (list != null) {
                                    for (int i = 0; i < list.size(); i++) {
                                        Vector vector = new Vector();
                                        vector.add(new Boolean(false));
                                        vector.add(list.get(i).getId());
                                        vector.add(list.get(i).getTname());
                                        vector.add(list.get(i).getType());
                                        datas.add(vector);
                                    }
                                }
                                TableModel se = new DefaultTableModel(datas, cols);
                                jTable1.setModel(se);
                                Tools.setbox(jTable1);
                                jTable1.repaint();
                            } else {
                                Templatelist = stservice.getAlltemplateByType(type);
                                for (int i = 0; i < Templatelist.size(); i++) {
                                    Vector vector = new Vector();
                                    vector.add(new Boolean(false));
                                    vector.add(Templatelist.get(i).getId());
                                    vector.add(Templatelist.get(i).getTname());
                                    vector.add(Templatelist.get(i).getType());
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
                {
                    jButton1 = new JButton();
                    jPanel1.add(jButton1);
                    jButton1.setText("预览");
                    jButton1.setBounds(35, 86, 105, 31);
                    jButton1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            int index = jTable1.getSelectedRow();
                            int id = (int) datas.get(index).get(1);
                            TemplateService service = new TemplateServiceimpl();
                            template = service.getTemplateById(id);
                            PreviewJFrame previewJFrame = new PreviewJFrame(template);
                            previewJFrame.setVisible(true);
                            previewJFrame.setLocationRelativeTo(null);
                        }
                    });
                }
                //删除
                {
                    jButton2 = new JButton();
                    jPanel1.add(jButton2);
                    jButton2.setText("删除");
                    jButton2.setBounds(158, 86, 114, 31);
                    jButton2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            TemplateService service = new TemplateServiceimpl();
                            for (int i = 0; i < jTable1.getRowCount(); ) {
                                if ((Boolean) jTable1.getValueAt(i, 0)) {
                                    Template t = service.getTemplateById((int) jTable1.getValueAt(i, 1));
                                    service.deleteTemplate(t);
                                    System.out.println(jTable1.getRowCount());
                                    tableModel.removeRow(i);
                                    i = 0;
                                    continue;
                                }
                                i++;
                            }
                            tableModel.fireTableDataChanged();
                            jTable1.repaint();
                        }
                    });
                }
                //add
                {
                    tt = this;
                    jButton3 = new JButton();
                    jPanel1.add(jButton3);
                    jButton3.setText("添加");
                    jButton3.setBounds(562, 86, 161, 31);
                    jButton3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            AddNewTemplate addNewTemplate = new AddNewTemplate(tt);
                            addNewTemplate.setVisible(true);
                            addNewTemplate.setLocationRelativeTo(null);
                        }
                    });
                }
                {
                    templates = new JScrollPane();
                    jPanel1.add(templates);
                    templates.setBounds(35, 146, 688, 245);
                    {
                        tableModel = new DefaultTableModel(datas, cols);
                        jTable1 = new JTable();
                        jTable1.setModel(tableModel);
                        templates.setViewportView(jTable1);
                        Tools.setbox(jTable1);
                        jTable1.setRowHeight(30);
                    }
                }
                {
                    jButton4 = new JButton();
                    jPanel1.add(jButton4);
                    jButton4.setText("保存");
                    jButton4.setBounds(109, 402, 101, 31);
                    jButton4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            JOptionPane.showMessageDialog(null, "保存成功");
                        }
                    });
                }
                {
                    jButton5 = new JButton();
                    jPanel1.add(jButton5);
                    jButton5.setText("退出");
                    jButton5.setBounds(520, 402, 97, 31);
                    jButton5.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            dispose();
                        }
                    });
                }
            }
            pack();
            this.setSize(803, 500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

