package daoimpl;
/*
 * 功能实现层
 * */

import po.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import dao.*;
import utils.Tools;
/*
*
* 员工Dao的实现
*
*
* */

public class StaffDaoimpl implements StaffDao {
    //员工
    @Override
    public int addstaff(Staff s) {
        try {
            Tools.write("staff", s.toString(), true);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Staff getByName(String name) {
        List<Staff> list1 = getAllstaff();
        for (Staff st : list1) {
            if (name.equals(st.getName())) {
                return st;
            }
        }
        return null;
    }

    @Override
    public List<Staff> getAllstaff() {
        List<Staff> list = Tools.getStaffList("staff");
        return list;
    }

    @Override
    public List<Staff> getAllstaffByJobTitle(String jobt) {
        List<Staff> Joblist = new ArrayList<>();
        List<Staff> list1 = getAllstaff();
        for (Staff st : list1) {
            if (jobt.equals(st.getJobTitle())) {
                Joblist.add(st);
            }
        }
        return Joblist;
    }

    @Override
    public int deletestaff(Staff s) {
        List<Staff> list = Tools.getStaffList("staff");
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAccount().equals(s.getAccount())) {
                flag = i;
            }
        }
        list.remove(flag);
        File file = new File("staff.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            Tools.write("staff", list.get(i).toString(), true);
        }
        return 0;
    }

    @Override
    public int modifystaff() {
        return 0;
    }
}







