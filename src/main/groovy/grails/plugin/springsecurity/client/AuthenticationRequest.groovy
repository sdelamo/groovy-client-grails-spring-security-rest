package grails.plugin.springsecurity.client

import groovy.transform.CompileStatic

@CompileStatic
class AuthenticationRequest {
    String path = '/api/login'
    String usernamePropertyName = 'username'
    String passwordPropertyName = 'password'
    String serverUrl
    String username
    String password

    boolean useJsonCredentials

    String endpoint() {
        "${serverUrl ?: ''}${path}" as String
    }

    Map<String, List<String>> queryParameters() {
        Map<String, List<String>> m = [:]
        credentials().each { k, v -> m.put(k, [v]) }
        m
    }

    Map<String, String> credentials() {
        def m = [:]
        m.put(usernamePropertyName, username)
        m.put(passwordPropertyName, password)
        m
    }
}
