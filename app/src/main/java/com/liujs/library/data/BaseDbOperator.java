
package com.liujs.library.data;

import android.content.ContentValues;
import android.content.Context;

import java.util.List;

/**
 * 数据库表操作类
 * 
 * @author liujs
 */
public abstract class BaseDbOperator<T extends Object> {

    protected Context mContext;

    public BaseDbOperator(Context context) {
        mContext = context;
    }

    /**
     * 新增
     * 
     * @param t
     * @return
     */
    public abstract long insert(T t);

    /**
     * 更新
     * 
     * @param t
     * @param selection
     * @param selectionArgs
     * @return
     */
    public abstract long update(ContentValues cv, String selection, String[] selectionArgs);

    /**
     * 删除
     * 
     * @param selection
     * @param selectionArgs
     * @return
     */
    public abstract long delete(String selection, String[] selectionArgs);

    /**
     * 查询
     * 
     * @param selection
     * @param selectionArgs
     * @param orderby
     * @return
     */
    public abstract List<T> query(String selection, String[] selectionArgs, String orderby);

    /**
     * 查询
     * 
     * @param selection
     * @param selectionArgs
     * @param orderby
     * @param limit
     * @return
     */
    public abstract List<T> query(String selection, String[] selectionArgs, String orderby,
            int limit);

    /**
     * 查询
     * 
     * @param selection
     * @param selectionArgs
     * @return
     */
    public abstract T query(String selection, String[] selectionArgs);

    /**
     * 获取数量
     * 
     * @param selection
     * @param selectionArgs
     * @return
     */
    public abstract int getCount(String selection, String[] selectionArgs);
}
