package tieorange.com.pjabuffetorders.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tieorange.com.pjabuffetorders.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends android.support.v4.app.Fragment {

  public static ProductsFragment newInstance() {
    Bundle args = new Bundle();
    ProductsFragment fragment = new ProductsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public ProductsFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_products, container, false);
  }
}
