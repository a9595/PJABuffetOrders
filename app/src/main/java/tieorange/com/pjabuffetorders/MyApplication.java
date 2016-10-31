package tieorange.com.pjabuffetorders;

import android.app.Application;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import tieorange.com.pjabuffetorders.utils.Constants;

/**
 * Created by tieorange on 26/10/2016.
 */

public class MyApplication extends Application {

  public static FirebaseDatabase sFirebaseDatabase;
  public static DatabaseReference sProductsReference;

  @Override public void onCreate() {
    super.onCreate();

    initFirebase();
  }

  private void initFirebase() {
    sFirebaseDatabase = FirebaseDatabase.getInstance();
    sProductsReference = MyApplication.sFirebaseDatabase.getReference(Constants.PRODUCTS);
  }
}
