package cn.abtion.keyboardtea.chat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import cn.abtion.keyboardtea.R;
import cn.abtion.keyboardtea.base.adapter.BaseRecyclerViewAdapter;

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

    static class ItemHolder extends ViewHolder<String> {
        @BindView(R.id.txt_user_id)
        TextView txtUserId;

        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(String s) {
            txtUserId.setText(s);
        }


    }
}
