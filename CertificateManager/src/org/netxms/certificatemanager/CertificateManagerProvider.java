package org.netxms.certificatemanager;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CertificateManagerProvider {
    private static volatile CertificateManager manager;

    private CertificateManagerProvider() {}

    public static synchronized CertificateManager provideCertificateManager() {
        if(manager != null) return manager;
        manager = new CertificateManagerProvider().createCertificateManager();
        return manager;
    }

    private CertificateManager createCertificateManager() {
        final String os = System.getProperty("os.name");
        final KeyStore keyStore;
        if(os.startsWith("Windows")) {
            try {
                keyStore = getWindowsKeyStore();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
                return null;
            } catch (KeyStoreException e) {
                e.printStackTrace();
                return null;
            } catch (CertificateException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }

        List<Certificate> certs = getCertificates(keyStore);

        return new CertificateManager(null);
    }

    private List<Certificate> getCertificates(KeyStore ks) {
        final Enumeration<String> aliases;

        try {
            aliases = ks.aliases();
        } catch (KeyStoreException e) {
            System.out.println(e.getMessage());
            return new ArrayList<Certificate>(0);
        }

        while(aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            System.out.println(alias);
        }

        return null;
    }

    private KeyStore getWindowsKeyStore()
        throws NoSuchProviderException, KeyStoreException,
        CertificateException, NoSuchAlgorithmException, IOException {

        KeyStore ks = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
        ks.load(null, null);
        return ks;
    }
}
