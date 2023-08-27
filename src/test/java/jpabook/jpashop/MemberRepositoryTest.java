//package jpabook.jpashop;
//
//import jpabook.jpashop.domain.Member;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//class MemberRepositoryTest {
//
//    @Autowired MemberRepository memberRepository;
//
//    //Settings -> Live Templates -> custom
//    //EntityManager를 통한 모든 데이터 변경은 트랜잭션 안에서 이루어져야 한다.
//    @Test
//    @Transactional //스프링꺼 권장 (쓸 수 있는 옵션이 많음)
//    @Rollback(false)
//    public void testMember() throws Exception {
//        //given
//        Member member = new Member();
//        member.setName("memberA");
//
//        //when
//        Long saveId = memberRepository.save(member);
//        Member findMember = memberRepository.find(saveId);
//
//        //then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
//        Assertions.assertThat(findMember).isEqualTo(member);
//        System.out.println("findMember == member: " + (findMember == member)); //같은 트랜잭션안에서 저장, 조회 -> 같은 엔티티
//    }
//}