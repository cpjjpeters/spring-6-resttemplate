package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTOPageImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClientImpl beerClient;
    
    @MockBean
    RestTemplateBuilder restTemplateBuilder;
    
    @MockBean
    RestTemplate restTemplate;

    @Test
    void listBeers() {
        // Given
        BeerDTOPageImpl mockPage = new BeerDTOPageImpl(Collections.emptyList());
        ResponseEntity<BeerDTOPageImpl> mockResponse = ResponseEntity.ok(mockPage);
        
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        when(restTemplate.getForEntity(any(String.class), eq(BeerDTOPageImpl.class)))
                .thenReturn(mockResponse);
        
        // When
        Page result = beerClient.listBeers();
        
        // Then
        assertNotNull(result);
        assertEquals(0, result.getContent().size());
    }
}