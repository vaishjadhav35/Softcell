package in.vaishnavi.softcell;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by vaishnavi on 29/12/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static int DATABASE_VERSION= 1;

    private static String DATABASE_NAME="UserDatabase.db";

    private static  final String TABLE_NAME="User";

    private static final String COLUMN_USERNAME="Username";
    private static final String COLUMN_PASSWORD="Password";

    private static final String CREATE_TABLE_USER = " CREATE TABLE " + TABLE_NAME + "(" + COLUMN_USERNAME + " VARCHAR," + COLUMN_PASSWORD + " VARCHAR" + ")";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext=context;
        this.DATABASE_NAME=name;
        this.DATABASE_VERSION=version;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME );
        onCreate(db);

    }


    public long insertData(User user){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_USERNAME,user.getmUserName());
        contentValues.put(COLUMN_PASSWORD,user.getmPassword());

        long row=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        Log.d("Row ID","Row:"+row);

        if (row>=0){
            Toast.makeText(mContext," Registration Successful",Toast.LENGTH_SHORT).show();

        }else
            {
            Toast.makeText(mContext,"Registration Failed",Toast.LENGTH_SHORT).show();

        }
        sqLiteDatabase.close();
        return row;
    }

    public boolean validateData(String Username,String Password){

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        String select_query=" SELECT * FROM " + TABLE_NAME +" WHERE " + COLUMN_USERNAME +
                " = " + "'"+Username+"'" + " AND " + COLUMN_PASSWORD + " = "+ "'"+Password+"'";

        Cursor cursor=sqLiteDatabase.rawQuery(select_query,null);

        if (cursor.getCount() > 0) {
            return true;
        }

        cursor.close();
        sqLiteDatabase.close();


        return false;
    }


}
