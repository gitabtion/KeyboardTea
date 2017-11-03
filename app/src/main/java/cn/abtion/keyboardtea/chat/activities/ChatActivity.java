package cn.abtion.keyboardtea.chat.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.keyboardtea.R;
import cn.abtion.keyboardtea.base.activity.BaseToolBarActivity;
import cn.abtion.keyboardtea.chat.adapters.MessageAdapter;
import cn.abtion.keyboardtea.util.Utility;

/**
 * @author abtion.
 * @since 17/10/24 22:03.
 * email caiheng@hrsoft.net.
 */

public class ChatActivity extends BaseToolBarActivity implements EMMessageListener {
    @BindView(R.id.rec_chat)
    RecyclerView recChat;

    @BindView(R.id.edit_chat_content)
    EditText editChatContent;
    private String chatId;
    private EMMessage emMessage;
    private EMConversation conversation;
    MessageAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initVariable() {
        Bundle bundle = getIntent().getExtras();
        chatId = bundle.getString(ContactListActivity.USER_ID);
        EMClient.getInstance().chatManager().addMessageListener(this);
        conversation = EMClient.getInstance().chatManager().getConversation(chatId,
                EMConversation.EMConversationType.Chat, true);
    }

    @Override
    protected void initView() {
        setActivityTitle("聊天");
        adapter = new MessageAdapter(this,conversation);
        recChat.setAdapter(adapter);
        recChat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void loadData() {
        refresh();
    }


    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        emMessage = EMMessage.createTxtSendMessage(editChatContent
                .getText().toString(), chatId);
        EMClient.getInstance().chatManager().sendMessage(emMessage);
        editChatContent.setText("");
        refresh();
    }

    @Override
    public void onMessageReceived(final List<EMMessage> messages) {
        refresh();
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

    private void refresh(){
        if (adapter!=null){
            adapter.refresh();
        }
        scrollToBottom();
    }
    private void scrollToBottom() {
        Utility.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recChat.scrollToPosition(adapter.getItemCount() - 1);
            }
        }, 200);
    }


}
