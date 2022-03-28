package po;
/*
*
* 问题类
*
* */
public class Question {
    private int id;
    private String content;
    private String type;
    private String answer1;
    private String answer2;
    private String answer3;

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }


    public Question() {
    }

    public Question(int id, String content, String type, String answer1, String answer2, String answer3) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public int getScore(String answer){
        if(answer.equals(this.answer1)){
            return 5;
        }
        if(answer.equals(this.answer2)){
            return 3;
        }
        if(answer.equals(this.answer3)){
            return 1;
        }
        return 0;
    }
    @Override
    public String toString() {
        return "{\"id\":" + this.id +
                ",\"content\":\"" + this.content +
                "\",\"type\":\"" + this.type +
                "\",\"answer1\":\"" + this.answer1 +
                "\",\"answer2\":\"" + this.answer2 +
                "\",\"answer3\":\"" + this.answer3 +"\"}";
    }
}
