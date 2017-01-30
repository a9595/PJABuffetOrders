package tieorange.com.pjabuffetorders;

import android.app.Application;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tieorange.com.pjabuffetorders.pojo.api.EndpointInterface;
import tieorange.com.pjabuffetorders.utils.Constants;

/**
 * Created by tieorange on 26/10/2016.
 */

public class MyApplication extends Application {
  //  private static final String LOCAL_IP = "192.168.1.103";
  private static final String LOCAL_IP = "10.0.2.2";
  //  private static final String BASE_URL = "http://" + LOCAL_IP + ":3000/";
  private static final String BASE_URL = "https://cryptic-chamber-82312.herokuapp.com/";
  public static FirebaseDatabase sFirebaseDatabase;
  public static DatabaseReference sProductsReference;
  public static DatabaseReference sOrdersReference;

  public static Retrofit sRetrofit;
  public static EndpointInterface sEndpointInterface;

  @Override public void onCreate() {
    super.onCreate();

    initFirebase();
    initRetrofit();
  }

  private void initRetrofit() {
    sRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    sEndpointInterface = sRetrofit.create(EndpointInterface.class);
  }

  private void initFirebase() {
    sFirebaseDatabase = FirebaseDatabase.getInstance();
    sProductsReference = sFirebaseDatabase.getReference(Constants.PRODUCTS);
    sOrdersReference = sFirebaseDatabase.getReference(Constants.ORDERS);
  }
}
