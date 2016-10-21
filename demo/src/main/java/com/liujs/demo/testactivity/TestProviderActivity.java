package com.liujs.demo.testactivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.liujs.demo.R;
import com.liujs.demo.providerdemo.bean.Book;
import com.liujs.library.base.BaseActivity;
import com.liujs.library.utils.TextUtil;

import java.math.BigDecimal;

public class TestProviderActivity extends BaseActivity {

    private  EditText mEditText;
    private EditText bookNameText;
    private   TextView bookMsg;
    private   Uri bookUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_provider);
         mEditText = (EditText)this.findViewById(R.id.id_editText);
        bookNameText = (EditText)this.findViewById(R.id.name_editText);

        Button insert_button = (Button)this.findViewById(R.id.insert_button);
        Button read_button = (Button)this.findViewById(R.id.read_button);
        Button delete_button = (Button)this.findViewById(R.id.delete_button);
        Button update_button = (Button)this.findViewById(R.id.update_button);
        insert_button.setOnClickListener(clickListener);
        read_button.setOnClickListener(clickListener);
        delete_button.setOnClickListener(clickListener);
        update_button.setOnClickListener(clickListener);

         bookMsg = (TextView)this.findViewById(R.id.read_result_tv);

        bookUri = Uri.parse("content://mz.privider.test/book");

        initBar();
    }
   private void initBar(){
       initActionBar();
       setTitle("ContentProvide演示");
       setNextLogo(R.mipmap.persional);
   }

    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.insert_button://contenprovider 插入数据
                 String bookId = mEditText.getText().toString();
                    String bookName = bookNameText.getText().toString();
                    if(TextUtil.isEmpty(bookId)||TextUtil.isEmpty(bookName)){
                        Toast.makeText(TestProviderActivity.this,"请输入完整信息",Toast.LENGTH_LONG).show();
                        return;
                    }
                    ContentValues values = new ContentValues();
                    values.put("_id",bookId);
                    values.put("name",bookName);
                    Uri mUri =  getContentResolver().insert(bookUri,values);
                    if(mUri!=null){
                        Toast.makeText(TestProviderActivity.this,"插入成功,哈哈！",Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.read_button://读取数据
                    Cursor bookCursor = getContentResolver().query(bookUri,new String[]{"_id","name"},null,null,null);
                    StringBuilder stringBuilder = new StringBuilder();
                    while (bookCursor.moveToNext()){
                        Book book = new Book();
                        book.setBookId(bookCursor.getInt(0));
                        book.setBookName(bookCursor.getString(1));
                        stringBuilder.append(book.toString()+"\n");
                        Log.d("MainActivity","query book:"+book.toString());
                    }
                    bookCursor.close();
                    bookMsg.setText(stringBuilder);
                    break;
                case R.id.delete_button://删除数据
                    int count = getContentResolver().delete(bookUri,null,null);
                    if(count > 0) {
                        bookMsg.setText("");
                        Toast.makeText(TestProviderActivity.this,"删除成功,哈哈！",Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.update_button://修改数据
                    String updateBookId = mEditText.getText().toString();
                    String updateBookName = bookNameText.getText().toString();
                    if(TextUtil.isEmpty(updateBookId)||TextUtil.isEmpty(updateBookName)){
                        Toast.makeText(TestProviderActivity.this,"请输入完整信息",Toast.LENGTH_LONG).show();
                        return;
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_id",updateBookId);
                    contentValues.put("name",updateBookName);
                    int  updateCount = getContentResolver().update(bookUri,contentValues,"_id=?",new String[]{updateBookId});
                    if(updateCount> 0){
                        Toast.makeText(TestProviderActivity.this,"修改成功！",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(TestProviderActivity.this,"修改失败！",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };
}
