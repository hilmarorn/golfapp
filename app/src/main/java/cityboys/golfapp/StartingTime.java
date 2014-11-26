package cityboys.golfapp;

/**
 * Created by hilmarhergeirsson on 25/11/14.
 */
public class StartingTime {

    private final String course_name;
    private final String course_id;
    private final String user_name;
    private final String user_id;
    private final String startDate;
    private final String startTime;

    StartingTime(String course_name, String course_id, String user_name, String user_id, String startDate, String startTime) {
        this.course_name = course_name;
        this.course_id = course_id;
        this.user_name = user_name;
        this.user_id = user_id;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public String getCourseName(){
        return this.course_name;
    }

    public String getCourseId(){
        return this.course_id;
    }

    public String getUserName(){
        return this.user_name;
    }

    public String getUserId(){
        return this.user_id;
    }

    public String getStartDate(){
        return this.startDate;
    }

    public String getStartTime(){
        return this.startTime;
    }
}