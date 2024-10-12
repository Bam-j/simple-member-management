package com.hello.member.service;

import com.hello.member.dto.MemberDTO;
import com.hello.member.entity.MemberEntity;
import com.hello.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        //repository save() 메소드 호출 (JPA repo 사용시 조건: entity를 넘겨줘야함)

        //1. DTO -> Entity 객체로 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        //2. repository의 save 호출 (jpa가 insert 쿼리를 만들어서 execute하는 역할)
        memberRepository.save(memberEntity);
    }
}
