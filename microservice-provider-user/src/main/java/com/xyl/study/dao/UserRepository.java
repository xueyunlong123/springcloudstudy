package com.xyl.study.dao;

import com.xyl.study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xyl on 10/30/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
