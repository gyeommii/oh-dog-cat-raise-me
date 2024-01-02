package com.ohdogcat.hikaricp;

import com.ohdogcat.model.Membership;
import com.ohdogcat.model.Payment;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HikariCPTest {

    private static final Logger log = LoggerFactory.getLogger(HikariCPTest.class);

    @Test
    public void test() throws SQLException {
        // HikariCP(커넥션 풀) 환경 설정을 위한 객체 생성:
        HikariConfig config = new HikariConfig();

        // HikariCP 환경 설정:
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        config.setJdbcUrl("jdbc:oracle:thin:@192.168.20.11:1521:xe");
//        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setUsername("scott");
        config.setPassword("tiger");
        // 환경 설정 내용이 적용된 커넥션 풀 객체(DataSource)를 생성:
        HikariDataSource ds = new HikariDataSource(config);

        // 커넥션 풀(데이터 소스) 객체는 null이 아니어야 함.
        Assertions.assertNotNull(ds);
        log.debug("ds={}", ds);

        // 커넥션 풀(데이터 소스)에서 커넥션 객체를 빌려옴.
        Connection conn = ds.getConnection();

//		PreparedStatement stmt = conn.prepareStatement("INSERT INTO BLOGS (TITLE, CONTENT, AUTHOR) VALUES (?, ?, ?)");
//		stmt.setString(1, "hihi");
//		stmt.setString(2, "hello");
//		stmt.setString(3, "hola");
//
//		int result = stmt.executeUpdate();
//
//		Assertions.assertEquals(1, result);

        // 커넥션 객체는 null이 아니어야 함.
        Assertions.assertNotNull(conn);
        log.debug("conn={}", conn);

        // 사용이 끝난 커넥션 객체는 풀에 반환.
        conn.close(); // close: DB 서버와의 물리적인 연결을 끊는게 아니라 커넥션 풀에 반환!
        log.debug("커넥션 반환 성공");

        Long pointToAccumulated = 0L;

        Membership membership = Membership.builder().point_rate(3).build();
        Payment payment = Payment.builder().accumulated_point(0L).total_price(3000L).build();
        pointToAccumulated =
            Math.round((payment.getTotal_price()) * (1 + membership.getPoint_rate() / 100.0));
        log.debug("pointToAccumulated={}", pointToAccumulated);
        log.debug("membership.getPoint_rate() / 100.0 = {}",
            1 + membership.getPoint_rate() / 100.0);
        log.debug("membership.getPoint_rate() / 100 = {}", 1 + membership.getPoint_rate() / 100);


    }

}
