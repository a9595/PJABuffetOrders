package tieorange.com.pjabuffetorders.utils;

/**
 * Created by tieorange on 03/11/2016.
 */

public class FirebaseDummyTools {

  /*private void addDummyOrder() {
    MyApplication.sProductsReference.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override public void onDataChange(DataSnapshot dataSnapshot) {
        List<Product> productsList = new ArrayList<Product>();

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
          Product product = snapshot.getValue(Product.class);
          productsList.add(product);
        }

        finishAddingDummyOrder(productsList);
      }

      @Override public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  private void finishAddingDummyOrder(List<Product> productsList) {
    Order order = new Order();
    order.setClientName("Andrii");
    order.getProductsCart().add(productsList.get(0));
    order.getProductsCart().add(productsList.get(1));

    MyApplication.sOrdersReference.push().setValue(order);
  }*/
}
