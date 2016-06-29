package cmru.kulachart.cmrurun;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String urlLogo = "http://walnut.cm2cars.com/cmrulogo.png";
    private static final String urlJSON = "http://www.swiftcodingthai.com/cmru/get_user_master.php";
    private ImageView imageView;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind widget
        imageView = (ImageView) findViewById(R.id.imageView6);
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);

        //load logo
        Picasso.with(this).load(urlLogo).resize(150, 180).into(imageView);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }//main medhod

    // Create Inner Class
    private class SyncUser extends AsyncTask<Void, Void, String> {

        //explicit
        private Context context;
        private String strURL;
        private boolean statusABoolean = true;
        private String truePasswordString, nameUserString;

        public SyncUser(Context context, String strURL) {
            this.context = context;
            this.strURL = strURL;

        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strURL).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("29June", "e doInback ==>" + e.toString());
                return null;
            }
            //return null;
        } // doInBackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("29June", "JSON ==>" + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (userString.equals(jsonObject.getString("User"))) {
                        statusABoolean = false;
                        truePasswordString = jsonObject.getString("Password");
                        nameUserString = jsonObject.getString("Name");
                    }//if

                }//for

                if (statusABoolean) {
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, "ไม่มี user นี้", "ไม่มี" + userString + "ในฐานข้อมูลของเรา");
                } else if (passwordString.equals(truePasswordString)) {
                    //pass true
                    Toast.makeText(context,"Welcome " + nameUserString, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                    fileList();
                } else {
                    //pass false
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(context, "Password False", "Please Try Again Password False");
                }//if

            } catch (Exception e) {
                Log.d("29June", "e onPost ==>" + e.toString());
            }//try

        }//onpost
    } // SynUser Class


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://cmru.kulachart.cmrurun/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://cmru.kulachart.cmrurun/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public void clickSignIn(View view) {
        // Click Sign In
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        // Check Space
        if (userString.equals("") || passwordString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ผิดพลาด", "โปรดกรอกข้อมูลให้ครบถ้วน");
        } else {
            checkUserPassword();
        }
    }

    private void checkUserPassword() {
        SyncUser synUser = new SyncUser(this, urlJSON);
        synUser.execute();
    }


    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }//clicksignupmain
} // main class
