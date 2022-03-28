package service;

import po.Patient;

import java.util.List;
/*
* 病人管理服务层
* */
public interface PatientService {
    //病人管理：
    //添加
    public int addpatient(Patient p);
    //查询
    public Patient getByname(String name);
    //获取所有的病患信息
    public List<Patient> getAllpatient();
    //删除
    public int deletepatient(Patient p);
    //修改
    public int modifypatient(Patient p);
}
