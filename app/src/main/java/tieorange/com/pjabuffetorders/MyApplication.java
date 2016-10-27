package tieorange.com.pjabuffetorders;

import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by tieorange on 26/10/2016.
 */

public class MyApplication extends Application {

  public static FirebaseDatabase sFirebaseDatabase;

  @Override public void onCreate() {
    super.onCreate();

    initFirebase();
  }

  private void initFirebase() {
    sFirebaseDatabase = FirebaseDatabase.getInstance();
  }
}
