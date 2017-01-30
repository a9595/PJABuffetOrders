package tieorange.com.pjabuffetorders.ordersListLib;

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
  @Exclude public String key;
  public Integer amount;

  public Product() {
  }

  public Product(String name, double price, int cookingTime) {
    this.name = name;
    this.price = convertPriceDoubleToInt(price);
    this.cookingTime = cookingTime;
  }

  public static int convertPriceDoubleToInt(double price) {
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

  public static double convertIntToDoublePrice(int result) {
    return result / 100f;
  }

  @Exclude public String getStringPriceWithZlote() {
    double priceDouble = getDoublePrice();
    return String.format("%.2f", priceDouble) + " zł";
  }

  @Exclude public String getStringPrice() {
    double priceDouble = getDoublePrice();
    return String.format("%.2f", priceDouble);
  }

  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public double getDoublePrice() {
    return price / 100f;
  }




    /*//region Parcel
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.price);
        dest.writeInt(this.cookingTime);
        dest.writeString(this.photoUrl);
        dest.writeString(this.key);
    }

    protected Product(Parcel in) {
        this.name = in.readString();
        this.price = in.readInt();
        this.cookingTime = in.readInt();
        this.photoUrl = in.readString();
        this.key = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    //endregion*/
}
