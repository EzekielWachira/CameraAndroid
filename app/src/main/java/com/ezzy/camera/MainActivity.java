package com.ezzy.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Camera cameraObject;
    private ShowCamera showCamera;
    private ImageView pic;
    public static Camera isCameraAvailable(){
        Camera object = null;
        try{
            object = Camera.open();
        }catch (Exception e){

        }
        return object;
    }

    private Camera.PictureCallback captureIt = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (bitmap == null){
                Toast.makeText(MainActivity.this, "Not taken", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "Taken", Toast.LENGTH_SHORT).show();
            }
            cameraObject.release();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pic = findViewById(R.id.imageview1);
        cameraObject = isCameraAvailable();
        showCamera = new ShowCamera(this, cameraObject);
        FrameLayout preview = findViewById(R.id.camera_preview);
        preview.addView(showCamera);
    }

    public void snapIt(View v){
        cameraObject.takePicture(null, null,captureIt);
    }


}
