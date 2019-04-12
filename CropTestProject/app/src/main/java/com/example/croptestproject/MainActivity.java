package com.example.croptestproject;

import android.Manifest;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.croptestproject.utils.ItemClickCallback;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int imageWidth = 1000;
    private int imageHeight = 1000;
    private ImageCropView imageView;
    private FrameLayout frameLayout;
    private List<Uri> pathList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private TextView textView;
    private ItemClickCallback clickCallback = new ItemClickCallback() {
        @Override
        public void Click(int pos) {
            Log.e("클릭 포지션", "" + pos);
            imageView.setImageFilePath(pathList.get(pos).toString());
            //  imageView.setAspectRatio(1, 1);
            // Glide.with(imageView.getContext()).load(pathList.get(pos).getPath()).into(imageView);
        }
    };

    public void setCropViewHeight() {
        DisplayMetrics disp = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(disp);
        Integer rwidth = disp.widthPixels; // 화면의 가로 해상도 구하기
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) frameLayout.getLayoutParams();
        layoutParams.height = rwidth;
        frameLayout.setLayoutParams(layoutParams);
        imageView.setAspectRatio(1, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageCropView) findViewById(R.id.cropView);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        //imageView.restoreState();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        textView = findViewById(R.id.txtGallary);
        textView.setOnClickListener(this);
        setCropViewHeight();

        // getMatrix();
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();

    }


    private void loadAsync() {
        Log.i("loadAsync", "loadAsync: $uri");

        if (imageView.getDrawable() != null && imageView.getDrawable() instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
            bitmapDrawable.getBitmap().recycle();
            //
        }

        AsyncTask<Void, Void, Bitmap> task = new DownloadAsync();
        task.execute();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.recyclerview:
                break;
            case R.id.txtGallary:
                setCrop();
                break;
        }
    }

    public class DownloadAsync extends AsyncTask<Void, Void, Bitmap> implements DialogInterface.OnCancelListener {
        private Uri uri;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapter = new ImageAdapter();
            adapter.setCallback(clickCallback);
        }

        @Override
        public void onCancel(DialogInterface dialog) {

        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            //  Bitmap bitmap = BitmapLoadUtils.decode(uri.toString(), imageWidth, imageHeight, true);
            long start = System.currentTimeMillis();
            adapter.setList(pathList);
            long end = System.currentTimeMillis();
            Log.e("doInBackground 걸린 시간", "" + (end - start) / 1000);
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            setImageURI(pathList.get(0));
            GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 4);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }
    }

    private void setImageURI(Uri uri) {
        // Log.d("Size", "image size: " + bitmap.getWidth() + "x" + bitmap.getHeight());
        imageView.setImageFilePath(uri.toString());

        // Glide.with(imageView.getContext()).load(uri.getPath()).into(imageView);

        //   mEditButton ?.isEnabled = true
    }


    private void pickRandomImage() {

        String[] mediaArray = new String[2];
        mediaArray[0] = MediaStore.Images.ImageColumns._ID;
        mediaArray[1] = MediaStore.Images.ImageColumns.DATA;

        String[] tempArray = new String[1];
        tempArray[0] = "90000";


        Cursor c = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, mediaArray,
                MediaStore.Images.ImageColumns.SIZE + ">?", tempArray, null);
        Uri uri = null;

        if (c != null) {
            int total = c.getCount();
            int position = (int) (Math.random() * total);
            Log.d("태그", "pickRandomImage. total images: $total, position: $position");
            int count = 0;
            long start = System.currentTimeMillis();
            while (count < total) {
                c.moveToNext();
                Uri insieUri = Uri.parse(c.getString(c.getColumnIndex(MediaStore.Images.ImageColumns.DATA)));
                //   Bitmap bitmap = BitmapLoadUtils.decode(insieUri.toString(), 150, 150, true);
                Log.e("이미지 Uri", "" + insieUri);
                pathList.add(insieUri);
           /*     if (count==position) {
                    String data = c.getString(c.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
                    uri = Uri.parse(data);
                    Log.d("태크", uri.toString());
                }*/
                count++;
            }
            c.close();
        }


    }


    //기본 권한 모두 동의 후  노티 리스너 알림 획득 권한 추가 질의
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    pickRandomImage();
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    loadAsync();

                }
            }.execute();


        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "권한승인해주셈", Toast.LENGTH_LONG).show();
        }
    };


    public void setCrop() {
        if (!imageView.isChangingScale()) {
            Bitmap b = imageView.getCroppedImage();
            if (b != null) {
                bitmapConvertToFile(b);
            } else {
                Toast.makeText(MainActivity.this, "실패했삼", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public File bitmapConvertToFile(Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        File bitmapFile = null;
        try {
            File file = new File("/sdcard/", "");
            if (!file.exists()) {
                file.mkdir();
            }

            bitmapFile = new File(file, "IMG_" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime()) + ".jpg");
            fileOutputStream = new FileOutputStream(bitmapFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            MediaScannerConnection.scanFile(this, new String[]{bitmapFile.getAbsolutePath()}, null, new MediaScannerConnection.MediaScannerConnectionClient() {
                @Override
                public void onMediaScannerConnected() {

                }

                @Override
                public void onScanCompleted(String path, Uri uri) {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "file saved", Toast.LENGTH_LONG).show());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
        }

        return bitmapFile;
    }


    public void getMatrix() {
        Integer rwidth, rheight, hgt, max, min, gcd, temp, war, har;

        DisplayMetrics disp = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(disp);
        rwidth = disp.widthPixels; // 화면의 가로 해상도 구하기
        //  rheight = disp.heightPixels;      // 화면의 세로 해상도 구하기
        rheight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 360f, disp);
        if (rwidth < rheight) {                // 화면의 가로, 세로 크기 비교
            max = rwidth;          // 큰쪽을 max로
            min = rheight;
        } else {
            max = rheight;
            min = rwidth;
        }
        while (max % min != 0) {      // 나머지가 0이 될 때까지
            temp = max % min;            // max/min의 나머지를 temp로
            max = min;
            // min이 나눠지는 수로
            min = temp;
            // temp가 나누는 수로 바뀌어 다시 나머지 계산 }
        }
        gcd = min;      // 최종적으로 나온 나누는 수가 바로 최대공약수
        war = rwidth / gcd;      // 화면의 가로/최대공약수 = 가로비율
        har = rheight / gcd;      // 화면의 세로/최대공약수 = 세로비율

        Log.e("가로세로 비율", war + ":/" + har);
      /*  if (WIDTH < war && HEIGHT < har) {
            SharedPreferenceBase.getInstance().setBoolean("matrix", true);
        } else {
            SharedPreferenceBase.getInstance().setBoolean("matrix", false);
        }*/

    }

}
