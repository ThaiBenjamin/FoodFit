package com.example.foodfit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import android.graphics.drawable.BitmapDrawable;

public class APIActivity extends FragmentActivity {
    private Button captureButton;
    private Button apiRequestButton;
    private ImageView capturedImage;
    private TextView apiResultTextView;
    private static final String TAG = "APIActivity";

    private final ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle extras = result.getData().getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    capturedImage.setImageBitmap(imageBitmap);
                }
            });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiactivity);
        requestCameraPermission();
        captureButton = findViewById(R.id.captureButton);
        apiRequestButton = findViewById(R.id.apiRequestButton);
        capturedImage = findViewById(R.id.capturedImage);
        apiResultTextView = findViewById(R.id.apiResultTextView);

        captureButton.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mGetContent.launch(takePictureIntent);
        });

        apiRequestButton.setOnClickListener(v -> {
            Bitmap imageBitmap = ((BitmapDrawable) capturedImage.getDrawable()).getBitmap();
            if (imageBitmap != null) {
                fetchData(imageBitmap);
            }
        });
    }

    private void fetchData(Bitmap imageBitmap) {
        Executors.newSingleThreadExecutor().execute(() -> {
            File file = bitmapToFile(imageBitmap);
            if (file != null) {
                makeApiRequest(file);
            }
        });
    }

    private File bitmapToFile(Bitmap bitmap) {
        File file = new File(getCacheDir(), "tempImage.jpg");
        try {
            file.createNewFile();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, bos);
            byte[] bitmapData = bos.toByteArray();

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(bitmapData);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return file;
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }
    }

    // Define a constant for the request code
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);  // This is the line to add

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted, yay!
                    // You can now access the camera
                } else {
                    // Permission denied, boo!
                    // Disable the functionality that depends on this permission.
                    Toast.makeText(this, "Camera permission is required to capture images.", Toast.LENGTH_LONG).show();
                }
                return;
            }
            // Other case lines to check for other permissions this app might request.
        }
    }


    private void makeApiRequest(File file) {
        try {
            String url = "https://api.someDomain.com/v2/image/recognition/type/v1.0";
            String apiKey = "b33ff2b0675815f3b4b8cb0c1a0f265e3eac45a5";  // Get this value securely, don't hardcode

            HttpResponse<String> response = Unirest.post(url)
                    .header("Authorization", apiKey)
                    .queryString("language", "eng")
                    .field("image", file)
                    .asString();

            runOnUiThread(() -> {
                if (response != null) {
                    apiResultTextView.setText(response.getBody());
                } else {
                    apiResultTextView.setText("Error fetching data from API.");
                }
            });

            // Clean up temporary file
            file.delete();

        } catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(() -> apiResultTextView.setText("Error fetching data from API."));
        }
    }
}
