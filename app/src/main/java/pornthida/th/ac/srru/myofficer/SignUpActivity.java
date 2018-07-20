package pornthida.th.ac.srru.myofficer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {

    // Explicit
    private EditText nameEditText, userEditText, passwordEditText;
    private ImageView imageView;
    private Button button;
    private String nameString, userString, passwordString, pathImageString, nameImageString;
    private Uri uri;
    private String tag = "pornthida";
    private boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText3);
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button2);

        //Get Event From Click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get Value From Edit Text
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
                    //Have Space
                    MyAlert myAlert = new MyAlert(SignUpActivity.this, "Have Space", "Please Fill All Blank");
                    myAlert.myDialog();

                } else if (aBoolean){
                    //Non Choose Image
                    MyAlert myAlert = new MyAlert(SignUpActivity.this, "ยังไม่ได้เลือกรูปภาพ", "โปรดเลือกรูปภาพด้วยค่ะ");
                    myAlert.myDialog();
                } else {
                    // Image ok
                }

            }   // onClick
        });

        //Image Controller
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Please Choose App"), 1);

            }   // onClink
        });

    }// Main Method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

                aBoolean = false;

            uri = data.getData();
            showImage(uri);

            pathImageString = findPathImage(uri);
            nameImageString = pathImageString.substring(pathImageString.lastIndexOf("/"));
            Log.d(tag, "path ==>" + pathImageString);
            Log.d(tag, "name ==>" + nameImageString);

        }   // if


    }

    private String findPathImage(Uri uri) {

        String result = null;
        String[] strings = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, strings, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();
            int i = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            result = cursor.getString(i);

        } else {
            result = uri.getPath();
        }


        return result;
    }

    private void showImage(Uri uri) {

        try {

            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            imageView.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}   // Main Class
