package org.netxms.certificatemanager;

public class TestListener implements CertificateManagerProviderListener {
    @Override
    public String requestKeyStorePassword() {
        return "helloo";
    }

    @Override
    public String requestKeyStoreLocation() {
        return "src/test/resource/keystore.p12";
    }
}
