package tieorange.com.pjabuffetorders.pojo.api;

import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;

/**
 * Created by tieorange on 03/11/2016.
 */
@Parcel public class Order {
  public List<Product> products = new ArrayList<>();
  public String clientName;

  public Order() {
  }
}
