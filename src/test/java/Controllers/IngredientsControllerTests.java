package Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fastspring.pizza.ReactAndSpringDataRestApplication;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReactAndSpringDataRestApplication.class)
@AutoConfigureMockMvc
public class IngredientsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TestGetIngredients() throws Exception {

        this.mockMvc.perform(get("/api/ingredients")).andDo(print())
                .andExpect(jsonPath("$._embedded.ingredients", Matchers.hasSize(5)));


    }

    @Test
    public void testPlaceOrder() throws Exception {

        mockMvc.perform(post("/bodyorder").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test user\",\"address\":\"my address\",\"phonenumber\":\"123456789\",\"pizzasize\":\"LARGE\", \"ingredients\": [\"cheese\"] }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Matchers.containsString("delivered")));
    }


}
