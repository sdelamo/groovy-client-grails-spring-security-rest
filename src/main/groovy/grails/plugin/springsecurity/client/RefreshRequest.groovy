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

    String endpoint() {
        "${serverUrl ?: ''}${path}" as String
    }

    Map<String, List<String>> queryParameters() {
        Map<String, List<String>> m = [:]
        params().each { k, v -> m.put(k,[v]) }
        m
    }

    Map<String, String> params() {
        def m = [:] as Map<String, String>
        m.put(refreshTokenPropertyName, refreshToken)
        m.put(grantTypePropertyName, grantType)
        m
    }
}
