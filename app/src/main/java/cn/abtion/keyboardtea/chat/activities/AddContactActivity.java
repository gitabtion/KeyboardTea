package cn.abtion.keyboardtea.chat.activities;

import android.widget.EditText;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.keyboardtea.R;
import cn.abtion.keyboardtea.base.activity.BaseToolBarActivity;
import cn.abtion.keyboardtea.util.ToastUtil;

/**
 * @author abtion.
 * @since 17/10/24 20:33.
 * email caiheng@hrsoft.net.
 */

public class AddContactActivity extends BaseToolBarActivity {
    private String userId;

    @BindView(R.id.edit_id)
    EditText editId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_contact;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle("添加联系人");
    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.btn_add_contact)
    public void onViewClicked() {
        userId = editId.getText().toString().trim();
        try {
            EMClient.getInstance().contactManager().addContact(userId,"123");
        } catch (HyphenateException e) {
            e.printStackTrace();
            ToastUtil.showToast(e.toString());
        }
    }
}
