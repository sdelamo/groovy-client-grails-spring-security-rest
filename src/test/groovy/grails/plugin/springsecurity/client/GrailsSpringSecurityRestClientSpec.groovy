package grails.plugin.springsecurity.client

import spock.lang.IgnoreIf
import spock.lang.Specification

class GrailsSpringSecurityRestClientSpec extends Specification {

    @SuppressWarnings('Instanceof')
    @IgnoreIf({ !System.getProperty('username') || !System.getProperty('password') || !System.getProperty('server') })
    def "test login"() {

        when:
        def client = new GrailsSpringSecurityRestClient()
        JwtResponse rsp = client.login {
            serverUrl System.getProperty('server')
            path '/oauth/token'
            useJsonCredentials = false
            username System.getProperty('username')
            password System.getProperty('password')
        }

        then:
        rsp instanceof JwtResponseOK

        when:
        Jwt jwt = (rsp as JwtResponseOK).jwt

        then:
        jwt
        jwt.expiresIn > 0
        jwt.username
        jwt.accessToken
        jwt.refreshToken
        jwt.roles
        jwt.roles.size() > 0

        when:
        rsp = client.login {
            serverUrl System.getProperty('server')
            path '/oauth/token'
            useJsonCredentials false
            username System.getProperty('username')
            password 'bogus'
        }

        then:
        rsp instanceof JwtResponseUnauthorized

        when:
        rsp = client.refresh {
            serverUrl System.getProperty('server')
            path '/oauth/token'
            refreshToken jwt.refreshToken
        }

        then:
        rsp instanceof JwtResponseOK

        when:
        jwt = (rsp as JwtResponseOK).jwt

        then:
        jwt
        jwt.expiresIn > 0
        jwt.username
        jwt.accessToken
        jwt.refreshToken
        jwt.roles
        jwt.roles.size() > 0
    }
}
