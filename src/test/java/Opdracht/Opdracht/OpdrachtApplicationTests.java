package Opdracht.Opdracht;

import Opdracht.Opdracht.entity.User;
import Opdracht.Opdracht.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest

class OpdrachtApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception{

		// given - precondition or setup
		User user = User.builder()
				.voorNaam("Jan")
				.achterNaam("Hanssen")
				.email("jan_hanssen@hotmail.com")
				.geboorteDatum(LocalDate.ofEpochDay(1982-01-22))
				.build();
		given(userService.createUser(any(User.class)))
				.willAnswer((invocation)-> invocation.getArgument(0));

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)));

		// then - verify the result or output using assert statements
		response.andDo(print()).
				andExpect(status().isCreated())
				.andExpect(jsonPath("$.voorNaam",
						is(user.getVoorNaam())))
				.andExpect(jsonPath("$.achterNaam",
						is(user.getAchterNaam())))
				.andExpect(jsonPath("$.email",
						is(user.getEmail())))
		        .andExpect(jsonPath("$.geboorteDatum",
		                is(user.getGeboorteDatum())));
		// JUnit test for Get All employees REST API
		@Test
		public void givenListOfUser_whenGetAllUser_thenReturnUserList() throws Exception{
			// given - precondition or setup
			List<User> listOfUser = new ArrayList<>();
			listOfUser.add(User.builder().voorNaam("Jan").achterNaam("Hanssen").email("jan_hanssen@hotmail.com").geboorteDatum(LocalDate.ofEpochDay(22/01/1982)).build());
			listOfUser.add(User.builder().voorNaam("Tony").achterNaam("Stark").email("tony@gmail.com").geboorteDatum(LocalDate.ofEpochDay(22/01/1982)).build());
			given(userService.getAllUsers()).willReturn(listOfUser);

			// when -  action or the behaviour that we are going test
			ResultActions response = mockMvc.perform(get("/user"));

			// then - verify the output
			response.andExpect(status().isOk())
					.andDo(print())
					.andExpect(jsonPath("$.size()",
							is(listOfUser.size())));

		}

		// positive scenario - valid employee id
		// JUnit test for GET employee by id REST API
		@Test
		public void givenUserId_whenGetUserById_thenReturnUserObject() throws Exception{
			// given - precondition or setup
			long userId = 1L;
			User user = User.builder()
					.voorNaam("Jan")
					.achterNaam("Hanssen")
					.email("jan_hanssen@hotmail.com")
					.geboorteDatum("22-01-1982")
					.build();
			given(userService.getUserById(userId)).willReturn(Optional.of(user));

			// when -  action or the behaviour that we are going test
			ResultActions response = mockMvc.perform(get("/user/{id}", userId));

			// then - verify the output
			response.andExpect(status().isOk())
					.andDo(print())
					.andExpect(jsonPath("$.voorNaam", is(user.getVoorNaam())))
					.andExpect(jsonPath("$.achterNaam", is(user.getAchterNaam())))
					.andExpect(jsonPath("$.email", is(user.getEmail())))
					.andExpect(jsonPath("$.geboorteDatum", is(user.getGeboorteDatum())));

		}

		// negative scenario - valid employee id
		// JUnit test for GET employee by id REST API
		@Test
		public void givenInvalidUserId_whenGetUserById_thenReturnEmpty() throws Exception{
			// given - precondition or setup
			long userId = 1L;
			User user = User.builder()
					.voorNaam("Jan")
					.achterNaam("Hanssen")
					.email("jan_hanssen@hotmail.com")
					.geboorteDatum(22/01/1982)
					.build();
			given(userService.getUserById(userId)).willReturn(Optional.empty());

			// when -  action or the behaviour that we are going test
			ResultActions response = mockMvc.perform(get("/user/{id}", userId));

			// then - verify the output
			response.andExpect(status().isNotFound())
					.andDo(print());

		}
		// JUnit test for update employee REST API - positive scenario
		@Test
		public void givenUpdatedUser_whenUpdateUser_thenReturnUpdateUserObject() throws Exception{
			// given - precondition or setup
			long userId = 1L;
			User savedUser = User.builder()
					.voorNaam("Jan")
					.achterNaam("Hanssen")
					.email("jan_hanssen@hotmail.com")
					.geboorteDatum('22/01/1982')
					.build();

			User updatedUser = User.builder()
					.voorNaam("Peter")
					.achterNaam("Selie")
					.email("peter@gmail.com")
					.geboorteDatum('01/01/1990')
					.build();
			given(userService.getUserById(userId)).willReturn(Optional.of(savedUser));
			given(userService.updateUser(any(User.class)))
					.willAnswer((invocation)-> invocation.getArgument(0));

			// when -  action or the behaviour that we are going test
			ResultActions response = mockMvc.perform(put("/user/{id}", userId)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(updatedUser)));


			// then - verify the output
			response.andExpect(status().isOk())
					.andDo(print())
					.andExpect(jsonPath("$.voorNaam", is(updatedUser.getVoorNaam())))
					.andExpect(jsonPath("$.achterNaam", is(updatedUser.getAchterNaam())))
					.andExpect(jsonPath("$.email", is(updatedUser.getEmail())))
					.andExpect(jsonPath("$.geboorteDatum",is(updatedUser.getGeboorteDatum())));
		}

		// JUnit test for update employee REST API - negative scenario
		@Test
		public void givenUpdatedEmployee_whenUpdateEmployee_thenReturn404() throws Exception{
			// given - precondition or setup
			long employeeId = 1L;
			Employee savedEmployee = Employee.builder()
					.firstName("Ramesh")
					.lastName("Fadatare")
					.email("ramesh@gmail.com")
					.build();

			Employee updatedEmployee = Employee.builder()
					.firstName("Ram")
					.lastName("Jadhav")
					.email("ram@gmail.com")
					.build();
			given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
			given(employeeService.updateEmployee(any(Employee.class)))
					.willAnswer((invocation)-> invocation.getArgument(0));

			// when -  action or the behaviour that we are going test
			ResultActions response = mockMvc.perform(put("/api/employees/{id}", employeeId)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(updatedEmployee)));

			// then - verify the output
			response.andExpect(status().isNotFound())
					.andDo(print());
		}

// JUnit test for delete employee REST API
		@Test
		public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception{
			// given - precondition or setup
			long employeeId = 1L;
			willDoNothing().given(employeeService).deleteEmployee(employeeId);

			// when -  action or the behaviour that we are going test
			ResultActions response = mockMvc.perform(delete("/api/employees/{id}", employeeId));

			// then - verify the output
			response.andExpect(status().isOk())
					.andDo(print());
		}
	}

}
