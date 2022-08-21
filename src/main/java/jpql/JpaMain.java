package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            for (int i = 0; i < 100; i++) {
//                Member member = new Member();
//                member.setUsername("member"+i);
//                member.setAge(i);
//                em.persist(member);
//            }
//
//
//            em.flush();
//            em.clear();
//
////            TypedQuery<Member> query = em.createQuery("select m from Member m where m.username=:username", Member.class);
////            query.setParameter("username", "member1");
////            Member result = query.getSingleResult();
////            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class).setParameter("username", "member1").getSingleResult();
////            System.out.println("result = " + result.getUsername());
//
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class).setFirstResult(1).setMaxResults(10).getResultList();


//
//            for (Member m : result) {
//                System.out.println(m.toString());
//            }
            // 평균보다 나이가 많은 회원 조회.
//            em.createQuery("select m from Member m where m.age > (select avg(m2.age) from Member m2)");
//            // 주문건수가 있는 회원 조회
//            em.createQuery("select m from Member m where (select count(o) from Order o where m= o.member) >0 ");
            // 팀 A 소속인 회원
//            em.createQuery("select m from Member m where exists (select t from m.team t where t.name = '팀A')");
////            팀에 소속된 회원
//            em.createQuery("select m from Member m where m.team = ANY ");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        em.close();
        emf.close();
    }
}


// 내부조인, 외부조인도 모두 가능
// 서브쿼리도 가능.
// JPA 는 select where having 절에서만 서브쿼리 사용 가능.
// from 은 불가능.
