package org.netxms.certificate.manager;

import org.netxms.certificate.request.KeyStoreLocationRequestListener;
import org.netxms.certificate.request.PasswordRequestListener;

public interface CertificateManagerProviderRequestListener
extends KeyStoreLocationRequestListener, PasswordRequestListener {}
