package com.facebook.myserverapi.clients;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

//import org.testng.annotations.Test;

public class TestHttpClient {

    @Test
    public void test() throws IOException {
        //  Create
        HttpClient _httpClient = HttpClientBuilder.create().build();

        //  Get
        String getUrl = "http://localhost:7077/myserver-backend/abc/asdfghjkl";
        HttpGet httpGet = new HttpGet(getUrl);
        HttpResponse responseGet = _httpClient.execute(httpGet);
        httpGet.releaseConnection();
        String result = EntityUtils.toString(responseGet.getEntity(), "UTF-8");
        System.out.println(result);

        //  Post + Get
        String postUrl = "http://localhost:7077/myserver-backend/abc";
        HttpPost httpPost = new HttpPost(postUrl);
        String jsonPost = "{\"shortUrl\":\"abcd\", \"longUrl\":\"asdfghjkl\"}";
        StringEntity entityPost = new StringEntity(jsonPost);
        httpPost.setEntity(entityPost);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse responsePost = _httpClient.execute(httpPost);
        httpPost.releaseConnection();

        HttpGet httpGetAfterPost = new HttpGet(getUrl);
        HttpResponse responseGetAfterPost = _httpClient.execute(httpGetAfterPost);
        httpGet.releaseConnection();
        String resultAfterPost = EntityUtils.toString(responseGetAfterPost.getEntity(), "UTF-8");
        System.out.println(resultAfterPost);

        //  Put + Get
        String putUrl = "http://localhost:7077/myserver-backend/abc/asdfghjkl";
        HttpPut httpPut = new HttpPut(putUrl);
        String jsonPut = "{\"shortUrl\":\"aaaaaa\", \"longUrl\":\"asdfghjkl\"}";
        HttpEntity entityPut = new StringEntity(jsonPut);
        httpPut.setEntity(entityPut);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        HttpResponse responsePut = _httpClient.execute(httpPut);
        httpPut.releaseConnection();

        HttpGet httpGetAfterPut = new HttpGet(putUrl);
        HttpResponse responseGetAfterPut = _httpClient.execute(httpGetAfterPut);
        httpGet.releaseConnection();
        String resultAfterPut = EntityUtils.toString(responseGetAfterPut.getEntity(), "UTF-8");
        System.out.println(resultAfterPut);

        //  Delete + Get
        String deleteUrl = "http://localhost:7077/myserver-backend/abc/asdfghjkl";
        HttpDelete httpDelete = new HttpDelete(deleteUrl);
        HttpResponse responseDelete = _httpClient.execute(httpDelete);
        httpDelete.releaseConnection();

        HttpGet httpGetAfterDelete = new HttpGet(getUrl);
        HttpResponse responseGetAfterDelete = _httpClient.execute(httpGetAfterDelete);
        httpGet.releaseConnection();
        String resultAfterDelete = EntityUtils.toString(responseGetAfterDelete.getEntity(), "UTF-8");
        System.out.println(resultAfterDelete);

    }
}