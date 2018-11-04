package com.xuwei.wachatcustomize.main.setting;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.xuwei.framework.utils.ToastUtils;
import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.util.SharepreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        etLabel.setText(SharepreferenceUtil.readStrFromPre("postStr") == null ? "请输入正确票圈文字" : SharepreferenceUtil.readStrFromPre("postStr"));
        etUrl.setText(SharepreferenceUtil.readStrFromPre("postUrl") == null ? "请输入正确票圈链接指向" : SharepreferenceUtil.readStrFromPre("postUrl"));
        etLikeNum.setText(SharepreferenceUtil.readStrFromPre("likeNum") == null ? "请输入正确票圈点赞数" : SharepreferenceUtil.readStrFromPre("likeNum"));
        switchUrl.setChecked((SharepreferenceUtil.readStatusFromPre("urlAble") == true ? true : false));
        swtichLikeNum.setChecked((SharepreferenceUtil.readStatusFromPre("likeAble") == true ? true : false));
        //下拉框监听事件   注意position从0开始
        if(SharepreferenceUtil.readStrFromPre("model")!=null){
            switch (SharepreferenceUtil.readStrFromPre("model")){
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
        }
        else {
            ToastUtils.getInstance(getApplicationContext()).show("还未设置默认模式");
        }
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        SharepreferenceUtil.saveStrToPre("model","0");
                        ToastUtils.getInstance(getApplicationContext()).show("设置为模式"+position);
                        break;
                    case 1:
                        SharepreferenceUtil.saveStrToPre("model","1");
                        ToastUtils.getInstance(getApplicationContext()).show("设置为模式"+position);
                        break;
                    case 2:
                        SharepreferenceUtil.saveStrToPre("model","2");
                        ToastUtils.getInstance(getApplicationContext()).show("设置为模式"+position);
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


    @OnClick({R.id.btn_text_ok, R.id.btn_url_ok, R.id.switch_url, R.id.btn_likeNum_ok, R.id.swtich_likeNum})
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

            default:
                break;
        }
    }
}
