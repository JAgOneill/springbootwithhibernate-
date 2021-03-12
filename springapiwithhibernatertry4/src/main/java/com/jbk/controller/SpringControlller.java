package com.jbk.controller;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.entity.Country;
import com.jbk.entity.Employee;
import com.jbk.entity.Registration;
import com.jbk.service.SpringService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SpringControlller {

	@Autowired
	SpringService springservice;
	@GetMapping("/Stringapimsg")
	public String getMessage()
	{
		return springservice.getMessage();
	}
	
	@GetMapping("/getallemployee")
	public List<Employee>getAllEmployee()
	{
		List<Employee>emplist= springservice.getAllEmployee();
		return emplist;
	}
	
	@GetMapping("/getallreg")
	public List<Registration>getAllreg()
	{
		List<Registration>reglist= springservice.getAllreg();
		return reglist;
	}
	
	
	@GetMapping("/getallcountry")
	public List<Country>getAllcounties()
	{
		List<Country>countrylist=springservice.getAllcounties();
		return countrylist;
	}
	@GetMapping(value="getemployeebyid/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployeeById(@PathVariable int id)
	{
		Employee listemp=springservice.getEmployeeById(id);
		return listemp;
	}
	
	@GetMapping("getcountrybyid/{id}")
	public List<Country>getCountryById(@PathVariable int id )
	{
		System.out.println("i m in controller");
		List<Country>countrylist=springservice.getCountryById(id);
		return countrylist;
	}
	
	@GetMapping("getemployeebystatus/{status}")
	public List<Employee>getEmployeebystatus(@PathVariable String status)
	{
		List<Employee>listemp=springservice.getEmployeebystatus(status);
		return listemp;
	}
	@GetMapping("getemployeebyname/{name}")
	public List<Employee>getEmployeebyName(@PathVariable String name)
	{
		List<Employee>listemp= springservice.getEmployeebyName(name);
		return listemp;
	}
	
	
	
	/*
	public List<Employee>addEmployee(@RequestBody Employee emp)
	{
		List<Employee>listemp= springservice.addEmployee(emp);
		return listemp;
	}*/
	
	@PostMapping("/addemployee")
	public String addEmployee(@RequestBody Employee emp)
	{
		String msg=springservice.addEmployee(emp);
		return msg;
	}
	
	@PostMapping("/addnewreg")
	public String addNewReg(@RequestBody Registration reg)
	{
		String msg=springservice.addNewReg(reg);
		return msg;
	}
	
	@PostMapping("/addcountry")
	public String addcountry(@RequestBody Country country)
	{
		String msg =springservice.addcountry(country);
		return msg;
	}
	
	@DeleteMapping("/deletemployeebyid/{id}")
	public String deleteEmployeeById(@PathVariable int id )
	{
		String msg= springservice.deleteEmployeeById(id);
		return msg;
	}
	
	
	
	@PutMapping("/updateemployee")
	public String updateEmployee(@RequestBody Employee emp)
	{
		String msg= springservice.updateEmployee(emp);
		return msg;
	}

	@PutMapping("/suspendemployee/{id}")
	public String suspendEmployeebyid(@PathVariable int id)
	{
		System.out.println("inside suspend Employee Controller");
		String suspendresult=springservice.suspendEmployeebyid(id);
		return "Employee got suspended Successfully";
	}
	
	@PutMapping("/changestatus")
	public String ChangeStatus(@RequestBody Employee emp )
	{
		String msg= springservice.ChangeStatus(emp);
		return msg;
	}
	
//	@PostMapping("/logincheck")
//	public HashMap LoginCheck(@RequestBody Registration usercredintials)
//	{
//		System.out.println("email is " +usercredintials.getEmail()+"password is "+usercredintials.getPassword());
//		HashMap hm = new HashMap(); 
//		Registration user= springservice.checkLogin(usercredintials.getEmail(),usercredintials.getPassword());
//		System.out.println("inside login controller");
//		if(user!=null)
//		{
//			hm.put("msg", "valid user");
//			hm.put("user", user);			
//		}
//		else
//		{
//			hm.put("msg","invalid user");
//
//			
//		}
//		return hm;
//	}
	
	@PostMapping("/logincheck")
	public HashMap LoginCheck(@RequestBody Registration usercredintials)
	{
		System.out.println("email is " +usercredintials.getEmail()+"password is "+usercredintials.getPassword());
		HashMap hm = springservice.checkLogin(usercredintials.getEmail(),usercredintials.getPassword());
		return hm;
	}
	
	
	
	/*@PostMapping("/logincheck")
	public HashMap LoginCheck(@RequestBody Registration reg)
	{
		HashMap hm= springservice.Logincheck
	}
	
	}*/
	
}
