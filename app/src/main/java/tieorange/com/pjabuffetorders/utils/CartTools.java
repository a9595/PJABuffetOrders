package tieorange.com.pjabuffetorders.utils;

<<<<<<< Updated upstream
=======
import android.util.Pair;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import tieorange.com.pjabuffetorders.ordersListLib.Product;
import tieorange.com.pjabuffetorders.pojo.api.Cart;

>>>>>>> Stashed changes
/**
 * Created by tieorange on 08/12/2016.
 */

<<<<<<< Updated upstream
class CartTools {
=======
public class CartTools {

  public static void setCurrentAmount(int amount, Product product, Cart cart) {
    setAmount(product, amount, cart);
  }

  public static void add(Product product, Cart cart) {
    if (!cart.getProducts().containsKey(product)) {
      cart.getProducts().put(product, 1);
    } else {
      incrementAmount(product, cart);
    }
  }

  public static void remove(Product product, Cart cart) {
    if (!cart.getProducts().containsKey(product)) return;

    final Integer amount = cart.getProducts().get(product);
    if (amount <= 1) { // remove from map
      cart.getProducts().remove(product);
    } else { // decrement
      decrementAmount(product, cart);
    }
  }

  private static void incrementAmount(Product product, Cart cart) {
    final Integer newValue = cart.getProducts().get(product) + 1;
    cart.getProducts().put(product, newValue);
  }

  private static void decrementAmount(Product product, Cart cart) {
    final Integer newValue = cart.getProducts().get(product) - 1;
    cart.getProducts().put(product, newValue);
  }

  public static boolean isEmpty(Cart cart) {
    return cart.getProducts().isEmpty();
  }

  public static Product getProduct(int position, Cart cart) {
    final Set<Product> values = cart.getProducts().keySet();
    ArrayList<Product> valueList = new ArrayList<>(values);
    if (valueList.isEmpty()) return null;
    final Product product = valueList.get(position);
    return product;
  }

  public static Integer getAmount(int position, Cart cart) {
    ArrayList<Integer> valueList = new ArrayList<>(cart.getProducts().values());
    if (valueList.isEmpty()) return null;
    return valueList.get(position);
  }

  public static String getCartTotalPrice(Cart cart) {
    int resultInt = 0;
    for (Map.Entry<Product, Integer> productEntry : cart.getProducts().entrySet()) {
      int productSum = productEntry.getKey().price * productEntry.getValue();
      resultInt += productSum;
    }
    double resultDouble = Product.convertIntToDoublePrice(resultInt);
    return String.format("%.2f", resultDouble);
  }

  public static int size(Cart cart) {
    return cart.getProducts().size();
  }

  public static int getSumOfTimeToWait(Cart cart) {
    int resultSum = 0;
    for (Map.Entry<Product, Integer> productEntry : cart.getProducts().entrySet()) {
      final int productTimeSum = productEntry.getKey().cookingTime * productEntry.getValue();
      resultSum += productTimeSum;
    }
    return resultSum;
  }

  public static Pair<Product, Integer> getEntry(int position, Cart cart) {
    // 1            1
    if (position >= cart.getProducts().size()) return null;

    final Product product = getProduct(position, cart);
    final Integer amount = getAmount(position, cart);

    if (product == null || amount == null) return null;

    Pair<Product, Integer> resultPair = new Pair<>(product, amount);
    return resultPair;
  }

  public static int getAmount(Product product, Cart cart) {
    final Integer integer = cart.getProducts().get(product);
    if (integer == null) {
      return 0;
    } else {
      return integer;
    }
    //return integer == null ? 0 : integer;
  }

  public static void setAmount(Product product, int amount, Cart cart) {
    for (int i = 0; i < amount; i++) {
      add(product, cart);
    }
  }

  public static int getAllProductsAmount(Cart cart) {
    int resultSum = 0;
    for (int i = 0; i < size(cart); i++) {
      final Integer amount = getAmount(i, cart);
      resultSum += amount;
    }
    return resultSum;
  }
>>>>>>> Stashed changes
}
