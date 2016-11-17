package tieorange.com.pjabuffetorders.activities;

import android.support.v7.app.AppCompatActivity;
import tieorange.com.pjabuffetorders.MyApplication;

/**
 * Created by root on 11/11/16.
 */
public class SuperFirebaseActivity extends AppCompatActivity {
  @Override protected void onResume() {
    super.onResume();
    MyApplication.sFirebaseDatabase.goOnline();
  }

  @Override protected void onPause() {
    MyApplication.sFirebaseDatabase.goOffline();
    super.onPause();
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}
