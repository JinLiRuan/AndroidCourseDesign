package rjl.com.androidcoursedesign;

public class Student {

    private int id;
    private String name;
    private String sno;
    private String major;

    public Student() {
    }

    public Student(int id, String name, String sno, String major) {
        this.id = id;
        this.name = name;
        this.sno = sno;
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sno='" + sno + '\'' +
                ", major='" + major + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSno() {
        return sno;
    }

    public String getMajor() {
        return major;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
