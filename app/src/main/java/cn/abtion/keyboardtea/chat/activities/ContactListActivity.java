package cn.abtion.keyboardtea.chat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.keyboardtea.R;
import cn.abtion.keyboardtea.base.activity.BaseToolBarActivity;
import cn.abtion.keyboardtea.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.keyboardtea.chat.adapters.ContactListAdapter;
import cn.abtion.keyboardtea.util.ToastUtil;
import cn.abtion.keyboardtea.util.Utility;

/**
 * @author abtion.
 * @since 17/10/23 21:05.
 * email caiheng@hrsoft.net.
 */

public class ContactListActivity extends BaseToolBarActivity implements BaseRecyclerViewAdapter.OnItemClicked<String>{
    private List<String> userNames;
    private ContactListAdapter adapter;
    public static String USER_ID = "USER_ID";
    private String chatId;

    @BindView(R.id.rec_contact)
    RecyclerView recContact;
    @BindView(R.id.swipe_contact_list)
    SwipeRefreshLayout swipeContactList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_list;
    }

    @Override
    protected void initVariable() {
        userNames = new ArrayList<>();
    }

    @Override
    protected void initView() {
        setActivityTitle("联系人");
        initToolbar();
        initSwipe();
        Utility.runOnNewThread(new Runnable() {
            @Override
            public void run() {
                try {
                    userNames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipeContactList.setRefreshing(false);
                        adapter.setData(userNames);
                    }
                });
            }
        },2000);

    }

    @Override
    protected void loadData() {
        Utility.runOnNewThread(new Runnable() {
            @Override
            public void run() {
                try {
                    userNames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    ToastUtil.showToast(e.toString());
                }
            }
        });
        adapter = new ContactListAdapter(this,userNames);
        adapter.setOnItemClickedListener(this);
        recContact.setAdapter(adapter);
        recContact.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    private void initSwipe(){
        swipeContactList.setRefreshing(true);
        swipeContactList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Utility.runOnNewThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            userNames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                        }
                    }
                });
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipeContactList.setRefreshing(false);
                                adapter.setData(userNames);
                            }
                        });
                    }
                },2000);
            }
        });
    }

    @OnClick(R.id.fab_add_contact)
    public void onAddContactFabClicked(){
        startActivity(new Intent(this,AddContactActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void initToolbar(){
        getToolbar().inflateMenu(R.menu.chat_toolbar_menu);
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_logout:
                        emcLogout();
                        break;
                    case R.id.action_add_friend:
                        // TODO: 17/10/31 addFriend;
                        break;
                    default:
                }
                return true;
            }
        });
    }

    private void emcLogout(){
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(ContactListActivity.this,LoginActivity.class));
                finish();
            }

            @Override
            public void onError(int code, String error) {
                ToastUtil.showToast("退出失败，请稍后再试");
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    @Override
    public void onItemClicked(String s, BaseRecyclerViewAdapter.ViewHolder holder) {
        Intent intent = new Intent(this,ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(USER_ID,s);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
