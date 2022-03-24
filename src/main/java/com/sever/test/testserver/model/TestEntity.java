package com.sever.test.testserver.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class TestEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw", nullable = false, length = 30)
    private String userPw;

    @Column(name = "user_nickname", length = 25)
    private String userNickName;

    @Column(name = "join_date")
    private Date joinDate;

    @Builder
    public TestEntity(String userId, String userPw, String userNickName, Date date) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNickName = userNickName;
        this.joinDate = date;
    }
}
