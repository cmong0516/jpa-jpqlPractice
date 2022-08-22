package jpql;

import javax.persistence.*;
import java.util.Collection;
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
            Team team = new Team();
            team.setName("teamA");

            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자1");
            member.setTeam(team);
            em.persist(member);

            Member member1 = new Member();
            member1.setUsername("관리자2");
            member1.setTeam(team);
            em.persist(member1);



//            String sql = "select m.username from Member m";
//            List<String> result = em.createQuery(sql, String.class).getResultList();
//            // m.username : 상태필드 경로탐색의 끝.

//            String sql = "select m.team from Member m";
//
//            List<String> result = em.createQuery(sql, String.class).getResultList();
            // m.team : 단일 값 연관 경로
            // 묵시적 내부조인 발생 탐색 o
//            String sql1 = "select m.team.name from Member m";

            String sql = "select t.members From Team t";

            // 컬렉션 값 연관 경로
            // 묵시적 내부 조인 발생 , 탐색 x
            //기존 sql 수정
            String sql1 = "select m.username from Team t join t.members m";
            List<String> result = em.createQuery(sql1, String.class).getResultList();

            System.out.println("result = " + result);

            // 묵시적 조인 말고 명시적 조인을 사용하자.

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
