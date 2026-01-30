package com.lua.converter;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class ComprehensiveLuaConverterTest {
    @Test
    public void testJavaAllFeatures() throws Exception {
        File javaFile = new File("src/test/resources/AllFeatures.java");
        String lua = JavaToLuaConverter.convert(javaFile);
        assertTrue(lua.contains("function"));
        assertTrue(lua.contains("if "));
        assertTrue(lua.contains("for "));
        assertTrue(lua.contains("while "));
        assertTrue(lua.contains("= {}"));
    }

    @Test
    public void testJsAllFeatures() throws Exception {
        File jsFile = new File("src/test/resources/allFeatures.js");
        String lua = JsToLuaConverter.convert(jsFile);
        assertTrue(lua.contains("function"));
        assertTrue(lua.contains("if "));
        assertTrue(lua.contains("for "));
        assertTrue(lua.contains("while "));
        assertTrue(lua.contains("-- Converted from JavaScript"));
    }

    // Python-to-Lua conversion removed

    // @Test
    @Test
    public void testJarAllFeatures() throws Exception {
        File jarFile = new File("src/test/resources/allFeatures.jar");
        String lua = JarToLuaConverter.convert(jarFile);
        assertTrue(lua.contains("-- class"));
        assertTrue(lua.contains("function"));
        assertTrue(lua.contains("-- method:"));
        assertTrue(lua.contains("-- field:"));
    }

    @Test
    public void testApigeeApiMapping() {
        assertEquals("ngx.var", ApigeeApiMapper.map("context.getVariable"));
        assertEquals("ngx.req", ApigeeApiMapper.map("context.getRequest"));
        assertEquals("ngx.header", ApigeeApiMapper.map("context.getResponseHeader"));
        assertEquals("resty.http:request_uri (GET)", ApigeeApiMapper.map("httpClient.get"));
        assertEquals("resty.http:request_uri (POST)", ApigeeApiMapper.map("httpClient.post"));
        assertEquals("resty.http:set_timeout", ApigeeApiMapper.map("httpClient.setTimeout"));
        assertEquals("ngx.req.get_headers", ApigeeApiMapper.map("context.getRequestHeader"));
        assertEquals("ngx.req.get_body_data()", ApigeeApiMapper.map("request.content"));
        assertEquals("cjson.decode", ApigeeApiMapper.map("JSON.parse"));
        assertEquals("cjson.encode", ApigeeApiMapper.map("JSON.stringify"));
        assertEquals("ngx.log(ngx.INFO, ...)", ApigeeApiMapper.map("console.log"));
        assertEquals("math.random()", ApigeeApiMapper.map("Math.random"));
        assertEquals("os.time()", ApigeeApiMapper.map("Date.now"));
        assertEquals("resty.aes", ApigeeApiMapper.map("crypto.createCipheriv"));
        assertEquals("resty.hmac", ApigeeApiMapper.map("crypto.createHmac"));
        assertEquals("ngx.timer.at", ApigeeApiMapper.map("setTimeout"));
    }

    // @Test
    // public void testLuaHttpUtils() {
    //     assertTrue(LuaHttpUtils.getHttpGetSnippet().contains("resty.http"));
    //     assertTrue(LuaHttpUtils.getHttpPostSnippet().contains("POST"));
    //     assertTrue(LuaHttpUtils.getHttpHeadersSnippet().contains("headers"));
    //     assertTrue(LuaHttpUtils.getHttpsSnippet().contains("ssl_verify"));
    // }

    // @Test
    // public void testLuaSecurityUtils() {
    //     assertTrue(LuaSecurityUtils.getAesEncryptSnippet().contains("resty.aes"));
    //     assertTrue(LuaSecurityUtils.getSha256Snippet().contains("resty.sha256"));
    //     assertTrue(LuaSecurityUtils.getHmacSnippet().contains("resty.hmac"));
    //     assertTrue(LuaSecurityUtils.getBase64Snippet().contains("base64"));
    //     assertTrue(LuaSecurityUtils.getJwtSnippet().contains("resty.jwt"));
    //     assertTrue(LuaSecurityUtils.getRandomSnippet().contains("math.random"));
    // }
}
