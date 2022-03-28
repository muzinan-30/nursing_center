package po;
/*
* 预览模板类
*
* */
public class PreviewTemplate {
    //预览模板问题ID
    private int id;
    //
    private int qid;
    private String qcontent;

    public PreviewTemplate() {
    }

    public PreviewTemplate(int id, int qid, String qcontent) {
        this.id = id;
        this.qid = qid;
        this.qcontent = qcontent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQcontent() {
        return qcontent;
    }

    public void setQcontent(String qcontent) {
        this.qcontent = qcontent;
    }

    @Override
    public String toString() {
        return "{\"id\":" + this.id +
                ",\"qid\":" + this.qid +
                ",\"qcontent\":\"" + this.qcontent + "\"}";
    }
}
