package tieorange.com.pjabuffetorders.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.google.firebase.database.DatabaseReference;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.pojo.api.Order;

public class OrderActivity extends AppCompatActivity {

  @InjectExtra Order mOrder;
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.accept) Button mAccept;
  @BindView(R.id.reject) Button mReject;
  @BindView(R.id.content_order) ConstraintLayout mContentOrder;
  @BindView(R.id.fab) FloatingActionButton mFab;
  private DatabaseReference mOrderRef;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order);
    ButterKnife.bind(this);
    Dart.inject(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    initFAB();
    initFirebase();
  }

  @OnClick(R.id.accept) public void onClickAccept() {
    acceptOrder();
  }

  @OnClick(R.id.reject) public void onClickReject() {
    rejectOrder();
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
}
