package tieorange.com.pjabuffetorders.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.pojo.api.Product;

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

    initFAB();
    initViews();
  }

  private void initViews() {
    mName.setText(mProduct.name);
    mPrice.setText(mProduct.getStringPrice());
    mTime.setText(String.valueOf(mProduct.cookingTime));
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

  private void initFAB() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show());
  }
}
