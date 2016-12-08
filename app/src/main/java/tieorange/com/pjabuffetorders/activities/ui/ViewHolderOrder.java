package tieorange.com.pjabuffetorders.activities.ui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.pojo.api.Order;

/**
 * Created by tieorange on 03/11/2016.
 */
public class ViewHolderOrder extends RecyclerView.ViewHolder {

  @BindView(R.id.position) TextView mPosition;
  @BindView(R.id.productsCount) TextView mProductsCount;
  @BindView(R.id.status) TextView mStatus;

  public ViewHolderOrder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void init(Order model, int position) {
    model.productsCart.convertProductsFromFirebase();

    model.setPosition(position);
    mPosition.setText(String.valueOf(position + 1));
    String productsCount = model.productsCart.size() + " products";
    mProductsCount.setText(productsCount);

    checkIfHasSecretCode(model);
  }

  private void checkIfHasSecretCode(Order model) {
    final String secretCode = model.getSecretCode();
    if (!TextUtils.isEmpty(secretCode)) {
      //mBinding.status.setText(secretCode);
    }
  }
}
