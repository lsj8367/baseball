package webserver.resolver;

import webserver.Resolver;

public class AbstractResolver implements Resolver {

    @Override
    public byte[] getFiles() {
        return new byte[0];
    }

}
