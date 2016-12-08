package tieorange.com.pjabuffetorders.pojo.api;

<<<<<<< Updated upstream
=======
import java.util.LinkedHashMap;
import java.util.Map;
import org.parceler.Parcel;
import tieorange.com.pjabuffetorders.ordersListLib.Product;
import tieorange.com.pjabuffetorders.utils.CartTools;

>>>>>>> Stashed changes
/**
 * Created by tieorange on 08/12/2016.
 */

<<<<<<< Updated upstream
class Cart {
=======
@Parcel public class Cart {
  private Map<Product, Integer> products = new LinkedHashMap<>();

  public Cart() {
    setProducts(new LinkedHashMap<Product, Integer>());
  }

  public Map<Product, Integer> getProducts() {
    return products;
  }

  public void setProducts(LinkedHashMap<Product, Integer> products) {
    this.products = products;
  }

  public int size() {
    return CartTools.getAllProductsAmount(this);
  }
>>>>>>> Stashed changes
}
