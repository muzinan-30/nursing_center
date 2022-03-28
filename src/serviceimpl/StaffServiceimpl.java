package serviceimpl;

import dao.StaffDao;
import daoimpl.StaffDaoimpl;
import po.Master;
import po.Staff;

import java.util.List;
import service.*;
/*
 * 员工管理服务层的实现类
 * */
public class StaffServiceimpl implements StaffService{
    StaffDao Dao=new StaffDaoimpl();
    @Override
    public int addstaff(Staff s) {
        return Dao.addstaff(s);
    }

    @Override
    public Staff getByName(String name) {
        return Dao.getByName(name);
    }

    @Override
    public List<Staff> getAllstaff() {
        return Dao.getAllstaff();
    }

    @Override
    public List<Staff> getAllstaffByJobTitle(String jobt) {
        return Dao.getAllstaffByJobTitle(jobt);
    }

    @Override
    public int deletestaff(Staff s) {
        return Dao.deletestaff(s);
    }


    @Override
    public int login(String account, String password) {
        int flag = 0;
        if (account.equals(Master.account) && password.equals(Master.password)) {
            return 2;
        }
        List<Staff> list = Dao.getAllstaff();
        for (Staff s : list) {
            if (account.equals(s.getAccount()) && password.equals(s.getPassword())) {
                return 1;
            }
        }
        return flag;
    }

}
