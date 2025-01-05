package com.example.helpon.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
@Slf4j
public class ConnectionTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void dataSourceTest(){
   /*  try-with-resources 문법을 사용했다.
       기존에 우리가 자원을 가져와 사용하면 다 쓴 자원을 닫아줘야하는데
       try-with-resources를 사용하면 자동으로 닫아준다.
    */
        try (Connection connection = dataSource.getConnection()) {
            log.info("datasource connection : " + connection);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void sqlSessionTest(){
        try(
             SqlSession sqlSession = sqlSessionFactory.openSession(true);
             Connection connection = sqlSession.getConnection();
             ) {
            log.info("connection : " + connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
