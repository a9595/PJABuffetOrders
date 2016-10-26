package tieorange.com.pjabuffetorders;

import android.app.Application;

/**
 * Created by tieorange on 26/10/2016.
 */

public class MyApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();

    initFirebase();
  }

  private void initFirebase() {

  }
}
