package com.ldj.wow.contacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ldj.wow.contacts.Contacter.ContacterShow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class HotelActivity extends AppCompatActivity{
    private ImageView go_back_main;
    ImageView imageView;
    String res;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x22:
                    imageView.setVisibility(View.VISIBLE);
                    String fileName ="qr_" + System.currentTimeMillis() + ".jpg";/*文件名称=qr_系统时间.jpg*/
                    File file = getFileRoot(fileName);
                    Bitmap bitmap=createQRImage(res
                            , imageView,200,200);/*二维码信息:Welcome!This is a QR_code*/
                    break;
                case 0x23:
                    imageView.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "注销成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_main);
        imageView = (ImageView) findViewById(R.id.img_qr);
        imageView.setVisibility(View.VISIBLE);
        String fileName ="qr_" + System.currentTimeMillis() + ".jpg";/*文件名称=qr_系统时间.jpg*/
        File file = getFileRoot(fileName);
        Bitmap bitmap=createQRImage(res, imageView,200,200);/*二维码信息:Welcome!This is a QR_code*/
        //saveImage(HotelActivity.this, bitmap, file, fileName);
        new Thread() {
            public void run() {
                handler.sendEmptyMessage(0x22);
            }
        }.start();
        Intent data=getIntent();
        res= data.getStringExtra("data_return");
        go_back_main = (ImageView) findViewById(R.id.show_back_contact);
        go_back_main.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();  //调用系统返回按钮
                finish();
            }
        });
    }
    //处理扫描后的结果
            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == 101){
                    Bundle bundle = data.getExtras();
                    String returnedData = data.getStringExtra("result");
                    String[] value_col = returnedData.split(",");
                    Intent intent = new Intent(HotelActivity.this, ContacterShow.class);
                    String test="5";//liangshihao!!
                    intent.putExtra("main_id",test);//main_id means test's main_id!!!
                    startActivity(intent);

                }
            }

        //文件存储根目录
        private File getFileRoot(String fileName) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");/*文件保存在手机根目录:Boohee*/
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                File file = new File(appDir, fileName);
                Log.e("路径：", file.toString());
                return file;
            }
            return null;
        }
        //要转换的地址或字符串,可以是中文
        public static Bitmap createQRImage(String url, ImageView sweepIV, int QR_WIDTH, int QR_HEIGHT ) {
            try {//判断URL合法性
                if (url == null || "".equals(url) || url.length() < 1) {
                    return null;
                }
                Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                //图像数据转换，使用了矩阵转换
                BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
                //
                int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
                //
                //下面这里按照二维码的算法，逐个生成二维码的图片，
                //
                //两个for循环是图片横列扫描的结果

                for (int y = 0; y < QR_HEIGHT; y++) {
                    for (int x = 0; x < QR_WIDTH; x++) {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * QR_WIDTH + x] = 0xff000000;
                        }
                        else {
                            pixels[y * QR_HEIGHT + x] = 0xffffffff;
                        }
                    }
                }//生成二维码图片的格式，使用ARGB_8888
                Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
                //显示到一个ImageView上面
                sweepIV.setImageBitmap(bitmap);
                return bitmap;
            }
            catch (WriterException e) {
                e.printStackTrace();
            }
            return null;
        }

        //保存图片
        public static void saveImage(Context context,Bitmap bitmap,File filePath,String fileName){
            try {
                FileOutputStream fos = new FileOutputStream(filePath);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {// 把文件插入到系统图库
                MediaStore.Images.Media.insertImage(context.getContentResolver(),
                        filePath.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + "/storage/emulated/0/Boohee/image.jpg")));
    }
}

