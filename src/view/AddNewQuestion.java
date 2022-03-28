package view;

import po.Question;
import service.QuestionService;
import serviceimpl.QuestionServiceimpl;
import utils.Tools;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


/*
 * 添加问题的界面
 *
 * */
public class AddNewQuestion extends javax.swing.JFrame {
    private JLabel jLabel1;
    private JTextField jTextField1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JButton jButton1;
    private JTextField jTextField6;
    private JLabel jLabel6;
    private JTextField jTextField5;
    private JTextField jTextField4;
    private JTextField jTextField3;
    private JTextField jTextField2;
    private Questions que;

    /**
     * Auto-generated main method to display this JFrame
     */ {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AddNewQuestion inst = new AddNewQuestion();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public AddNewQuestion() {
        super();
        initGUI();
    }

    public AddNewQuestion(Questions que) {
        super();
        this.que = que;
        initGUI();
    }

    private void initGUI() {
        try {
            {
                this.setSize(344, 455);
            }

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);

            {
                jLabel1 = new JLabel();
                getContentPane().add(jLabel1);
                jLabel1.setText("ID");
                jLabel1.setBounds(40, 26, 20, 20);
            }
            {
                jTextField1 = new JTextField();
                getContentPane().add(jTextField1);
                jTextField1.setBounds(90, 23, 40, 27);
            }
            {
                jLabel2 = new JLabel();
                getContentPane().add(jLabel2);
                jLabel2.setText("\u9898\u76ee");
                jLabel2.setBounds(140, 26, 50, 20);
            }
            {
                jTextField2 = new JTextField();
                getContentPane().add(jTextField2);
                jTextField2.setBounds(200, 23, 120, 27);
            }
            {
                jLabel3 = new JLabel();
                getContentPane().add(jLabel3);
                jLabel3.setText("\u7b54\u6848\u4e00");
                jLabel3.setBounds(20, 73, 60, 20);
            }
            {
                jLabel4 = new JLabel();
                getContentPane().add(jLabel4);
                jLabel4.setText("\u7b54\u6848\u4e8c");
                jLabel4.setBounds(20, 112, 60, 20);
            }
            {
                jLabel5 = new JLabel();
                getContentPane().add(jLabel5);
                jLabel5.setText("\u7b54\u6848\u4e09");
                jLabel5.setBounds(20, 156, 60, 20);
            }
            {
                jTextField3 = new JTextField();
                getContentPane().add(jTextField3);
                jTextField3.setBounds(90, 70, 193, 27);
            }
            {
                jTextField4 = new JTextField();
                getContentPane().add(jTextField4);
                jTextField4.setBounds(90, 109, 193, 27);
            }
            {
                jTextField5 = new JTextField();
                getContentPane().add(jTextField5);
                jTextField5.setBounds(90, 153, 193, 27);
            }
            /*类型*/
            {
                jLabel6 = new JLabel();
                getContentPane().add(jLabel6);
                jLabel6.setText("\u7c7b\u578b");
                jLabel6.setBounds(20, 202, 60, 20);
            }
            {
                jTextField6 = new JTextField();
                getContentPane().add(jTextField6);
                jTextField6.setBounds(90, 199, 82, 27);
            }
            //将新添加的问题信息写入文件中，并更新表格中的数据
            {
                jButton1 = new JButton();
                getContentPane().add(jButton1);
                jButton1.setText("\u65b0\u589e");
                jButton1.setBounds(215, 199, 70, 27);
                jButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        QuestionService service = new QuestionServiceimpl();
                        Question q = new Question();
                        q.setId(Integer.parseInt(jTextField1.getText()));
                        q.setContent(jTextField2.getText());
                        q.setAnswer1(jTextField3.getText());
                        q.setAnswer2(jTextField4.getText());
                        q.setAnswer3(jTextField5.getText());
                        q.setType(jTextField6.getText());
                        service.addQuestion(q);

                        Vector<Object> v = new Vector<Object>();
                        v.add(new Boolean(false));
                        v.add(Integer.parseInt(jTextField1.getText()));
                        v.add(jTextField2.getText());
                        v.add(jTextField6.getText());
                        que.datas.add(v);
                        TableModel tableModel = new DefaultTableModel(que.datas, que.cols);
                        que.jTable1.setModel(tableModel);
                        Tools.setbox(que.jTable1);
                        que.jTable1.repaint();
                        dispose();
                    }
                });

            }
            pack();
            setSize(400, 300);
        } catch (Exception e) {
            //add your error handling code here
            e.printStackTrace();
        }
    }

}
