package tieorange.com.pjabuffetorders.activities.ui;

import android.support.v7.widget.RecyclerView;
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

  public ViewHolderOrder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void init(Order model, int position) {
    mPosition.setText(String.valueOf(position + 1));
    String productsCount = model.products.size() + " products";
    mProductsCount.setText(productsCount);
  }
}
