package tieorange.com.pjabuffetorders.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.fragments.OrdersFragment;
import tieorange.com.pjabuffetorders.fragments.ProductsFragment;

public class MainActivity extends SuperFirebaseActivity {

  public static final int ID_ORDERS = 2;
  private static final int ID_PRODUCTS = 1;
  @BindView(R.id.toolbar) public Toolbar mToolbar;
  @BindView(R.id.fragmentContainer) FrameLayout mFragmentContainer;
  @BindView(R.id.fab) FloatingActionButton fab;
  private Drawer mDrawerBuild;
  private ProductsFragment mProductsFragment;
  private OrdersFragment mOrdersFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setSupportActionBar(mToolbar);

    initFragments();
    initDrawer();
  }

  private void initFragments() {
    mProductsFragment = ProductsFragment.newInstance();
    mOrdersFragment = OrdersFragment.newInstance();
  }

  private void setFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragmentContainer, fragment)
        .commit();
  }

  private void initDrawer() {
    //if you want to update the items at a later time it is recommended to keep it in a variable
    SecondaryDrawerItem itemProducts = new SecondaryDrawerItem().withIdentifier(ID_PRODUCTS)
        .withName(R.string.products)
        .withIcon(GoogleMaterial.Icon.gmd_local_pizza);
    SecondaryDrawerItem itemOrders = new SecondaryDrawerItem().withIdentifier(ID_ORDERS)
        .withName(R.string.orders)
        .withIcon(GoogleMaterial.Icon.gmd_shopping_cart);
    //SecondaryDrawerItem secondaryDrawerItem = new SecondaryDrawerItem().withName(R.string.settings);

    //create the drawer and remember the `Drawer` result object
    mDrawerBuild = new DrawerBuilder().withActivity(this)
        .withToolbar(mToolbar)
        .addDrawerItems(itemProducts, itemOrders)
        .withOnDrawerItemClickListener(getOnDrawerItemClickListener())
        .build();

    mDrawerBuild.setSelection(itemOrders);
  }

  @NonNull private Drawer.OnDrawerItemClickListener getOnDrawerItemClickListener() {
    return (view, position, drawerItem) -> {
      int identifier = (int) drawerItem.getIdentifier();
      switch (identifier) {
        case ID_ORDERS:
          setFragment(mOrdersFragment);
          setTitle(R.string.orders);
          break;
        case ID_PRODUCTS:
          setFragment(mProductsFragment);
          setTitle(R.string.products);
          break;

        default:
          Toast.makeText(MainActivity.this, "default", Toast.LENGTH_SHORT).show();
          break;
      }

      return true;
    };
  }

  @OnClick(R.id.fab) public void onClickFab() {
    scanQrCode();
  }

  private void scanQrCode() {
    IntentIntegrator integrator = new IntentIntegrator(this);
    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
    integrator.setPrompt("Scan a barcode");
    //integrator.setCameraId(0);  // Use a specific camera of the device
    integrator.setBeepEnabled(true);
    integrator.setBarcodeImageEnabled(true);
    integrator.initiateScan();
  }

  private void scanQrCodeFinished(String orderKey) {
    if (orderKey == null) {
      Toast.makeText(this, "Please, try to scan again", Toast.LENGTH_SHORT).show();
      return;
    }

    final Intent intent =
        Henson.with(MainActivity.this).gotoOrderActivity().mOrderKey(orderKey).build();
    startActivity(intent);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    if (result != null) {
      final String orderKey = result.getContents();
      if (orderKey == null) {
        Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
      } else {
        Toast.makeText(this, "Scanned: " + orderKey, Toast.LENGTH_LONG).show();
        scanQrCodeFinished(orderKey);
      }
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    if (id == R.id.action_orders_history) {
      openOrdersHistoryActivity();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void openOrdersHistoryActivity() {
    Intent intent = Henson.with(this).gotoOrdersHistoryActivity().build();
    startActivity(intent);
  }
}
