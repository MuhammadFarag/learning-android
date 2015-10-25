package com.mfarag.learn.content_provider;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Created by muhammadfarag on 10/25/15.
 */
public class ItemsContract {
    public static final String CONTENT_AUTHORITY = "com.mfarag.learn.content_provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_ITEMS = "items";

    public static final class ItemsEntry {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ITEMS).build();
        public static final String CONTENT_TYPE = ContentResolver.ANY_CURSOR_ITEM_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ITEMS;
    }

}
