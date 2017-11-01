package cn.abtion.keyboardtea.chat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.util.List;

import butterknife.BindView;
import cn.abtion.keyboardtea.R;
import cn.abtion.keyboardtea.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.keyboardtea.util.Utility;

/**
 * @author abtion.
 * @since 17/10/23 21:13.
 * email caiheng@hrsoft.net.
 */

public class ContactListAdapter extends BaseRecyclerViewAdapter<String> {




    public ContactListAdapter(Context context, List<String> strings) {
        super(context, strings);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        return new ItemHolder(view);
    }

    class ItemHolder extends ViewHolder<String> {
        @BindView(R.id.txt_unread_count)
        TextView txtUnreadCount;
        @BindView(R.id.txt_user_id)
        TextView txtUserId;
        EMConversation conversation;
        String userId;

        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(String s) {
            txtUserId.setText(s);
            userId =s;
//            Utility.runOnNewThread(new Runnable() {
//                @Override
//                public void run() {
//                    conversation = EMClient.getInstance().chatManager().getConversation(userId, EMConversation
//                            .EMConversationType.Chat,true);
//                    Utility.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            int unreadCount = conversation.getUnreadMsgCount();
//                            if (unreadCount==0){
//                                txtUnreadCount.setVisibility(View.GONE);
//                            }else if (unreadCount>99){
//                                txtUnreadCount.setText("99+");
//                            }else {
//                                txtUnreadCount.setText(unreadCount);
//                            }
//                        }
//                    });
//                }
//            });
        }


    }
}
