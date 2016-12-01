package grails.plugin.springsecurity.client

import groovy.transform.CompileStatic

@CompileStatic
class RefreshRequest {
    String path = '/oauth/access_token'
    String serverUrl
    String refreshToken
    String grantType = 'refresh_token'
    String refreshTokenPropertyName = 'refresh_token'
    String grantTypePropertyName = 'grant_type'

    @SuppressWarnings('ConfusingMethodName')
    void path(String str) {
        this.path  = str
    }

    @SuppressWarnings('ConfusingMethodName')
    void serverUrl(String str) {
        this.serverUrl = str
    }

    @SuppressWarnings('ConfusingMethodName')
    void refreshToken(String str) {
        this.refreshToken = str
    }

    @SuppressWarnings('ConfusingMethodName')
    void grantType(String str) {
        this.grantType = str
    }

    @SuppressWarnings('ConfusingMethodName')
    void refreshTokenPropertyName(String str) {
        refreshTokenPropertyName = str
    }

    @SuppressWarnings('ConfusingMethodName')
    void grantTypePropertyName(String str) {
        grantTypePropertyName = str
    }

    String endpoint() {
        "${serverUrl ?: ''}${path}" as String
    }

    Map<String, List<String>> queryParameters() {
        Map<String, List<String>> m = [:]
        params().each { k, v -> m.put(k, [v]) }
        m
    }

    Map<String, String> params() {
        def m = [:]
        m.put(refreshTokenPropertyName, refreshToken)
        m.put(grantTypePropertyName, grantType)
        m
    }
}
