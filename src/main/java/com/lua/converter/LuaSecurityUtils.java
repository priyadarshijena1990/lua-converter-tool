package com.lua.converter;

public class LuaSecurityUtils {
    // Example Lua code snippets for security/crypto
    public static String getAesEncryptSnippet() {
        return "local aes = require('resty.aes')\n" +
               "local str = require('resty.string')\n" +
               "local encrypted = aes:new('key'):encrypt('data')\n";
    }
    public static String getSha256Snippet() {
        return "local resty_sha256 = require('resty.sha256')\n" +
               "local str = require('resty.string')\n" +
               "local sha256 = resty_sha256:new()\n" +
               "sha256:update('data')\n" +
               "local digest = sha256:final()\n" +
               "local hex = str.to_hex(digest)\n";
    }
    public static String getHmacSnippet() {
        return "local hmac = require('resty.hmac')\n" +
               "local h = hmac:new('key', hmac.ALGOS.SHA256)\n" +
               "local mac = h:final('data')\n";
    }
    public static String getBase64Snippet() {
        return "local b64 = require('ngx.base64')\n" +
               "local encoded = b64.encode_base64('data')\n";
    }
    public static String getJwtSnippet() {
        return "local jwt = require('resty.jwt')\n" +
               "local token = jwt:sign('secret', { payload = { foo = 'bar' } })\n";
    }
    public static String getRandomSnippet() {
        return "local rand = math.random()\n";
    }
    // Add more security/crypto/encoding snippets as needed
}
