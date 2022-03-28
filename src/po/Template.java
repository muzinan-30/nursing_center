package po;

import java.util.Vector;
/*
* 模板类
*
* */
public class Template {
    private int id;
    private String tname;
    private String type;
    Vector<Question> questionVector;

    public Vector<Question> getQuestionVector() {
        return questionVector;
    }

    public void setQuestionVector(Vector<Question> questionVector) {
        this.questionVector = questionVector;
    }

    public Template(int id, String tname, String type, Vector<Question> questionVector) {
        this.id = id;
        this.tname = tname;
        this.type = type;
        this.questionVector = questionVector;
    }

    public Template() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return   "{\"id\":" + this.id +
                ",\"tname\":\"" + this.tname +
                "\",\"type\":\"" + this.type + "\"}";

    }
}
