package com.hello.member.service;

import com.hello.member.dto.MemberDTO;
import com.hello.member.entity.MemberEntity;
import com.hello.member.repository.MemberRepository;
import java.util.Optional;
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

    public MemberDTO login(MemberDTO memberDTO) {
        //1. 회원이 입력한 이메일로 DB에서 조회
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(
            memberDTO.getMemberEmail());

        //2. DB에서 조회한 비밀번호화 사용자가 입력한 비밀번호가 일치하는지 확인
        if (byMemberEmail.isPresent()) {
            //조회 결과 -> 해당 이메일을 가진 회원이 있다.
            MemberEntity memberEntity = byMemberEmail.get();

            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                //비밀번호가 일치. 엔티티를 dto로 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);

                return dto;
            } else {
                //비밀번호가 불일치
                return null;
            }
        } else {
            //조회 결과 -> 해당 이메일을 가진 회원이 없다.
            return null;
        }
    }
}
