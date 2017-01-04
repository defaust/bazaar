package ua.com.codespace.config.JpaConfig;

import ua.com.codespace.config.TransactionConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@Profile("test")
@EnableJpaRepositories("ua.com.codespace.repository")
@Import(TransactionConfig.class)
public class Test {

    @Bean
    //@Profile("test")
    DataSource testDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("test_db")
                .build();
    }

    @Bean
    //@Profile("test")
    JpaVendorAdapter testJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);

        adapter.setGenerateDdl(true);

        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        return adapter;
    }

    @Bean("entityManagerFactory")
    LocalContainerEntityManagerFactoryBean localContainerEMF(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcmfb = new LocalContainerEntityManagerFactoryBean();
        lcmfb.setDataSource(dataSource);
        lcmfb.setJpaVendorAdapter(jpaVendorAdapter);
        lcmfb.setPersistenceUnitName("TestPersistenceUnit");//todo: you can rename persistent unit
        lcmfb.setPackagesToScan("ua.com.codespace.model");

        //lcmfb.getJpaPropertyMap().put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2dll);

        return lcmfb;
    }
}