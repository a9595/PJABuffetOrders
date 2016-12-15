package tieorange.com.pjabuffetorders.pojo.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tieorange on 15/12/2016.
 */

public interface EndpointInterface {

  @GET("demo/index") Call<User> pushOrderFinishedNotification(/*@Query("token") String token,
      @Query("uid") String uid, @Query("orderId") String orderId*/);
}
