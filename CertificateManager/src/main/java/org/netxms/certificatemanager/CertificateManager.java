package org.netxms.certificatemanager;

public class CertificateManager {
    private final Certificate[] certs;

    CertificateManager(Certificate[] certs) {
        this.certs = certs;
    }

    public Certificate[] getCerts() {
        return certs;
    }

    public boolean hasNoCertificates() {
        return certs.length == 0;
    }
}
