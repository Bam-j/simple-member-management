package com.hello.member.repository;

import com.hello.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {   //JpaRepository<엔티티 클래스, PK 타입>

}
