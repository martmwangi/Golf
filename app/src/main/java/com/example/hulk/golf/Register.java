package com.example.hulk.golf;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Register extends AppCompatActivity {
    private Button btn;
//    For saving the images
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_register);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        final Firebase register = new Firebase("https://golfke.firebaseio.com/");
        final Firebase theregister = register.child("Members");


        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText registerName = (EditText) findViewById(R.id.nametext);
                final EditText registerAge = (EditText) findViewById(R.id.agetext);
                final EditText registerLocation = (EditText) findViewById(R.id.locationtext);
                final ImageView image = (ImageView) findViewById(R.id.imageView);
                final Button post_btn = (Button) findViewById(R.id.button);

                //        Capture user input
                String theName = registerName.getText().toString();
                String  theAge = registerAge.getText().toString();
                String theLocation = registerLocation.getText().toString();
                String theImage = image.toString();


//                Connection to the contructor class i.e. register_con
                Regist_con regist_con = new Regist_con(theName,theLocation,theAge, theImage);

//              Save newly registered members to firebase
                theregister.push().setValue(regist_con, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {

                        if (firebaseError != null) {
                            Toast.makeText(getApplicationContext(), "Registration Failed, check internet connection", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        image = (ImageView) findViewById(R.id.imageView);
//        Loading image from SD card to image view
        image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);

            }
        });

   }
//    Method that handles loaded image
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
        if (requestCode == SELECT_PICTURE) {
            Uri selectedImageUri = data.getData();
            selectedImagePath = getPath(selectedImageUri);
            System.out.println("Image Path : " + selectedImagePath);
            image.setImageURI(selectedImageUri);

        }
    }
}
//    Gets image location
public String getPath(Uri uri) {
    String[] projection = {MediaStore.Images.Media.DATA};
    Cursor cursor = managedQuery(uri, projection, null, null, null);
    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    return cursor.getString(column_index);


}
    //    Method to convert image to base64
    public static String encodeToBase64(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = com.firebase.client.utilities.Base64.encodeBytes(b);
        return imageEncoded;
    }

    public static Bitmap decodeFromBase64(String input) throws IOException, IOException {
        byte[] decodedByte = com.firebase.client.utilities.Base64.decode(input);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }


}
