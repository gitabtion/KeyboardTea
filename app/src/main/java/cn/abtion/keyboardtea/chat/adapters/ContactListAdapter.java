package cn.abtion.keyboardtea.chat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.abtion.keyboardtea.R;
import cn.abtion.keyboardtea.base.adapter.BaseRecyclerViewAdapter;
import cn.abtion.keyboardtea.chat.models.UserModel;

/**
 * @author abtion.
 * @since 17/10/23 21:13.
 * email caiheng@hrsoft.net.
 */

public class ContactListAdapter extends BaseRecyclerViewAdapter<UserModel> {
    public ContactListAdapter(Context context, List<UserModel> userModels) {
        super(context, userModels);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_contact,parent,false);
        return null;
    }

    static class ItemHolder extends ViewHolder<UserModel> {
        public ItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(UserModel userModel) {

        }
    }
}
