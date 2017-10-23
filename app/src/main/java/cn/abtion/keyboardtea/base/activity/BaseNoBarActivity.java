package cn.abtion.keyboardtea.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author abtion.
 * @since 17/10/23 19:58.
 * email caiheng@hrsoft.net.
 */

public abstract class BaseNoBarActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }
}
