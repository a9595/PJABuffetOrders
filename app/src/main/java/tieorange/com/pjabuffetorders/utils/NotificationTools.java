package tieorange.com.pjabuffetorders.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.pojo.api.Order;
import tieorange.com.pjabuffetorders.pojo.api.PushNotificationBuffet;

/**
 * Created by tieorange on 15/12/2016.
 */

public class NotificationTools {
  private static String TAG = NotificationTools.class.getSimpleName();

  public static void pushNotificationOrderFinished(Context context, Order order) {
    PushNotificationBuffet push = new PushNotificationBuffet(order);
    String pushJson = getJson(
        push);        //  {"orderKey":"-K__s9yOcM-CO9xeg_Xt","secretCode":"043","token":"c8MxKBXI4sI:APA91bEGF0ux03XYkVKqQ4x7xDWMsIJc04IMj1tbj72UdQzZtMQeauy2Yibl8hT4b3ozOWgOKDsp2FFjbYnUI3FmCIUfLLXiUBDnLWEfafJzb0ffAm88jeFRVGAYZMA7jmrfapSvHjtY","userUID":"Pixel"}
    Log.d(TAG, "pushNotificationOrderFinished: json = " + pushJson);

    //RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
    RequestBody requestBody = RequestBody.create(mediaType, pushJson);

    MyApplication.sEndpointInterface.pushOrderFinishedNotificationBody(requestBody)
        .enqueue(new Callback<Void>() {
          @Override public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d(TAG,
                "onResponse() called with: call = [" + call + "], response = [" + response + "]");
          }

          @Override public void onFailure(Call<Void> call, Throwable t) {
            Toast.makeText(context, "Notification push error", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
          }
        });
  }

  private static String getJson(PushNotificationBuffet push) {
    Gson gson = new Gson();
    return gson.toJson(push);
  }
}
