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
public class OrdersFragment extends android.support.v4.app.Fragment {

  public static OrdersFragment newInstance() {
    Bundle args = new Bundle();
    OrdersFragment fragment = new OrdersFragment();
    fragment.setArguments(args);
    return fragment;
  }

  public OrdersFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_orders, container, false);
  }
}
