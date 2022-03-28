package view;

import po.Staff;
import service.StaffService;
import serviceimpl.StaffServiceimpl;
import utils.Tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class AddNewStaff extends javax.swing.JFrame {

    {
        //Set Look & Feel
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JLabel jLabel1;
    private JTextField jTextField1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JButton jButton1;
    private JTextField jTextField6;
    private JTextField jTextField5;
    private JTextField jTextField4;
    private JTextField jTextField3;
    private JTextField jTextField2;
    private JTextField jTextField7;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private Staffs sts;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AddNewStaff inst = new AddNewStaff();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public AddNewStaff() {
        super();
        initGUI();
    }
   public AddNewStaff(Staffs sts) {
        super();
        this.sts=sts;
        initGUI();
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            {
                jLabel1 = new JLabel();
                getContentPane().add(jLabel1);
                jLabel1.setText("\u7528\u6237\u540d:");
                jLabel1.setBounds(20, 25, 72, 25);
            }
            {
                jTextField1 = new JTextField();
                getContentPane().add(jTextField1);
                jTextField1.setBounds(103, 25, 120, 27);
            }
            {    jLabel7 = new JLabel();
                getContentPane().add(jLabel7);
                jLabel7.setText("密码:");
                jLabel7.setBounds(42, 55, 72, 25);

            }
            {   jTextField7 = new JTextField();
                getContentPane().add(jTextField7);
                jTextField7.setBounds(103, 55, 120, 27);

            }
            {
                jLabel2 = new JLabel();
                getContentPane().add(jLabel2);
                jLabel2.setText("\u59d3\u540d:");
                jLabel2.setBounds(42, 85, 45, 23);
            }
            {
                jLabel3 = new JLabel();
                getContentPane().add(jLabel3);
                jLabel3.setText("年龄:");
                jLabel3.setBounds(42, 115, 45, 21);
            }
            {
                jLabel4 = new JLabel();
                getContentPane().add(jLabel4);
                jLabel4.setText("\u4e13\u957f:");
                jLabel4.setBounds(37, 150, 46, 17);
            }
            {
                jLabel5 = new JLabel();
                getContentPane().add(jLabel5);
                jLabel5.setText("\u804c\u79f0:");
                jLabel5.setBounds(40, 175, 54, 28);
            }
            {
                jLabel6 = new JLabel();
                getContentPane().add(jLabel6);
                jLabel6.setText("\u8054\u7cfb\u7535\u8bdd:");
                jLabel6.setBounds(5, 205, 95, 20);
            }
            {
                jTextField2 = new JTextField();
                getContentPane().add(jTextField2);
                jTextField2.setBounds(103, 85, 120, 27);
            }
            {
                jTextField3 = new JTextField();
                getContentPane().add(jTextField3);
                jTextField3.setBounds(103, 115, 119, 27);
            }
            {
                jTextField4 = new JTextField();
                getContentPane().add(jTextField4);
                jTextField4.setBounds(103, 145, 119, 29);
            }
            {
                jTextField5 = new JTextField();
                getContentPane().add(jTextField5);
                jTextField5.setBounds(103, 175, 119, 28);
            }
            {
                jTextField6 = new JTextField();
                getContentPane().add(jTextField6);
                jTextField6.setBounds(103, 205, 119, 25);
            }
            //将新添加的员工信息写入文件中，并更新表格中的数据
            {
                jButton1 = new JButton();
                getContentPane().add(jButton1);
                jButton1.setText("\u6ce8\u518c");
                jButton1.setBounds(245, 205, 80, 27);
               jButton1.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       if(jTextField1.getText().equals("")){
                           JOptionPane.showMessageDialog(null, "账号不能为空", "Error", JOptionPane.ERROR_MESSAGE);
                       }
                       else {
                           StaffService service = new StaffServiceimpl();
                           Staff s = new Staff();
                           s.setAccount(jTextField1.getText());
                           s.setName(jTextField2.getText());
                           s.setAge(Integer.parseInt(jTextField3.getText()));
                           s.setExpertise(jTextField4.getText());
                           s.setJobTitle(jTextField5.getText());
                           s.setPhoneNumber(Integer.parseInt(jTextField6.getText()));
                           s.setPassword(jTextField7.getText());
                           service.addstaff(s);

                           Vector<Object> v = new Vector<Object>();
                           v.add(new Boolean(false));
                           v.add(jTextField1.getText());
                           v.add(jTextField2.getText());
                           v.add(jTextField3.getText());
                           v.add(jTextField4.getText());
                           v.add(jTextField5.getText());
                           v.add(jTextField6.getText());
                           sts.data.add(v);
                           Tools.setbox(sts.jTable1);
                           sts.jTable1.updateUI();
                           dispose();
                       }
                   }
               });
            }
            pack();
            setSize(400, 350);
        } catch (Exception e) {
            //add your error handling code here
            e.printStackTrace();
        }
    }
}


