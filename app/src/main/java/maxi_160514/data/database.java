package maxi_160514.custom.maxi_data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
	public static String dbName = "cc";
	public static int version = 1;

	public database(Context context) {
		super(context, dbName, null, version);
	}

	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + "news" + "(" + "user_account"
				+ " char(10) primary key," + "user_nick" + " char(10),"
				+ "news_num" + " int(4))";
		db.execSQL(sql);

	}

	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

	/*public void insert(ccUser cc) {System.out.println("-----------------------数据库有插入");

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select user_account from news where user_account=?",
				new String[] { cc.account });
		if (cursor.getCount() == 0) {

			String sql = "insert into news values('" + cc.account + "','"
					+ cc.nick + "'," + cc.getNewsNumToOrther() + ")";
			db = this.getWritableDatabase();
			db.execSQL(sql);
		}

	}

	public List<ccUser> getData() {

		ArrayList<ccUser> ccList = new ArrayList<ccUser>();

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from news", null);
		while (cursor.moveToNext()) {
			ccUser cc = new ccUser();
			cc.account = cursor.getString(0); // 获取第一列的值,第一列的索引从0开始
			cc.nick = cursor.getString(1);// 获取第二列的值
			cc.setNewsNumToOrther(cursor.getInt(2));// 获取第三列的值
			ccList.add(cc);

		}
		cursor.close();
		db.close();
		return ccList;

	}

	public void delete(String user_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("news", "user_account=?", new String[] { user_id });
		db.close();
	}

	public void updata(String user_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "update news set news_num='0' where user_account='"+user_id+"'; ";
		
		db.execSQL(sql);
	}

	public void deleteDateBase(Context context) {
		context.deleteDatabase(dbName);
	}*/

}
