package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            em.flush();
            em.clear();
            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);


            em.flush();
            em.clear();

//            Member m = em.find(Member.class, member1.getId());
            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
            //즉시로딩은 jpql에서 N+1문제를 일으킴.
            //전부 lazy로 사용하고 필요시에 fetch join로 가져올수있음
            //manytoone, onetoone은 기본이 즉시로딩으로 되어있음(다 LAZY로 적어줘야함)
            //onetoMany는 기본이 lazy


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

}
