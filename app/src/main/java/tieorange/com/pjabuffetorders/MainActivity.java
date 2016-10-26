package tieorange.com.pjabuffetorders;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

  private static final int ID_PRODUCTS = 1;
  public static final int ID_ORDERS = 2;
  private Drawer mDrawerBuild;
  @BindView(R.id.toolbar) public Toolbar mToolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);

    initFAB();
    initDrawer();
  }

  private void initDrawer() {

    //if you want to update the items at a later time it is recommended to keep it in a variable
    PrimaryDrawerItem itemProducts =
        new PrimaryDrawerItem().withIdentifier(ID_PRODUCTS).withName(R.string.products).withIcon(GoogleMaterial.Icon.gmd_local_pizza);
    PrimaryDrawerItem itemOrders =
        new PrimaryDrawerItem().withIdentifier(ID_ORDERS).withName(R.string.orders).withIcon(GoogleMaterial.Icon.gmd_shopping_cart);
    //SecondaryDrawerItem secondaryDrawerItem = new SecondaryDrawerItem().withName(R.string.settings);

    //create the drawer and remember the `Drawer` result object
    mDrawerBuild = new DrawerBuilder().withActivity(this)
        .withToolbar(mToolbar)
        .addDrawerItems(itemProducts, itemOrders)
        .withOnDrawerItemClickListener(getOnDrawerItemClickListener())
        .build();
  }

  @NonNull private Drawer.OnDrawerItemClickListener getOnDrawerItemClickListener() {
    return new Drawer.OnDrawerItemClickListener() {
      @Override public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        switch ((int) drawerItem.getIdentifier()) {
          case ID_ORDERS:
            Toast.makeText(MainActivity.this, "orders", Toast.LENGTH_SHORT).show();
            break;
          case ID_PRODUCTS:
            Toast.makeText(MainActivity.this, "products", Toast.LENGTH_SHORT).show();
            break;

          default:
            Toast.makeText(MainActivity.this, "default", Toast.LENGTH_SHORT).show();
            break;
        }

        return true;
      }
    };
  }

  private void initFAB() {
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
      }
    });
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

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void writeToDatabase() {
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    myRef.setValue("Hello, World!");
  }

  //public void readFromDatabase(){
  //  // Read from the database
  //  myRef.addValueEventListener(new ValueEventListener() {
  //    @Override
  //    public void onDataChange(DataSnapshot dataSnapshot) {
  //      // This method is called once with the initial value and again
  //      // whenever data at this location is updated.
  //      String value = dataSnapshot.getValue(String.class);
  //      Log.d(TAG, "Value is: " + value);
  //    }
  //
  //    @Override
  //    public void onCancelled(DatabaseError error) {
  //      // Failed to read value
  //      Log.w(TAG, "Failed to read value.", error.toException());
  //    }
  //  });
  //}
}
