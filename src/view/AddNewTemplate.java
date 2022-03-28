package view;

import po.Template;
import service.TemplateService;
import serviceimpl.TemplateServiceimpl;
import utils.Tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AddNewTemplate extends javax.swing.JFrame {
    private JPanel jPanel1;
    private JTextField nametxt;
    private JButton jButton1;
    private JTextField typetxt;
    private JLabel jLabel3;
    private JTextField Idtxt;
    private JLabel jLabel2;
    private JLabel jLabel1;
    private TemplateTable templateTable;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AddNewTemplate inst = new AddNewTemplate();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public AddNewTemplate() {
        super();
        initGUI();
    }

    public AddNewTemplate(TemplateTable tt) {
        super();
        this.templateTable = tt;
        initGUI();
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            {
                jPanel1 = new JPanel();
                getContentPane().add(jPanel1, "Center");
                jPanel1.setLayout(null);
                jPanel1.setBounds(0, 0, 391, 373);
                {
                    jLabel1 = new JLabel();
                    jPanel1.add(jLabel1);
                    jLabel1.setText("name:");
                    jLabel1.setBounds(46, 49, 57, 24);
                }
                {
                    nametxt = new JTextField();
                    jPanel1.add(nametxt);
                    nametxt.setBounds(115, 46, 157, 31);
                }
                {
                    jLabel2 = new JLabel();
                    jPanel1.add(jLabel2);
                    jLabel2.setText("ID:");
                    jLabel2.setBounds(74, 102, 34, 24);
                }
                {
                    Idtxt = new JTextField();
                    jPanel1.add(Idtxt);
                    Idtxt.setBounds(115, 99, 157, 31);
                }
                {
                    jLabel3 = new JLabel();
                    jPanel1.add(jLabel3);
                    jLabel3.setText("type:");
                    jLabel3.setBounds(57, 159, 55, 24);
                }
                {
                    typetxt = new JTextField();
                    jPanel1.add(typetxt);
                    typetxt.setBounds(115, 156, 157, 31);
                }
                //将新添加的模板信息写入文件中，并更新表格中的数据
                {
                    jButton1 = new JButton();
                    jPanel1.add(jButton1);
                    jButton1.setText("finish");
                    jButton1.setBounds(115, 220, 100, 31);
                    jButton1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            TemplateService service = new TemplateServiceimpl();
                            Template t = new Template();
                            t.setId(Integer.parseInt(Idtxt.getText()));
                            t.setType(typetxt.getText());
                            t.setTname(nametxt.getText());
                            service.addModel(t);

                            Vector<Object> v = new Vector<Object>();
                            v.add(new Boolean(false));
                            v.add(Integer.parseInt(Idtxt.getText()));
                            v.add(nametxt.getText());
                            v.add(typetxt.getText());
                            templateTable.datas.add(v);
                            TableModel tableModel = new DefaultTableModel(templateTable.datas, templateTable.cols);
                            templateTable.jTable1.setModel(tableModel);
                            Tools.setbox(templateTable.jTable1);
                            templateTable.jTable1.repaint();
                            dispose();
                        }
                    });
                }
            }
            pack();
            this.setSize(413, 355);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
