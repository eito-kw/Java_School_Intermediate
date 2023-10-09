// UserService クラス

package com.example.javasamplepj.domain.service.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.javasamplepj.domain.model.user.User;
import com.example.javasamplepj.domain.model.user.UserRequest;
import com.example.javasamplepj.domain.repository.UserRepository;
import com.example.javasamplepj.domain.util.user.PasswordUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> searchAll() {
    return userRepository.findAll();
  }

  public void insertUser(UserRequest userRequest) {
    User user = createUser(userRequest);
    userRepository.save(user); // この行を修正しました
  }

  private User createUser(UserRequest userRequest) {
    String hashPw;
    Date now = new Date();
    User user = new User();
    hashPw = PasswordUtil.hashSHA256(userRequest.getPassword());
    user.setUserName(userRequest.getUserName());
    user.setPassword(hashPw);
    user.setMailAddress(userRequest.getMailAddress());
    user.setCreateDate(now);
    user.setUpdateDate(now);
    return user;
  }
}
