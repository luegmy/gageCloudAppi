package com.cloudappi.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cloudappi.user.model.Address;
import com.cloudappi.user.model.User;
import com.cloudappi.user.repository.IUserRepository;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	IUserRepository userRepository;

	@Test
	void whenCreateUserThenReturnNotNull() {
		User user = User.builder().name("miguel neyra").email("luegmy@gmail.com").birthDate("24-10-80")
				.address(Address.builder().id(1).build()).build();

		User userCreated = userRepository.save(user);

		Assertions.assertThat(userCreated).isNotNull();
	}
	
	@Test
	void whenFindByIdUserThenReturnNameUser() {
		User user=userRepository.findById(1).orElse(null);//este registro esta en data.sql
		
		Assertions.assertThat(user.getName()).isEqualTo("rafael neyra");
	}
	
	
	@Test
	void whenFindByIdUserThenNotExistUserReturnNull() {
		User user=userRepository.findById(2).orElse(null);
		
		Assertions.assertThat(user).isNull();
	}
	
	@Test
	void whenUpdateUserThenReturnUserNameModify() {
		User userUpdated=userRepository.findById(1).orElse(null);
		if (userUpdated!=null) {
			User user=new User();
			user=userUpdated;
			user.setName("rafael neyra lopez");
			userUpdated= userRepository.save(user);
		}
		Assertions.assertThat(userUpdated.getName()).isEqualTo("rafael neyra lopez");//atributo modificado
		Assertions.assertThat(userUpdated.getEmail()).isEqualTo("muygel@hotmail.com");//atributo no modificado
	}
	
	@Test
	void whenDeleteUserThenReturnExistUserBeforeAndNoExistUserAfter() {
		boolean isExistBeforeDelete=userRepository.findById(1).isPresent();
		
		userRepository.deleteById(1);
		
		boolean isNoExistAfterDelete=userRepository.findById(1).isPresent();
		
		Assertions.assertThat(isExistBeforeDelete).isTrue();
		Assertions.assertThat(isNoExistAfterDelete).isFalse();
	}
	
	

}
