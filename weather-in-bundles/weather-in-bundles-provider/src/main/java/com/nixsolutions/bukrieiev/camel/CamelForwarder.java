package com.nixsolutions.bukrieiev.camel;

import com.nixsolutions.bukrieiev.api.Weather;
import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mail.MailComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelForwarder implements CamelForwarderInterface {

    private CamelContext camel;

    public void forward(Weather weather) {
        SimpleRouteBuilder routeBuilder = new SimpleRouteBuilder();
        camel = new DefaultCamelContext();
        try {
            Component mailComponent = new MailComponent();
            camel.addComponent("smtps", mailComponent);
            camel.addRoutes(routeBuilder);
            camel.start();


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ProducerTemplate producerTemplate = camel.createProducerTemplate();
        producerTemplate.sendBody("direct://start", weather.toString());
    }

}
