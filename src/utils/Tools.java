package utils;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import po.*;
import net.sf.json.JSONObject;
import po.Record;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class Tools {
    /*public static String createJsonString(String name, List list) {
        JSONObject obj = new JSONObject();
        obj.put(name, list);

        return obj.toString();
    }

    public static String createToJson(List list) {
        JSONArray array = new JSONArray();
        array.add(list);
        return array.toString();
    }
     public static JSONArray readToJson(String name) {
        JSONArray array = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(name + ".txt"));
            String jsonStr = readInputStream(bis);
            String str = jsonStr.replace('\\', '|').replace("\"[", "[").replace("]\"", "]");
            JSONObject obj = JSONObject.fromObject(str.replace("|\"", "'").replace('\"', '\''));
            array = new JSONArray();
            array = obj.getJSONArray(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }*/
    //将数据写入文件中
    public static void write(String name, String jsonString, boolean flag) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(name + ".txt", flag));
            bw.write(jsonString);
            bw.newLine();
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                bw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static String readInputStream(InputStream in) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            InputStream inStream = in;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            inStream.close();
        } catch (Exception e) {

        }
        return new String(outStream.toByteArray());
    }

    //读取文件
    public static String Read(String fileName) {
        StringBuffer sb = null;
        String path = fileName + ".txt";
        try {

            sb = new StringBuffer();
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String msg = br.readLine();
            while (msg != null) {
                sb.append(msg + "/");
                msg = br.readLine();
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //从文件中读取员工的信息
    public static List<Staff> getStaffList(String fileName) {
        String msg = Read(fileName);
        if (msg.length() == 0) {
            return null;
        } else {
            String b[] = msg.split("/");
            Staff st1 = new Staff();
            List<Staff> list = new ArrayList();
            for (String string : b) {
                JSONObject json = JSONObject.fromObject(string);
                Staff st2 = (Staff) JSONObject.toBean(json, st1.getClass());
                list.add(st2);
            }
            return list;
        }
    }

    //从文件中读取病患的信息
    public static List<Patient> getPatientList(String filename) {
        String msg = Read(filename);
        if (msg.length() == 0) {
            return null;
        } else {
            String b[] = msg.split("/");
            Patient pat1 = new Patient();
            List<Patient> listpatient = new ArrayList();
            for (String string : b) {
                JSONObject json = JSONObject.fromObject(string);
                Patient pat2 = (Patient) JSONObject.toBean(json, pat1.getClass());
                listpatient.add(pat2);
            }
            return listpatient;
        }
    }

    //从文件中读取问题的信息
    public static List<Question> getQuestionList(String fileName) {
        String msg = Read(fileName);
        if (msg.length() == 0) {
            return null;
        } else {
            String b[] = msg.split("/");
            Question q = new Question();
            List<Question> list = new ArrayList();
            for (String string : b) {
                JSONObject json = JSONObject.fromObject(string);
                Question q2 = (Question) JSONObject.toBean(json, q.getClass());
                list.add(q2);
            }
            return list;
        }
    }

    //从文件中读取模板信息
    public static List<Template> getTemplateList(String fileName) {
        String msg = Read(fileName);
        if (msg.length() == 0) {
            return null;
        } else {
            String b[] = msg.split("/");
            Template t1 = new Template();
            List<Template> list = new ArrayList();
            for (String string : b) {
                JSONObject json = JSONObject.fromObject(string);
                Template t2 = (Template) JSONObject.toBean(json, t1.getClass());
                list.add(t2);
            }
            return list;
        }
    }

    //从文件中读取预览模板
    public static List<PreviewTemplate> getPreviewTemplate(String fileName) {
        String msg = Read(fileName);
        if (msg.length() == 0) {
            return null;
        } else {
            String b[] = msg.split("/");
            PreviewTemplate pt1 = new PreviewTemplate();
            List<PreviewTemplate> list = new ArrayList();
            for (String string : b) {
                JSONObject json = JSONObject.fromObject(string);
                PreviewTemplate pt = (PreviewTemplate) JSONObject.toBean(json, pt1.getClass());
                list.add(pt);
            }
            return list;
        }
    }

    //从文件中读取记录
    public static Vector<Vector> getRecordVector(String fileName) {
        String msg = Read(fileName);
        if (msg.length() == 0) {
            return null;
        } else {
            String b[] = msg.split("/");
            Record record = new Record();
            Vector<Vector> vectors = new Vector<>();
            for (String string : b) {
                JSONObject json = JSONObject.fromObject(string);
                Record r2 = (Record) JSONObject.toBean(json, record.getClass());
                Vector<Object> v = new Vector<>();
                v.add(r2.getName());
                v.add(r2.getGender());
                v.add(r2.getTname());
                v.add(r2.getTtype());
                v.add(r2.getTime());
                v.add(r2.getEname());
                v.add(r2.getSuggest());
                vectors.add(v);
            }
            return vectors;
        }
    }

    //获取当前的时间
    public static String getTime() {
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str = format.format(date);
        return str;
    }

    public static void setbox(JTable jTable1) {
        JCheckBox box = new JCheckBox();
        jTable1.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
        jTable1.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                // 第一列渲染成复选框
                if (column == 0) {
                    JCheckBox box = new JCheckBox(); // 复选框
                    box.setOpaque(true);  // 设置成不透明
                    box.setHorizontalAlignment(JCheckBox.CENTER); // 居中
                    if (isSelected) {//点击表格的时候改变点击的行的背景色
                        box.setBackground(new Color(135, 206, 250));
                    } else {
                        if (row % 2 == 0) { // 偶数行的前景、背景颜色
                            box.setBackground(new Color(240, 250, 250));
                            box.setForeground(table.getForeground());
                        } else { // 奇数行的背景颜色
                            box.setBackground(table.getBackground());
                        }
                    }
                    boolean valu = (Boolean) value;
                    box.setSelected(valu);
                    return box;
                }
                // 其它列渲染成普通标签
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setHorizontalAlignment(JLabel.CENTER);
                if (isSelected) {
                    // 点击表格的时候改变点击的行的背景色
                    label.setBackground(new Color(237, 85, 106));
                } else {
                    if (row % 2 == 0) {
                        label.setBackground(new Color(237, 85, 106));
                        label.setForeground(table.getForeground());
                    } else {
                        label.setBackground(table.getBackground());
                    }
                }
                label.setText(value != null ? value.toString() : "");
                return label;
            }
        });
    }
}



