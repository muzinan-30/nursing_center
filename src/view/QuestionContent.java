package view;

import po.Question;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
/*
* 问题详情界面
*
* */
public class QuestionContent extends javax.swing.JFrame {

    {
        //Set Look & Feel
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel jPanel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel10;
    private JLabel jLabel9;
    private JLabel jLabel8;
    private JLabel jLabel7;
    private JLabel jLabel6;
    private JLabel cc;
    private JLabel jLabel2;
    private JLabel IDc;
    private JLabel jLabel1;
    private Question q;

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                QuestionContent inst = new QuestionContent();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public QuestionContent() {
        super();
        initGUI();
    }

    public QuestionContent(Question q) {
        super();
        this.q = q;
        initGUI();
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            {
                jPanel1 = new JPanel();
                getContentPane().add(jPanel1);
                jPanel1.setBounds(0, 0, 399, 342);
                jPanel1.setLayout(null);
                {
                    jLabel1 = new JLabel();
                    jPanel1.add(jLabel1);
                    jLabel1.setText("ID");
                    jLabel1.setBounds(44, 35, 71, 24);
                }
                {
                    IDc = new JLabel();
                    jPanel1.add(IDc);
                    IDc.setBounds(124, 35, 170, 25);
                    IDc.setText(String.valueOf(q.getId()));
                }
                {
                    jLabel2 = new JLabel();
                    jPanel1.add(jLabel2);
                    jLabel2.setText("content");
                    jLabel2.setBounds(44, 85, 77, 21);
                }
                {
                    cc = new JLabel();
                    jPanel1.add(cc);
                    cc.setBounds(130, 80, 216, 26);
                    cc.setText(q.getContent());
                }
                {
                    jLabel3 = new JLabel();
                    jPanel1.add(jLabel3);
                    jLabel3.setText("type");
                    jLabel3.setBounds(44, 133, 77, 21);
                }
                {
                    jLabel4 = new JLabel();
                    jPanel1.add(jLabel4);
                    jLabel4.setBounds(130, 130, 162, 24);
                    jLabel4.setText(q.getType());
                }
                {
                    jLabel5 = new JLabel();
                    jPanel1.add(jLabel5);
                    jLabel5.setText("answer1");
                    jLabel5.setBounds(44, 182, 80, 21);
                }
                {
                    jLabel6 = new JLabel();
                    jPanel1.add(jLabel6);
                    jLabel6.setText(q.getAnswer1());
                    jLabel6.setBounds(124, 178, 168, 25);
                }
                {
                    jLabel7 = new JLabel();
                    jPanel1.add(jLabel7);
                    jLabel7.setText("answer2");
                    jLabel7.setBounds(44, 228, 63, 21);
                }
                {
                    jLabel8 = new JLabel();
                    jPanel1.add(jLabel8);
                    jLabel8.setText(q.getAnswer2());
                    jLabel8.setBounds(124, 225, 170, 24);
                }
                {
                    jLabel9 = new JLabel();
                    jPanel1.add(jLabel9);
                    jLabel9.setText("answer3");
                    jLabel9.setBounds(44, 272, 63, 21);
                }
                {
                    jLabel10 = new JLabel();
                    jPanel1.add(jLabel10);
                    jLabel10.setText(q.getAnswer3());
                    jLabel10.setBounds(130, 268, 164, 25);
                }
            }
            pack();
            this.setSize(421, 398);
        } catch (Exception e) {
            //add your error handling code here
            e.printStackTrace();
        }
    }

}