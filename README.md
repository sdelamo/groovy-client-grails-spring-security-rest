Groovy Client - Grails Spring Security Rest Plugin
================

[![Build Status](https://travis-ci.org/sdelamo/groovy-client-grails-spring-security-rest.svg?branch=master)](https://travis-ci.org/sdelamo/groovy-client-grails-spring-security-rest)
[![Download](https://api.bintray.com/packages/sdelamo/plugins/groovy-client-grails-spring-security-rest/images/download.svg)](https://bintray.com/sdelamo/plugins/groovy-client-grails-spring-security-rest/_latestVersion)


# Introduction 

This is a groovy client to interact with the [Grails Spring Security Rest Plugin](https://github.com/alvarosanchez/grails-spring-security-rest)


# Usage

## Login
````
def client = new GrailsSpringSecurityRestClient()
JwtResponse rsp = client.login {
    serverUrl = 'http://yourgrailsapp.com'
    username = 'joe@gmail.com'
    password = 'secret'
}
````

## Refresh

````
JwtResponse rsp = client.refresh {
    serverUrl = 'http://yourgrailsapp.com'
    refreshToken = 'xxxxxxxxxxx'
}
````