package org.netxms.certificate;

import java.security.KeyStore;
import java.util.List;

abstract class CertificateManager {
    public abstract List<Certificate> listCertificates();
}
