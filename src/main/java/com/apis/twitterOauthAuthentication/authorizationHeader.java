package com.apis.twitterOauthAuthentication;

import com.apis.helpers.genericHelpers;
import com.apis.helpers.propertiesFile;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class authorizationHeader {
    private static long timeStamp = 1;

    private static String newOauthNonce()
    {
        String uuid_string = UUID.randomUUID().toString();
        uuid_string = uuid_string.replaceAll("-", "");
        return uuid_string;
    }

    private static String createSignatureBaseString(String postUrl, String[] parameters, String nonce) throws IOException {

        List myList = new ArrayList();

        String oauth_consumer_key = genericHelpers.percentEncode("oauth_consumer_key") + "=" + genericHelpers.percentEncode(propertiesFile.getTwitterSetting("oauth.consumerKey"));
        myList.add(oauth_consumer_key);
        String oauth_token = genericHelpers.percentEncode("oauth_token") + "=" + genericHelpers.percentEncode(propertiesFile.getTwitterSetting("oauth.accessToken"));
        myList.add(oauth_token);
        String oauth_signature_method = genericHelpers.percentEncode("oauth_signature_method") + "=" + genericHelpers.percentEncode("HMAC-SHA1");
        myList.add(oauth_signature_method);
        timeStamp = System.currentTimeMillis() / 1000;
        String oauth_timestamp = genericHelpers.percentEncode("oauth_timestamp") + "=" + genericHelpers.percentEncode(Long.toString(timeStamp));
        myList.add(oauth_timestamp);
        String oauth_nonce = genericHelpers.percentEncode("oauth_nonce") + "=" + genericHelpers.percentEncode(nonce);
        myList.add(oauth_nonce);
        String oauth_version = genericHelpers.percentEncode("oauth_version") + "=" + genericHelpers.percentEncode("1.0");
        myList.add(oauth_version);

        //ADD PARAMETERS TO LIST
        for (Object s : parameters)
        {
            myList.add(s);
        }
        java.util.Collections.sort(myList);

        String parameterString = "";
        for (Object s : myList)
        {
            parameterString += s + "&";
        }
        parameterString = parameterString.substring(0, parameterString.length()-1);
        String outputString = "POST&" + genericHelpers.percentEncode(postUrl) + "&" + genericHelpers.percentEncode(parameterString);
        return outputString;
    }

    private static String generateOauthSignature(String postUrl, String[] parameters, String nonce) {
        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance("HMACSHA1");
            SecretKeySpec spec;
            if (null == propertiesFile.getTwitterSetting("oauth.accessTokenSecret")) {
                String signingKey = genericHelpers.percentEncode(propertiesFile.getTwitterSetting("oauth.consumerSecret")) + '&';
                spec = new SecretKeySpec(signingKey.getBytes(), "HMACSHA1");
            } else {
                String signingKey = genericHelpers.percentEncode(propertiesFile.getTwitterSetting("oauth.consumerSecret")) + '&' + genericHelpers.percentEncode(propertiesFile.getTwitterSetting("oauth.accessTokenSecret"));
                spec = new SecretKeySpec(signingKey.getBytes(), "HMACSHA1");
            }
            mac.init(spec);
            byteHMAC = mac.doFinal(createSignatureBaseString(postUrl, parameters, nonce).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BASE64Encoder().encode(byteHMAC);
    }

    public static String buildAuthorizationString(String postUrl, String[] parameters) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("OAuth ");

        stringBuilder.append(genericHelpers.percentEncode("oauth_consumer_key"));
        stringBuilder.append("=\"");
        stringBuilder.append(genericHelpers.percentEncode(propertiesFile.getTwitterSetting("oauth.consumerKey")));
        stringBuilder.append("\", ");

        stringBuilder.append(genericHelpers.percentEncode("oauth_nonce"));
        stringBuilder.append("=\"");
        String nonce = newOauthNonce();
        stringBuilder.append(genericHelpers.percentEncode(nonce));
        stringBuilder.append("\", ");

        stringBuilder.append(genericHelpers.percentEncode("oauth_signature"));
        stringBuilder.append("=\"");
        stringBuilder.append(genericHelpers.percentEncode(generateOauthSignature(postUrl, parameters, nonce)));
        stringBuilder.append("\", ");

        stringBuilder.append(genericHelpers.percentEncode("oauth_signature_method"));
        stringBuilder.append("=\"");
        stringBuilder.append(genericHelpers.percentEncode("HMAC-SHA1"));
        stringBuilder.append("\", ");

        stringBuilder.append(genericHelpers.percentEncode("oauth_timestamp"));
        stringBuilder.append("=\"");
        stringBuilder.append(genericHelpers.percentEncode(Long.toString(timeStamp)));
        stringBuilder.append("\", ");

        stringBuilder.append(genericHelpers.percentEncode("oauth_token"));
        stringBuilder.append("=\"");
        stringBuilder.append(genericHelpers.percentEncode(propertiesFile.getTwitterSetting("oauth.accessToken")));
        stringBuilder.append("\", ");

        stringBuilder.append(genericHelpers.percentEncode("oauth_version"));
        stringBuilder.append("=\"");
        stringBuilder.append(genericHelpers.percentEncode("1.0"));
        stringBuilder.append("\"");

        String headerString = stringBuilder.toString();

        return headerString;
    }
}