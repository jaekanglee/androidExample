package com.example.gifsaveproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.gifsaveproject.databinding.ActivityMainBinding;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String url = "https://media.giphy.com/media/WnIu6vAWt5ul3EVcUE/giphy.gif";
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        compositeDisposable = new CompositeDisposable();


        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();


    }

    public void loadFile() {
        try {
            //글라이드를 통해 파일 다운로드
            RequestManager requestManager = Glide.with(this);
            File file = requestManager.downloadOnly().load(url).submit().get();
            Log.e("파일사이즈", "" + file.length());
            saveFile(file);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String filePath = "gifTest/folder/";
    private String TAG = "로그";

    public void saveFile(File file) {

        File localFile = new File(Environment.getExternalStoragePublicDirectory(filePath).getPath());
        if (!localFile.exists()) {// 디렉토리가 있는지 없는지 부터 체크
            localFile.mkdirs(); //없으면 생성
        }
        String filepath = Environment.getExternalStoragePublicDirectory(filePath).getPath() + System.currentTimeMillis() + ".gif";
        localFile = new File(filepath); // 만들고자 하는 파일패스 + 네임 객체 할당

        try {

            InputStream
                    is = new FileInputStream(file);

            Log.d(TAG, "on do in background, url get input stream");
            BufferedInputStream bis = new BufferedInputStream(is);
            Log.d(TAG, "on do in background, create buffered input stream");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Log.d(TAG, "on do in background, create buffered array output stream");

            byte[] img = new byte[1024];

            int current = 0;

            Log.d(TAG, "on do in background, write byte to baos");
            while ((current = bis.read()) != -1) {
                baos.write(current);
            }


            Log.d(TAG, "on do in background, done write");

            Log.d(TAG, "on do in background, create fos");
            FileOutputStream fos = new FileOutputStream(localFile);
            fos.write(baos.toByteArray());

            Log.d(TAG, "on do in background, write to fos");
            fos.flush();

            fos.close();
            is.close();
            Log.d(TAG, "on do in background, done write to fos");
            scanMedia(); //미디어 스캔을 해줘야 , 시스템에서 바로 반영됨
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {

            compositeDisposable.add(
                    Completable.fromAction(() -> loadFile())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(() -> Toast.makeText(MainActivity.this, "완료", Toast.LENGTH_SHORT).show()));
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "권한승인해주셈", Toast.LENGTH_LONG).show();
        }
    };

    public void scanMedia() {
        //객체만 초기화해주는 것으로도 스캔이 전달됨
        MediaScannerConnection.MediaScannerConnectionClient mScanClient = new MediaScannerConnection.MediaScannerConnectionClient() {

            public void onMediaScannerConnected() {

            }

            public void onScanCompleted(String path, Uri uri) {

            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}


//