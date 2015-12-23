# ApiTestingFramework
A framework used to test GET and POST APIs.

Before using this framework, you will need to setup a twitter account to be able to generate your own access tokens. 
Twitter's own documentation explains how to do it:

https://dev.twitter.com/oauth/overview/application-owner-access-tokens

Basically, this is what you have to do:
1. If you don't have a Twitter account, create one;
2. Go to dev.twitter.com/apps/ and log in using your account;
3. Click the Create a new application button and enter the name and description of your application.
4. On the "website" field, you can add your GitHub address, for example.
5. Click on "Create my access token" at the bottom of the Details tab.

Finished! Now you have your own keys and tokens:
OAuth: Consumer key
OAuth: Consumer secret
Token: Access token
Token: Access token secret

Add these to the twitter.properties file and you'll be good to go.


-----------------------------------------------


To get a Spotify's "X-Mashape-Key", go to the following url:

https://market.mashape.com/jperez/spotify-web

Then just add it to spotify.properties.
