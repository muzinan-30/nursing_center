package view;

import po.PreviewTemplate;
import po.Question;
import po.Template;
import service.PreviewTemplateService;
import service.QuestionService;
import serviceimpl.PreviewTemplateServiceimpl;
import serviceimpl.QuestionServiceimpl;
import utils.Tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;
/*
* 预览界面
* */
public class PreviewJFrame extends javax.swing.JFrame {

    {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel preview;
    private JButton jButton1;
    JTable previewTest;
    private JButton jButton3;
    private JButton jButton2;
    private JScrollPane jScrollPane1;
    private JLabel jLabel1;
    Template template;
    PreviewJFrame thisPreviewJFrame;
    Vector<Vector> quedata = new Vector<>();
    Vector<Object> cols = new Vector<>();
    private DefaultTableModel tableModel = null;
    private JCheckBox jCheckBox;
    List<PreviewTemplate> previewTemplateList;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PreviewJFrame inst = new PreviewJFrame();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public PreviewJFrame() {
        super();
        initGUI();
    }

    public PreviewJFrame(Template t) {
        super();
        this.template = t;
        initGUI();
    }

    private void initGUI() {
        PreviewTemplateService service = new PreviewTemplateServiceimpl();
        template.getId();
        previewTemplateList = service.getAllQue();
        if (previewTemplateList != null) {
            for (int i = 0; i < previewTemplateList.size(); i++) {
                if (template.getId() == previewTemplateList.get(i).getId()) {
                    Vector<Object> vector = new Vector();
                    vector.add(new Boolean(false));
                    vector.add(previewTemplateList.get(i).getQid());
                    vector.add(previewTemplateList.get(i).getQcontent());
                    quedata.add(vector);
                }
            }
        }
        cols.add("");
        cols.add("ID");
        cols.add("question");
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                preview = new JPanel();
                getContentPane().add(preview, BorderLayout.CENTER);
                preview.setPreferredSize(new java.awt.Dimension(646, 421));
                preview.setLayout(null);
                {
                    jLabel1 = new JLabel();
                    preview.add(jLabel1);
                    jLabel1.setText(template.getTname());
                    jLabel1.setBounds(30, 35, 118, 30);
                }
                {
                    jButton1 = new JButton();
                    preview.add(jButton1);
                    jButton1.setText("添加");
                    thisPreviewJFrame = this;
                    jButton1.setBounds(528, 65, 80, 27);
                    jButton1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            Questions questions = new Questions(thisPreviewJFrame);
                            questions.setVisible(true);
                            questions.setLocationRelativeTo(null);
                        }
                    });
                }
                {
                    jScrollPane1 = new JScrollPane();
                    preview.add(jScrollPane1);
                    jScrollPane1.setBounds(30, 121, 581, 252);

                    {
                        tableModel = new DefaultTableModel(quedata, cols);
                        previewTest = new JTable();
                        jScrollPane1.setViewportView(previewTest);
                        previewTest.setModel(tableModel);
                        Tools.setbox(previewTest);
                        previewTest.setRowHeight(30);
                    }
                }
                {
                    jButton2 = new JButton();
                    preview.add(jButton2);
                    jButton2.setText("删除");
                    jButton2.setBounds(132, 380, 86, 31);
                    jButton2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            PreviewTemplateService service = new PreviewTemplateServiceimpl();
                            QuestionService service1=new QuestionServiceimpl();
                            for (int i = 0; i < previewTest.getRowCount(); ) {
                                if ((Boolean) previewTest.getValueAt(i, 0)) {
                                    Question q = service1.searchQuestionById((int) previewTest.getValueAt(i, 1));
                                    service.deletePreviewTemplate(template,q);
                                    tableModel.removeRow(i);
                                    i = 0;
                                    continue;
                                }
                                i++; // 不能放到for中，否则在全删时会有漏删
                            }
                            tableModel.fireTableDataChanged();
                            previewTest.repaint();
                        }
                    });
                }

                //退出按钮
                {
                    jButton3 = new JButton();
                    preview.add(jButton3);
                    jButton3.setText("Exit");
                    jButton3.setBounds(414, 380, 81, 31);
                    jButton3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            dispose();
                        }
                    });
                }
            }
            pack();
            this.setSize(669, 478);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
