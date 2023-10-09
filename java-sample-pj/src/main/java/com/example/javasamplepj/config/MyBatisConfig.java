package com.example.javasamplepj.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// @org.springframework.transaction.annotation.EnableTransactionManagementを 付与し、
// アノテーション駆動(@Transactional)のトランザクション制御を有効にします。
@EnableTransactionManagement
public class MyBatisConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    // データソースのBean定義をします。
    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName(driverClassName)
                .username(username)
                .password(password)
                .url(url)
                .build();
    }

    // org.mybatis.spring.SqlSessionFactoryBeanをBean定義します。
    // これによりSqlSessionFactoryBeanを利用してSqlSessionFactoryが生成されま す。
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        // データソースを設定する。MyBatisの処理の中でSQLを発行すると、
        // ここで指定したデータソースからコネクションが取得されます。
        sessionFactoryBean.setDataSource(dataSource());
        // MyBatis設定ファイルを指定します。
        // 今回はresources直下に設定ファイルを配置します。
        sessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sessionFactoryBean;
    }

    // トランザクションマネージャーのBeanを定義します。
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}