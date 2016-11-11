package tieorange.com.pjabuffetorders.pojo.api;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.google.firebase.database.Exclude;
import com.tieorange.orderlistlibrary.Product;

import java.util.ArrayList;
import java.util.List;

import org.parceler.Parcel;

import tieorange.com.pjabuffetorders.R;

/**
 * Created by tieorange on 03/11/2016.
 */
@Parcel
public class Order {
    public static final int STATE_ORDERED = 1;
    public static final int STATE_ACCEPTED = 2;
    public static final int STATE_READY = 3;
    public static final int STATE_REJECTED = -1;

    public List<Product> products = new ArrayList<>();
    public String clientName;
    public int status;
    @Exclude
    public String key;

    public Order() {
    }

    @Exclude
    public String getStatusString(Context context) {
        String statusNow = null;
        if (status == STATE_ACCEPTED) {
            statusNow = context.getString(R.string.status_accepted);
        } else if (status == STATE_ORDERED) {
            statusNow = context.getString(R.string.status_ordered);
        } else if (status == STATE_READY) {
            statusNow = context.getString(R.string.status_ready);
        } else if (status == STATE_REJECTED) {
            statusNow = context.getString(R.string.status_rejected);
        }
        return statusNow;
    }
}
