package tieorange.com.pjabuffetorders.pojo.api;

import com.google.firebase.database.Exclude;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.parceler.Parcel;
import tieorange.com.pjabuffetorders.ordersListLib.Product;
import tieorange.com.pjabuffetorders.utils.CartTools;

@Parcel public class Cart {
  @Exclude private Map<Product, Integer> products = new LinkedHashMap<>();

  private List<Product> productsFirebase = new ArrayList<>();

  public Cart() {
    setProducts(new LinkedHashMap<Product, Integer>());
  }

  @Exclude public void convertProductsToFirebase() {
    for (Map.Entry<Product, Integer> productEntry : products.entrySet()) {
      final Product product = productEntry.getKey();
      product.amount = productEntry.getValue();
      productsFirebase.add(product);
    }
  }

  @Exclude public void convertProductsFromFirebase() {
    for (Product product : productsFirebase) {
      products.put(product, product.amount);
    }
  }

  @Exclude public Map<Product, Integer> getProducts() {
    return products;
  }

  @Exclude public void setProducts(LinkedHashMap<Product, Integer> products) {
    this.products = products;
  }

  public List<Product> getProductsFirebase() {
    return productsFirebase;
  }

  public void setProductsFirebase(List<Product> productsFirebase) {
    this.productsFirebase = productsFirebase;
  }

  public int size() {
    return CartTools.getAllProductsAmount(this);
  }
}
