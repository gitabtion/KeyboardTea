package cn.abtion.keyboardtea.chat;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.abtion.keyboardtea.R;
import cn.abtion.keyboardtea.base.activity.BaseToolBarActivity;

/**
 * @author abtion.
 * @since 17/10/23 21:05.
 * email caiheng@hrsoft.net.
 */

public class ContactListActivity extends BaseToolBarActivity {
    @BindView(R.id.rec_contact)
    RecyclerView recContact;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_list;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
