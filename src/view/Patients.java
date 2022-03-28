package view;

import po.Patient;
import service.PatientService;
import serviceimpl.PatientServiceimpl;
import utils.Tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import javax.swing.table.*;

/*
病患管理：
 * */
public class Patients extends javax.swing.JFrame {
    String[] cols = {"", "姓名", "年龄", "性别", "身份证号", "联系电话", "紧急联系人", "联系人电话", "评估"};

    {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JPanel jPanel1;
    private JButton jButton1;
    private JButton jButton7;
    private JButton jButton6;
    JTable jTable1;
    private JButton jButton5;
    private JButton jButton4;
    private JButton jButton3;
    private JButton jButton2;
    private JTextField jTextField1;
    private JCheckBox jCheckBox;
    Vector<Object> colsV = new Vector<>();
    Vector<Vector> data = new Vector<>();
    private JScrollPane jScrollPane;
    Patients pat;
    private String staffname;
    DefaultTableModel tableModel = new DefaultTableModel(data, colsV);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Patients inst = new Patients();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public Patients() {
        super();
        initGUI();
    }

    public Patients(String name) {
        super();
        this.staffname = name;
        initGUI();
    }


    private void initGUI() {
        for (int i = 0; i < cols.length; i++) {
            colsV.add(cols[i]);
        }
        //访问服务层调数据
        PatientService stservice = new PatientServiceimpl();
        List<Patient> listpatient = stservice.getAllpatient();
        //存储病人数据
        for (int i = 0; i < listpatient.size(); i++) {
            Vector vector = new Vector<>();
            vector.add(new Boolean(false));
            vector.add(listpatient.get(i).getName());
            vector.add(listpatient.get(i).getAge());
            vector.add(listpatient.get(i).getGender());
            vector.add(listpatient.get(i).getIdNumber());
            vector.add(listpatient.get(i).getPhoneNumber());
            vector.add(listpatient.get(i).getEmergencyContact());
            vector.add(listpatient.get(i).getEcPhone());
            data.add(vector);
        }
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                jScrollPane = new JScrollPane();
                getContentPane().add(jScrollPane);
                jScrollPane.setBounds(38, 152, 1000, 250);
                jPanel1 = new JPanel();
                getContentPane().add(jPanel1, BorderLayout.CENTER);
                jPanel1.setLayout(null);
                jPanel1.setPreferredSize(new java.awt.Dimension(1200, 300));
                //search By name
                {
                    jTextField1 = new JTextField();
                    jPanel1.add(jTextField1);
                    jTextField1.setText("");//姓名查询
                    jTextField1.setBounds(38, 37, 118, 31);
                }
                //姓名查询
                {
                    jButton1 = new JButton();
                    jPanel1.add(jButton1);
                    jButton1.setText("\u59d3\u540d\u67e5\u8be2");
                    jButton1.setBounds(162, 37, 118, 31);
                    jButton1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            String name = jTextField1.getText();
                            PatientService stservice = new PatientServiceimpl();
                            Patient p = stservice.getByname(name);
                            if (p != null) {
                                data.clear();
                                Vector<Object> vector2 = new Vector();
                                vector2.add(new Boolean(false));
                                vector2.add(p.getName());
                                vector2.add(p.getAge());
                                vector2.add(p.getGender());
                                vector2.add(p.getIdNumber());
                                vector2.add(p.getPhoneNumber());
                                vector2.add(p.getEmergencyContact());
                                vector2.add(p.getEcPhone());
                                data.add(vector2);
                                TableModel se = new DefaultTableModel(data, colsV);
                                jTable1.setModel(se);
                                Tools.setbox(jTable1);
                                MyEvent e = new MyEvent() {
                                    @Override
                                    public void invoke(ActionEvent e) {
                                        MyButton button = (MyButton) e.getSource();
                                        //打印被点击的行和列
                                        System.out.println("row:" + button.getRow() + "column :" + button.getColumn());
                                        String name = jTable1.getValueAt(button.getRow(), 1).toString();
                                        String gender = jTable1.getValueAt(button.getRow(), 3).toString();
                                        Evaluation evaluation = new Evaluation(name, gender, staffname);
                                        evaluation.setVisible(true);
                                        evaluation.setLocationRelativeTo(null);
                                    }

                                };
                                //设置表格的渲染器
                                jTable1.getColumnModel().getColumn(8).setCellRenderer(new MyButtonRender());
                                MyButtonEditor editor = new MyButtonEditor(e);
                                //设置表格的编辑
                                jTable1.getColumnModel().getColumn(8).setCellEditor(editor);
                                jTable1.repaint();
                            } else {
                                JOptionPane.showMessageDialog(null, "查无此人");
                            }
                        }
                    });
                }
                //修改病患信息
                {
                    jButton2 = new JButton();
                    jPanel1.add(jButton2);
                    jButton2.setText("修改病患信息");
                    pat = this;
                    jButton2.setBounds(840, 42, 194, 31);
                    jButton2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            String name = null;
                            PatientService service = new PatientServiceimpl();
                            for (int i = 0; i < jTable1.getRowCount(); i++) {
                                if ((Boolean) jTable1.getValueAt(i, 0)) {
                                    name = jTable1.getValueAt(i, 1).toString();
                                }
                            }
                            Patient patient = service.getByname(name);
                            ModifyPatient modifyPatient = new ModifyPatient(patient, pat, staffname);
                            modifyPatient.setVisible(true);
                            modifyPatient.setLocationRelativeTo(null);
                            dispose();
                        }
                    });
                }
                //TemplateTable
                {
                    jButton3 = new JButton();//模板列表
                    jPanel1.add(jButton3);
                    jButton3.setText("\u6a21\u677f\u5217\u8868");
                    jButton3.setBounds(800, 97, 105, 29);
                    jButton3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            TemplateTable templateTable = new TemplateTable();
                            templateTable.setVisible(true);
                            templateTable.setLocationRelativeTo(null);
                        }
                    });
                }
                //添加用户
                {
                    jButton4 = new JButton();//添加用户
                    jPanel1.add(jButton4);
                    jButton4.setText("\u6dfb\u52a0\u7528\u6237");
                    jButton4.setBounds(930, 97, 105, 29);
                    pat = this;
                    jButton4.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            AddNewPatient addNewPatient = new AddNewPatient(pat, staffname);
                            addNewPatient.setVisible(true);
                            addNewPatient.setLocationRelativeTo(null);
                            dispose();
                        }
                    });
                }
                //删除
                {
                    jButton5 = new JButton();
                    jPanel1.add(jButton5);
                    jButton5.setText("\u5220\u9664");
                    jButton5.setBounds(38, 97, 88, 29);
                    jButton5.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PatientService service = new PatientServiceimpl();
                            for (int i = 0; i < jTable1.getRowCount(); ) {
                                if ((Boolean) jTable1.getValueAt(i, 0)) {
                                    Patient p = service.getByname((String) jTable1.getValueAt(i, 1));
                                    service.deletepatient(p);
                                    System.out.println(jTable1.getRowCount());
                                    tableModel.removeRow(i);
                                    tableModel.fireTableDataChanged();
                                    i = 0;
                                    continue;
                                }
                                i++;
                                System.out.println(i);
                            }
                            jTable1.updateUI();
                        }
                    });
                }
                //评估
                {
                    tableModel = new DefaultTableModel(data, colsV);
                    jTable1 = new JTable();
                    jTable1.setModel(tableModel);
                    Tools.setbox(jTable1);
                    jScrollPane.setViewportView(jTable1);
                    jTable1.setBounds(0, 0, 1000, 250);
                    jTable1.setRowHeight(30);
                    MyEvent e = new MyEvent() {
                        @Override
                        public void invoke(ActionEvent e) {
                            MyButton button = (MyButton) e.getSource();
                            //打印被点击的行和列
                            System.out.println("row:" + button.getRow() + "column :" + button.getColumn());
                            String name = jTable1.getValueAt(button.getRow(), 1).toString();
                            String gender = jTable1.getValueAt(button.getRow(), 3).toString();
                            Evaluation evaluation = new Evaluation(name, gender, staffname);
                            evaluation.setVisible(true);
                            evaluation.setLocationRelativeTo(null);
                        }

                    };
                    //设置表格的渲染器
                    jTable1.getColumnModel().getColumn(8).setCellRenderer(new MyButtonRender());
                    MyButtonEditor editor = new MyButtonEditor(e);
                    //设置表格的编辑
                    jTable1.getColumnModel().getColumn(8).setCellEditor(editor);

                }
                {
                    jButton6 = new JButton();//保存
                    jPanel1.add(jButton6);
                    jButton6.setText("\u4fdd\u5b58");
                    jButton6.setBounds(162, 409, 105, 29);
                    jButton6.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {

                        }
                    });
                }
                //exit
                {
                    jButton7 = new JButton();//返回
                    jPanel1.add(jButton7);
                    jButton7.setText("\u8fd4\u56de");
                    jButton7.setBounds(800, 409, 105, 29);
                    jButton7.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            //访问服务层调数据
                            PatientService stservice = new PatientServiceimpl();
                            List<Patient> listpatient = stservice.getAllpatient();
                            data.clear();
                            //存储病人数据
                            for (int i = 0; i < listpatient.size(); i++) {
                                Vector vector = new Vector<>();
                                vector.add(new Boolean(false));
                                vector.add(listpatient.get(i).getName());
                                vector.add(listpatient.get(i).getAge());
                                vector.add(listpatient.get(i).getGender());
                                vector.add(listpatient.get(i).getIdNumber());
                                vector.add(listpatient.get(i).getPhoneNumber());
                                vector.add(listpatient.get(i).getEmergencyContact());
                                vector.add(listpatient.get(i).getEcPhone());
                                data.add(vector);
                            }
                            TableModel se = new DefaultTableModel(data, colsV);
                            jTable1.setModel(se);
                            Tools.setbox(jTable1);
                            jTable1.repaint();
                            MyEvent e = new MyEvent() {
                                @Override
                                public void invoke(ActionEvent e) {
                                    MyButton button = (MyButton) e.getSource();
                                    //打印被点击的行和列
                                    System.out.println("row:" + button.getRow() + "column :" + button.getColumn());
                                    String name = jTable1.getValueAt(button.getRow(), 1).toString();
                                    String gender = jTable1.getValueAt(button.getRow(), 3).toString();
                                    Evaluation evaluation = new Evaluation(name, gender, staffname);
                                    evaluation.setVisible(true);
                                    evaluation.setLocationRelativeTo(null);
                                }

                            };
                            //设置表格的渲染器
                            jTable1.getColumnModel().getColumn(8).setCellRenderer(new MyButtonRender());
                            MyButtonEditor editor = new MyButtonEditor(e);
                            //设置表格的编辑
                            jTable1.getColumnModel().getColumn(8).setCellEditor(editor);
                        }
                    });
                }
            }
            pack();
            this.setSize(1100, 531);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class MyButton extends JButton {

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

    public MyButton() {

    }

    public MyButton(String name) {
        super(name);
    }

}

class MyButtonEditor extends DefaultCellEditor {

    private MyButton button;

    private MyEvent event;

    public MyButtonEditor() {
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

    public MyButtonEditor(MyEvent e) {
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


interface MyEvent {
    public abstract void invoke(ActionEvent e);
}

class MyButtonRender implements TableCellRenderer {

    private JButton button;

    public MyButtonRender() {
        button = new JButton("评估");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

}