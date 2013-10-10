package org.netxms.certificatemanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class CertificateManagerProvider {
    private static volatile CertificateManager manager;
    private final CertificateManagerProviderListener listener;
    private String keyStorePass = "";

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
            e.printStackTrace();
            certs = new Certificate[0];
        } catch(KeyStoreException e) {
            e.printStackTrace();
            certs = new Certificate[0];
        } catch(CertificateException e) {
            e.printStackTrace();
            certs = new Certificate[0];
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
            certs = new Certificate[0];
        } catch(IOException e) {
            e.printStackTrace();
            certs = new Certificate[0];
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
            certs = new Certificate[0];
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
        keyStorePass = listener.requestKeyStorePassword();

        FileInputStream fis;
        try {
            fis = new FileInputStream(ksLocation);
            try {
                ks.load(fis, keyStorePass.toCharArray());
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
        throws KeyStoreException, UnrecoverableEntryException,
        NoSuchAlgorithmException {

        int numOfCerts = ks.size();
        final Certificate[] certs = new Certificate[numOfCerts];

        if(numOfCerts == 0) {
            return certs;
        }

        Enumeration<String> aliases = ks.aliases();
        KeyStore.ProtectionParameter protParam =
            new KeyStore.PasswordProtection("helloo".toCharArray());

        for(int i = 0; i < numOfCerts; i++) {
            String alias = aliases.nextElement();
            X509Certificate x509Cert = (X509Certificate) ks.getCertificate(alias);
            Principal subjectField = x509Cert.getSubjectDN();

            Subject subject = parseSubjectField(subjectField);
            KeyStore.PrivateKeyEntry pkEntry =
                (KeyStore.PrivateKeyEntry) ks.getEntry(alias, protParam);
            PrivateKey pk = pkEntry.getPrivateKey();

            certs[i] = new Certificate(subject, pk);
        }

        return certs;
    }

    protected Subject parseSubjectField(Principal subjectField) {
        String[] subjectKeys = subjectField.toString().split(",");
        Subject subject = new Subject();

        for(String pair : subjectKeys) {
            String[] keyVal = pair.split("=");

            if(keyVal[0].equals("CN")) {
                subject.setCommonName(keyVal[1]);
            } else if(keyVal[0].equals("O")) {
                subject.setOrganization(keyVal[1]);
            } else if(keyVal[0].equals("ST")) {
                subject.setState(keyVal[1]);
            } else if(keyVal[0].equals("C")) {
                subject.setCountry(keyVal[1]);
            }
        }

        return subject;
    }

    public static synchronized void dispose() {
        manager = null;
    }
}
