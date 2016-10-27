package tieorange.com.pjabuffetorders.activities.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tieorange on 15/10/2016.
 */

public class GridItemSpacingDecorator extends RecyclerView.ItemDecoration {
  private final int columnCount;
  private final int spaceInPixels;

  public GridItemSpacingDecorator(int columnCount, int spaceInPixels) {
    this.columnCount = columnCount;
    this.spaceInPixels = spaceInPixels;
  }

  public GridItemSpacingDecorator(int columnCount, int spaceInPixels, float densityMultiplier) {
    this.columnCount = columnCount;
    this.spaceInPixels = (int) (spaceInPixels * densityMultiplier);
  }

  /*@Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    // We always want to add right/bottom spaceInPixels
    outRect.right = spaceInPixels;
    outRect.bottom = spaceInPixels;

    //int spanIndex = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();


    // Only add top margin for the first row
    if (parent.getChildPosition(view) < columnCount) {
      outRect.top = spaceInPixels;
    }

    // Only add left margin for the left-most items
    if (parent.getChildPosition(view) % columnCount == 0) {
      outRect.left = spaceInPixels;
    }
  }
*/

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    // We always want to add right/bottom spaceInPixels
    outRect.bottom = spaceInPixels;
    outRect.top = spaceInPixels;
    outRect.right = spaceInPixels;
    outRect.left = spaceInPixels;

    /*int spanIndex = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();

    if (spanIndex == 0) {
      outRect.left = spaceInPixels;
      //outRect.right = spaceInPixels / 2;
    } else {
      //outRect.left = spaceInPixels / 2;
      outRect.right = spaceInPixels;
    }*/
  }
}