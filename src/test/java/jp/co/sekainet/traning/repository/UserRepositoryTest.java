package jp.co.sekainet.traning.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jp.co.sekainet.traning.BootWebApplication;
import jp.co.sekainet.traning.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootWebApplication.class)
public class UserRepositoryTest {

    @Autowired private UserRepository userRepo;

    @Before
    public void Userテーブルを空にする() {
        userRepo.deleteAll();
    }

    @Test
    public void Userテーブルからレコードの取得と登録がおこなえる() {
        assertThat(userRepo.count(), equalTo(0L));
        User user = new User();
        user.setName("河野");
        user.setLoginId("y.kono");
        user.setPassword("password");

        assertThat(user.getId(), equalTo(0L));
        userRepo.save(user);
        assertThat(user.getId(), not(equalTo(0L)));

        List<User> users = userRepo.findAll();
        assertThat(users.size(), equalTo(1));
        assertThat(users.get(0), equalTo(user));
    }

    @Test
    public void findByNameでエンティティが検索できること() {
        // 検索対象のデータを準備
        User user = new User();
        user.setName("河野");
        user.setLoginId("y.kono");
        user.setPassword("password");
        userRepo.save(user);

        // 検索結果の件数が想定通りであることを検証
        assertThat(userRepo.findByName("清野").size(), equalTo(0));
        assertThat(userRepo.findByName("河野").size(), equalTo(1));
        assertThat(userRepo.findByName("河野"), hasItems(user));
    }

}