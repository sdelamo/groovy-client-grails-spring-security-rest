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

    void useJsonCredentials(boolean useJsonCredentials) {
        this.useJsonCredentials = useJsonCredentials
    }

    void serverUrl(String str) {
        this.serverUrl = str
    }

    void username(String str) {
        this.username = str
    }

    void password(String str) {
        this.password = str
    }

    void path(String str) {
        this.path = str
    }

    void usernamePropertyName(String str) {
        usernamePropertyName = str
    }

    void passwordPropertyName(String str) {
        passwordPropertyName = str
    }


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
