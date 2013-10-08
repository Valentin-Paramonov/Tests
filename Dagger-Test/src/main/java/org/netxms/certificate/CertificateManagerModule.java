package org.netxms.certificate;

import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module(injects = Main.class)
class CertificateManagerModule {
    @Provides @Singleton
    CertificateManager provideCertificateManager() {
        return new WindowsCertificateManager();
    }
}
