package org.netxms.certificatemanager;

import java.util.List;

public class CertificateManager {
    private final List<Certificate> certs;

    protected CertificateManager(List<Certificate> certs) {
        this.certs = certs;
    }

    public boolean hasCertificates() {
        return (!certs.isEmpty());
    }
}
