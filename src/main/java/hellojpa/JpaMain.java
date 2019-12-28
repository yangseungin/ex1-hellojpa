package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            JPQL
//            List<Member> result = em.createQuery(
//                    "select m from Member as m where m.username like '%kim'", Member.class
//            ).getResultList();
//            for (Member member : result) {
//                System.out.println("member = " + member);
//            }

//            Criteria - 표준스펙이나 sql스럽지않고 유지보수하기 힘듬.
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//
//            Root<Member> m = query.from(Member.class);
//            CriteriaQuery<Member> cq = query.select(m);
//
//            String username="test";
//            if(username!=null){
//                cq.where(cb.equal(m.get("username"), "kim"));
//            }
//            List<Member> resultList = em.createQuery(cq)
//                    .getResultList();

            //Native SQL
//            em.createNativeQuery("select MEMBER_ID,USERNAME from Member",Member.class).getResultList();



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
