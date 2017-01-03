package tieorange.com.pjabuffetorders.pojo.api;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tieorange on 15/12/2016.
 */

public interface EndpointInterface {

    @GET("demo/index")
    Call<User> pushOrderFinishedNotification(@Query("token") String token,
                                             @Query("uid") String uid, @Query("secretCode") String secretCode,
                                             @Query("orderKey") String orderKey);

    // TODO: 19/12/2016 change Call<User> to ResponsePush  - check if was sent
    @POST("notification")
    Call<Void> pushOrderFinishedNotificationBody(
            @Body RequestBody json);
}
