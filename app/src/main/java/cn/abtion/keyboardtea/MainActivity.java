package cn.abtion.keyboardtea;


import android.content.Intent;

import butterknife.OnClick;
import cn.abtion.keyboardtea.base.activity.BaseToolBarActivity;
import cn.abtion.keyboardtea.chat.activities.ContactListActivity;
import cn.abtion.keyboardtea.chat.activities.LoginActivity;
import cn.abtion.keyboardtea.main.activity.TextViewActivity;


/**
 * @author abtion.
 * @since 17/10/23 21:05.
 * email caiheng@hrsoft.net.
 */
public class MainActivity extends BaseToolBarActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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


    @OnClick(R.id.btn_text_view)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setClass(this, TextViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_chat)
    public void onChatBtnClicked(){
        startActivity(new Intent(this, LoginActivity.class));
    }
}
