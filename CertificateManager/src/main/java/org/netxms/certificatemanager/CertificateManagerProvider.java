package org.netxms.certificatemanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.Enumeration;

public class CertificateManagerProvider {
    private static volatile CertificateManager manager;
    private final CertificateManagerProviderListener listener;

    private CertificateManagerProvider(CertificateManagerProviderListener listener) {
        this.listener = listener;
    }

    public static synchronized CertificateManager provideCertificateManager(
        CertificateManagerProviderListener listener) {

        if(manager != null) return manager;
        manager = new CertificateManagerProvider(listener).createCertificateManager();
        return manager;
    }

    protected CertificateManager createCertificateManager() {
        final String os = System.getProperty("os.name");
        final KeyStore keyStore;
        Certificate[] certs;

        try {
            if(os.startsWith("Windows")) {
                keyStore = getMscKeyStore();
            } else {
                keyStore = getPkcsKeyStore();
            }

            certs = getCertsFromKeyStore(keyStore);
        } catch(NoSuchProviderException e) {
            certs = new Certificate[0];
            e.printStackTrace();
        } catch(KeyStoreException e) {
            certs = new Certificate[0];
            e.printStackTrace();
        } catch(CertificateException e) {
            certs = new Certificate[0];
            e.printStackTrace();
        } catch(NoSuchAlgorithmException e) {
            certs = new Certificate[0];
            e.printStackTrace();
        } catch(IOException e) {
            certs = new Certificate[0];
            e.printStackTrace();
        }

        return new CertificateManager(certs);
    }

    protected KeyStore getMscKeyStore()
        throws NoSuchProviderException, KeyStoreException,
        CertificateException, NoSuchAlgorithmException, IOException {

        KeyStore ks = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
        ks.load(null, null);

        return ks;
    }

    protected KeyStore getPkcsKeyStore()
        throws KeyStoreException, CertificateException,
        NoSuchAlgorithmException, IOException {

        KeyStore ks = KeyStore.getInstance("PKCS12");
        String ksLocation = listener.requestKeyStoreLocation();
        String ksPassword = listener.requestKeyStorePassword();

        FileInputStream fis;
        try {
            fis = new FileInputStream(ksLocation);
            try {
                ks.load(fis, ksPassword.toCharArray());
            } finally {
                fis.close();
            }
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
            fnfe.printStackTrace();
        }

        return ks;
    }

    protected Certificate[] getCertsFromKeyStore(KeyStore ks)
        throws KeyStoreException {

        int numOfCerts = ks.size();
        final Certificate[] certs = new Certificate[numOfCerts];

        if(numOfCerts == 0) {
            return certs;
        }

        Enumeration<String> aliases = ks.aliases();

        for(int i = 0; i < numOfCerts; i++) {
            String alias = aliases.nextElement();
            System.out.println(alias);
        }

        return certs;
    }

    public static synchronized void dispose() {
        manager = null;
    }
}
