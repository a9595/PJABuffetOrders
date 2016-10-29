package tieorange.com.pjabuffetorders.pojo.api;

import com.google.firebase.database.Exclude;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.parceler.Parcel;

/**
 * Created by tieorange on 15/10/2016.
 */
@Parcel public class Product implements Cloneable {
  public String name;
  public int price;
  public int cookingTime;
  public String photoUrl;

  public Product() {
  }

  public Product(String name, double price, int cookingTime) {
    this.name = name;
    this.price = convertPriceDoubleToInt(price);
    this.cookingTime = cookingTime;
  }

  private static int convertPriceDoubleToInt(double price) {
    return (int) (price * 100);
  }

  public static List<Product> getDummy(int count) {
    List<Product> productList = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < count; i++) {
      int cookingTime = random.nextInt(20);
      double price = random.nextDouble() * 20;
      productList.add(new Product("Pierogi", price, cookingTime));
    }
    return productList;
  }

  @Exclude public String getStringPrice() {
    double priceDouble = price / 100f;
    return String.format("%.2f", priceDouble) + " zł";
  }

  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
