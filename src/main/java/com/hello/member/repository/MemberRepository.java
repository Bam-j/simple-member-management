package com.hello.member.repository;

import com.hello.member.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {   //JpaRepository<엔티티 클래스, PK 타입>
    //이메일로 회원 정보 조회 (SELECT * FROM member_table WHERE member_email=?)
    Optional<MemberEntity> findByMemberEmail(String userEmail);
}
