package tieorange.com.pjabuffetorders.utils;

import com.google.firebase.database.Query;
import tieorange.com.pjabuffetorders.MyApplication;
import tieorange.com.pjabuffetorders.pojo.api.Order;

/**
 * Created by tieorange on 17/11/2016.
 */

public class FirebaseTools {
  public static Query getQueryOrdersOrdered() {
    return MyApplication.sOrdersReference.orderByChild(Constants.STATUS)
        .startAt(Order.ORDERED_ORDERS_START_WITH)
        .endAt(Order.ORDERED_ORDERS_ENDS_WITH);
  }

  public static Query getQueryOrdersFinished() {
    return MyApplication.sOrdersReference.orderByChild(Constants.STATUS)
        .startAt(Order.FINISHED_ORDERS_START_WITH)
        .endAt(Order.FINISHED_ORDERS_END_WITH);
  }
}
