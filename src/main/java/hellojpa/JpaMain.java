package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

//            쿼리가 한번만 실행됨
            Member findmember1 = em.find(Member.class, 101L);//sql조회
            Member findmember2 = em.find(Member.class, 101L);//1차캐시조회

//            영속 엔티티의 동일성 보장
            System.out.println("result = " + (findmember2==findmember2));

//            쓰기지연
//            Member memberA=new Member(150L,"A");
//            Member memberB=new Member(160L,"B");
//
//
//            em.persist(memberA);
//            em.persist(memberB);

//            엔티티 수정 - 변경감지
                Member member = em.find(Member.class,160L);
                member.setName("zzzzzz");




            System.out.println("=========="); // 선 출력이후 쿼리가 나

//                변경이후 새로 저장하는 코드를 쓰지않는다
//            em.persist(member);


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
