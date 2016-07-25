package jp.co.sekainet.traning.entity;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class UserTest {

    @Test
    public void Userインスタンス同士の比較が正しく行えること() {
        User user = new User();
        user.setId(1);
        user.setLoginId("y.kono");
        user.setPassword("password");
        user.setName("河野");

        User user2 = new User();
        user2.setId(1);
        user2.setLoginId("y.kono");
        user2.setPassword("password");
        user2.setName("河野");

        assertThat(user, equalTo(user));
        assertThat(user, equalTo(user2));
    }

//    @Test
//    public void idに0以下の値は設定できない() {
//        User user = new User();
//        try {
//            user.setId(0L);
//            fail("idに0Lは設定できない");
//        } catch (IllegalArgumentException ok) {}
//        try {
//            user.setId(-1L);
//            fail("idに-1Lは設定できない");
//        } catch (IllegalArgumentException ok) {}
//        // 例外が発生しないこと
//        user.setId(1L);
//    }

    @Test
    public void シリアライズとデシリアライズが行えること() throws Exception {
        User user = new User();
        user.setId(1);
        user.setLoginId("y.kono");
        user.setPassword("password");
        user.setName("河野");

        // userをバイト配列へシリアライズ
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream);
        objectOutStream.writeObject(user);
        byte[] serializedUser = byteOutStream.toByteArray();
        System.out.println("serializedUser: " + new String(serializedUser));

        // バイト配列からUserオブジェクトへデシリアライズ
        ByteArrayInputStream byteInStream = new ByteArrayInputStream(serializedUser);
        ObjectInputStream objectInStream = new ObjectInputStream(byteInStream);
        User deserializedUser = (User)objectInStream.readObject();

        assertThat(user, equalTo(deserializedUser));
    }
}
