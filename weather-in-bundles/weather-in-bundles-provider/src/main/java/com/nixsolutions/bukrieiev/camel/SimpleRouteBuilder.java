package com.nixsolutions.bukrieiev.camel;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("direct://start").doTry().setHeader("subject", simple("Weather from Buker ;)"))
                .setHeader("to", simple("buer2012@gmail.com"))
                .to("smtps://smtp.gmail.com:465?username=RAW(samoylenko.test@gmail.com)&password=2143658709Aa");

    }
}
