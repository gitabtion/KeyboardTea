package cn.abtion.keyboardtea;


import android.content.Intent;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.keyboardtea.base.activity.BaseActivity;
import cn.abtion.keyboardtea.main.activity.TextViewActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void initWidget() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initWindows() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }



    @OnClick(R.id.text_view)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setClass(this, TextViewActivity.class);
        startActivity(intent);
    }
}
