package com.alibaba.dataops.server.start.test.sql;

import java.sql.Connection;
import java.sql.Statement;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 模板
 *
 * @author Jiaju Zhuang
 */
@Slf4j
public class JdbcTemplateTest {

    private static JdbcTemplate jdbcTemplate;

    @BeforeAll
    public static void prepare() throws Exception {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://rm-8vb099vo8309mcngk.mysql.zhangbei.rds.aliyuncs.com:3306");
        druidDataSource.setUsername("grow");
        druidDataSource.setPassword("v5EdRurYac");

        jdbcTemplate = new JdbcTemplate(druidDataSource);
        log.info("连接mysql");
    }

    @Test
    @Order(2)
    public void test() throws Exception {

        jdbcTemplate.execute("use data_ops_test");

        Connection connection = jdbcTemplate.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        boolean execute = statement.execute("select * from test_query");
        log.info("execute：{}",execute);

        statement = connection.createStatement();
        execute = statement.execute("INSERT INTO `test_query` (name,date,number) VALUES ('姓名','2022-01-01',123);");
        log.info("execute：{}",execute);
        //Returns:
        //true if the first result is a ResultSet object; false if it is an update count or there are no results
        statement = connection.createStatement();
        execute = statement.execute("show tables");
        log.info("execute：{}",execute);
    }
}
