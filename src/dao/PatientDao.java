package dao;

import po.Patient;

import java.util.List;
/*
* 病人管理操作接口
*
* */
public interface PatientDao {
    //对于病人：
    //添加
    public int addpatient(Patient p);
    //通过病人的名字去获取病人
    public Patient getByname(String name);
    //获取所有的病人
    public List<Patient> getAllpatient();
    //删除病人
    public int deletepatient(Patient p);
    //修改病人
    public int modifypatient(Patient p);
}
