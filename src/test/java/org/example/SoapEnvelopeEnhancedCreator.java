package org.example;

import static org.springframework.ws.test.support.AssertionErrors.fail;

import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMResult;

import org.custommonkey.xmlunit.XMLUnit;
import org.springframework.core.io.Resource;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.xml.transform.ResourceSource;
import org.springframework.xml.transform.TransformerHelper;
import org.w3c.dom.Document;

public class SoapEnvelopeEnhancedCreator implements RequestCreator {

    private Source expected;

    private final TransformerHelper transformerHelper = new TransformerHelper();

    static {
        XMLUnit.setIgnoreWhitespace(true);
    }

    public SoapEnvelopeEnhancedCreator(Resource soapEnvelope) throws IOException {
        expected = new ResourceSource(soapEnvelope);
    }

// @formatter:off
// default SoapEnvelopeMessageCreator    
//    @Override
//    protected void doWithMessage(WebServiceMessage message) throws IOException {
//        assertTrue("Message created with factory is not a SOAP message", message instanceof SoapMessage);
//        SoapMessage soapMessage = (SoapMessage) message;
//        try {
//            DOMResult result = new DOMResult();
//            transformerHelper.transform(soapEnvelope, result);
//            soapMessage.setDocument((Document) result.getNode());
//        }
//        catch (TransformerException ex) {
//            fail("Could not transform request SOAP envelope to message: " + ex.getMessage());
//        }       
//    }
// @formatter:on

    @Override
    public WebServiceMessage createRequest(WebServiceMessageFactory messageFactory) throws IOException {
        WebServiceMessage message = messageFactory.createWebServiceMessage();
        SaajSoapMessage soapMessage = (SaajSoapMessage) message;
        try {
            DOMResult result = new DOMResult();
            transformerHelper.transform(expected, result);
            soapMessage.setDocument((Document) result.getNode());
            soapMessage.setSaajMessage(soapMessage.getSaajMessage());
        } catch (TransformerException ex) {
            fail("Could not transform request SOAP envelope to message: " + ex.getMessage());
        }
        return soapMessage;
    }

    public static SoapEnvelopeEnhancedCreator withEnhancedSoapEnvelope(Resource soapEnvelope) throws IOException {
        return new SoapEnvelopeEnhancedCreator(soapEnvelope);
    }

}
