# Android_JHipster_Basketball
Android frontend that integrates with JHipster backend via OAuth 2.0

This is the corresponding JHipster repository: https://github.com/alfredorueda/Spring-Basketball-oAuth2

You simply need to properly configure the properties:
https://github.com/alfredorueda/Android-Basketball/blob/master/app/src/main/java/com/taniafontcuberta/basketball/util/CustomProperties.java
as defined in your JHipster app:
https://github.com/alfredorueda/basketballOAuth2/blob/master/src/main/resources/config/application.yml

security:
        authentication:
            oauth:
                clientid: basketballapp
                secret: mySecretOAuthSecret
                # Token is valid 30 minutes
                tokenValidityInSeconds: 1800
        rememberme:
            # security key (this key should be unique for your application, and kept secret)
            key: a2a6c0b3627730ff7edad3f9b829c9d80969fe7a
            
            


TODO: 
 Use best practices from this nice book: https://leanpub.com/retrofit-love-working-with-apis-on-android (interceptor)
   (https://futurestud.io/blog/retrofit-series-round-up)

