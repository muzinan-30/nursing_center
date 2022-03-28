package serviceimpl;

import dao.PatientDao;
import daoimpl.PatientDaoimpl;
import po.Patient;

import java.util.List;

import service.*;
/*
* 病人管理服务层的实现类
* */
public class PatientServiceimpl implements PatientService{
    PatientDao Dao=new PatientDaoimpl();
    @Override
    public int addpatient(Patient p) {
        return Dao.addpatient(p);
    }

    @Override
    public Patient getByname(String name) {
        return Dao.getByname(name);
    }

    @Override
    public List<Patient> getAllpatient() {
        return Dao.getAllpatient();
    }

    @Override
    public int deletepatient(Patient p) {
        return Dao.deletepatient(p);
    }

    @Override
    public int modifypatient(Patient p) {
        return Dao.modifypatient(p);
    }
}
