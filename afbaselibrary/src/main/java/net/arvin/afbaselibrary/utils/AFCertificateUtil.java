package net.arvin.afbaselibrary.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by arvinljw on 17/5/12 17:01
 * Function：https请求证书工具
 * Desc：
 */
public class AFCertificateUtil {

    private static InputStream[] getCertificatesByAssert(Context context, String... certificateNames) {
        if (context == null) {
            Logger.d("context is empty");
            return null;
        }
        if (certificateNames == null) {
            Logger.d("certificate is empty");
            return null;
        }

        AssetManager assets = context.getAssets();
        InputStream[] certificates = new InputStream[certificateNames.length];
        for (int i = 0; i < certificateNames.length; i++) {
            String certificateName = certificateNames[i];
            try {
                certificates[i] = assets.open(certificateName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return certificates;
    }

    public static SSLSocketFactory setCertificates(Context context, String... certificateNames) {
        InputStream[] certificates = getCertificatesByAssert(context, certificateNames);
        if (certificates == null) {
            return null;
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
