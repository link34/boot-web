package jp.co.sekainet.traning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sekainet.traning.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    User findByLoginId(String loginId);

}
