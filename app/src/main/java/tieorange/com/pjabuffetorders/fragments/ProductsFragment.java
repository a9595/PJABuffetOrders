package tieorange.com.pjabuffetorders.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.activities.ui.GridItemSpacingDecorator;
import tieorange.com.pjabuffetorders.activities.ui.ItemClickSupport;
import tieorange.com.pjabuffetorders.activities.ui.ViewHolderProduct;
import tieorange.com.pjabuffetorders.ordersListLib.Product;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends android.support.v4.app.Fragment {

  @BindView(R.id.recycler) RecyclerView mRecycler;
  private FirebaseRecyclerAdapter<Product, ViewHolderProduct> mAdapter;
  private List<Product> mProductsList = new ArrayList<>();

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

    initItemClickListener();
  }

  private void initItemClickListener() {
    ItemClickSupport.addTo(mRecycler).setOnItemClickListener((recyclerView, position, v) -> {
      String key = mAdapter.getRef(position).getKey();
      Product product = mAdapter.getItem(position);
      product.key = key;
//      Intent intent = Henson.with(getContext()).gotoProductActivity().mProduct(product).build();
//      startActivity(intent);
    });
  }

  private void initAdapter() {

    mAdapter = new FirebaseRecyclerAdapter<Product, ViewHolderProduct>(Product.class, R.layout.item_menu, ViewHolderProduct.class,
        MyApplication.sProductsReference) {
      @Override protected void populateViewHolder(ViewHolderProduct viewHolder, Product model, int position) {
        //String key = getRef(position).getKey();
        //model.key = key;
        viewHolder.initProduct(model, getContext());
      }
    };
    mRecycler.setAdapter(mAdapter);
  }
}
