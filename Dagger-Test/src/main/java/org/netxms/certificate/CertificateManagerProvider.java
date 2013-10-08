package org.netxms.certificate;

import dagger.Lazy;
import dagger.ObjectGraph;

import javax.inject.Inject;

public class CertificateManagerProvider {
    @Inject
    Lazy<CertificateManager> cmLazy;
    ObjectGraph og;

    public CertificateManagerProvider() {
        buildGraph();
    }

    public void buildGraph() {
        ObjectGraph og = ObjectGraph.create(CertificateManagerModule.class);
        Main main = og.get(Main.class);
    }

    public CertificateManager getCertificateManager() {
        if(cmLazy != null) {
            return cmLazy.get();
        }

        ObjectGraph og = ObjectGraph.create(CertificateManagerModule.class);
        og.get(CertificateManagerProvider.class);

        return getCertificateManager();
    }
}
