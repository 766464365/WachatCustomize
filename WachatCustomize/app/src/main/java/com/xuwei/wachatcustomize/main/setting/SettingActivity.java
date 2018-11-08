package com.xuwei.wachatcustomize.main.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.xuwei.framework.utils.ToastUtils;
import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.util.SharepreferenceUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private File tempFile;
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";

    @BindView(R.id.tv_label)
    TextView tvLabel;
    @BindView(R.id.et_label)
    EditText etLabel;
    @BindView(R.id.btn_text_ok)
    Button btnOk;
    @BindView(R.id.et_url)
    EditText etUrl;
    @BindView(R.id.btn_url_ok)
    Button btnUrlOk;
    @BindView(R.id.switch_url)
    Switch switchUrl;
    @BindView(R.id.et_likeNum)
    EditText etLikeNum;
    @BindView(R.id.btn_likeNum_ok)
    Button btnLikeNumOk;
    @BindView(R.id.swtich_likeNum)
    Switch swtichLikeNum;
    @BindView(R.id.spiner)
    Spinner spiner;
    @BindView(R.id.btn_choose_pic)
    Button btnChoosePic;
    @BindView(R.id.iv_choose_pic)
    ImageView ivChoosePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
        //读取图片
        getBitmapFromSharedPreferences();
    }

    private void initView() {
        etLabel.setText(SharepreferenceUtil.readStrFromPre("postStr") == null ? "请输入正确票圈文字" : SharepreferenceUtil.readStrFromPre("postStr"));
        etUrl.setText(SharepreferenceUtil.readStrFromPre("postUrl") == null ? "请输入正确票圈链接指向" : SharepreferenceUtil.readStrFromPre("postUrl"));
        etLikeNum.setText(SharepreferenceUtil.readStrFromPre("likeNum") == null ? "请输入正确票圈点赞数" : SharepreferenceUtil.readStrFromPre("likeNum"));
        switchUrl.setChecked((SharepreferenceUtil.readStatusFromPre("urlAble") == true ? true : false));
        swtichLikeNum.setChecked((SharepreferenceUtil.readStatusFromPre("likeAble") == true ? true : false));
        //下拉框监听事件   注意position从0开始
        if (SharepreferenceUtil.readStrFromPre("model") != null) {
            switch (SharepreferenceUtil.readStrFromPre("model")) {
                case "0":
                    spiner.setSelection(0);
                    break;
                case "1":
                    spiner.setSelection(1);
                    break;
                case "2":
                    spiner.setSelection(2);
                    break;
                default:
                    break;
            }
        } else {
            ToastUtils.getInstance(getApplicationContext()).show("还未设置默认模式");
        }
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        SharepreferenceUtil.saveStrToPre("model", "0");
                        ToastUtils.getInstance(getApplicationContext()).show("设置为模式" + position);
                        break;
                    case 1:
                        SharepreferenceUtil.saveStrToPre("model", "1");
                        ToastUtils.getInstance(getApplicationContext()).show("设置为模式" + position);
                        break;
                    case 2:
                        SharepreferenceUtil.saveStrToPre("model", "2");
                        ToastUtils.getInstance(getApplicationContext()).show("设置为模式" + position);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick({R.id.btn_text_ok, R.id.btn_url_ok, R.id.switch_url, R.id.btn_likeNum_ok, R.id.swtich_likeNum,R.id.btn_choose_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_text_ok:      //票圈文字按钮
                if (!etLabel.getText().toString().trim().equals("")) {
                    //设置票圈显示文字
                    SharepreferenceUtil.saveStrToPre("postStr", etLabel.getText().toString().trim());
                    ToastUtils.getInstance(this).show("设置成功");
                } else {
                    ToastUtils.getInstance(this).show("请输入正确票圈文字");
                }
                break;
            case R.id.btn_url_ok:           //票圈链接指向
                if (!etUrl.getText().toString().trim().equals("")) {
                    //设置票圈显示文字
                    SharepreferenceUtil.saveStrToPre("postUrl", etUrl.getText().toString().trim());
                    ToastUtils.getInstance(this).show("设置成功");
                } else {
                    ToastUtils.getInstance(this).show("请输入正确票圈链接指向");
                }
                break;
            case R.id.btn_likeNum_ok:       //票圈点赞数确定按钮
                if (!etLikeNum.getText().toString().trim().equals("")) {
                    //设置票圈显示文字
                    SharepreferenceUtil.saveStrToPre("likeNum", etLikeNum.getText().toString().trim());
                    ToastUtils.getInstance(this).show("设置成功");
                } else {
                    ToastUtils.getInstance(this).show("请输入正确票圈点赞数");
                }
                break;
            case R.id.swtich_likeNum:       //票圈点赞开关
                boolean likeStatus = (swtichLikeNum.isChecked() ? true : false);
                SharepreferenceUtil.saveStatusToPre("likeAble", likeStatus);
                ToastUtils.getInstance(this).show("设置开启点赞功能的标致为" + likeStatus);
                break;
            case R.id.switch_url:           //票圈开启链接指向开关
                boolean status = (switchUrl.isChecked() ? true : false);
                SharepreferenceUtil.saveStatusToPre("urlAble", status);
                ToastUtils.getInstance(this).show("设置url指向的标致为" + status);
                break;
            case R.id.btn_choose_pic:
                ToastUtils.getInstance(this).show( "正在打开图库");
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        }
        else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                /**
                 * 获得图片
                 */
                ivChoosePic.setImageBitmap(bitmap);
                //保存到SharedPreferences
                saveBitmapToSharedPreferences(bitmap);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        if(SharepreferenceUtil.readStrFromPre("model").equals("1")){
            // 裁剪框的比例，1：1
            intent.putExtra("aspectX",SharepreferenceUtil.readStrFromPre("model").equals("1")? 3: 1);
            intent.putExtra("aspectY", 1);
            // 裁剪后输出图片的尺寸大小
            intent.putExtra("outputX", SharepreferenceUtil.readStrFromPre("model").equals("1")? 750:250);
            intent.putExtra("outputY", 250);
        }

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    //保存图片到SharedPreferences
    private void saveBitmapToSharedPreferences(Bitmap bitmap) {
        // Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //存储到本地
        File file =new File(Environment.getExternalStorageDirectory()+"20180602");
        FileOutputStream out;
        try{
            out=new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream));
            {
                out.flush();
                out.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("settingInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", imageString);
        editor.commit();
    }

    //从SharedPreferences获取图片
    private  void getBitmapFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("settingInfo", Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString = sharedPreferences.getString("image", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
        if (byteArray.length == 0) {
            ivChoosePic.setImageResource(R.mipmap.ic_launcher);
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

            //第三步:利用ByteArrayInputStream生成Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            ivChoosePic.setImageBitmap(bitmap);
        }
    }
}
