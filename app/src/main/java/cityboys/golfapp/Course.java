package cityboys.golfapp;

/**
 * Created by hilmarhergeirsson on 21/11/14.
 */

// Notkun: Course this_course = new Course(String courseName, String clubName, String clubShortName, String course_id);
// Fyrir: ekkert
// Eftir: búið er að gera nýjan Course hlut
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

    // Notkun: Course.getCourseName()
    // Fyrir: ekkert
    // Eftir: búið er að sækja nafn vallarins
    public String getCourseName(){
        return this.courseName;
    }

    // Notkun: Course.getClubName()
    // Fyrir: ekkert
    // Eftir: búið er að sækja nafn klúbbsins
    public String getClubName(){
        return this.clubName;
    }

    // Notkun: Course.getClubShortName()
    // Fyrir: ekkert
    // Eftir: búið er að sækja skammstöfun klúbbsins
    public String getClubShortName(){
        return this.clubShortName;
    }

    // Notkun: Course.getCourseId()
    // Fyrir: ekkert
    // Eftir: búið er að sækja id vallarins
    public String getCourseId(){
        return this.courseId;
    }
}
