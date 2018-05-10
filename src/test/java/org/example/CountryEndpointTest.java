/*
 * Created on Aug 19, 2011
 * (C) 2011 ICZ, a.s.
 */
package org.example;

import static org.example.SoapEnvelopeEnhancedCreator.withEnhancedSoapEnvelope;
import static org.springframework.ws.test.server.RequestCreators.withSoapEnvelope;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.soapEnvelope;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;

/**
 * @author Tomáš Hudec
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CountryEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void defaultTest() throws Exception {
        mockClient.sendRequest(withSoapEnvelope(applicationContext.getResource("classpath:xml/Request.xml")))
                .andExpect(noFault())
                .andExpect(soapEnvelope(applicationContext.getResource("classpath:xml/Response.xml")));
    }

    @Test
    public void enhancedTest() throws Exception {
        mockClient.sendRequest(withEnhancedSoapEnvelope(applicationContext.getResource("classpath:xml/Request.xml")))
                .andExpect(noFault())
                .andExpect(soapEnvelope(applicationContext.getResource("classpath:xml/Response.xml")));
    }

}