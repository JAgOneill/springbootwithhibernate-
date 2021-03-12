package com.jbk.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.jbk.entity.Country;
import com.jbk.entity.Employee;
import com.jbk.entity.Registration;

@Configuration
public class HibernateConfiguration {
	
	@Autowired
	DataSource datasource;
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory()
	{
		System.out.println("hello i am in configuration");
		LocalSessionFactoryBean  Factorybean= new LocalSessionFactoryBean();
		Factorybean.setDataSource(datasource);
		System.out.print("datasoucre");
		Factorybean.setAnnotatedClasses(Employee.class,Country.class,Registration.class);
		return Factorybean;
	}

}
