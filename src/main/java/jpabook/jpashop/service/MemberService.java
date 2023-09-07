package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기에는 이렇게 해주면 최적화 됨
//@AllArgsConstructor
@RequiredArgsConstructor //final 필드로 생성자
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member); //em.persist(member) -> 영속성 컨텍스트에 member를 올림 (id 값이 키가 되기때문에 이 시점에 id가 세팅됨)
        return member.getId();
    }

    //간단하게 멤버수가 0보다 크면~ 으로 하는게 더 최적화
    //이 로직이 있더라도 동시성을 고려해서 DB에 member의 name을 유니크 제약 조건으로 걸어줘야 한다.
    private void validateDuplicateMember(Member member) {
        List<Member> findMember = memberRepository.findByName(member.getName());
        if (!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }
}
