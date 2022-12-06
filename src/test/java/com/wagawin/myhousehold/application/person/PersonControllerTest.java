package com.wagawin.myhousehold.application.person;

import com.wagawin.myhousehold.domain.person.PersonService;
import com.wagawin.myhousehold.domain.person.model.House;
import com.wagawin.myhousehold.domain.person.model.HouseType;
import com.wagawin.myhousehold.domain.person.model.Person;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static com.wagawin.myhousehold.application.person.PersonController.V1_PERSONS_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void getHouseShouldSucceed() throws Exception {
        Mockito.when(personService.getPerson(Mockito.anyLong()))
                .thenReturn(person());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_PERSONS_URL + "/1/house").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getHouseMissingPersonShouldFail() throws Exception {
        Mockito.when(personService.getPerson(Mockito.anyLong()))
                .thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_PERSONS_URL + "/2/house").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getHouseWithNegativePersonIdShouldFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(V1_PERSONS_URL + "/-1/house").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private Person person() {
        House house = new House(1, HouseType.FLAT, "Karlsruhe", "76199");
        return new Person(1, "Anna", 32, house, Collections.emptyList());
    }
}
