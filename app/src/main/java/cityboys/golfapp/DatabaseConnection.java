package cityboys.golfapp;

import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by hilmarhergeirsson on 21/11/14.
 */
public class DatabaseConnection extends AsyncTask<Void, Void, Boolean> {

    private final String mArg1;
    private final String mArg2;
    private final String mArg3;
    private final String mLink;
    private final char mType;
    public String mData;

    DatabaseConnection(String arg1, String arg2, String arg3, String link, char type) {
        mArg1 = arg1;
        mArg2 = arg2;
        mArg3 = arg3;
        mLink = link;
        mType = type;
        this.mData = "";
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        try {
            String data = "";

            switch(this.mType) {
                case 'i':
                    data = URLEncoder.encode("course_id", "UTF-8")
                            + "=" + URLEncoder.encode(mArg1, "UTF-8");
                    data += "&" + URLEncoder.encode("user_id", "UTF-8")
                            + "=" + URLEncoder.encode(User.getUserId(), "UTF-8");
                    data += "&" + URLEncoder.encode("startDate", "UTF-8")
                            + "=" + URLEncoder.encode(mArg2, "UTF-8");
                    data += "&" + URLEncoder.encode("startTime", "UTF-8")
                            + "=" + URLEncoder.encode(mArg3, "UTF-8");
                    break;
            }

            URL url = new URL(mLink);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter
                    (conn.getOutputStream());

            switch(this.mType) {
                case 'i':
                    wr.write(data);
                    break;
            }
            wr.flush();
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                //sb.append(line);
                sb.append(line+"\n");
                break;
            }
            this.mData = sb.toString();

            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        //Check which type of connection is established
        switch(this.mType) {
            case 'c':
                if(success){
                    Courses.initCourses(this.mData);
                }
                break;
            case 'g':
                if(success){
                    Clubs.initClubs(this.mData);
                }
                break;
            case 's':
                if(success){
                    StartingTimes.initStartingTimes(this.mData);
                }
                break;
            case 'i':
                if(success){
                    //System.out.println("Test");
                    //System.out.println(this.mData);
                    //TODO: Reload starting time by calling a database connection
                    /*Establish database connection to get starting times*/
                    String startLink="https://notendur.hi.is/~hoh40/Hugbunadarverkfraedi1/getStartingTimes.php";
                    DatabaseConnection mStartTask = new DatabaseConnection(null, null, null, startLink, 's');
                    mStartTask.execute();
                    RastimaYfirlit.listAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}