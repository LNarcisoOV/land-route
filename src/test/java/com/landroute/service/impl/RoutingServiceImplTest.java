package com.landroute.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.landroute.model.Country;
import com.landroute.model.dto.LandRouteDTO;
import com.landroute.service.RoutingService;

@RunWith(MockitoJUnitRunner.class)
public class RoutingServiceImplTest {
    
    private static final String COUTRIES_JSON_FILE_PATH =
            "/raw.githubusercontent.com_mledoze_countries_master_countries.json";

	@InjectMocks
	private RoutingServiceImpl routingServiceImpl;
	
	@Mock
    private RoutingService routingService;
	
	@Mock
	private List<Country> countries;
	
	@Before
	public void init() {
	    given(countries.stream()).willReturn(loadContries().stream(), loadContries().stream());
	}
	

	@Test
	public void testLoadCountries() {
	    assertNotNull(loadContries());
		assertTrue(loadContries().size() > 0);
		assertEquals(loadContries().get(0).getCca3(), "ABW");
	}

	@Test
	public void testLoadRoute() {
	    LandRouteDTO landRouteDTO = routingServiceImpl.loadRoute("CZE", "ITA");
	    
	    assertNotNull(landRouteDTO);
	    assertFalse(CollectionUtils.isEmpty(landRouteDTO.getRoute()));
		assertEquals(landRouteDTO.getRoute(),landRoute1List());
	}

	@Test(expected = NoSuchElementException.class)
	public void testLoadRouteShouldThrowNoSuchElementExceptionWhenOriginIsInvalid() {
	    routingServiceImpl.loadRoute("ABC", "ITA");
	}
	
	@Test(expected = NoSuchElementException.class)
    public void testLoadRouteShouldThrowNoSuchElementExceptionWhenDestinationIsInvalid() {
        routingServiceImpl.loadRoute("CZE", "ABC");
    }
	
	@Test(expected = NoSuchElementException.class)
    public void testLoadRouteShouldThrowNoSuchElementExceptionWhenOriginIsEmpty() {
        routingServiceImpl.loadRoute("", "ABC");
    }
	
	@Test(expected = NoSuchElementException.class)
    public void testLoadRouteShouldThrowNoSuchElementExceptionWhenDestinationIsEmpty() {
        routingServiceImpl.loadRoute("CZE", "ABC");
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testLoadRouteShouldThrowNIllegalArgumentExceptionWhenOriginIsEqualsDestination() {
        routingServiceImpl.loadRoute("CZE", "CZE");
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testLoadRouteShouldThrowNIllegalArgumentExceptionWhenBorderIsEmpty() {
        routingServiceImpl.loadRoute("ABW", "CZE");
    }
//
//	private Subscription subscription1() {
//		Subscription subscription = new Subscription();
//		subscription.setDescription("Desc 1");
//		subscription.setActive(Boolean.TRUE);
//		return subscription;
//	}
//
//	private Optional<Subscription> subscription1WithId() {
//		Subscription subscription = subscription1();
//		subscription.setId(1L);
//		return Optional.of(subscription);
//	}
//
//	private SubscriptionDTO subscriptionDTO1() {
//		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
//		subscriptionDTO.setDescription("Desc 1");
//		subscriptionDTO.setActive(Boolean.TRUE);
//		return subscriptionDTO;
//	}
	
	
    private List<Country> loadContries() {
        List<Country> countries = new ArrayList<Country>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream(COUTRIES_JSON_FILE_PATH);
        try {
            countries = mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            System.out.println("Unable to load countries: " + e.getMessage());
        }
        return countries;
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
