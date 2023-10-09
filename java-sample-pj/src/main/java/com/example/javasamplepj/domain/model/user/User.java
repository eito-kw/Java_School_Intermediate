package com.example.javasamplepj.domain.model.user;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    // ユーザID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    // ユーザ名
    private String userName;
    // パスワード
    private String password;
    // メールアドレス
    private String mailAddress;
    // 登録日時
    private Date createDate;
    // 更新日時
    private Date updateDate;
    // 削除日時
    private Date deleteDate;
}