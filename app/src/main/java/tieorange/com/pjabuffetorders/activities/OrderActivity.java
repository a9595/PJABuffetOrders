package tieorange.com.pjabuffetorders.activities;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.databinding.ActivityOrderBinding;
import tieorange.com.pjabuffetorders.databinding.ActivityOrdersHistoryBinding;
import tieorange.com.pjabuffetorders.ordersListLib.AdapterOrderItem;
import tieorange.com.pjabuffetorders.pojo.api.Order;
import tieorange.com.pjabuffetorders.utils.OrderTools;
import tieorange.com.pjabuffetorders.utils.Tools;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static tieorange.com.pjabuffetorders.pojo.api.Order.STATE_ACCEPTED;
import static tieorange.com.pjabuffetorders.pojo.api.Order.STATE_ORDERED;
import static tieorange.com.pjabuffetorders.pojo.api.Order.STATE_READY;
import static tieorange.com.pjabuffetorders.pojo.api.Order.STATE_REJECTED;

public class OrderActivity extends SuperFirebaseActivity {

  @InjectExtra Order mOrder;
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.accept) Button mAccept;
  @BindView(R.id.reject) Button mReject;
  @BindView(R.id.content_order) RelativeLayout mContentOrder;
  @BindView(R.id.fab) FloatingActionButton mFab;
  @BindView(R.id.ready) Button mReady;
  @BindView(R.id.buttonsLayout) RelativeLayout mButtonsLayout;
  @BindView(R.id.recycler) RecyclerView mRecycler;
  @BindView(R.id.pullToRefresh) SwipeRefreshLayout mPullToRefresh;
  private DatabaseReference mOrderRef;
  private int mOrderStatus;
  private AdapterOrderItem mAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final ActivityOrderBinding binding =
        DataBindingUtil.setContentView(this, R.layout.activity_order);

    ButterKnife.bind(this);
    Dart.inject(this);

    binding.contentOrder.setOrder(mOrder);

    initViews();
    initFirebase();
  }

  private void initViews() {
    initToolbar();
    initFAB();
    refreshTopButtons();
    initRecycler();
    //initPullToRefresh();
  }

  private void initPullToRefresh() {
    mPullToRefresh.setEnabled(false);
    mPullToRefresh.setRefreshing(false);
  }

  private void refreshTopButtons() {
    if (mOrder.getStatus() == STATE_ACCEPTED) {
      Tools.setViewVisibility(mAccept, GONE);
      Tools.setViewVisibility(mReady, VISIBLE);
    } else if (mOrder.getStatus() == STATE_ORDERED) {
      Tools.setViewVisibility(mAccept, VISIBLE);
    } else if (mOrder.getStatus() == STATE_READY) {
      Tools.setViewVisibility(mAccept, GONE);
      Tools.setViewVisibility(mReady, GONE);
    } else if (mOrder.getStatus() == STATE_REJECTED) {
      Tools.setViewVisibility(mReject, GONE);
      Tools.setViewVisibility(mAccept, VISIBLE);
      Tools.setViewVisibility(mReady, GONE);
    }
  }

  private void initToolbar() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    mToolbar.setNavigationOnClickListener(view -> {
      onBackPressed();
    });
    refreshToolbar();
  }

  private void refreshToolbar() {
    String title = mOrder.getStatusString();
    setTitle("Order:    " + title);
  }

  private void initRecycler() {
    mRecycler.setLayoutManager(new LinearLayoutManager(this));
    mAdapter = new AdapterOrderItem(this, mOrder.getProducts());
    mRecycler.setAdapter(mAdapter);
  }

  @OnClick(R.id.accept) public void onClickAccept() {
    setOrderStatus(STATE_ACCEPTED);
  }

  @OnClick(R.id.reject) public void onClickReject() {
    setOrderStatus(STATE_REJECTED);
  }

  @OnClick(R.id.ready) public void onClickFinished() {
    setOrderStatus(STATE_READY);
    OrderTools.setSecretCodeToFirebase(mOrder, (databaseError, databaseReference) -> {
      // TODO: 18/11/2016 show secret code on UI:
    });
  }

  private void initFirebase() {
    mOrderRef = MyApplication.sOrdersReference.child(mOrder.getKey());

    /*mOrderRef.addValueEventListener(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        final String key = mOrder.getKey();
        mOrder = dataSnapshot.getValue(Order.class);
        mOrder.key = key;
        setOrderStatus(mOrder.getStatus());
      }

      @Override public void onCancelled(DatabaseError databaseError) {
        Toast.makeText(OrderActivity.this, "onCancelled", Toast.LENGTH_SHORT).show();
      }
    });*/
  }

  private void initFAB() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show());
  }

  public void setOrderStatus(String orderStatus) {
    //mPullToRefresh.setRefreshing(true);
    mOrder.setStatus(orderStatus);
    mOrderRef.setValue(mOrder, (databaseError, databaseReference) -> {
      //            showMessage();
      refreshTopButtons();
      refreshToolbar();

      //mPullToRefresh.setRefreshing(false);
    });
  }

  private void showMessage() {
    String message = mOrder.getStatusString();
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }
}
