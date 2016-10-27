package tieorange.com.pjabuffetorders.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.activities.ui.GridItemSpacingDecorator;
import tieorange.com.pjabuffetorders.pojo.api.Product;
import tieorange.com.pjabuffetorders.utils.Constants;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends android.support.v4.app.Fragment {

  @BindView(R.id.recycler) RecyclerView mRecycler;

  public static ProductsFragment newInstance() {
    Bundle args = new Bundle();
    ProductsFragment fragment = new ProductsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ProductsFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_products, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initRecycler();
    initFirebase();
  }

  private void initRecycler() {
    int spanCount;
    int orientation = getResources().getConfiguration().orientation;
    if (orientation == ORIENTATION_PORTRAIT) {
      spanCount = 2;
    } else {
      spanCount = 3;
    }

    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
    //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
    mRecycler.setLayoutManager(layoutManager);
    int spacing = 20;
    //mRecycler.addItemDecoration(new SpacesItemDecoration(spacing, spacing, spacing, spacing));
    mRecycler.addItemDecoration(new GridItemSpacingDecorator(spanCount, spacing));

    initAdapter();
  }

  private void initAdapter() {
    DatabaseReference databaseReference = MyApplication.sFirebaseDatabase.getReference(Constants.PRODUCTS);
    FirebaseRecyclerAdapter<Product, ViewHolderProduct> adapter =
        new FirebaseRecyclerAdapter<Product, ViewHolderProduct>(Product.class, R.layout.item_menu, ViewHolderProduct.class, databaseReference) {
          @Override protected void populateViewHolder(ViewHolderProduct viewHolder, Product model, int position) {
            viewHolder.initProduct(model, getContext());
          }
        };
    mRecycler.setAdapter(adapter);
  }

  private void initFirebase() {
  }

  static class ViewHolderProduct extends RecyclerView.ViewHolder {
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
      price.setText(model.getStringPrice());
      cookingTime.setText(model.cookingTime + " min");
      Picasso.with(context).load(model.photoUrl).placeholder(R.drawable.pierogi_ruskie).into(image);
    }
  }
}
