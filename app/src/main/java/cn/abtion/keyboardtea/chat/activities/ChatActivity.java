package cn.abtion.keyboardtea.chat.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.keyboardtea.R;
import cn.abtion.keyboardtea.base.activity.BaseToolBarActivity;
import cn.abtion.keyboardtea.util.Utility;

/**
 * @author abtion.
 * @since 17/10/24 22:03.
 * email caiheng@hrsoft.net.
 */

public class ChatActivity extends BaseToolBarActivity implements EMMessageListener{
    private String chatId;
    EMMessage emMessage;
    @BindView(R.id.txt_chat_content)
    TextView txtChatContent;
    @BindView(R.id.edit_chat_content)
    EditText editChatContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
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


    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        txtChatContent.setText(txtChatContent.getText() + "\n"+ editChatContent.getText());
        Utility.runOnNewThread(new Runnable() {
            @Override
            public void run() {
                emMessage = EMMessage.createTxtSendMessage(editChatContent
                        .getText().toString(),chatId);
                EMClient.getInstance().chatManager().sendMessage(emMessage);

            }
        });
    }

    @Override
    public void onMessageReceived(final List<EMMessage> messages) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (EMMessage message:messages){
                    txtChatContent.setText(txtChatContent.getText()+"\n"+message);
                }
            }
        });
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }
}
