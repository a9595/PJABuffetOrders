package tieorange.com.pjabuffetorders.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import org.w3c.dom.Text;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.activities.Henson;
import tieorange.com.pjabuffetorders.activities.ui.GridItemSpacingDecorator;
import tieorange.com.pjabuffetorders.activities.ui.ItemClickSupport;
import tieorange.com.pjabuffetorders.activities.ui.ViewHolderOrder;
import tieorange.com.pjabuffetorders.databinding.ItemOrderBinding;
import tieorange.com.pjabuffetorders.pojo.api.Order;
import tieorange.com.pjabuffetorders.utils.FirebaseTools;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends android.support.v4.app.Fragment {

  @BindView(R.id.recycler) RecyclerView mRecycler;
  @BindView(R.id.noOrdersYet) TextView mNoOrdersYet;
  private FirebaseRecyclerAdapter<Order, ViewHolderOrder> mAdapter;

  public OrdersFragment() {
    // Required empty public constructor
  }

  public static OrdersFragment newInstance() {
    Bundle args = new Bundle();
    OrdersFragment fragment = new OrdersFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_orders, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initRecycler();
  }

  private void initRecycler() {
    mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    int spacing = 20;
    //mRecycler.addItemDecoration(new SpacesItemDecoration(spacing, spacing, spacing, spacing));
    mRecycler.addItemDecoration(new GridItemSpacingDecorator(1, spacing));

    ItemClickSupport.addTo(mRecycler).setOnItemClickListener((recyclerView, position, v) -> {
      String key = mAdapter.getRef(position).getKey();
      Order order = mAdapter.getItem(position);
      order.setKey(key);
      Intent intent = Henson.with(getContext()).gotoOrderActivity().mOrder(order).build();
      startActivity(intent);
    });

    initAdapter();
  }

  private void initAdapter() {
    final Query queryOrdersOrdered = FirebaseTools.getQueryOrdersOrdered();

    mAdapter = new FirebaseRecyclerAdapter<Order, ViewHolderOrder>(Order.class, R.layout.item_order,
        ViewHolderOrder.class, queryOrdersOrdered) {
      @Override
      protected void populateViewHolder(ViewHolderOrder viewHolder, Order model, int position) {
        model.setPosition(position);
        viewHolder.init(model, position);

        mNoOrdersYet.setVisibility(View.GONE);
      }
    };

    mRecycler.setAdapter(mAdapter);
  }
}
