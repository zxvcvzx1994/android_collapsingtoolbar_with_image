package vo.cvcompany.com.myapplication.Module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kh on 8/9/2017.
 */

public class Mysqlite {
    private static  Mysqlite mysqlite;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private String DATABASE_NAME = "v2.db";
    private int DATABASE_VERSION = 1;
    private String TABLE = "vinh";
    private String USER = "user";
    private String NAME_FILE = "name_file";
    private String DESCRIPTION = "description";
    private String ID= "id";
    private String CREATE_TABLE ="CREATE TABLE "+TABLE+" ("+ID+" integer primary key , "+NAME_FILE+" text, "+DESCRIPTION+" text, hinhanh BLOB)";
    private  Mysqlite(Context context){
        this.context = context;
        sqLiteDatabase = new MySqliteOpenHelper(context,DATABASE_NAME, null,DATABASE_VERSION).getWritableDatabase();
    }

    public synchronized static Mysqlite getMysqlite(Context context){
        if(mysqlite==null)
            mysqlite=  new Mysqlite(context);
        return mysqlite;
    }

    public Cursor getData(){
        Cursor cursor = sqLiteDatabase.query(TABLE, new String[]{ID,NAME_FILE, DESCRIPTION },null,null,null,null,null);
        return cursor;
    }

    public   long insert(String name, String description, byte[] hinhanh){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_FILE, name );
        contentValues.put(DESCRIPTION, description);
        contentValues.put("hinhanh", hinhanh);
        return  sqLiteDatabase.insert(TABLE,null, contentValues);
    }



    private class MySqliteOpenHelper extends SQLiteOpenHelper {
        public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
