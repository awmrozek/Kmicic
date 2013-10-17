package com.amrozek.kmicic;
 
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
 
import java.util.ArrayList;
import java.util.List;
 
public class MyDataHelper1 {
   public static final String TAG = "[Kmicic][database] ** ";
   private static final String DATABASE_NAME = "slownik.db";
   private static final int DATABASE_VERSION = 1;
   private static final String TABLE_NAME = "dictionary";
 
   private Context context;
   private SQLiteDatabase db;
 
   private SQLiteStatement insertStmt;
   private static final String INSERT = "insert into "
      + TABLE_NAME + "(name) values (?)";
 
   public MyDataHelper1(Context context) {
      Log.v (TAG, " -- Starting MyDataHelper1 --");
      this.context = context;
      OpenHelper openHelper = new OpenHelper(this.context);
      this.db = openHelper.getReadableDatabase();
//      this.insertStmt = this.db.compileStatement(INSERT);

	  Log.v (TAG, "  -- Constructor initialized --");
   }
 
   public List<String> select(String query) {
      List<String> list = new ArrayList<String>();
	  String sql = "latin LIKE '%" + query + "%';";
	  Cursor cursor = this.db.query(TABLE_NAME, new String[] { "myWord" },
	  	sql, null, null, null, "myWord");
	  int count = 0;
	  if (cursor.moveToFirst()) {
	     do {
		    list.add(cursor.getString(0));
		 } while (cursor.moveToNext() && count++ < 10);
	  }
	  if (cursor != null && !cursor.isClosed()) {
	  	cursor.close();
	  }
	  return list;
   }

   public List<String> selectAll() {
      List<String> list = new ArrayList<String>();
      Cursor cursor = this.db.query(TABLE_NAME, new String[] { "myWord" },
        null, null, null, null, "myWord");
	  int count = 0;
      if (cursor.moveToFirst()) {
         do {
            list.add(cursor.getString(0));
         } while (cursor.moveToNext() && count++ < 5);
      }
      if (cursor != null && !cursor.isClosed()) {
         cursor.close();
      }
      return list;
   }
 
   private static class OpenHelper extends SQLiteOpenHelper {
 
      OpenHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
      }
 
      @Override
      public void onCreate(SQLiteDatabase db) {
         //db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name TEXT)");
      }
 
      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 //        Log.w("Example", "Upgrading database, this will drop tables and recreate.");
   //      db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
     //    onCreate(db);
      }
   }
}
