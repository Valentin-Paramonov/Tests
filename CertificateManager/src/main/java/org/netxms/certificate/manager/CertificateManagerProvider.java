package org.netxms.certificate.manager;

import org.netxms.certificate.Certificate;
import org.netxms.certificate.loader.KeyStoreLoader;
import org.netxms.certificate.loader.MSCKeyStoreLoader;
import org.netxms.certificate.loader.PKCS12KeyStoreLoader;

public class CertificateManagerProvider {
    private static volatile CertificateManager manager;
    private final CertificateManagerProviderRequestListener listener;

    private CertificateManagerProvider(
        CertificateManagerProviderRequestListener listener) {

        this.listener = listener;
    }

    public static synchronized CertificateManager provideCertificateManager(
        CertificateManagerProviderRequestListener listener) {

        if(manager != null) return manager;
        manager = new CertificateManagerProvider(listener).createCertificateManager();
        return manager;
    }

    protected CertificateManager createCertificateManager() {
        final String os = System.getProperty("os.name");
        final KeyStoreLoader loader;
        Certificate[] certs;

        if(os.startsWith("Windows")) {
            loader = new MSCKeyStoreLoader();
        } else {
            loader = new PKCS12KeyStoreLoader(listener);
        }

        certs = loader.retrieveCertificates();

        return new CertificateManager(certs);
    }

    public static synchronized void dispose() {
        manager = null;
    }
}
