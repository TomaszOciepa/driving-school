package date.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private int courseId;

    @Column(name = "COURSE_NAME")
    private String courseName;

    @Column(name = "COURSE_DATA_START")
    private String courseDataStart;

    @Column(name = "COURSE_DATA_FINISH")
    private String courseDataFinish;

    @Column(name = "COURSE_CATEGORY_DRIVE_LICENSE")
    private String courseCatrgoryDriveLicense;

    @Column(name = "COURSE_NUMBER_HOURS")
    private String courseNumberHours;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private List<Student> students;

    public Course() {
    }

    public Course(String courseName, String courseDataStart, String courseDataFinish, String courseCatrgoryDriveLicense, String courseNumberHours) {
        this.courseName = courseName;
        this.courseDataStart = courseDataStart;
        this.courseDataFinish = courseDataFinish;
        this.courseCatrgoryDriveLicense = courseCatrgoryDriveLicense;
        this.courseNumberHours = courseNumberHours;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDataStart() {
        return courseDataStart;
    }

    public void setCourseDataStart(String courseDataStart) {
        this.courseDataStart = courseDataStart;
    }

    public String getCourseDataFinish() {
        return courseDataFinish;
    }

    public void setCourseDataFinish(String courseDataFinish) {
        this.courseDataFinish = courseDataFinish;
    }

    public String getCourseCatrgoryDriveLicense() {
        return courseCatrgoryDriveLicense;
    }

    public void setCourseCatrgoryDriveLicense(String courseCatrgoryDriveLicense) {
        this.courseCatrgoryDriveLicense = courseCatrgoryDriveLicense;
    }

    public String getCourseNumberHours() {
        return courseNumberHours;
    }

    public void setCourseNumberHours(String courseNumberHours) {
        this.courseNumberHours = courseNumberHours;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
