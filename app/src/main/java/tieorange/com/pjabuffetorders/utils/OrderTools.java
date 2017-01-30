package tieorange.com.pjabuffetorders.utils;

import android.content.Context;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import java.util.UUID;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.pojo.api.Order;

/**
 * Created by stud on 17/11/2016.
 */

public class OrderTools {

  public static String getRandomCode() {
    String uuid = UUID.randomUUID().toString();
    uuid = uuid.substring(0, 3);
    return uuid.toUpperCase();
  }

  public static void setSecretCodeToFirebase(Context context, Order order,
      final ISecretCodeSetCompleted iSecretCodeSetCompleted) {
    order.setSecretCode(getRandomCode());

    MyApplication.sOrdersReference.child(order.getKey())
        .setValue(order, new DatabaseReference.CompletionListener() {
          @Override
          public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
            iSecretCodeSetCompleted.onComplete(databaseError, databaseReference);
            NotificationTools.pushNotificationOrderFinished(context, order);
          }
        });
  }

  public interface ISecretCodeSetCompleted {
    void onComplete(DatabaseError databaseError, DatabaseReference databaseReference);
  }
}
