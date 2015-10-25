package com.mfarag.learn.content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by muhammadfarag on 10/25/15.
 */
public class ItemProvider extends ContentProvider {
    public static final int SINGLE_ITEM = 100;
    public static final int MULTIPLE_ITEMS = 101;
    public static UriMatcher sMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(ItemsContract.CONTENT_AUTHORITY, ItemsContract.PATH_ITEMS + "/#", SINGLE_ITEM);
        matcher.addURI(ItemsContract.CONTENT_AUTHORITY, ItemsContract.PATH_ITEMS, MULTIPLE_ITEMS);
        return matcher;
    }

    // Returns true if the provider is constructed successfully or false otherwise
    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sMatcher.match(uri)) {
            case SINGLE_ITEM:
                return ItemsContract.ItemsEntry.CONTENT_ITEM_TYPE;
            case MULTIPLE_ITEMS:
                return ItemsContract.ItemsEntry.CONTENT_DIR_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
