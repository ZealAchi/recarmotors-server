package com.recarmotors.set;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan("com.recarmotors.mapper*")// Esta anotación es equivalente a la siguiente @Bean MapperScannerConfigurer, se puede configurar uno de los dos 
public class MybatisPlusConfig {

	/**
    * Complemento de paginación
    */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
