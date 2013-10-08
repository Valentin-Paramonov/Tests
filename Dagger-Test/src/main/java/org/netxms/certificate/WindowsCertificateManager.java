package org.netxms.certificate;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class WindowsCertificateManager extends CertificateManager {
    public List<Certificate> listCertificates() {
        List<Certificate> certs = new LinkedList<>();

        certs.add(new Certificate("John", null));

        return certs;
    }
}
