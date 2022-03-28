package po;
/*
* 员工类
*
* */
public class Staff extends People {
    private String account;
    private String password;
    private String expertise;
    private String jobTitle;
    private int authority;//权限

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "{\"account\":\"" + this.account +
                "\",\"name\":\"" + this.getName() +
                "\",\"age\":" + this.getAge() +
                ",\"expertise\":\"" + this.expertise +
                "\",\"jobTitle\":\"" + this.jobTitle +
                "\",\"password\":\"" + this.password +
                "\",\"phoneNumber\":" + this.getPhoneNumber() + "}";
    }

}
