package org.netxms.certificate;

import dagger.Lazy;
import dagger.ObjectGraph;

import javax.inject.Inject;

public class Main {
    @Inject
    Lazy<CertificateManager> cm;

    public void run() {
        CertificateManager certManager = cm.get();
        for(Certificate cert : certManager.listCertificates()) {
            System.out.printf(
                "Alias: %s, PK: %s\n", cert.getAlias(),
                cert.getPrivateKey());
        }
    }

    public static void main(String[] args) {
        ObjectGraph og = ObjectGraph.create(
            CertificateManagerModule.class);
        Main main = og.get(Main.class);
        main.run();
    }
}
