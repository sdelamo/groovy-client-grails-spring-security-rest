package grails.plugin.springsecurity.client

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
class Jwt {
    String username
    List<String> roles
    String tokenType
    String accessToken
    int expiresIn
    String refreshToken
    String scope
}
