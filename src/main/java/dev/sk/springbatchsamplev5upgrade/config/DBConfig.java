package dev.sk.springbatchsamplev5upgrade.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.url}")
    String url;

    @Bean
    JdbcTemplate createDatasource(DataSource dataSource){
        System.err.println("Creating DataSource...");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = jdbcTemplate.queryForObject("select count(*) from mock_data",Integer.class);
        System.out.println("Data Count: "+result);
        return jdbcTemplate;
    }
}
