package tieorange.com.pjabuffetorders.activities.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import tieorange.com.pjabuffetorders.databinding.ItemOrderBinding;
import tieorange.com.pjabuffetorders.pojo.api.Order;

/**
 * Created by tieorange on 03/11/2016.
 */
public class ViewHolderOrder extends RecyclerView.ViewHolder {

  public final ItemOrderBinding mBinding;
  TextView mPosition;
  TextView mProductsCount;
  TextView mStatus;

  public ViewHolderOrder(View itemView) {
    super(itemView);
    mBinding = DataBindingUtil.bind(itemView);
  }

  public void init(Order model, int position) {
    model.setPosition(position);
    mBinding.setOrder(model);
    //mBinding.position.setText(String.valueOf(position + 1));
    //String productsCount = model.products.size() + " products";
    //mBinding.productsCount.setText(productsCount);

    checkIfHasSecretCode(model);
  }

  private void checkIfHasSecretCode(Order model) {
    final String secretCode = model.getSecretCode();
    if (!TextUtils.isEmpty(secretCode)) {
      mBinding.status.setText(secretCode);
    }
  }
}
