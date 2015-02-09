package cityboys.rastimar;

/**
 * Created by hilmarhergeirsson on 25/11/14.
 */

// Notkun: StartingTime this_starting_time = new StartingTime(String course_name, String course_id, String full_name, String user_id, String startDate, String startTime);
// Fyrir: ekkert
// Eftir: búið er að gera nýjan StartingTime hlut
public class StartingTime {

    private final String course_name;
    private final String course_id;
    private final String full_name;
    private final String user_id;
    private final String startDate;
    private final String startTime;

    StartingTime(String course_name, String course_id, String full_name, String user_id, String startDate, String startTime) {
        this.course_name = course_name;
        this.course_id = course_id;
        this.full_name = full_name;
        this.user_id = user_id;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    // Notkun: StartingTime.getCourseName()
    // Fyrir: ekkert
    // Eftir: búið er að sækja nafn vallarins
    public String getCourseName(){
        return this.course_name;
    }

    // Notkun: StartingTime.getCourseId()
    // Fyrir: ekkert
    // Eftir: búið er að sækja id vallarins
    public String getCourseId(){
        return this.course_id;
    }

    // Notkun: StartingTime.getUserName()
    // Fyrir: ekkert
    // Eftir: búið er að sækja nafn kylfingsins
    public String getUserName(){
        return this.full_name;
    }

    // Notkun: StartingTime.getUserId()
    // Fyrir: ekkert
    // Eftir: búið er að sækja id kylfingsins
    public String getUserId(){
        return this.user_id;
    }

    // Notkun: StartingTime.getStartDate()
    // Fyrir: ekkert
    // Eftir: búið er að sækja dagsetningu rástímans
    public String getStartDate(){
        return this.startDate;
    }

    // Notkun: StartingTime.getStartTime()
    // Fyrir: ekkert
    // Eftir: búið er að sækja tímasetningu rástímans
    public String getStartTime(){
        return this.startTime;
    }
}