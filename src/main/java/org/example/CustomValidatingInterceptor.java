package org.example;

import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.support.interceptor.WebServiceValidationException;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.xml.xsd.XsdSchema;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class CustomValidatingInterceptor extends PayloadValidatingInterceptor {

    @Autowired
    private ObjectFactory<AppConfig> konfigurace;

    public CustomValidatingInterceptor(XsdSchema schema) {
        setXsdSchema(schema);
    }

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint)
            throws IOException, SAXException, TransformerException {
        return super.handleRequest(messageContext, endpoint);
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) throws IOException, SAXException {
        return super.handleResponse(messageContext, endpoint);
    }

    @Override
    protected boolean handleRequestValidationErrors(MessageContext messageContext, SAXParseException[] errors)
            throws TransformerException {
        return super.handleRequestValidationErrors(messageContext, errors);
    }

    @Override
    protected boolean handleResponseValidationErrors(MessageContext messageContext, SAXParseException[] errors)
            throws WebServiceValidationException {
        return super.handleResponseValidationErrors(messageContext, errors);
    }

    @Override
    protected Source getValidationRequestSource(WebServiceMessage request) {
        return super.getValidationRequestSource(request);
    }

    @Override
    protected Source getValidationResponseSource(WebServiceMessage response) {
        return super.getValidationResponseSource(response);
    }

}
