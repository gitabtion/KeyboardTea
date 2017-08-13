package cn.abtion.keyboardtea.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author abtion.
 * @since 17/8/12 16:41.
 * email caiheng@hrsoft.net
 */

public abstract class BaseFragment extends Fragment {
    private View mRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        int layoutId = getLayoutId();
        if (mRoot == null) {
            View root = inflater.inflate(layoutId, container, false);
            initWidget(root);
            mRoot = root;
        } else {
            if (mRoot.getParent() != null) {
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }


        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initWidget(View root);

    protected abstract void initData();

    protected abstract int getLayoutId();
}
