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

    @SuppressWarnings('ConfusingMethodName')
    void useJsonCredentials(boolean useJsonCredentials) {
        this.useJsonCredentials = useJsonCredentials
    }

    @SuppressWarnings('ConfusingMethodName')
    void serverUrl(String str) {
        this.serverUrl = str
    }

    @SuppressWarnings('ConfusingMethodName')
    void username(String str) {
        this.username = str
    }

    @SuppressWarnings('ConfusingMethodName')
    void password(String str) {
        this.password = str
    }

    @SuppressWarnings('ConfusingMethodName')
    void path(String str) {
        this.path = str
    }

    @SuppressWarnings('ConfusingMethodName')
    void usernamePropertyName(String str) {
        usernamePropertyName = str
    }

    @SuppressWarnings('ConfusingMethodName')
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
