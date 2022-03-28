package view;

import po.Patient;
import service.PatientService;
import serviceimpl.PatientServiceimpl;

import javax.swing.*;

import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewPatient extends javax.swing.JFrame {
    {
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
    private JTextField jTextField7;
    private JLabel jLabel7;
    private JTextField jTextField6;
    private JLabel jLabel6;
    private JTextField jTextField5;
    private JTextField jTextField4;
    private JTextField jTextField3;
    private JTextField jTextField2;
    Patients pat;
    private  String name;

    //StafFrame j;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AddNewPatient inst = new AddNewPatient();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public AddNewPatient() {
        super();
        initGUI();
    }

    public AddNewPatient(Patients pat,String name) {
        super();
        this.pat = pat;
        this.name=name;
        initGUI();
    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            {
                jLabel1 = new JLabel();
                getContentPane().add(jLabel1);
                jLabel1.setText("\u59d3\u540d");
                jLabel1.setBounds(40, 33, 80, 20);
            }
            {
                jTextField1 = new JTextField();
                getContentPane().add(jTextField1);
                jTextField1.setBounds(88, 30, 122, 27);
            }
            {
                jLabel2 = new JLabel();
                getContentPane().add(jLabel2);
                jLabel2.setText("年龄");
                jLabel2.setBounds(242, 33, 80, 20);
            }
            {
                jTextField2 = new JTextField();
                getContentPane().add(jTextField2);
                jTextField2.setBounds(320, 30, 125, 27);
            }
            {
                jLabel3 = new JLabel();
                getContentPane().add(jLabel3);
                jLabel3.setText("\u6027\u522b");
                jLabel3.setBounds(40, 81, 80, 20);
            }
            {
                jTextField3 = new JTextField();
                getContentPane().add(jTextField3);
                jTextField3.setBounds(88, 78, 122, 27);
            }

            {
                jLabel4 = new JLabel();
                getContentPane().add(jLabel4);
                jLabel4.setText("\u8eab\u4efd\u8bc1\u53f7");
                jLabel4.setBounds(242, 81, 80, 20);
            }
            {
                jTextField4 = new JTextField();
                getContentPane().add(jTextField4);
                jTextField4.setBounds(320, 81, 125, 27);
            }
            {
                jLabel5 = new JLabel();
                getContentPane().add(jLabel5);
                jLabel5.setText("\u8054\u7cfb\u7535\u8bdd");
                jLabel5.setBounds(10, 130, 80, 20);
            }
            {
                jTextField5 = new JTextField();
                getContentPane().add(jTextField5);
                jTextField5.setBounds(88, 127, 122, 27);
            }
            {
                jLabel6 = new JLabel();
                getContentPane().add(jLabel6);
                jLabel6.setText("\u7d27\u6025\u8054\u7cfb\u4eba");
                jLabel6.setBounds(234, 134, 100, 20);
            }
            {
                jTextField6 = new JTextField();
                getContentPane().add(jTextField6);
                jTextField6.setBounds(321, 131, 124, 27);
            }
            {
                jLabel7 = new JLabel();
                getContentPane().add(jLabel7);
                jLabel7.setText("\u7d27\u6025\u8054\u7cfb\u4eba\u7535\u8bdd");
                jLabel7.setBounds(10, 178, 130, 20);
            }
            {
                jTextField7 = new JTextField();
                getContentPane().add(jTextField7);
                jTextField7.setBounds(154, 175, 155, 28);
            }
            //将新添加的病人信息写入文件中，并更新表格中的数据
            {
                jButton1 = new JButton();
                getContentPane().add(jButton1);
                jButton1.setText("\u6ce8\u518c");
                jButton1.setBounds(336, 175, 109, 27);
                JButton x = new JButton("评估");
                ActionListener a2 = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Evaluation evaluation = new Evaluation();
                        evaluation.setVisible(true);
                        evaluation.setLocationRelativeTo(null);
                    }
                };
                x.addActionListener(a2);
                jButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PatientService staffDaoTest = new PatientServiceimpl();
                        Patient p = new Patient();
                        p.setName(jTextField1.getText());
                        p.setAge(Integer.parseInt(jTextField2.getText()));
                        p.setGender(jTextField3.getText());
                        p.setIdNumber(jTextField4.getText());
                        p.setPhoneNumber(Integer.parseInt(jTextField5.getText()));
                        p.setEmergencyContact(jTextField6.getText());
                        p.setEcPhone(Integer.parseInt(jTextField7.getText()));
                        staffDaoTest.addpatient(p);
                        Patients patients=new Patients(name);
                        patients.setLocationRelativeTo(null);
                        patients.setVisible(true);
                        dispose();
                    }
                });
            }
            pack();
            setSize(500, 300);
        } catch (Exception e) {
            //add your error handling code here
            e.printStackTrace();
        }
    }

}
//自定义BUtton
class MyButton1 extends JButton {

    private int row;

    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public MyButton1() {

    }

    public MyButton1(String name) {
        super(name);
    }

}
//重写编辑器方法

class MyButtonEditor1 extends DefaultCellEditor {

    private MyButton button;

    private MyEvent event;

    public MyButtonEditor1() {
        super(new JTextField());
        button = new MyButton("评估");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //这里调用自定义的事件处理方法
                event.invoke(e);
            }

        });

    }

    public MyButtonEditor1(MyEvent e) {
        this();
        this.event = e;
    }

    /*
    重写编辑器方法，返回一个按钮给JTable
    */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
//      setClickCountToStart(1);
//将这个被点击的按钮所在的行和列放进button里面
        button.setRow(row);
        button.setColumn(column);
        return button;
    }
}


interface MyEvent1 {
    public abstract void invoke(ActionEvent e);
}

class MyButtonRender1 implements TableCellRenderer {

    private JButton button;

    public MyButtonRender1() {
        button = new JButton("评估");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

}