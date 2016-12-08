package tieorange.com.pjabuffetorders.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.f2prateek.dart.HensonNavigable;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.activities.ui.ViewHolderOrder;
import tieorange.com.pjabuffetorders.pojo.api.Order;
import tieorange.com.pjabuffetorders.utils.FirebaseTools;

@HensonNavigable public class OrdersHistoryActivity extends SuperFirebaseActivity {

  Toolbar mToolbar;
  RecyclerView mRecycler;
  FloatingActionButton mFab;
  private FirebaseRecyclerAdapter<Order, ViewHolderOrder> mAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_orders_history);

    initViews();
  }

  private void initViews() {
    bindViewToFields();
    initToolbar();
    initFAB();
    initRecycler();
  }

  private void bindViewToFields() {
    // TODO: 08/12/2016  
    //mToolbar = mBinding.toolbar;
    //mRecycler = mBinding.contentOrdersHistory.recycler;
    //mFab = mBinding.fab;
  }

  private void initRecycler() {
    mRecycler.setLayoutManager(new LinearLayoutManager(this));
    initAdapter();
  }

  private void initAdapter() {
    Query query = FirebaseTools.getQueryOrdersFinished();

    mAdapter = new FirebaseRecyclerAdapter<Order, ViewHolderOrder>(Order.class, R.layout.item_order,
        ViewHolderOrder.class, query) {
      @Override
      protected void populateViewHolder(ViewHolderOrder viewHolder, Order model, int position) {
        viewHolder.init(model, position);
      }
    };
    mRecycler.setAdapter(mAdapter);
  }

  private void initToolbar() {
    setSupportActionBar(mToolbar);
    mToolbar.setNavigationOnClickListener(view -> onBackPressed());
  }

  private void initFAB() {
    mFab.setOnClickListener(
        view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show());
  }
}
