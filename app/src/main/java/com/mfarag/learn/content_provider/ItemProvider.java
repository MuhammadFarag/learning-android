package com.mfarag.learn.content_provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammadfarag on 10/25/15.
 */
public class ItemProvider extends ContentProvider {
    public static final int SINGLE_ITEM = 100;
    public static final int MULTIPLE_ITEMS = 101;
    public static UriMatcher sMatcher = buildUriMatcher();

    public static List<String> sElements = new ArrayList<>();

    public static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(ItemsContract.CONTENT_AUTHORITY, ItemsContract.PATH_ITEMS + "/#", SINGLE_ITEM);
        matcher.addURI(ItemsContract.CONTENT_AUTHORITY, ItemsContract.PATH_ITEMS, MULTIPLE_ITEMS);
        return matcher;
    }

    // Returns true if the provider is constructed successfully or false otherwise
    @Override
    public boolean onCreate() {
        sElements.add("item 1");
        sElements.add("item 2");
        sElements.add("item 3");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return new ResultCursor(sElements);
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


class ResultCursor implements Cursor {

    private List<String> elements;
    private int position = -1;

    public ResultCursor(List<String> elements) {
        this.elements = elements;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public boolean move(int i) {
        if (position + i < 0) {
            position = -1;
            return false;
        } else if (position + i > elements.size() - 1) {
            position = getColumnCount();
            return false;
        }
        position = position + i;
        return true;
    }

    @Override
    public boolean moveToPosition(int i) {
        if (i >= -1 && i <= getColumnCount()) {
            position = i;
            return true;
        }
        return false;
    }

    @Override
    public boolean moveToFirst() {
        if (getCount() == 0) {
            return false;
        }
        position = 0;
        return true;
    }

    @Override
    public boolean moveToLast() {
        if (getCount() == 0) {
            return false;
        }
        position = getCount() - 1;
        return true;
    }

    @Override
    public boolean moveToNext() {
        if (position >= getCount()-1) {
            return false;
        }
        position++;
        return true;
    }

    @Override
    public boolean moveToPrevious() {
        if (position <= -1)
            return false;
        position--;
        return true;
    }

    @Override
    public boolean isFirst() {
        if (position == 0) return true;
        return false;
    }

    @Override
    public boolean isLast() {
        if (position == getCount() - 1) return true;
        return false;
    }

    @Override
    public boolean isBeforeFirst() {
        if (position == -1) return true;
        return false;
    }

    @Override
    public boolean isAfterLast() {
        if (position == getCount()) return true;
        return false;
    }

    @Override
    public int getColumnIndex(String s) {
        return 0;
    }

    @Override
    public int getColumnIndexOrThrow(String s) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public String getColumnName(int i) {
        return "";
    }

    @Override
    public String[] getColumnNames() {
        return new String[0];
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public byte[] getBlob(int i) {
        return new byte[0];
    }

    @Override
    public String getString(int i) {
        return elements.get(position);
    }

    @Override
    public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {

    }

    @Override
    public short getShort(int i) {
        return 0;
    }

    @Override
    public int getInt(int i) {
        return 0;
    }

    @Override
    public long getLong(int i) {
        return 0;
    }

    @Override
    public float getFloat(int i) {
        return 0;
    }

    @Override
    public double getDouble(int i) {
        return 0;
    }

    @Override
    public int getType(int i) {
        return FIELD_TYPE_STRING;
    }

    @Override
    public boolean isNull(int i) {
        return false;
    }

    @Override
    public void deactivate() {

    }

    @Override
    public boolean requery() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void registerContentObserver(ContentObserver contentObserver) {

    }

    @Override
    public void unregisterContentObserver(ContentObserver contentObserver) {

    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {

    }

    @Override
    public Uri getNotificationUri() {
        return null;
    }

    @Override
    public boolean getWantsAllOnMoveCalls() {
        return false;
    }

    @Override
    public Bundle getExtras() {
        return null;
    }

    @Override
    public Bundle respond(Bundle bundle) {
        return Bundle.EMPTY;
    }

    @Override
    public String toString() {
        return "ResultCursor{" +
                "position=" + position +
                ", elements=" + elements +
                '}';
    }
}