package org.netxms.certificatemanager;

public class TestListener implements CertificateManagerProviderRequestListener {
    @Override
    public String keyStorePasswordRequested() {
        return "helloo";
    }

    @Override
    public String keyStoreLocationRequested() {
        return "src/test/resource/keystore.p12";
    }
}
