package org.netxms.certificatemanager;

import org.netxms.certificatemanager.loader.KeyStoreLoader;
import org.netxms.certificatemanager.loader.MSCKeyStoreLoader;
import org.netxms.certificatemanager.loader.PKCS12KeyStoreLoader;

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
