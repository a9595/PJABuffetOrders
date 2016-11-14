package tieorange.com.pjabuffetorders.pojo.api;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.firebase.database.Exclude;
import com.tieorange.orderlistlibrary.Product;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import tieorange.com.pjabuffetorders.R;

/**
 * Created by tieorange on 03/11/2016.
 */
@Parcel
public class Order extends BaseObservable {
    public static final int STATE_ORDERED = 1;
    public static final int STATE_ACCEPTED = 2;
    public static final int STATE_READY = 3;
    public static final int STATE_REJECTED = -1;

    private List<Product> products = new ArrayList<>();
    private String clientName;
    private int status;
    @Exclude
    private String key;

    @Exclude
    private int position;

    public Order() {
    }

    @Exclude
    public String getStatusString(Context context) {
        String statusNow = "NO STATE";
        if (getStatus() == STATE_ACCEPTED) {
            statusNow = context.getString(R.string.status_accepted);
        } else if (getStatus() == STATE_ORDERED) {
            statusNow = context.getString(R.string.status_ordered);
        } else if (getStatus() == STATE_READY) {
            statusNow = context.getString(R.string.status_ready);
        } else if (getStatus() == STATE_REJECTED) {
            statusNow = context.getString(R.string.status_rejected);
        }
        return statusNow;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Bindable
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.products);
    }

    @Bindable
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.clientName);
    }

    @Bindable
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.status);
    }

    @Bindable
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.position);
    }
}
