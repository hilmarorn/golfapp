package cityboys.golfapp;

/**
 * Created by hilmarhergeirsson on 21/11/14.
 */
public class Course {

    private final String courseName;
    private final String clubName;
    private final String clubShortName;
    private final String courseId;


    Course(String courseName, String clubName, String clubShortName, String course_id) {
        this.courseName = courseName;
        this.clubName = clubName;
        this.clubShortName = clubShortName;
        this.courseId = course_id;
    }

    public String getCourseName(){
        return this.courseName;
    }

    public String getClubName(){
        return this.clubName;
    }

    public String getClubShortName(){
        return this.clubShortName;
    }

    public String getCourseId(){
        return this.courseId;
    }
}
