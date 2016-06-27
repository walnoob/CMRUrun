package cmru.kulachart.cmrurun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {

    //explicit
    private EditText nameEditText, passwordEditText , userEditText;
    private RadioGroup radioGroup;
    private RadioButton avatar0RadioButton, avatar1RadioButton, avatar2RadioButton, avatar3RadioButton, avatar4RadioButton, avatar5RadioButton;


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
}//main class
