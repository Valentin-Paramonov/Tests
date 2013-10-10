package org.netxms.certificatemanager;

public interface CertificateManagerProviderListener {
    String requestKeyStorePassword();
    String requestKeyStoreLocation();
}
