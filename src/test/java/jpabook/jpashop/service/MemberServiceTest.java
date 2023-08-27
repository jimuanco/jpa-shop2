package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
//    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("Ahn");

        //when
        Long savedId = memberService.join(member);

        em.flush(); //insert 쿼리 나감
        //then
//        Assertions.assertEquals(member, memberRepository.findOne(savedId));
        Assertions.assertThat(memberRepository.findOne(savedId)).isEqualTo(member);
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("Ahn");

        Member member2 = new Member();
        member2.setName("Ahn");

        //when
        memberService.join(member1);

        //then
        Assertions.assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }

}