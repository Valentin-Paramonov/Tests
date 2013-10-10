package org.netxms.certificatemanager;

import java.security.PrivateKey;

public class Certificate {
    private final String alias;
    private final PrivateKey privateKey;

    public Certificate(String alias, PrivateKey privateKey) {
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
