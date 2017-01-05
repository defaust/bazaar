package ua.com.codespace.config.JpaConfig;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import ua.com.codespace.config.TransactionConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.sql.DataSource;

@Configuration
@Profile("dev")
@EnableJpaRepositories("ua.com.codespace.repository")
@Import(TransactionConfig.class)
public class Dev {

    //todo: don't forget to set your database properties in the application.properties file
    @Value("${spring.data.db.url}")
    private String url;
    @Value("${spring.data.db.user}")
    private String user;
    @Value("${spring.data.db.password}")
    private String password;
    @Value("${spring.data.db.driver}")
    private String driverClassName;
    @Value("${spring.data.db.cp.maxSize}")
    private Integer maxPoolSize;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2dll;

    @Bean
    DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    //Bla bla bla

    @Bean
    DataSource pooledDataSource(HikariConfig config) {
        return new HikariDataSource(config);
    }

    @Bean
    HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        hikariConfig.setPoolName("springHikariCP");
        hikariConfig.setConnectionTestQuery("SELECT 1");

        return hikariConfig;
    }


    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);

        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");

        return adapter;
    }

    // todo: use this EMF if you want to configure it with /META-INF/persistence.xml
    @Bean("entityManagerFactory")
    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("PersistenceUnit");
        return emfb;
    }
}