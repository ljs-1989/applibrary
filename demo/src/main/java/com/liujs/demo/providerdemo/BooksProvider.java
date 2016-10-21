package com.liujs.demo.providerdemo;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.liujs.library.data.BaseContentProvider;

/**
 * Created by liujs on 2016/10/20.
 * 邮箱：725459481@qq.com
 */

public class BooksProvider extends BaseContentProvider {

    private static final String DB_NAME = "book_provider.db";
    private static final String BOOK_TABLE_NAME = "book";
    private static final String USER_TALBE_NAME = "user";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/user");
    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;
    private static final UriMatcher sUriMathcer ;
    private static final int DB_VERSION = 1;
    //图书和用户信息表
    private final String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS "+BOOK_TABLE_NAME+
            "(_id INTEGER PRIMARY KEY,"+"name TEXT)";
    private final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "+ USER_TALBE_NAME+
            "(_id INTEGER PRIMARY KEY, name TEXT, sex INF)";


    static {
        sUriMathcer = new UriMatcher(UriMatcher.NO_MATCH);
        AUTHORITY = "mz.privider.test";
        sUriMathcer.addURI(AUTHORITY,"book",BOOK_URI_CODE);
        sUriMathcer.addURI(AUTHORITY,"user",USER_URI_CODE);
    }

    private String getTableName(Uri uri){
        String tableName = null;
        switch (sUriMathcer.match(uri)){
            case BOOK_URI_CODE:
                tableName =BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = USER_TALBE_NAME;
                break;
        }
        return tableName;
    }
    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    protected int getDatabaseVersion() {
        return DB_VERSION;
    }

    @Override
    protected void onDatabaseCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    protected void onDatabaseUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          // *****数据库升级逻辑*****//
        db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TALBE_NAME);
        onDatabaseCreate(db);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d("BookProvider","query,current thread:"+Thread.currentThread().getName());
        String table = getTableName(uri);
        if(table==null){
            throw new IllegalArgumentException("Unsupported URI:"+uri);
        }
        return getReadableDatabase().query(table,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String table = getTableName(uri);
        if(table==null){
            throw new IllegalArgumentException("Unsupported URI:"+uri);
        }
        long insertResult =  getWritableDatabase().insert(table,null,values);
        if (insertResult > 0) {
            Uri pUri = ContentUris.withAppendedId(BOOK_CONTENT_URI, insertResult);
            getContext().getContentResolver().notifyChange(pUri, null);
            return pUri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String table  = getTableName(uri);
     int count =   getReadableDatabase().delete(table,selection,selectionArgs);
        if(count > 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String table  = getTableName(uri);
        SQLiteDatabase db = getWritableDatabase();
        int count = db.update(table,values,selection,selectionArgs);
        if(count > 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }
}
