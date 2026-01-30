package com.lua.converter;

import java.util.HashMap;
import java.util.Map;

public class ApigeeApiMapper {
    private static final Map<String, String> apigeeToLua = new HashMap<>();
    static {
        // Apigee context and variables
        apigeeToLua.put("context.getVariable", "ngx.var");
        apigeeToLua.put("context.setVariable", "ngx.var");
        apigeeToLua.put("context.getEnvironmentVariable", "os.getenv");
        apigeeToLua.put("context.setEnvironmentVariable", "os.setenv");
        apigeeToLua.put("context.getRequest", "ngx.req");
        apigeeToLua.put("context.getResponse", "ngx.resp");
        apigeeToLua.put("context.getRequestHeader", "ngx.req.get_headers");
        apigeeToLua.put("context.setRequestHeader", "ngx.req.set_header");
        apigeeToLua.put("context.getResponseHeader", "ngx.header");
        apigeeToLua.put("context.setResponseHeader", "ngx.header");
        apigeeToLua.put("context.removeRequestHeader", "ngx.req.clear_header");
        apigeeToLua.put("context.removeResponseHeader", "ngx.header = nil");
        apigeeToLua.put("context.getVariableNames", "ngx.var");
        apigeeToLua.put("context.getFlowVariable", "ngx.var");
        apigeeToLua.put("context.setFlowVariable", "ngx.var");
        apigeeToLua.put("context.removeFlowVariable", "ngx.var = nil");

        // HTTP/REST
        apigeeToLua.put("httpClient.send", "ngx.location.capture");
        apigeeToLua.put("httpClient.get", "resty.http:request_uri (GET)");
        apigeeToLua.put("httpClient.post", "resty.http:request_uri (POST)");
        apigeeToLua.put("httpClient.put", "resty.http:request_uri (PUT)");
        apigeeToLua.put("httpClient.delete", "resty.http:request_uri (DELETE)");
        apigeeToLua.put("httpClient.setTimeout", "resty.http:set_timeout");
        apigeeToLua.put("httpClient.setFollowRedirects", "resty.http:set_follow_redirects");
        apigeeToLua.put("httpClient.getResponseCode", "ngx.status");
        apigeeToLua.put("httpClient.getResponseBody", "ngx.arg[\"body\"]");
        apigeeToLua.put("httpClient.getResponseHeader", "ngx.header");

        // Request/Response
        apigeeToLua.put("request.content", "ngx.req.get_body_data()");
        apigeeToLua.put("request.headers", "ngx.req.get_headers()");
        apigeeToLua.put("request.queryParams", "ngx.req.get_uri_args()");
        apigeeToLua.put("request.verb", "ngx.req.get_method()");
        apigeeToLua.put("request.path", "ngx.var.uri");
        apigeeToLua.put("request.url", "ngx.var.request_uri");
        apigeeToLua.put("request.formParams", "ngx.req.get_post_args()");
        apigeeToLua.put("response.content", "ngx.arg[\"body\"]");
        apigeeToLua.put("response.headers", "ngx.header");
        apigeeToLua.put("response.status", "ngx.status");

        // Crypto/Security
        apigeeToLua.put("crypto-js", "resty.aes/resty.string");
        apigeeToLua.put("crypto.createHash", "resty.sha256/resty.string");
        apigeeToLua.put("crypto.createCipheriv", "resty.aes");
        apigeeToLua.put("crypto.randomBytes", "math.random");
        apigeeToLua.put("crypto.createSign", "resty.openssl");
        apigeeToLua.put("crypto.createVerify", "resty.openssl");
        apigeeToLua.put("crypto.createHmac", "resty.hmac");
        apigeeToLua.put("crypto.pbkdf2Sync", "resty.pbkdf2");

        // JSON
        apigeeToLua.put("JSON.parse", "cjson.decode");
        apigeeToLua.put("JSON.stringify", "cjson.encode");

        // Logging
        apigeeToLua.put("console.log", "ngx.log(ngx.INFO, ...)");
        apigeeToLua.put("console.error", "ngx.log(ngx.ERR, ...)");
        apigeeToLua.put("System.out.println", "ngx.log(ngx.INFO, ...)");
        apigeeToLua.put("print", "ngx.log(ngx.INFO, ...)");

        // Utility/Env
        apigeeToLua.put("Date.now", "os.time()");
        apigeeToLua.put("Math.random", "math.random()");
        apigeeToLua.put("Math.floor", "math.floor()");
        apigeeToLua.put("Math.ceil", "math.ceil()");
        apigeeToLua.put("Math.abs", "math.abs()");
        apigeeToLua.put("setTimeout", "ngx.timer.at");
        apigeeToLua.put("setInterval", "ngx.timer.every");

        // Add more mappings as needed
    }

    public static String map(String apigeeCall) {
        return apigeeToLua.getOrDefault(apigeeCall, apigeeCall);
    }
}
