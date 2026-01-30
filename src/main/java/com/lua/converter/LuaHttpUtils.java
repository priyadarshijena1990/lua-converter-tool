package com.lua.converter;

public class LuaHttpUtils {
    // Example Lua code snippets for HTTP/REST
    public static String getHttpGetSnippet() {
        return "local http = require('resty.http')\n" +
               "local httpc = http.new()\n" +
               "local res, err = httpc:request_uri('http://example.com', { method = 'GET' })\n";
    }
    public static String getHttpPostSnippet() {
        return "local http = require('resty.http')\n" +
               "local httpc = http.new()\n" +
               "local res, err = httpc:request_uri('http://example.com', { method = 'POST', body = 'data' })\n";
    }
    public static String getHttpHeadersSnippet() {
        return "local headers = res.headers\n";
    }
    public static String getHttpCookiesSnippet() {
        return "local cookies = res.cookies\n";
    }
    public static String getHttpsSnippet() {
        return "local http = require('resty.http')\n" +
               "local httpc = http.new()\n" +
               "local res, err = httpc:request_uri('https://example.com', { method = 'GET', ssl_verify = false })\n";
    }
    // Add more HTTP/REST/Web API snippets as needed
}
