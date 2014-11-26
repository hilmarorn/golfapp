package cityboys.golfapp;

/**
 * Created by hilmarhergeirsson on 26/11/14.
 */
public class Hole {

    private final String courseName;
    private final String courseId;
    private final String holeNumber;
    private final String holePar;
    private final String holeLength;
    private final String handicap;
    private final String whiteLength;
    private final String yellowLength;
    private final String blueLength;
    private final String redLength;
    private final String goldLength;

    Hole(String courseName, String course_id, String holeNumber, String holePar, String holeLength, String handicap, String whiteLength, String yellowLength, String blueLength, String redLength, String goldLength) {

        this.courseName = courseName;
        this.courseId = course_id;
        this.holeNumber = holeNumber;
        this.holePar = holePar;
        this.holeLength = holeLength;
        this.handicap = handicap;
        this.whiteLength = whiteLength;
        this.yellowLength = yellowLength;
        this.blueLength = blueLength;
        this.redLength = redLength;
        this.goldLength = goldLength;
    }

    public String getCourseName(){
        return this.courseName;
    }

    public String getCourseId(){
        return this.courseId;
    }

    public String getHoleNumber(){
        return this.holeNumber;
    }

    public String getHolePar(){
        return this.holePar;
    }

    public String getHoleLength(){
        return this.holeLength;
    }

    public String getHandicap(){
        return this.handicap;
    }

    public String getWhiteLength(){
        return this.whiteLength;
    }

    public String getYellowLength(){
        return this.yellowLength;
    }

    public String getBlueLength(){
        return this.blueLength;
    }

    public String getRedLength(){
        return this.redLength;
    }

    public String getGoldLength(){
        return this.goldLength;
    }
}
