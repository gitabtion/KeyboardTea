package cn.abtion.keyboardtea.chat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.keyboardtea.R;
import cn.abtion.keyboardtea.base.activity.BaseToolBarActivity;
import cn.abtion.keyboardtea.util.ToastUtil;
import cn.abtion.keyboardtea.util.Utility;

/**
 * @author abtion.
 * @since 17/10/24 09:46.
 * email caiheng@hrsoft.net.
 */

public class LoginActivity extends BaseToolBarActivity {
    private String userId;
    private String userPassword;

    @BindView(R.id.edit_id)
    TextInputEditText editId;
    @BindView(R.id.edit_password)
    TextInputEditText editPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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

    private void login() {
        EMClient.getInstance().login(userId, userPassword, new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LoginActivity.this,ContactListActivity.class));
                    }
                });
            }

            @Override
            public void onError(int code, String error) {
                ToastUtil.showToast(error);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }


    @OnClick(R.id.btn_register)
    public void onBtnRegisterClicked() {
        userId = editId.getText().toString().trim();
        userPassword = editPassword.getText().toString().trim();
        Utility.runOnNewThread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(userId, userPassword);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    ToastUtil.showToast(e.toString());

                }
            }
        });
        Snackbar.make(getWindow().getDecorView(), "注册成功", Snackbar.LENGTH_SHORT);
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
        userId = editId.getText().toString().trim();
        userPassword = editPassword.getText().toString().trim();
        login();
    }

//    @OnClick(R.id.btn_register)
//    public void onBtnRegisterClicked() {
//        userId = editId.getText().toString().trim();
//        userPassword = editPassword.getText().toString().trim();
//        Utility.runOnNewThread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    EMClient.getInstance().createAccount(userId, userPassword);
//
//                } catch (HyphenateException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Snackbar.make(getWindow().getDecorView(), "注册成功", Snackbar.LENGTH_SHORT);
//    }
//
//    @OnClick(R.id.btn_login)
//    public void onBtnLoginClicked() {
//        userId = editId.getText().toString().trim();
//        userPassword = editPassword.getText().toString().trim();
//        login();
//    }


}
