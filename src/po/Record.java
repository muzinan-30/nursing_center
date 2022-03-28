package po;
/*
* 纪录类
*
* */
public class Record {
    private String name;
    private String gender;
    private String tname;
    private String ttype;
    private String time;
    private String ename;
    private String suggest;

    public Record() {
    }

    public Record(String name, String gender, String tname, String ttype, String time, String ename, String suggest) {
        this.name = name;
        this.gender = gender;
        this.tname = tname;
        this.ttype = ttype;
        this.time = time;
        this.ename = ename;
        this.suggest = suggest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + this.name +
                "\",\"gender\":\"" + this.gender +
                "\",\"tname\":\"" + this.tname +
                "\",\"ttype\":\"" + this.ttype +
                "\",\"time\":\"" + this.time +
                "\",\"ename\":\"" + this.ename +
                "\",\"suggest\":\"" + this.suggest + "\"}";
    }
}
