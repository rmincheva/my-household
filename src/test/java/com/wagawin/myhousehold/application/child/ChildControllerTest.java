package com.wagawin.myhousehold.application.child;

import com.wagawin.myhousehold.domain.child.ChildService;
import com.wagawin.myhousehold.domain.child.model.Child;
import com.wagawin.myhousehold.domain.child.model.Daughter;
import com.wagawin.myhousehold.domain.child.model.Meal;
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

import java.time.LocalDate;
import java.util.Collections;

import static com.wagawin.myhousehold.application.child.ChildController.V1_CHILDREN_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(ChildController.class)
public class ChildControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChildService childService;

    @Test
    void getInfoShouldSucceed() throws Exception {
        Mockito.when(childService.getChild(Mockito.anyLong()))
                .thenReturn(child());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_CHILDREN_URL + "/1/info").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getInfoMissingChildShouldFail() throws Exception {
        Mockito.when(childService.getChild(Mockito.anyLong()))
                .thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_CHILDREN_URL + "/2/info").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getInfoWithNegativeChildIdShouldFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(V1_CHILDREN_URL + "/-1/info").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getColorShouldSucceed() throws Exception {
        Mockito.when(childService.getChild(Mockito.anyLong()))
                .thenReturn(child());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_CHILDREN_URL + "/1/color").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getColorMissingChildShouldFail() throws Exception {
        Mockito.when(childService.getChild(Mockito.anyLong()))
                .thenThrow(new EntityNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_CHILDREN_URL + "/2/color").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getColorWithNegativeChildIdShouldFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(V1_CHILDREN_URL + "/-1/color").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private Child child() {
        Meal meal = new Meal(1, "pizza", LocalDate.now());
        Person parent = new Person(1, "Anna", 32);
        return new Daughter(1, "Rose", 32, "blond", parent, Collections.singletonList(meal));
    }
}
