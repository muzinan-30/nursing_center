package daoimpl;

import po.Patient;
import utils.Tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import dao.*;
/*
* 病人dao的实现
*
* */
public class PatientDaoimpl implements PatientDao{
    //病患：
    @Override
    public int addpatient(Patient p) {
        try {
            Tools.write("patient", p.toString(), true);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Patient getByname(String name) {
        List<Patient> listpatient1 = getAllpatient();
        for (Patient p : listpatient1) {
            if (name.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Patient> getAllpatient() {
        List<Patient> listpatient = Tools.getPatientList("patient");
        return listpatient;
    }

    @Override
    public int deletepatient(Patient p) {
        List<Patient> list = Tools.getPatientList("patient");
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdNumber().equals(p.getIdNumber())) {
                flag = i;
            }
        }
        list.remove(flag);
        File file = new File("patient.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            Tools.write("patient", list.get(i).toString(), true);
        }
        return 0;
    }


    @Override
    public int modifypatient(Patient p) {
        List<Patient> list = Tools.getPatientList("patient");
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName() .equals(p.getName())) {
                flag = i;
            }
        }
        list.set(flag,p);
        File file = new File("patient.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < list.size(); i++) {
            Tools.write("patient", list.get(i).toString(), true);
        }
        return 0;
    }
}
