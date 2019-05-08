/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.nixsolutions.bukrieiev.provider;

import com.google.gson.Gson;
import com.nixsolutions.bukrieiev.api.Weather;
import com.nixsolutions.bukrieiev.api.WeatherServiceCassandraInterface;
import com.nixsolutions.bukrieiev.api.WeatherServiceRestInterface;
import com.nixsolutions.bukrieiev.camel.CamelForwarderInterface;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class WeatherServiceRest implements WeatherServiceRestInterface {

    public WeatherServiceRest(WeatherServiceCassandraInterface service, CamelForwarderInterface camelForwarder) {
        this.service = service;
        this.camelForwarder = camelForwarder;
    }

    private CamelForwarderInterface camelForwarder;
    private WeatherServiceCassandraInterface service;

    public Weather getByZip(int zip) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?units=metric&zip=" + zip + "&APPID=5380dedbab3d1dfec4185202fd595b32");
        InputStream in = url.openStream();
        Weather weather = getWeatherFromJson(in);
        service.registerCall(weather, String.valueOf(zip));
        camelForwarder.forward(weather);
        return weather;
    }

    public Weather getByCity(String city) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?units=metric&q=" + city + "&APPID=5380dedbab3d1dfec4185202fd595b32");
        InputStream in = url.openStream();
        Weather weather = getWeatherFromJson(in);
        service.registerCall(weather, city);
        camelForwarder.forward(weather);
        return weather;
    }


    private static Weather getWeatherFromJson(InputStream in) throws IOException {
        CachedOutputStream bos = new CachedOutputStream();
        IOUtils.copy(in, bos);
        in.close();
        bos.close();
        String json = bos.getOut().toString();
        Gson gson = new Gson();
        return gson.fromJson(json, Weather.class);
    }

}
