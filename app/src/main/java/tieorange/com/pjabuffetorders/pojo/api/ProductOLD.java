package tieorange.com.pjabuffetorders.pojo.api;

import java.util.UUID;

/**
 * Created by tieorange on 26/10/2016.
 */
public class ProductOLD {
  public String id;
  public String name;
  public Integer price;
  public Integer cookingTime;
  public String photoUrl;

  public ProductOLD(String name, Integer price, Integer cookingTime) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.price = price;
    this.cookingTime = cookingTime;
    this.photoUrl = "https://www.nalesniki.eu/wp-content/uploads/2015/10/nalesniki_z_malinami.png";
  }

  public String getStringPrice() {
    double floatPrice = price / 100f;
    return String.valueOf(floatPrice);
  }
}
