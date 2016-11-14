package tieorange.com.pjabuffetorders.activities.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.databinding.ItemOrderBinding;
import tieorange.com.pjabuffetorders.pojo.api.Order;

/**
 * Created by tieorange on 03/11/2016.
 */
public class ViewHolderOrder extends RecyclerView.ViewHolder {

    @BindView(R.id.rootLayoutOrderItem)
    ConstraintLayout mRootLayout;
    //    @BindView(R.id.position)
    TextView mPosition;
    //    @BindView(R.id.productsCount)
    TextView mProductsCount;
    //    @BindView(R.id.status)
    TextView mStatus;
    public final ItemOrderBinding mBinding;

    public ViewHolderOrder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }

    public void hide() {
        mRootLayout.setVisibility(View.GONE);
    }

    public void init(Order model, int position) {
     /*   mPosition.setText(String.valueOf(position + 1));
        String productsCount = model.products.size() + " products";
        mProductsCount.setText(productsCount);

        mStatus.setText(model.getStatusString(mStatus.getContext()));*/
    }
}
