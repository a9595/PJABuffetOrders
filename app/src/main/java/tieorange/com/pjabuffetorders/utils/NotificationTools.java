package tieorange.com.pjabuffetorders.utils;

import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.pojo.api.Order;
import tieorange.com.pjabuffetorders.pojo.api.User;

/**
 * Created by tieorange on 15/12/2016.
 */

public class NotificationTools {
  public static String TAG = NotificationTools.class.getSimpleName();

  public static void pushOrderFinished(Order order) {
    MyApplication.sEndpointInterface.pushOrderFinishedNotification(order.user.getToken(),
        order.user.getUid(), order.secretCode).enqueue(new Callback<User>() {
      @Override public void onResponse(Call<User> call, Response<User> response) {
        Log.d(TAG,
            "onResponse() called with: call = [" + call + "], response = [" + response + "]");
      }

      @Override public void onFailure(Call<User> call, Throwable t) {
        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
      }
    });
  }
}
