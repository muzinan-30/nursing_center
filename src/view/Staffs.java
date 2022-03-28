package view;

import po.Staff;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import service.StaffService;
import serviceimpl.StaffServiceimpl;
import utils.Tools;
/*
*
* 员工管理界面
*
* */
public class Staffs extends javax.swing.JFrame {

    {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel jPanel1;
    private JButton jButton1;
    private JButton exit;
    private JButton save;
    JTable jTable1;
    private JScrollPane staffs;
    Vector title = new Vector();
    //Vector<Vector<Object>> datas = new Vector<>();
    Vector<Vector<Object>> data=new Vector<>();
    private Object[] titles;
    private JComboBox jComboBox1;
    private JButton jButton2;
    private JTextField jTextField1;
    private JButton jButton3;
    Staffs sts;
    private List<Staff> stafflist = new ArrayList<>();
    private List<Staff> list;
    private DefaultTableModel tableModel=null;


    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Staffs inst = new Staffs();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public Staffs() {
        super();
        initGUI();
    }

    private void initGUI() {
        titles = new Object[]{" ", "用户名", "姓名", "年龄", "专长", "职称", "联系电话"};
        for (int i = 0; i < titles.length; i++) {
            title.add(titles[i]);
        }
        StaffService stservice = new StaffServiceimpl();
        list = stservice.getAllstaff();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Vector vector = new Vector();
                vector.add(new Boolean(false));
                vector.add(list.get(i).getAccount());
                vector.add(list.get(i).getName());
                vector.add(list.get(i).getAge());
                vector.add(list.get(i).getExpertise());
                vector.add(list.get(i).getJobTitle());
                vector.add(list.get(i).getPhoneNumber());
                data.add(vector);
            }
        }
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jPanel1 = new JPanel();
            getContentPane().add(jPanel1, BorderLayout.CENTER);
            jPanel1.setPreferredSize(new java.awt.Dimension(874, 404));
            jPanel1.setSize(800, 400);
            jPanel1.setLayout(null);
            {
                jTextField1 = new JTextField();
                jPanel1.add(jTextField1);
                jTextField1.setText("");
                jTextField1.setBounds(49, 30, 147, 31);
            }
            {
                jButton1 = new JButton();//姓名查询
                jPanel1.add(jButton1);
                jButton1.setText("\u59d3\u540d\u67e5\u8be2");
                jButton1.setBounds(202, 30, 110, 31);
                jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String name = jTextField1.getText();
                        StaffService stservice = new StaffServiceimpl();
                        Staff s = stservice.getByName(name);
                        if (s != null) {
                            data.clear();
                            Vector<Object> vector2 = new Vector();
                            vector2.add(new Boolean(false));
                            vector2.add(s.getAccount());
                            vector2.add(s.getName());
                            vector2.add(s.getAge());
                            vector2.add(s.getExpertise());
                            vector2.add(s.getJobTitle());
                            vector2.add(s.getPhoneNumber());
                            data.add(vector2);
                            TableModel se = new DefaultTableModel(data, title);
                            jTable1.setModel(se);
                            Tools.setbox(jTable1);
                            jTable1.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "此人不存在");
                        }
                    }

                });
            }
            //添加人员
            {
                jButton2 = new JButton();
                jPanel1.add(jButton2);//添加人员
                jButton2.setText("\u6dfb\u52a0\u5de5\u4f5c\u4eba\u5458");
                jButton2.setBounds(598, 89, 149, 31);
                sts = this;
                jButton2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        AddNewStaff add = new AddNewStaff(sts);
                        add.setVisible(true);
                        add.setLocationRelativeTo(null);
                    }
                });
            }
            //查找按职称
            {
                jComboBox1 = new JComboBox();
                jComboBox1.addItem("所有");
                jComboBox1.addItem("医生");
                jComboBox1.addItem("护士");
                jComboBox1.addItem("护工");
                jPanel1.add(jComboBox1);
                /* jComboBox1.setModel(jComboBox1Model);*/
                jComboBox1.setBounds(49, 83, 147, 30);
                jComboBox1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        data.clear();
                        String zhicheng = jComboBox1.getSelectedItem().toString();
                        StaffService stservice = new StaffServiceimpl();
                        if(zhicheng.equals("所有")) {
                            List<Staff> staff=stservice.getAllstaff();
                            if (staff != null) {
                                data.clear();
                                for (int i = 0; i < staff.size(); i++) {
                                    Vector vector = new Vector();
                                    vector.add(new Boolean(false));
                                    vector.add(staff.get(i).getAccount());
                                    vector.add(staff.get(i).getName());
                                    vector.add(staff.get(i).getAge());
                                    vector.add(staff.get(i).getExpertise());
                                    vector.add(staff.get(i).getJobTitle());
                                    vector.add(staff.get(i).getPhoneNumber());
                                    data.add(vector);
                                }
                            }
                            TableModel se1 = new DefaultTableModel(data, title);
                            jTable1.setModel(se1);
                            Tools.setbox(jTable1);
                            jTable1.repaint();
                        }
                        else {
                            stafflist = stservice.getAllstaffByJobTitle(zhicheng);
                            data.clear();
                            for (int i = 0; i < stafflist.size(); i++) {
                                Vector vector = new Vector();
                                vector.add(new Boolean(false));
                                vector.add(stafflist.get(i).getAccount());
                                vector.add(stafflist.get(i).getName());
                                vector.add(stafflist.get(i).getAge());
                                vector.add(stafflist.get(i).getExpertise());
                                vector.add(stafflist.get(i).getJobTitle());
                                vector.add(stafflist.get(i).getPhoneNumber());
                                data.add(vector);
                            }
                            TableModel se1 = new DefaultTableModel(data, title);
                            jTable1.setModel(se1);
                            Tools.setbox(jTable1);
                            jTable1.repaint();
                        }
                    }
                });


            }
            //删除
            {
                jButton3 = new JButton();
                jPanel1.add(jButton3);
                jButton3.setText("\u5220\u9664");
                jButton3.setBounds(205, 83, 88, 30);
                jButton3.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        StaffService service=new StaffServiceimpl();
                        for(int i=0;i<jTable1.getRowCount();){
                            if((Boolean)jTable1.getValueAt(i, 0)){
                                Staff s=service.getByName((String)jTable1.getValueAt(i,2));
                                service.deletestaff(s);
                                System.out.println(jTable1.getRowCount());
                                tableModel.removeRow(i);
                                i=0;
                                continue;
                            }
                            i++;
                        }
                        tableModel.fireTableDataChanged();
                        jTable1.repaint();
                    }
                });
            }
            {
                staffs = new JScrollPane();
                jPanel1.add(staffs);
                staffs.setBounds(46, 141, 701, 202);
                {   tableModel=new DefaultTableModel(data,title);
                    jTable1 = new JTable();
                    jTable1.setModel(tableModel);
                    Tools.setbox(jTable1);
                    staffs.setViewportView(jTable1);
                    jTable1.setRowHeight(30);
                    jTable1.setPreferredSize(new java.awt.Dimension(698, 172));
                }
            }
            {
                save = new JButton();
                jPanel1.add(save);
                save.setText("\u4fdd\u5b58");
                save.setBounds(122, 355, 74, 31);
                save.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        JOptionPane.showMessageDialog(null, "保存成功");
                    }
                });
            }
            {
                exit = new JButton();//退出
                jPanel1.add(exit);
                exit.setText("\u8fd4\u56de");
                exit.setBounds(537, 355, 78, 31);
                exit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (JOptionPane.showConfirmDialog(null, "确定关闭吗？", "确认", JOptionPane.OK_CANCEL_OPTION) == 0)
                        {   dispose();
                            LoginTest logt = new LoginTest();
                            logt.setLocationRelativeTo(null);
                            logt.setVisible(true);
                        }
                    }
                });
            }
            pack();
            this.setSize(821, 460);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
