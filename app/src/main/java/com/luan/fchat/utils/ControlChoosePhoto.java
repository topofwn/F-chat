package com.luan.fchat.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;

import com.luan.fchat.BuildConfig;
import com.luan.fchat.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ControlChoosePhoto {

    protected static final int GALLERY_PICTURE = 1;
    protected static final int CAMERA_REQUEST = 0;
    protected static final int PIC_CROP = 2;
    private final int WRITE_EXTERNAL_STORAGE_CODE = 100;
    private final int CAMERA_CODE = 101;
    private final String[] ALL_PERMISSIONS = new String[]{"android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
    private final String[] STORAGE_PERMISSIONS = new String[]{"android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    private Uri imageUri;
    private Bitmap mBitmap;
    private Activity mAct;
    private ImageView mImagePhoto;
    private SelectImageListener imageListener;
    private int ratioX = 1, ratioY = 1;

    public ControlChoosePhoto(Activity act, ImageView img) {
        mAct = act;
        mImagePhoto = img;
    }

    public ControlChoosePhoto(Activity act) {
        mAct = act;
    }

    public void doShowGallery() {
        if (ActivityUtils.isPermissionListGranted(mAct, STORAGE_PERMISSIONS)) {
            showGallery();
        } else {
            ActivityUtils.addPermissionList(mAct, STORAGE_PERMISSIONS, WRITE_EXTERNAL_STORAGE_CODE);
        }
    }

    public void setImageListener(SelectImageListener imageListener) {
        this.imageListener = imageListener;
    }

    public void doImageCapture() {
        if (ActivityUtils.isPermissionListGranted(mAct, ALL_PERMISSIONS)) {
            imageCapture();
        } else {
            ActivityUtils.addPermissionList(mAct, ALL_PERMISSIONS, CAMERA_CODE);
        }
    }

    public void clearBitmap() {
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    private void showGallery() {
        Intent pictureActionIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mAct.startActivityForResult(pictureActionIntent, GALLERY_PICTURE);
    }

    private void imageCapture() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageUri = FileProvider.getUriForFile(mAct,
                        BuildConfig.APPLICATION_ID + ".fileprovider",
                        createImageFile());
            } else {
                File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
                imageUri = Uri.fromFile(file);
            }
            //use standard intent to capture an image
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            //we will handle the returned data in onActivityResult
            mAct.startActivityForResult(captureIntent, CAMERA_REQUEST);
        } catch (RuntimeException | IOException e) {
            OGILVYLog.l(e);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "image_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }

    private void performCrop(Uri url) {
        Intent intent2 = CropImage.activity(url)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(ratioX, ratioY)
                .setFixAspectRatio(true)
                .getIntent(mAct);
        mAct.startActivityForResult(intent2, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
    }


    private Bitmap rotateImage(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();
        matrix.postRotate(orientation);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return bitmap1;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_CODE:
                imageCapture();
                break;
            case WRITE_EXTERNAL_STORAGE_CODE:
                showGallery();
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST:
                    handleCameraRequest();
                    break;
                case GALLERY_PICTURE:
                    if (data != null) {
                        handleGalleryData(data);
                    }
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    handleCropImageRequest(data);
                    break;
                case PIC_CROP:
                    handleImageCropBySystem(data);
            }
        }
    }

    private void handleImageCropBySystem(Intent data) {
        try {
            Bundle extras = data.getExtras();
            if (extras != null && imageUri != null) {
                doRotation(extras);
            }
            if (mImagePhoto != null) {
                mImagePhoto.setImageBitmap(mBitmap);
            }
            if (imageListener != null) {
                imageListener.onSelectImageDone();
            }
        } catch (RuntimeException | IOException ex) {
            OGILVYLog.logEx(ex, ControlChoosePhoto.class);
        }
    }

    private void handleCropImageRequest(Intent data) {
        CropImage.ActivityResult result = CropImage.getActivityResult(data);
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
        imageUri = result.getUri();
        try {
            mBitmap = MediaStore.Images.Media.getBitmap(mAct.getContentResolver(), imageUri);
        } catch (IOException e) {
            OGILVYLog.l(e);
            UIUtils.showToast(mAct, StringUtils.getStringBaseOnLanguage(mAct, R.string.error_select_avatar));
            return;
        }
        mBitmap = IOUtils.getResizedBitmap(mBitmap, 256);
        if (mImagePhoto != null) {
            mImagePhoto.setImageBitmap(mBitmap);

        }
        if (imageListener != null) {
            imageListener.onSelectImageDone();
        }
    }

    private void handleGalleryData(Intent data) {
        Cursor cursor = mAct.getContentResolver().query(data.getData(), null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String fileSrc = cursor.getString(idx);
            Uri uri = Uri.fromFile(new File(fileSrc));
            if (uri != null && !Uri.EMPTY.equals(uri)) {
                performCrop(uri);
            } else {
                UIUtils.showToast(mAct, StringUtils.getStringBaseOnLanguage(mAct, R.string.failed_to_crop_pic));
            }
        } else {
            try {
                performCrop(data.getData());
            } catch (RuntimeException ex) {
                OGILVYLog.l(ex);
            }
        }
    }

    private void handleCameraRequest() {
        if (imageUri != null && !Uri.EMPTY.equals(imageUri)) {
//                        if (getBitmapFromCamera(data)) return;
//                        if (rotateBitmapToCorrect()) return;
            if (imageUri != null) {
                performCrop(imageUri);
            }
        } else {
            UIUtils.showToast(mAct, StringUtils.getStringBaseOnLanguage(mAct, R.string.failed_to_crop_pic));
        }
    }

    private void doRotation(Bundle extras) throws IOException {
        mBitmap = extras.getParcelable("data");
        ExifInterface ei;
        ei = new ExifInterface(imageUri.getPath());
        int orientation = ei.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                mBitmap = rotateImage(mBitmap, 90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                mBitmap = rotateImage(mBitmap, 180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                mBitmap = rotateImage(mBitmap, 270);
                break;
            // etc.
        }
    }

    public void showChooseImageDialog() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(mAct);
        myAlertDialog.setTitle(StringUtils.getStringBaseOnLanguage(mAct, R.string.upload_pictures_option));
        myAlertDialog.setMessage(StringUtils.getStringBaseOnLanguage(mAct, R.string.how_do_you_want_to_set_your_picture));
        myAlertDialog.setPositiveButton(StringUtils.getStringBaseOnLanguage(mAct, R.string.gallery),
                (arg0, arg1) -> doShowGallery());
        myAlertDialog.setNegativeButton(StringUtils.getStringBaseOnLanguage(mAct, R.string.camera),
                (arg0, arg1) -> doImageCapture());
        myAlertDialog.show();
    }

    public interface SelectImageListener {
        void onSelectImageDone();
    }

    public Uri getURI() {
        return imageUri;
    }

    public void setCropRatio(int x, int y) {
        ratioX = x;
        ratioY = y;
    }
}
