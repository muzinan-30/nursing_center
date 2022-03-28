package view;

import service.StaffService;
import serviceimpl.StaffServiceimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
/*
*
*
* 用户登陆界面
*
*
* */
public class LoginTest extends javax.swing.JFrame {

    private StaffService stservice;
    private JPanel bgPanel1;
    private JLabel jLabel2;
    private JButton jButton1;
    private JLabel title;
    private JButton jButton2;
    private JTextField password;
    private JTextField jTextField1;
    private JLabel jLabel1;

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginTest inst = new LoginTest();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public LoginTest() {
        super();
        initGUI();
    }

    private void initGUI() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        try {

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            {
                bgPanel1 = new JPanel() {
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon = new ImageIcon(getClass().getResource("/images/login.jpg"));
                        Image img = icon.getImage();
                        g.drawImage(img, 0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());
                        bgPanel1.setSize(icon.getIconWidth(), icon.getIconHeight());
                    }
                };

                getContentPane().add(bgPanel1);
                bgPanel1.setBounds(0, 0, 512, 454);
            }
            {
                jLabel1 = new JLabel();
                getContentPane().add(jLabel1);
                jLabel1.setText("用户名");
                jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 20));
                jLabel1.setBounds(530, 123, 81, 24);
            }
            {
                jTextField1 = new JTextField();
                getContentPane().add(jTextField1);
                jTextField1.setBounds(598, 120, 151, 31);
            }
            {
                password = new JPasswordField();
                getContentPane().add(password);
                password.setBounds(598, 180, 151, 31);
            }
            {
                jLabel2 = new JLabel();
                getContentPane().add(jLabel2);
                jLabel2.setText("密码");
                jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 20));
                jLabel2.setBounds(550, 183, 86, 24);
            }
            {
                jButton1 = new JButton();
                getContentPane().add(jButton1);
                jButton1.setText("login");
                jButton1.setBounds(598, 229, 100, 31);
                jButton1.setFont(new java.awt.Font("Segoe Print", 0, 20));
                jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        jBtnLoginActionPerformed(evt);
                    }
                });
            }
            {
                jButton2 = new JButton();
                getContentPane().add(jButton2);
                jButton2.setText("exit");
                jButton2.setBounds(598, 278, 100, 31);
                jButton2.setFont(new java.awt.Font("Segoe Print", 0, 18));
                jButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (JOptionPane.showConfirmDialog(null, "确定关闭吗？", "确认", JOptionPane.OK_CANCEL_OPTION) == 0)
                            System.exit(0);
                    }
                });
            }
            {
                title = new JLabel();
                getContentPane().add(title);
                title.setText("   颐养中心");
                title.setBounds(512, 48, 229, 43);
                title.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 40));
            }
            this.setTitle("登录");
            pack();
            this.setSize(775, 510);
        } catch (Exception e) {
            //add your error handling code here
            e.printStackTrace();
        }
    }

    private void jBtnLoginActionPerformed(ActionEvent evt) {
            String name = jTextField1.getText().trim();
            String password1 = password.getText().trim();
        stservice = new StaffServiceimpl();
        int flag = stservice.login(name, password1);
        //调用服务层的login方法，验证身份
        System.out.println(flag);//测试
        if (flag == 1) {
            System.out.println("登录成功！");
            Patients test = new Patients(name);
            test.setLocationRelativeTo(null);//居中
            test.setVisible(true);//可见
            dispose();
        }
        if (flag == 2) {
            Staffs stas = new Staffs();
            stas.setLocationRelativeTo(null);
            stas.setVisible(true);
            dispose();
        }
        if (flag != 1 && flag != 2) {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "账号不能为空", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "请输入正确的账号", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }


    }

}



