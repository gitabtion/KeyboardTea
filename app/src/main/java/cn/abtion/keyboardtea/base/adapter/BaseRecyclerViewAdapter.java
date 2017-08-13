package cn.abtion.keyboardtea.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author abtion.
 * @since 17/8/12 16:54.
 * email caiheng@hrsoft.net
 */

public abstract class BaseRecyclerViewAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    protected List<T> list;
    protected Context context;
    protected LayoutInflater inflater;

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    /**
     * 刷新页面
     */
    public void refresh() {
        notifyDataSetChanged();
    }

    /**
     * 设置数据
     *
     * @param data 数据
     */
    public void setData(Collection<T> data) {
        this.list.clear();
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加一条数据
     *
     * @param t
     */
    public void add(T t) {
        this.list.add(t);
        notifyDataSetChanged();
    }

    /**
     * 添加多条数据
     *
     * @param collection 数据
     */
    public void addAll(Collection<T> collection) {
        this.list.addAll(collection);
        notifyDataSetChanged();
    }

    /**
     * 移除一条数据
     *
     * @param t 需移除的数据
     */
    public void remove(T t) {
        this.list.remove(t);
        notifyDataSetChanged();
    }

    /**
     * 清空列表
     */
    public void clean() {
        this.list.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取数据列表
     *
     * @return list
     */
    public List<T> getList() {
        return this.list;
    }

    /**
     * 获取某位置的数据
     * @param position 位置
     * @return 数据
     */
    public T getItem(int position) {
        return this.list.get(position);
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


}
