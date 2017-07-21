package fakhriimron.com.mits_easyimagechooserdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

import pl.aprilapps.easyphotopicker.EasyImage;

public class MainActivity extends AppCompatActivity {

    private String path = "";
    private ImageView imageView;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.tv_add_photo);
    }

    public void chooseImage(View view) {
        EasyImage.openChooserWithDocuments(this, "pilih gamba sesuka hati !", 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                onPhotoReturned(imageFile);
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photo = EasyImage.lastlyTakenButCanceledPhoto(MainActivity.this);
                    if (photo != null) photo.delete();
                }
            }
        });
    }

    private void onPhotoReturned(File imageFile) {
        if (imageFile != null) {
            Glide.with(this).load(imageFile).crossFade().centerCrop().into(imageView);
            path = imageFile.getAbsolutePath();
            Log.d("isi path image : ", path);
            textView.setVisibility(View.GONE);
        }
    }
}
