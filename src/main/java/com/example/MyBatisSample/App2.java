package com.example.MyBatisSample;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.entity.TUserInfo;
import com.example.entity.TUserInfoMapper;

/**
 * Hello world!
 *
 */
public class App2
{
    public static void main( String[] args ){

    // resources直下のmybatis-config.xmlを読み込みます(1)
    try (Reader r = Resources.getResourceAsReader("mybatis-config.xml");) {

        // 読み込んだ設定ファイルからSqlSessionFactoryを生成します(2)
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);

        // SQLセッションを取得します(3)
        try (SqlSession session = factory.openSession()) {

            // ActorテーブルのMapperを取得します(4)
          TUserInfoMapper map = session.getMapper(TUserInfoMapper.class);
            // Actorテーブルの主キー（actor_id)が10002であるレコードを検索します(5)
          TUserInfo tUserInfo = map.selectByPrimaryKey((String) "10002");

      // 取得した内容を確認します
            System.out.println("tUserInfo.getUserId     = " + tUserInfo.getUserId());
            System.out.println("tUserInfo.getUserPass   = " + tUserInfo.getUserPass());
            System.out.println("tUserInfo.getUserName   = " + tUserInfo.getUserName());
            System.out.println("tUserInfo.getUserSexKbn = " + tUserInfo.getUserSexKbn());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
