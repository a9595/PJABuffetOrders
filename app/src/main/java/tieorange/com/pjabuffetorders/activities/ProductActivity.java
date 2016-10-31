package tieorange.com.pjabuffetorders.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.pojo.api.Product;
import tieorange.com.pjabuffetorders.utils.interfaces.IPositiveDialog;

public class ProductActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar mToolbar;
  //@BindView(R.id.content_product) ConstraintLayout mContentProduct;
  @InjectExtra Product mProduct;
  @BindView(R.id.toolbar_layout) CollapsingToolbarLayout mToolbarLayout;
  @BindView(R.id.app_bar) AppBarLayout mAppBar;
  @BindView(R.id.fab) FloatingActionButton mFab;
  @BindView(R.id.tvName) TextView mTvName;
  @BindView(R.id.tvPrice) TextView mTvPrice;
  @BindView(R.id.tvTime) TextView mTvTime;
  @BindView(R.id.name) EditText mName;
  @BindView(R.id.price) EditText mPrice;
  @BindView(R.id.time) EditText mTime;
  @BindView(R.id.content_product) ConstraintLayout mContentProduct;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product);
    ButterKnife.bind(this);
    Dart.inject(this);
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    initViews();
  }

  private void initViews() {
    mName.setText(mProduct.name);
    String price = String.valueOf(mProduct.getDoublePrice());
    mPrice.setText(price);
    mTime.setText(String.valueOf(mProduct.cookingTime));
  }

  @OnClick(R.id.fab) public void onClickFAB() {
    saveDataToFirebase();
  }

  private void saveDataToFirebase() {
    /*Query query = MyApplication.sProductsReference.orderByKey().equalTo(mProduct.key);
    query.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {

      }

      @Override public void onCancelled(DatabaseError databaseError) {

      }
    });*/

    modifyProductObject();

    MyApplication.sProductsReference.child(mProduct.key)
        .setValue(mProduct, (databaseError, databaseReference) -> Toast.makeText(ProductActivity.this, "saved", Toast.LENGTH_SHORT).show());
  }

  private void modifyProductObject() {
    String name = mName.getText().toString();
    int price = getPriceInteger(mPrice.getText().toString());
    int time = Integer.parseInt(mTime.getText().toString());

    mProduct.name = name;
    mProduct.price = price;
    mProduct.cookingTime = time;
  }

  private int getPriceInteger(String priceString) {
    double priceDouble = Double.parseDouble(priceString);
    return Product.convertPriceDoubleToInt(priceDouble);
  }

  // TODO
  public void showDialog(IPositiveDialog iPositiveDialog) {

  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == android.R.id.home) {
      onBackPressed();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
