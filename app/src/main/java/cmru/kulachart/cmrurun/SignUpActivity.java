package cmru.kulachart.cmrurun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {

    //explicit
    private EditText nameEditText, passwordEditText , userEditText;
    private RadioGroup radioGroup;
    private RadioButton avatar0RadioButton, avatar1RadioButton, avatar2RadioButton, avatar3RadioButton, avatar4RadioButton, avatar5RadioButton;
    private String nameString, userString, passwordString;

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
        avatar1RadioButton = (RadioButton) findViewById(R.id.radioButton1);
        avatar2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        avatar3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        avatar4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        avatar5RadioButton = (RadioButton) findViewById(R.id.radioButton5);

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
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่อง");
        }
    }//clicksignupsign

}//main class
