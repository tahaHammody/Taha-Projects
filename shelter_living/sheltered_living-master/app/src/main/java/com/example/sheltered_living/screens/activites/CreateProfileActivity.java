package com.example.sheltered_living.screens.activites;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.sheltered_living.AlertDialogUtils;
import com.example.sheltered_living.SqlDataBaseManager;
import com.example.sheltered_living.FirebaseDataBaseManager;
import com.example.sheltered_living.R;
import com.example.sheltered_living.SessionManager;
import com.example.sheltered_living.models.StaffMember;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class CreateProfileActivity extends AppCompatActivity {

    private TextInputEditText fullNameET;
    private TextInputEditText phoneET;
    private AppCompatImageView imageView;
    private Bitmap imageBitmap;
    //private LoadingView loadingView;
    private StorageReference storageRef;

    private Address address;
    private String imageDownloadUrl;

    private ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                    imageBitmap = photo;
                    imageView.setImageBitmap(photo);
                }
            }
    );

    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    try {
                        Uri uri = result.getData().getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        imageBitmap = bitmap;
                        imageView.setImageBitmap(bitmap);
                    } catch (Exception e) {
                    }
                }
            }
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile_layout);

        fullNameET = findViewById(R.id.fullNameET);
        phoneET = findViewById(R.id.phoneET);
        imageView = findViewById(R.id.imageView);
        MaterialButton takePhotoButton = findViewById(R.id.takePhoto);
        MaterialButton galleryButton = findViewById(R.id.addFromGallery);
        MaterialButton saveButton = findViewById(R.id.save);

        saveButton.setOnClickListener(v -> {
            onSaveClicked();
        });

        takePhotoButton.setOnClickListener(v -> {
            openCamera();
        });

        galleryButton.setOnClickListener(v -> {
            openPhotoGallery();
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraActivityResultLauncher.launch(takePictureIntent);
    }

    private void openPhotoGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        galleryActivityResultLauncher.launch(intent);
    }

    private void uploadImageToStorage(Runnable onDone) {
        StorageReference imageRef = storageRef.child("images/" + SessionManager.uid + ".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageData = baos.toByteArray();
        // Upload file to Firebase Storage
        UploadTask uploadTask = imageRef.putBytes(imageData);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                imageDownloadUrl = uri.toString();
                onDone.run();
            });
        }).addOnFailureListener(e -> {
            // Handle failed upload
            Log.e("TAG", "Upload failed: " + e.getMessage());
        });
    }

    private void onSaveClicked() {
        if (checkValidInput()) {
            AlertDialogUtils.showAlertDialogPositiveNegative(this, R.string.please,
                    R.string.are_you_sure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            uploadImageToStorage(CreateProfileActivity.this::createStaffMemberProfile);
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
        } else {
            Toast.makeText(this, getString(R.string.create_profile_invalid_message), Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkValidInput() {
        return !TextUtils.isEmpty(fullNameET.getText()) && !TextUtils.isEmpty(phoneET.getText())&& imageBitmap != null ;
    }

    private void createStaffMemberProfile() {
        StaffMember staffMember = new StaffMember(
                SessionManager.uid,
                fullNameET.getText().toString(),
                FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                phoneET.getText().toString(),
                imageDownloadUrl);

        SessionManager.staffMember = staffMember;
        FirebaseDataBaseManager.createStaffMember(staffMember, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                SqlDataBaseManager.database.saveStaffMember(SessionManager.staffMember);
                Toast.makeText(CreateProfileActivity.this.getApplicationContext(), getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
                goToStaffScreen();
            }
        });
    }

    private void goToStaffScreen() {
        Intent intent = new Intent(this, HomeStaffActivity.class);
        startActivity(intent);
        finish();
    }
}
