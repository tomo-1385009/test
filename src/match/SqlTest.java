package match;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlTest {

    public static void main(String[] args) throws Exception {

        // 準備。servicesとかは入ってないみたいなので、1.6でもforNameする。
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        Statement stmt = conn.createStatement();

        // テーブルを作る。
        // 実行するとgetConnection時に指定したtest.dbという名前のファイルが出来る。
        stmt.execute( "create table test1( str1 string, int1 integer )" );

        // とりあえず、１行INSERT。
        stmt.execute( "insert into test1 values ( 'abc', 1 )" );

        // SELECTして値を見てみる。
        ResultSet rs = stmt.executeQuery( "select * from test1" );
        while( rs.next() ) {
            System.out.println( rs.getString( 1 ) );
            System.out.println( rs.getInt( 2 ) );
        }

        // 閉じる
        conn.close();
    }
}