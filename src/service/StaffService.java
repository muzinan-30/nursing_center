package service;

import po.Staff;

import java.util.List;
/*
*
*员工管理服务层
* */
public interface StaffService {
    //工作人员管理
    //登录
    public int login(String account,String password);
    //添加
    public int addstaff(Staff s);
    //通过姓名查找
    public Staff getByName(String name);
    //获取所有的员工的信息
    public List<Staff> getAllstaff();
    //通过员工的职称来获取所有的员工
    public List<Staff> getAllstaffByJobTitle(String jobt);
    //删除员工
    public int deletestaff(Staff s);

}
