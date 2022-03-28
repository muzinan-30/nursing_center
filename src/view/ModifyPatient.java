package view;

import po.Patient;
import service.PatientService;
import serviceimpl.PatientServiceimpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
* 病患信息的修改
*
* */
public class ModifyPatient extends javax.swing.JFrame{
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
    Patient p;
    Patients pat;
    private String name;

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

    public ModifyPatient() {
        super();
        initGUI();
    }

    public ModifyPatient(Patient p,Patients pat,String name) {
        super();
        this.p = p;
        this.pat=pat;
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
                jTextField1.setText(p.getName());
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
                jTextField2.setText(String.valueOf(p.getAge()));
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
                jTextField3.setText(p.getGender());
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
                jTextField4.setText(p.getIdNumber());
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
                jTextField5.setText(String.valueOf(p.getPhoneNumber()));
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
                jTextField6.setText(p.getEmergencyContact());
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
                jTextField7.setText(String.valueOf(p.getEcPhone()));
                jTextField7.setBounds(154, 175, 155, 28);
            }
            {
                jButton1 = new JButton();
                getContentPane().add(jButton1);
                jButton1.setText("保存");
                jButton1.setBounds(336, 175, 109, 27);
                jButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PatientService service = new PatientServiceimpl();
                        Patient p = new Patient();
                        p.setName(jTextField1.getText());
                        p.setAge(Integer.parseInt(jTextField2.getText()));
                        p.setGender(jTextField3.getText());
                        p.setIdNumber(jTextField4.getText());
                        p.setPhoneNumber(Integer.parseInt(jTextField5.getText()));
                        p.setEmergencyContact(jTextField6.getText());
                        p.setEcPhone(Integer.parseInt(jTextField7.getText()));
                        service.modifypatient(p);
                        Patients patients=new Patients(name);
                        patients.setVisible(true);
                        patients.setLocationRelativeTo(null);
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

