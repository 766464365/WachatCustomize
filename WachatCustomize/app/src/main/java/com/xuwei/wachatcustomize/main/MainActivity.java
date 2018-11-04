package com.xuwei.wachatcustomize.main;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.xuwei.framework.base.BaseActivity;
import com.xuwei.wachatcustomize.R;
import com.xuwei.wachatcustomize.main.fragments.AboutMeFragment;
import com.xuwei.wachatcustomize.main.fragments.FoundFragments;
import com.xuwei.wachatcustomize.main.fragments.StatementFragment;
import com.xuwei.wachatcustomize.main.fragments.WechatFragment;
import com.xuwei.wachatcustomize.main.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.frameLayoutContainer)
    FrameLayout frameLayoutContainer;
    @BindView(R.id.radioBtnWechat)
    RadioButton radioBtnWechat;
    @BindView(R.id.radioBtnContacts)
    RadioButton radioBtnContacts;
    @BindView(R.id.radioBtnPost)
    RadioButton radioBtnPost;
    @BindView(R.id.radioBtnMe)
    RadioButton radioBtnMe;
    @BindView(R.id.radioGroupMenu)
    RadioGroup radioGroupMenu;

    FragmentManager fragmentManager;
    Fragment wechatFragment;
    Fragment contactsFragment;
    Fragment postFragment;
    Fragment aboutMeFragment;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        setStatsBarColor(R.color.bg_f303030);       //设置标题栏颜色
    }


    private void initView() {
        fragmentManager = getSupportFragmentManager();

        for (int i = 0; i < radioGroupMenu.getChildCount(); i++) {
            RadioButton rb = (RadioButton) radioGroupMenu.getChildAt(i);
            setDrawableBond(rb);
        }

        radioGroupMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment temp = null;
                FragmentTransaction trx = fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.radioBtnWechat:
                        if (wechatFragment == null) {
                            wechatFragment = new WechatFragment();
                            trx.add(R.id.frameLayoutContainer, wechatFragment);
                        }
                        temp = wechatFragment;
                        break;
                    case R.id.radioBtnContacts:
                        if (contactsFragment == null) {
                            contactsFragment = new StatementFragment();
                            trx.add(R.id.frameLayoutContainer, contactsFragment);
                        }
                        temp = contactsFragment;
                        break;
                    case R.id.radioBtnPost:
                        if (postFragment == null) {
                            postFragment = new FoundFragments();
                            trx.add(R.id.frameLayoutContainer, postFragment);
                        }
                        temp = postFragment;
                        break;
                    case R.id.radioBtnMe:
                        if (aboutMeFragment == null) {
                            aboutMeFragment = new AboutMeFragment();
                            trx.add(R.id.frameLayoutContainer, aboutMeFragment);
                        }
                        temp = aboutMeFragment;
                        break;
                    default:
                        break;
                }
                if (currentFragment != null) {
                    trx.hide(currentFragment);
                }
                trx.show(temp);
                trx.commitAllowingStateLoss();
                currentFragment = temp;
            }
        });
        radioBtnWechat.setChecked(true);
    }

    private void setDrawableBond(RadioButton rb) {
        Drawable[] drawables = rb.getCompoundDrawables();
        int width = getResources().getDimensionPixelSize(R.dimen.x72);
        drawables[1].setBounds(0, 0, width, width);
        rb.setCompoundDrawables(null, drawables[1], null, null);
    }

}
