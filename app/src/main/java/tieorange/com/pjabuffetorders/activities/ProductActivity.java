package tieorange.com.pjabuffetorders.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.HensonNavigable;
import com.f2prateek.dart.InjectExtra;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.pojo.api.Product;

//@HensonNavigable
public class ProductActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.name) TextView mName;
  @BindView(R.id.content_product) ConstraintLayout mContentProduct;
  @BindView(R.id.fab) FloatingActionButton mFab;

  @InjectExtra Product mProduct;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product);
    ButterKnife.bind(this);
    Dart.inject(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    initFAB();
  }

  private void initFAB() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show());
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }
}
