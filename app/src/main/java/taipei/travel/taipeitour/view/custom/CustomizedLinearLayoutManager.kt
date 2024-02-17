package taipei.travel.taipeitour.view.custom

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomizedLinearLayoutManager(ctx: Context) : LinearLayoutManager(ctx) {
    /**
     * Return false or the [RecyclerView] will scroll to the top automatically
     * after certain actions by user in certain Android SDK versions
     */
    override fun requestChildRectangleOnScreen(
            parent: RecyclerView,
            child: View,
            rect: Rect,
            immediate: Boolean,
            focusedChildVisible: Boolean
    ) = false
}
