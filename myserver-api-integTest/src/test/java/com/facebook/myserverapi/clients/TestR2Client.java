package com.facebook.myserverapi.clients;

import com.linkedin.r2.RemoteInvocationException;
import com.linkedin.r2.transport.common.bridge.client.TransportClientAdapter;
import com.linkedin.r2.transport.http.client.HttpClientFactory;
import com.linkedin.restli.client.*;
import nam.e.spa.ce.Abc;
import nam.e.spa.ce.AbcRequestBuilders;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;


public class TestR2Client {

    @Test
    public void test() throws IOException, RemoteInvocationException {

        final String longUrl = "asdfghjkl";
        HttpClientFactory http = new HttpClientFactory();
        TransportClientAdapter r2Client = new TransportClientAdapter(
                http.getClient(Collections.<String, String>emptyMap()));
        RestClient _restClient = new RestClient(r2Client, "http://localhost:7077/myserver-backend/");


        // get
        GetRequest<Abc> request = new AbcRequestBuilders()
                .get()
                .id(longUrl)
                .build();
        Abc abc = _restClient.sendRequest(request).getResponse().getEntity();
        System.out.println(abc);

        // create
        CreateIdRequest<String, Abc> requestAfterCreate = new AbcRequestBuilders()
                .create()
                .input(new Abc().setLongUrl(longUrl).setShortUrl("abcd"))
                .build();
        _restClient.sendRequest(requestAfterCreate).getResponse();

        Abc abcAfterCreate = _restClient.sendRequest(request).getResponse().getEntity();
        System.out.println(abcAfterCreate);

        // update
        UpdateRequest<Abc> requestAfterUpdate = new AbcRequestBuilders()
                .update()
                .id(longUrl)
                .input(new Abc().setLongUrl(longUrl).setShortUrl("aaaaaa"))
                .build();
        _restClient.sendRequest(requestAfterUpdate).getResponse();

        Abc abcAfterUpdate = _restClient.sendRequest(request).getResponse().getEntity();
        System.out.println(abcAfterUpdate);

        // delete
        DeleteRequest<Abc> requestAfterDelete = new AbcRequestBuilders().delete()
                .id(longUrl)
                .build();
        _restClient.sendRequest(requestAfterDelete).getResponse();

        Abc abcAfterDelete = _restClient.sendRequest(request).getResponse().getEntity();
        System.out.println(abcAfterDelete);
    }
}