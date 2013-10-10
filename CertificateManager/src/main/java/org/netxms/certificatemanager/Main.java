package org.netxms.certificatemanager;

public class Main {
    public static void main(String[] args) {
        CertificateManager mgr = CertificateManagerProvider.provideCertificateManager(new CertificateManagerProviderListener() {
            @Override
            public String requestKeyStorePassword() {
                return null;
            }

            @Override
            public String requestKeyStoreLocation() {
                return null;
            }
        });

        System.out.println(mgr.getCerts()[0].getCommonName());
    }
}
