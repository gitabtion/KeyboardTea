package cn.abtion.keyboardtea;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.hyphenate.chat.EMClient;

import butterknife.OnClick;
import cn.abtion.keyboardtea.base.activity.BaseToolBarActivity;
import cn.abtion.keyboardtea.chat.activities.ContactListActivity;
import cn.abtion.keyboardtea.chat.activities.LoginActivity;
import cn.abtion.keyboardtea.main.activity.TextViewActivity;
import cn.abtion.keyboardtea.util.ToastUtil;


/**
 * @author abtion.
 * @since 17/10/23 21:05.
 * email caiheng@hrsoft.net.
 */
public class MainActivity extends BaseToolBarActivity {
    private DrawerLayout menuDrawer;
    private NavigationView navigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable() {
        menuDrawer = findViewById(R.id.menu_drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setCheckable(true);
                switch (item.getItemId()) {
                    case R.id.nav_textview:
                        onViewClicked();
                        break;
                    case R.id.nav_chat:
                        onChatBtnClicked();
                        break;
                    default:
                        Log.e("error", "there is no choice");
                }
                menuDrawer.closeDrawers();
                ToastUtil.showToast("test");
                return true;
            }
        });
    }

    @Override
    protected void initView() {
        getToolbar().setNavigationIcon(R.drawable.ic_category);
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
    public void onChatBtnClicked() {
        if (EMClient.getInstance().isLoggedInBefore()) {
            startActivity(new Intent(MainActivity.this, ContactListActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                menuDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
