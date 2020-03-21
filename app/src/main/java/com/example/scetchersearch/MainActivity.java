package com.example.scetchersearch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;    //ImageView 불러옴
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import android.view.View.OnClickListener;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_main,null);
        addContentView(v , new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));

        Button button = v.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeScreenshot();
            }
        });


    }





//    //캡쳐버튼클릭
//    public void mOnCaptureClick(View v){
//        Toast.makeText(getApplicationContext(), "화면 캡쳐", Toast.LENGTH_LONG).show();
//
//        //전체화면
//        View rootView = getWindow().getDecorView();
//
//        File screenShot = ScreenShot(rootView);
//        if (screenShot != null) {
//            //갤러리에 추가
//            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(screenShot)));
//        }
//    }
//
//
//    //화면 캡쳐하기
//    public File ScreenShot(View view){
//        view.setDrawingCacheEnabled(true);  //화면에 뿌릴때 캐시를 사용하게 한다
//
//        Bitmap screenBitmap = view.getDrawingCache();   //캐시를 비트맵으로 변환
//
//        String filename = "screenshot.png";
//        File file = new File(Environment.getExternalStorageDirectory().toString()+ "/Android", filename);  //Pictures폴더 screenshot.png 파일
//        FileOutputStream os = null;
//        try{
//            os = new FileOutputStream(file);
//            screenBitmap.compress(Bitmap.CompressFormat.PNG, 90, os);   //비트맵을 PNG파일로 변환
//            os.close();
//        }catch (IOException e){
//            e.printStackTrace();
//            return null;
//        }
//
//        view.setDrawingCacheEnabled(false);
//        return file;
//    }

//    @Override
//    public void onClick(View arg0) {
//        Bitmap screenshot = CaptureUtil.captureScreen(findViewById(android.R.id.content), getApplicationContext());
//        if(screenshot != null){
//            try {
//                CaptureUtil.saveImage(screenshot);
//            } catch (IOException e) {
//                e.printStackTrace()
//            }
//        }
//    }



    //그림판 생성
    public class MyView extends View{
        Paint paint = new Paint();
        boolean isDrawing = false;
        Path path = new Path();

        MyView(Context context)
        {
            super(context);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(8);
        }

        @Override
        public void onDraw(Canvas canvas)
        {
            canvas.drawARGB(255,0,255,204);
            canvas.drawPath(path, paint);
        }
        public boolean onTouchEvent(MotionEvent event){
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(event.getX(),event.getY());
                    isDrawing = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(isDrawing){
                        path.lineTo(event.getX(), event.getY());
                        invalidate();
                    }break;
                case MotionEvent.ACTION_UP:
                    isDrawing = false;
                    break;
                default:
                    break;
            }
            return true;
        }

    }//그림판 생성



    //이미지 저장
    private void takeScreenshot() {

        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            Toast.makeText(getApplicationContext(),"화면캡쳐",Toast.LENGTH_SHORT).show();

            // image naming and path  to include sd card  appending name you choose for file
            // 저장할 주소 + 이름
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            // 화면 이미지 만들기
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            // 이미지 파일 생성
            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }//이미지 저장









//        Button button = (Button)findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "다음 페이지입니다.", Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(MainActivity.this, ScetchActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });


}
