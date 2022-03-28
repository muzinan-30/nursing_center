package po;
/*
* 病患类
* */
public class Patient extends People{
    //紧急联系人
    private String emergencyContact;
    //紧急联系人电话
    private int ecPhone;

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public int getEcPhone() {
        return ecPhone;
    }

    public void setEcPhone(int ecPhone) {
        this.ecPhone = ecPhone;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + this.getName()
                + "\",\"age\":\"" + this.getAge()
                +"\",\"gender\":\"" + this.getGender()
                +"\",\"idNumber\":\"" + this.getIdNumber()
                +"\",\"phoneNumber\":" + this.getPhoneNumber()
                +",\"emergencyContact\":\"" + this.emergencyContact
                +"\",\"ecPhone\":" + this.ecPhone + "}";
    }
}
