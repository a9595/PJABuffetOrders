package tieorange.com.pjabuffetorders.utils;

import android.view.View;

/**
 * Created by tieorange on 06/11/2016.
 */
public class Tools {
  public static void setViewVisibility(View view, int visibility) {
    if (view == null) return;
    view.setVisibility(visibility);
  }
}
