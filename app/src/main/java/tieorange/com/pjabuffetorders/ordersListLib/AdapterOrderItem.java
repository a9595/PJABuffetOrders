package tieorange.com.pjabuffetorders.ordersListLib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.pojo.api.Order;
import tieorange.com.pjabuffetorders.utils.CartTools;

/**
 * Created by tieorange on 16/10/2016.
 */

public class AdapterOrderItem extends RecyclerView.Adapter<AdapterOrderItem.ViewHolder> {

  private final Context mContext;
  private Order mOrder;

  public AdapterOrderItem(Context context, Order order) {
    mContext = context;
    mOrder = order;
    mOrder.productsCart.convertProductsFromFirebase();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(mContext).inflate(R.layout.item_product_in_order, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    final Pair<Product, Integer> entry = CartTools.getEntry(position, mOrder.productsCart);

    Product product = entry.first;
    final String amount = String.valueOf(entry.second);

    holder.name.setText(product.name);
    holder.price.setText(product.getStringPrice());
    holder.amount.setText(amount);

    // TODO: 08/12/2016 Amount
  }

  @Override public int getItemCount() {
    return CartTools.size(mOrder.productsCart);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.itemName) public TextView name;
    @BindView(R.id.itemPrice) TextView price;
    @BindView(R.id.amount) TextView amount;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
