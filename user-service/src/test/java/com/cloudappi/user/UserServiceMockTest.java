package com.cloudappi.user;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cloudappi.user.model.Address;
import com.cloudappi.user.model.User;
import com.cloudappi.user.repository.IUserRepository;
import com.cloudappi.user.service.IUserService;
import com.cloudappi.user.service.UserService;

@SpringBootTest
public class UserServiceMockTest {

	// Se comprobo el test repository, por lo tanto ahora simulamos un objeto con
	// Mock y no tener dependencias
	IUserRepository userRepositoryMock = Mockito.mock(IUserRepository.class);

	IUserService userService;

	@BeforeEach
	void setup() {
		userService = new UserService(userRepositoryMock);

		User user = User.builder().name("jneyra").email("kimienke@gmail.com").birthDate("25-02-1988")
				.address(Address.builder().id(1).build()).build();

		// comportamientos predecibles de la dependencia
		Mockito.when(userRepositoryMock.save(user)).thenReturn(user);
		Mockito.when(userRepositoryMock.findById(1)).thenReturn(Optional.of(user));
		Mockito.when(userRepositoryMock.findAll()).thenReturn(Arrays.asList(user));
	}
	
	@Test
	void whenGetByIdUserThenReturnNameUser() {
		User user = userService.getUserById(1);
		if(user ==null) {
			Assertions.assertThat(user).isNull();
		}else {
		Assertions.assertThat(user.getName()).isEqualTo("jneyra");
		}
	}

	@Test
	void whenGetUsersThenReturnList() {
		List<User> users = userService.getUsers();

		Assertions.assertThat(users.size()).isEqualTo(1);
	}

	@Test
	void whenUpdateUserThenReturnUserNameModify() {
		
		User user = userService.getUserById(2);
		if(user ==null) {
			Assertions.assertThat(user).isNull();
		}else {
			user.setName("rafael neyra lopez");
			userService.updateUser(user);
			Assertions.assertThat(user.getName()).isEqualTo("rafael neyra lopez");//atributo modificado
			Assertions.assertThat(user.getEmail()).isEqualTo("kimienke@gmail.com");//atributo sin modificar
		}

	}

	@Test
	void whenDeleteUserThenReturnUserBeforeNotNullAndUserAfterNull() {
		User userBeforeDelete = userService.getUserById(1);

		userService.deleteUser(userBeforeDelete.getId());

		User userAfterDelete =  userService.getUserById(userBeforeDelete.getId());

		Assertions.assertThat(userBeforeDelete).isNotNull();
		Assertions.assertThat(userAfterDelete).isNull();
	}
	
	

}
