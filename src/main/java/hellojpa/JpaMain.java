package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);


            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass()); //Proxy
//            refMember.getUsername(); //강제 초기화
//            System.out.println("isLoaded= "+ emf.getPersistenceUnitUtil().isLoaded(refMember));

            Hibernate.initialize(refMember); // 강제 초기화  - JPA표준에는 강제초기화가 없음


            //1. 이미 영속성 컨텍스트에 1차캐시로 있어서 member를 내뱉음
            //2. JPA에서는 ==비교가 한 영속성 컨텍스트안에서 가져온놈은 true를 반환해줘야함
//            Member reference = em.find(Member.class, member1.getId());
//            System.out.println("reference = " + reference.getClass());
//            System.out.println("a == a: "+(refMember ==reference));


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
