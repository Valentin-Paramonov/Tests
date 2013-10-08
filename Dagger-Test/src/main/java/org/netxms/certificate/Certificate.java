package org.netxms.certificate;

import java.security.PrivateKey;

public class Certificate {
    private String alias;
    private PrivateKey privateKey;

    Certificate(String alias, PrivateKey privateKey) {
        this.alias = alias;
        this.privateKey = privateKey;
    }

    public String getAlias() {
        return alias;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
