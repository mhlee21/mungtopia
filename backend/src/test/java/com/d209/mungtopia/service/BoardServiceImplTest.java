package com.d209.mungtopia.service;

import com.d209.mungtopia.repository.BoardRepository;
import org.junit.jupiter.api.Test;
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
@Transactional //Test 끝나면 Rollback 해준다.
public class BoardServiceImplTest {

    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 상세_글_보기() throws Exception {
        //given

        //when

        //then
//        em.flush();
    }
}