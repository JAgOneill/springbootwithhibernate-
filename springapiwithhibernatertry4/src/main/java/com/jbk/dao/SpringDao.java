package com.jbk.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.Country;
import com.jbk.entity.Employee;
import com.jbk.entity.Registration;

@RestController
public class SpringDao {
	@Autowired
	SessionFactory sessionfactory;

	public String getMessage()
	{
		return "hello world !!!!!!!! happy !";
	}
	public List<Employee>getAllEmployee()
	{
		Session session= sessionfactory.openSession();
		List<Employee>emplist=session.createCriteria(Employee.class).list();
		return emplist;
	
	}
	
	
	public List<Registration>getAllreg()
	{
		Session session= sessionfactory.openSession();
		List<Registration>reglist= session.createCriteria(Registration.class).list();
		return reglist;
	}
	
	public List<Country>getAllcounties()
	{
		Session session=sessionfactory.openSession();
		List<Country>countrylist=session.createCriteria(Country.class).list();
		return countrylist;
	}
	public Employee getEmployeeById(int id)
	{
		Session session= sessionfactory.openSession();
		Criteria criteria =session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", id));
		Employee  listemp=(Employee)criteria.uniqueResult();
		return listemp;
	}
	
	public List<Country>getCountryById(int id )
	{
		Session session= sessionfactory.openSession();
		Criteria criteria =session.createCriteria(Country.class);
		criteria.add(Restrictions.eq("id", id));
		List<Country>countrylist=criteria.list();
		return countrylist;
	}
	public List<Employee>getEmployeebystatus(String status)
	{
		Session session= sessionfactory.openSession();
		Criteria criteria =session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("status", status));
		List<Employee>listemp=criteria.list();
		return listemp;
	}
	
	public List<Employee>getEmployeebyName(String name)
	{
		Session session= sessionfactory.openSession();
		Criteria criteria =session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("name", name));
		List<Employee>listemp=criteria.list();
		return listemp;
	}
	
	/*public List<Employee> addEmployee(Employee emp)
	{
		
			Session session = sessionfactory.openSession();
			Transaction transaction= session.beginTransaction();
			System.out.println("Employee name"+emp.getName());
			session.save(emp);
			System.out.println("session got saved");
			transaction.commit();
			Criteria criteria =session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("emp", emp));
			List<Employee>listemp=criteria.list();
			return listemp;
	}*/
	public String addEmployee(Employee emp)
	{
		Session session = sessionfactory.openSession();
		Transaction transaction= session.beginTransaction();
		System.out.println("Employee name"+emp.getName());
		session.save(emp);
		System.out.println("session got saved");
		transaction.commit();
		return" Employee added successfully";
	}
	
	// add new registration
	public String addNewReg( Registration reg)
	{
		Session session = sessionfactory.openSession();
		Transaction transaction= session.beginTransaction();
		System.out.println("Employee name"+reg.getFirstname());
		session.save(reg);
		System.out.println("session got saved");
		transaction.commit();
		return" Employee registered successfully";
	}
	
	
	
		public String addcountry(Country country)
	{
		Session session= sessionfactory.openSession();
		Transaction transaction= session.beginTransaction();
		System.out.println("Country Name is "+country.getCname());
		session.save(country);
		System.out.println("session got saved");
		transaction.commit();
		return "Country added Successfully";
	}
		
		
		
		
	
	//using criteria 
	public String deleteEmployeeById(int id)
	{
		Session session = sessionfactory.openSession();
		Transaction transaction= session.beginTransaction();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", id));
		Employee emp=(Employee)criteria.uniqueResult();
		session.remove(emp);
		transaction.commit();
		return "record deleted Sucessfully";
		
	}
	
	
	
	public String updateEmployee(Employee emp)
	{
		Session session= sessionfactory.openSession();
		Transaction transaction = session.beginTransaction();
		System.out.println("updating");
		session.saveOrUpdate(emp);
		System.out.println("data got updated");
		transaction.commit();
		System.out.println("transaction got commited");
		return "Employee updated sucessfully";
	}
	
	
/*	public String updateEmployee(Employee emp)
	{
		System.out.println(emp);
	
		Session session= sessionfactory.openSession();
		
		
	Query query= session.createQuery("update Employee set name=:name ,phoneno=:phoneno,departmentit=:departmentit,status=:status,createddtm=:createddtm,createdby=:createdby,updateddtm=:updateddtm,updatedby=:updatedby, cid=:cid where id=:id");
	query.setParameter("name", emp.getName());
	query.setParameter("phoneno",emp.getPhoneno());
	query.setParameter("departmentit",emp.getDepartmentit());
	query.setParameter("status",emp.getStatus());
	query.setParameter("createddtm", emp.getCreateddtm());
	query.setParameter("createdby", emp.getCreatedby());
	query.setParameter("updateddtm", emp.getUpdateddtm());
	query.setParameter("updatedby", emp.getUpdatedby());
	query.setParameter("country", emp.getCountry());
	query.setParameter("id", emp.getId());
	int i =query.executeUpdate();
	Transaction transaction = session.beginTransaction();
	System.out.println("transaction began ");
	transaction.commit();
	if(i>0)
	{
		return "employee"+emp.getName()+"updated successfully";
		
	}
	else
	{
		return "error";
	}
	
	
		
	}*/
	public String suspendEmployeebyid(int id)
	{
		System.out.println("inside suspend Employee Dao layer");
		Session session= sessionfactory.openSession();
		Query query= session.createQuery("update Employee set status=:status where id=:id");
		query.setParameter("id",id);
		query.setParameter("status", "suspended");
		Transaction transaction= session.beginTransaction();
		query.executeUpdate();
		transaction.commit();
		return "Employee got suspended Successfully";
	}
	
	
	
	public String ChangeStatus(Employee emp )
	{
		System.out.println("inside changeStatus Employee Dao layer");
		String activestatus="active";
		String inactivestatus="inactive";
		String suspendedstatus="suspend";
		Session session= sessionfactory.openSession();
		Query query= session.createQuery("update Employee set status=:status where id=:id");
		System.out.println(emp.getStatus());
		query.setParameter("id",emp.getId());
		
		if((emp.getStatus()).equals(activestatus))
		{
		query.setParameter("status", inactivestatus);
		}
		else if((emp.getStatus()).equals(inactivestatus))
		{
			query.setParameter("status", activestatus);
		}
		else
		{
			return "Suspended Employee Cannot be Activated";
		}
		Transaction transaction= session.beginTransaction();
		query.executeUpdate();
		transaction.commit();
		System.out.println(emp.getStatus());
		return "status changed successfully";
	}
	public Registration checkLogin(String email,String password)
	{
		System.out.println("inside login method of Dao layer");
		System.out.println("email is " +email);
		Session session= sessionfactory.openSession();
		Criteria criteria= session.createCriteria(Registration.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("password",password));
		System.out.println("email is now" +email);
		Registration user=(Registration) criteria.uniqueResult();
		System.out.println("user is " +user);
		return user;
	
	
	}

	}
