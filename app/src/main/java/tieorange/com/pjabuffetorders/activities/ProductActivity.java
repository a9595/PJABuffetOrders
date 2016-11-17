package tieorange.com.pjabuffetorders.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.MaterialDialog;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.ordersListLib.Product;
import tieorange.com.pjabuffetorders.utils.interfaces.IPositiveDialog;

public class ProductActivity extends AppCompatActivity {

  public static final String CURRENCY = "zł ";
  private static final String TAG = ProductActivity.class.getCanonicalName();
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
    setOnPriceChangeListener();
  }

  private void initViews() {
    mName.setText(mProduct.name);
    String price = CURRENCY + String.valueOf(mProduct.getStringPrice());
    mPrice.setText(price);
    mTime.setText(String.valueOf(mProduct.cookingTime));
  }

  @OnClick(R.id.fab) public void onClickFAB() {
    saveDataToFirebase();
  }

  private void saveDataToFirebase() {
    modifyProductObject();
    MyApplication.sProductsReference.child(mProduct.key)
        .setValue(mProduct,
            (databaseError, databaseReference) -> Toast.makeText(ProductActivity.this, "saved",
                Toast.LENGTH_SHORT).show());
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
    priceString = getCleanPriceString(priceString);
    double priceDouble = Double.parseDouble(priceString);
    return Product.convertPriceDoubleToInt(priceDouble);
  }

  // TODO
  public void showDialog(IPositiveDialog iPositiveDialog) {
    new MaterialDialog.Builder(this).title("")
        .content("")
        .positiveText(R.string.yes)
        .negativeText(R.string.no)
        .show();
  }

  public void setOnPriceChangeListener() {
    mPrice.addTextChangedListener(new TextWatcher() {
      private String current = "";

      @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        if (!s.toString().equals(current)) {
          mPrice.removeTextChangedListener(this);

          String cleanString = getCleanPriceString(s);

          double parsed = 0;
          try {
            parsed = Double.parseDouble(cleanString);
          } catch (NumberFormatException e) {
            Log.d(TAG, "onTextChanged: " + e.getMessage());
          }
          double priceDouble = parsed / 100;

          //String formatted = NumberFormat.getCurrencyInstance().format(priceDouble);
          String formatted = CURRENCY + String.format("%.2f", priceDouble);

          current = formatted;
          mPrice.setText(formatted);
          mPrice.setSelection(formatted.length());

          mPrice.addTextChangedListener(this);
        }
      }

      @Override public void afterTextChanged(Editable editable) {

      }
    });
  }

  private String getCleanPriceString(CharSequence s) {
    return s.toString().replaceAll("[" + CURRENCY + ",.]", "");
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
