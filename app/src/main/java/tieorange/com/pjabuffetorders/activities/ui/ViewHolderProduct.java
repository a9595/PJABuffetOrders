package tieorange.com.pjabuffetorders.activities.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.pojo.api.Product;

/**
 * Created by tieorange on 29/10/2016.
 */
public class ViewHolderProduct extends RecyclerView.ViewHolder {
  @BindView(R.id.image) ImageView image;
  @BindView(R.id.name) TextView name;
  @BindView(R.id.price) TextView price;
  @BindView(R.id.cookingTime) TextView cookingTime;

  public ViewHolderProduct(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void initProduct(Product model, Context context) {
    name.setText(model.name);
    price.setText(model.getStringPriceWithZlote());
    cookingTime.setText(model.cookingTime + " min");
    Picasso.with(context).load(model.photoUrl).placeholder(R.drawable.pierogi_ruskie).into(image);
  }
}
