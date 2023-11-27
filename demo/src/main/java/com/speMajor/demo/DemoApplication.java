package com.speMajor.demo;

import com.speMajor.demo.config.AppConstants;
import com.speMajor.demo.model.Employee;
import com.speMajor.demo.model.Role;
import com.speMajor.demo.repository.EmployeeRepository;
import com.speMajor.demo.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("singhal"));
		try {
			Role role=new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");

			List<Role> roles= List.of(role,role1);
			List<Role> result=this.roleRepository.saveAll(roles);

			result.forEach(r->{
				System.out.println(r.getName());
			});
		}catch (Exception e){
			e.printStackTrace();
		}
	}


//	@Autowired
//	private EmployeeRepository employeeRepository;
//implements CommandLineRunner
////	@Override
////	public void run(String... args) throws Exception {
//////		Employee employee = new Employee();
//////		employee.setFirstName("Ramesh");
//////		employee.setLastName("Fadatare");
//////		employee.setEmailId("ramesh@gmail.com");
//////		employee.setPassword("zjhbf");
//////		employeeRepository.save(employee);
////
////	}

}
