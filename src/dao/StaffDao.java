package dao;

import po.Staff;

import java.util.List;
/*
 * 员工管理操作接口
 *
 * */
public interface StaffDao {
    //添加员工
    public int addstaff(Staff s);
    //获取员工通过姓名
    public Staff getByName(String name);
    //获取所有的员工
    public List<Staff> getAllstaff();
    //通过职称来获取所有的员工
    public List<Staff> getAllstaffByJobTitle(String jobt);
    //删除
    public int deletestaff(Staff s);
    //修改
    public int modifystaff();
}
