//package jpabook.jpashop;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jpabook.jpashop.domain.Member;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class MemberRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    //커맨드와 쿼리를 분리
//    //저장은 사이드 이펙트를 일으킬 수 있는 커맨드
//    //리턴값을 id 정도로만 한다. (Member 반환 X)
//    public Long save(Member member) {
//        em.persist(member);
//        return member.getId();
//    }
//
//    public Member find(Long id) {
//        return em.find(Member.class, id);
//    }
//}
