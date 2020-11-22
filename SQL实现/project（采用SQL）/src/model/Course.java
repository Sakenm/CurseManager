package model;

/**
 * @author Administrator
 */
public class Course {
    /**课程号*/
    private int id;
    /**课程名*/
    private String name;
    /**课程学时*/
    private int studyTime;
    /**课程学分*/
    private int studyGrade;

    /**空构造器*/
    public Course(){

    }

    /**含参构造器*/
    public Course(int id, String name, int studyTime, int studyGrade) {
        this.id = id;
        this.name = name;
        this.studyTime = studyTime;
        this.studyGrade = studyGrade;
    }

    /**取得课程的信息，返回一个字符串*/
    public String getInfo(){
        return "课程号："+this.id+"   课程名："+this.name+"   学时："+this.studyTime+"   学分："+studyGrade;
    }

    /**取得课程号*/
    public int getId() {
        return id;
    }

    /**设定课程号*/
    public void setId(int id) {
        this.id = id;
    }

    /**取得课程名*/
    public String getName() {
        return name;
    }

    /**设定课程名*/
    public void setName(String name) {
        this.name = name;
    }

    /**取得学时*/
    public int getStudyTime() {
        return studyTime;
    }

    /**设定学时*/
    public void setStudyTime(int studyTime) {
        this.studyTime = studyTime;
    }

    /**取得学分*/
    public int getStudyGrade() {
        return studyGrade;
    }

    /**设定学分*/
    public void setStudyGrade(int studyGrade) {
        this.studyGrade = studyGrade;
    }
}
