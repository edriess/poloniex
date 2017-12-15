package com.demo.ticker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public class PoloniexManager {

    public static final String POLONIEX_TICKER_URL = "https://poloniex.com/public?command=returnTicker";

    private static final SSLSocketFactory ignoreAllProblems = createNonSecureSSLSocketFactory();


    public Map<String, PoloniexTickerBean> convertToObjects(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, new TypeReference<Map<String, PoloniexTickerBean>>() {});
    }


    public String loadTickerJson() throws Exception {
        final URI uri = new URI(POLONIEX_TICKER_URL);

        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(20000);
        connection.setRequestMethod("GET");

        if (connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) connection).setSSLSocketFactory(ignoreAllProblems);
        }

        final int status = connection.getResponseCode();

        if (status != HTTP_OK) {
            System.out.println("Error: http return status was something other than 200");
            System.out.println("status = " + status);
        }

        final InputStream inputStream = connection.getInputStream();

        return getStringFromStream(inputStream);
    }

    private String getStringFromStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString("UTF-8");
    }

    private static SSLSocketFactory createNonSecureSSLSocketFactory() {
        // see Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType)
                            throws CertificateException {
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType)
                            throws CertificateException {
                    }
                }
        };

        try {
            final SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            return sc.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
