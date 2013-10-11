package org.netxms.certificatemanager.loader;

import org.netxms.certificatemanager.Certificate;

public interface KeyStoreLoader {
    Certificate[] retrieveCertificates();
}
