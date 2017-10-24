package cn.abtion.keyboardtea.chat.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import cn.abtion.keyboardtea.chat.adapters.ContactListAdapter;
import cn.abtion.keyboardtea.util.ToastUtil;
import cn.abtion.keyboardtea.util.Utility;

/**
 * @author abtion.
 * @since 17/10/23 21:05.
 * email caiheng@hrsoft.net.
 */

public class ContactListActivity extends BaseToolBarActivity {
    private List<String> userNames;
    private ContactListAdapter adapter;

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
        initSwipe();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                swipeContactList.setRefreshing(false);
            }
        },1000);
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
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                swipeContactList.setRefreshing(false);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.setData(userNames);
                                    }
                                });
                            }
                        },1000);
                    }
                });
            }
        });
    }

    @OnClick(R.id.fab_add_contact)
    public void onAddContactFabClicked(){
        startActivity(new Intent(this,AddContactActivity.class));
    }

}
