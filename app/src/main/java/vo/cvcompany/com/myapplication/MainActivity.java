package vo.cvcompany.com.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import vo.cvcompany.com.myapplication.Activity.Main2;
import vo.cvcompany.com.myapplication.Module.Mysqlite;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etDescription)
    EditText etDescription;
    @BindView(R.id.imgTakePicture)
    ImageView imgTakePicture;
    @BindView(R.id.imgPicture)
    ImageView imgPicture;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btnCancel)
    public void btncancel(){
        etName.setText("");
        etDescription.setText("");
    }

    @OnClick(R.id.btnActivity2)
    public void btnActivity2(){
        Intent intent = new Intent(this, Main2.class);
        startActivity(intent);
    }

    public void checkpermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        200);
        }else{
            Intent intent  =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
            Log.i(TAG, "imgTakePicture: 123213213213213" );
        }
    }

    @OnTextChanged(R.id.etDescription)
    public void etDescription(){

        etDescription.setBackgroundResource(R.drawable.sd);
    }
    @OnTextChanged(R.id.etName)
    public void etName(){
        etName.setBackgroundResource(R.drawable.sd);
    }
    @OnClick(R.id.btnAdd)
    public void btnAdd(){
        String name = etName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        byte[] bytes;
           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();

        if(name.length()==0 && description.length()==0){
            etName.setBackgroundResource(R.drawable.red);
            etDescription.setBackgroundResource(R.drawable.red);
            Toast.makeText(this, "Name and Description are null", Toast.LENGTH_SHORT).show();
            return;
        }else
        if(name.length()==0 && description.length()>0){
            etName.setBackgroundResource(R.drawable.red);
            Toast.makeText(this, "Name is null", Toast.LENGTH_SHORT).show();
            return;
        }else
        if(description.length()==0 && name.length()>0){
            etDescription.setBackgroundResource(R.drawable.red);
            Toast.makeText(this, "Description is null", Toast.LENGTH_SHORT).show();
            return;
        }
        long a = Mysqlite.getMysqlite(this).insert(name, description,bytes);
        Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();

    }
    @OnClick(R.id.imgPicture)
    public void imgPicture(){
        Intent intent  = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode== RESULT_OK && data!=null){
            Uri imageUri =  data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgPicture.setImageURI(imageUri);
        }
    }

    @OnClick(R.id.imgTakePicture)
    public void imgTakePicture(){
        checkpermission();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent  =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                    Log.i(TAG, "imgTakePicture: 123213213213213" );

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }
}
