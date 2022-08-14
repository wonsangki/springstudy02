package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {


    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    @Rollback(false)
    public void join() {
        // given
        Member member = new Member();
        member. setName ("kim");
    //when
        Long savedId = memberService. join (member);
    //then
        assertEquals (member, memberRepository. findOne (savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void duplicateMemberException() throws Exception{
        Member member1 = new Member();
        member1.setName("Kim");

        Member member2 = new Member();
        member2.setName("Kim");

        memberService.join(member1);
        memberService.join(member2);



    }
}