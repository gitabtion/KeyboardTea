package cn.abtion.keyboardtea.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author abtion.
 * @since 17/8/12 16:24.
 * email caiheng@hrsoft.net
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        int layoutId = getLayoutId();
        setContentView(layoutId);
        initWindows();
        initData();
        initWidget();
    }

    protected abstract void initWidget();

    protected abstract void initData();

    protected abstract void initWindows();

    protected abstract int getLayoutId();
}
