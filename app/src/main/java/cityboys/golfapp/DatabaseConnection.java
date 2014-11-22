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

    private final String mUsername;
    private final String mPassword;
    private final String mLink;
    private final char mType;
    public String mData;

    DatabaseConnection(String username, String password, String link, char type) {
        mUsername = username;
        mPassword = password;
        mLink = link;
        mType = type;
        this.mData = "";
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        try {
            String data = "";
            if(mUsername!=null || mPassword!=null) {
                data = URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode(mUsername, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8")
                        + "=" + URLEncoder.encode(mPassword, "UTF-8");
            }
            URL url = new URL(mLink);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter
                    (conn.getOutputStream());
            if(mUsername!=null || mPassword!=null) {
                wr.write(data);
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
        }
    }
}