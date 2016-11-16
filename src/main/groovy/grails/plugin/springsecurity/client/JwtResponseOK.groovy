package grails.plugin.springsecurity.client

import groovy.transform.CompileStatic

@CompileStatic
class JwtResponseOK implements JwtResponse {
    Jwt jwt
}
