package tieorange.com.pjabuffetorders.activities.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.ordersListLib.Product;

/**
 * Created by tieorange on 15/10/2016.
 */
public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ViewHolder> {
  private static final String TAG = AdapterMenu.class.getCanonicalName();
  public List<Product> mProducts = new ArrayList<>();
  private Context mContext;

  public AdapterMenu(Context mContext, List<Product> products) {
    this.mContext = mContext;
    initProducts(products);
  }

  private void initProducts(List<Product> products) {
    this.mProducts = new ArrayList<>(products);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu, null);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Product product = mProducts.get(position);
    holder.name.setText(product.name);

    holder.price.setText(product.getStringPriceWithZlote());
    String cookingTimeText = String.valueOf(product.cookingTime) + " min";
    holder.cookingTime.setText(cookingTimeText);
    Picasso.with(mContext)
        .load(product.photoUrl)
        .placeholder(R.drawable.ic_pie_chart_outlined)
        .into(holder.image);
  }

  @Override public int getItemCount() {
    return mProducts.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image) ImageView image;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.price) TextView price;
    @BindView(R.id.cookingTime) TextView cookingTime;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
