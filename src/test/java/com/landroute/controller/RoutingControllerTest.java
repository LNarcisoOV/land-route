package com.landroute.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.landroute.model.dto.LandRouteDTO;
import com.landroute.service.RoutingService;

@RunWith(SpringRunner.class)
@WebMvcTest(RoutingController.class)
public class RoutingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoutingService routingService;
    

    @Test
    public void checkRouteShouldReturnOK() throws Exception {
        given(routingService.loadRoute(Mockito.anyString(), Mockito.anyString()))
                .willReturn(landRoute1());

        mockMvc.perform(get("/routing/CZE/ITA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("route", is(landRoute1List())));
    }

    
    @Test
    public void checkRouteShouldReturnInternalServerError() throws Exception {
        given(routingService.loadRoute("ITA", "ITA")).willThrow(NoSuchElementException.class);

        mockMvc.perform(get("/routing/ITA/ITA")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError());
    }
    
     @Test
     public void checkRouteShouldReturnBadRequest() throws Exception {
         given(routingService.loadRoute("ITA", "ITA")).willThrow(IllegalArgumentException.class);

         mockMvc.perform(get("/routing/ITA/ITA")
         .contentType(MediaType.APPLICATION_JSON))
         .andExpect(status().isBadRequest());
     }

    private LandRouteDTO landRoute1() {
        LandRouteDTO landRouteDTO = new LandRouteDTO();
        landRouteDTO.setRoute(Arrays.asList("CZE", "AUT", "ITA"));
        return landRouteDTO;
    }

    private List<String> landRoute1List() {
        return Arrays.asList("CZE", "AUT", "ITA");
    }
}
