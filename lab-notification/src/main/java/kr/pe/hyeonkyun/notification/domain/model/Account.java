package kr.pe.hyeonkyun.notification.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    /**
     * 사용자 ID (PK)
     */
    @Id
    @Column(name = "USER_ID", length = 50, nullable = false)
    private String userId;

    /**
     * 비밀번호 (BCrypt 암호화된 값)
     */
    @Column(name = "PASSWORD", length = 128)
    private String password;

    /**
     * 상태 코드 (0:요청전, 1:등록요청, 2:등록취소, 3:반려, 4:등록승인)
     */
    @Column(name = "STATUS_CD", columnDefinition = "CHAR(1) DEFAULT '1'")
    private String statusCd = "1";

    /**
     * 요청 사유
     */
    @Column(name = "REQ_REASON", length = 1000)
    private String reqReason;
}