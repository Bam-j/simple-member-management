package com.hello.member.entity;

import com.hello.member.dto.MemberDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//엔티티는 일종의 table의 역할을 한다.
//Spring Data JPA는 DB의 테이블을 자바 객체처럼 활용할 수 있도록 해주는 특징이 있다.
@Entity
@Setter
@Getter
@Table(name = "member_table")   //DB에 클래스에서 정의한대로 테이블이 생성된다. 옵션 name은 테이블 이름
public class MemberEntity {
    @Id //primary key 정의
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment 지정(mysql)
    private Long id;

    @Column(unique = true) //일반 컬럼 정의. unique 조건 추가
    private String memberEmail; //기본적으로 컬럼 삽입시 member_email 형태로 들어감

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    //DTO 객체를 Entity로 변환 메소드
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());

        return memberEntity;
    }
}
