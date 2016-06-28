package cmru.kulachart.cmrurun;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    //explicit
    private EditText nameEditText, passwordEditText, userEditText;
    private RadioGroup radioGroup;
    private RadioButton avatar0RadioButton, avatar1RadioButton, avatar2RadioButton, avatar3RadioButton, avatar4RadioButton;
    private String nameString, userString, passwordString, avatarString;
    //private static final String urlPHP = "http://walnut.cm2cars.com/add_user_master.php";
    private static final String urlPHP = "http://swiftcodingthai.com/cmru/add_user_master.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //bind widget
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        radioGroup = (RadioGroup) findViewById(R.id.radAvatar);
        avatar0RadioButton = (RadioButton) findViewById(R.id.radioButton);
        avatar1RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        avatar2RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        avatar3RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        avatar4RadioButton = (RadioButton) findViewById(R.id.radioButton5);


        //radio controller
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        avatarString = "0";
                        break;
                    case R.id.radioButton2:
                        avatarString = "1";
                        break;
                    case R.id.radioButton3:
                        avatarString = "2";
                        break;
                    case R.id.radioButton4:
                        avatarString = "3";
                        break;
                    case R.id.radioButton5:
                        avatarString = "4";
                        break;
                }//switch
            }//onchecked
        });


    }//main mothod

    public void clickSignUpSign(View view) {
        //get value drom edit text
        nameString = nameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        //chech space

        if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
            // havve space
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "ผิดพลาด", "กรุณากรอกข้อมูลให้ครบทุกช่อง");
        } else if (checkChooseAvatar()) {
            confirmData();
        } else {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"กรุณาเลือก Avatar",
                    "กรุณาเลือก Avatar ด้วย");
        }//if

    }//clicksignupsign


    private void confirmData() {
        MyData myData = new MyData();
        int[] avatarInts = myData.getAvatarInts();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(avatarInts[Integer.parseInt(avatarString)]);
        builder.setTitle(nameString);
        builder.setMessage("User = " + userString + "\n" + "Password = " + passwordString);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                upLloadUserToServer();
            }
        });
        builder.show();
    }

    private void upLloadUserToServer() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Name", nameString)
                .add("User", userString)
                .add("Password", passwordString)
                .add("Avata", avatarString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(urlPHP).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                finish();
            }
        });

    }

    private boolean checkChooseAvatar() {
        boolean status = true;
        if (avatar0RadioButton.isChecked() ||
                avatar1RadioButton.isChecked() ||
                avatar2RadioButton.isChecked() ||
                avatar3RadioButton.isChecked() ||
                avatar4RadioButton.isChecked()) {
            //have checked
            status = true;
        } else {
            status = false;
        }//if
        return status;
    }

}//main class
