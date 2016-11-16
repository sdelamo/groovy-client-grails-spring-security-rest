package grails.plugin.springsecurity.client

import com.budjb.httprequests.HttpClient
import com.budjb.httprequests.HttpRequest
import com.budjb.httprequests.HttpResponse
import com.budjb.httprequests.jersey1.JerseyHttpClientFactory
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

@CompileStatic
class GrailsSpringSecurityRestClient {
    AuthenticationRequest authenticationRequest = new AuthenticationRequest()
    RefreshRequest refreshRequest = new RefreshRequest()
    HttpClient client = new JerseyHttpClientFactory().createHttpClient()

    JwtResponse refresh(@DelegatesTo(RefreshRequest) Closure refreshRequestClosure) {
        refreshRequestClosure.delegate = refreshRequest
        refreshRequestClosure()
        executeRefresh()
    }

    JwtResponse login(@DelegatesTo(AuthenticationRequest) Closure authenticationRequestClosure) {
        authenticationRequestClosure.delegate = authenticationRequest
        authenticationRequestClosure()
        executeLogin()
    }

    private JwtResponse executeRefresh() {
        String urlStr = refreshRequest.endpoint()
        def request = new HttpRequest()
                .setUri(urlStr)
                .setContentType('application/x-www-form-urlencoded')
                .setQueryParameters(refreshRequest.queryParameters())
        HttpResponse response = client.post(request)
        jwtResponseWithHttpResponse(response)
    }

    private JwtResponse executeLogin() {
        String urlStr = authenticationRequest.endpoint()
        HttpResponse response
        if ( authenticationRequest.useJsonCredentials ) {
            def request = new HttpRequest()
                    .setUri(urlStr)
                    .setContentType('application/json')
            response = client.post(request, authenticationRequest.credentials())

        } else {
            def request = new HttpRequest()
                    .setUri(urlStr)
                    .setQueryParameters(authenticationRequest.queryParameters())
            response = client.post(request)
        }
        jwtResponseWithHttpResponse(response)
    }

    static JwtResponse jwtResponseWithHttpResponse(HttpResponse response) {
        if ( !response ) {
            return null
        }
        if ( response.status == 200 ) {
            Map json = response.getEntity(Map)
            return jwtResponseOKWithMap(json)
        }

        if ( response.status == 401 ) {
            return new JwtResponseUnauthorized()
        }

        if ( response.status == 403 ) {
            return new JwtResponseForbidden()
        }

        if ( response.status == 400) {
            return new JwtResponseBadRequest()
        }

        null
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    static JwtResponseOK jwtResponseOKWithMap(Map json) {
        new JwtResponseOK(jwt: new Jwt(username: json.username,
                roles: json.roles,
                tokenType: json.token_type,
                accessToken: json.access_token,
                refreshToken: json.refresh_token,
                expiresIn: json.expires_in))
    }
}
