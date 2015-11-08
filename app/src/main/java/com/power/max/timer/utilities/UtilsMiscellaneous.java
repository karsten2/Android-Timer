package com.power.max.timer.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;

/**
 * Created by Karsten on 18.09.2015.
 */
public class UtilsMiscellaneous {
    /**
     * Returns the size in pixels of an attribute dimension
     *
     * @param context the context to get the resources from
     * @param attr is the attribute dimension we want to know the size from
     *
     * @return the size in pixels of an attribute dimension
     */
    public static int getThemeAttributeDimensionSize(@NonNull final Context context, final int attr)
    {
        TypedArray a = null;

        try
        {
            a = context.getTheme().obtainStyledAttributes(new int[] { attr });
            return a.getDimensionPixelSize(0, 0);
        }
        finally
        {
            if(a != null)
            {
                a.recycle();
            }
        }
    }
}
