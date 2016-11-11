package tieorange.com.pjabuffetorders.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.google.firebase.database.DatabaseReference;
import com.tieorange.orderlistlibrary.AdapterOrderItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.pojo.api.Order;
import tieorange.com.pjabuffetorders.utils.Tools;

public class OrderActivity extends AppCompatActivity {

    @InjectExtra
    Order mOrder;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.accept)
    Button mAccept;
    @BindView(R.id.reject)
    Button mReject;
    @BindView(R.id.content_order)
    ConstraintLayout mContentOrder;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.ready)
    Button mReady;
    @BindView(R.id.buttonsLayout)
    RelativeLayout mButtonsLayout;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    private DatabaseReference mOrderRef;
    private int mOrderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        Dart.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        initFirebase();
    }

    private void initViews() {
        initFAB();
        initRecycler();
    }

    private void initRecycler() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
//        mAccept = new AdapterOrderItem(this, mOrder.products);
    }

    @OnClick(R.id.accept)
    public void onClickAccept() {
        //acceptOrder();
        setOrderStatus(Order.STATE_ACCEPTED);
    }

    @OnClick(R.id.reject)
    public void onClickReject() {
        //rejectOrder();
        setOrderStatus(Order.STATE_REJECTED);
    }

    @OnClick(R.id.ready)
    public void onClickFinished() {
        setOrderStatus(Order.STATE_READY);
    }

    private void initFirebase() {
        mOrderRef = MyApplication.sOrdersReference.child(mOrder.key);
    /*mOrderRef.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        Order value = dataSnapshot.getValue(Order.class);
        value.status = Order.STATE_ACCEPTED;
        acceptOrder(value);
      }

      @Override public void onCancelled(DatabaseError databaseError) {

      }
    });*/
    }

    private void acceptOrder() {
        mOrder.status = Order.STATE_ACCEPTED;
        mOrderRef.setValue(mOrder, (databaseError, databaseReference) -> {
            // TODO: 06/11/2016 Handle errors
            Toast.makeText(OrderActivity.this, "ACCEPTED", Toast.LENGTH_SHORT).show();
        });
    }

    private void rejectOrder() {
        mOrder.status = Order.STATE_REJECTED;
        mOrderRef.setValue(mOrder, (databaseError, databaseReference) -> {
            // TODO: 06/11/2016 Handle errors
            Toast.makeText(OrderActivity.this, "REJECTED", Toast.LENGTH_SHORT).show();
        });
    }

    private void initFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show());
    }

    public void setOrderStatus(int orderStatus) {
        mOrder.status = orderStatus;
        mOrderRef.setValue(mOrder, (databaseError, databaseReference) -> {
            showMessage(orderStatus);

            if (orderStatus == Order.STATE_ACCEPTED) {
                Tools.setViewVisibility(mReady, View.VISIBLE);
            }
        });
    }

    private void showMessage(int orderStatus) {
        String message = "ERROR";
        if (orderStatus == Order.STATE_ACCEPTED) {
            message = "ACCEPTED";
        } else if (orderStatus == Order.STATE_READY) {
            message = "READY";
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
