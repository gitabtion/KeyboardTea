package cn.abtion.keyboardtea.chat.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.keyboardtea.R;
import cn.abtion.keyboardtea.base.activity.BaseToolBarActivity;
import cn.abtion.keyboardtea.util.Utility;

/**
 * @author abtion.
 * @since 17/10/24 22:03.
 * email caiheng@hrsoft.net.
 */

public class ChatActivity extends BaseToolBarActivity implements EMMessageListener {
    private String chatId;
    private EMMessage emMessage;
    private List<EMMessage> messages;
    private EMConversation conversation;
    private String currentUser;
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
        Bundle bundle = getIntent().getExtras();
        chatId = bundle.getString(ContactListActivity.USER_ID);
        EMClient.getInstance().chatManager().addMessageListener(this);
        currentUser = EMClient.getInstance().getCurrentUser();
    }

    @Override
    protected void initView() {
        setActivityTitle("聊天");
    }

    @Override
    protected void loadData() {
        Utility.runOnNewThread(new Runnable() {
            @Override
            public void run() {
                conversation = EMClient.getInstance().chatManager().getConversation(chatId,
                        EMConversation.EMConversationType.Chat, true);
                if (conversation != null) {
                    messages = conversation.getAllMessages();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (messages != null) {
                            for (EMMessage message : messages) {
                                EMTextMessageBody textMessageBody = (EMTextMessageBody) message.getBody();
                                txtChatContent.setText(txtChatContent.getText() + "\n" + message.getUserName()
                                        + "\n" + textMessageBody.getMessage() + "\n");
                            }
                        }
                    }
                });
            }
        });
    }


    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        txtChatContent.setText(txtChatContent.getText() + "\n" + currentUser + "\n" +
                editChatContent.getText
                        () + "\n");
        Utility.runOnNewThread(new Runnable() {
            @Override
            public void run() {
                emMessage = EMMessage.createTxtSendMessage(editChatContent
                        .getText().toString(), chatId);
                EMClient.getInstance().chatManager().sendMessage(emMessage);

            }
        });
    }

    @Override
    public void onMessageReceived(final List<EMMessage> messages) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (EMMessage message : messages) {
                    EMTextMessageBody textMessageBody = (EMTextMessageBody) message.getBody();
                    txtChatContent.setText(txtChatContent.getText() + "\n" + message.getUserName()
                            + "\n" + textMessageBody.getMessage() + "\n");
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
