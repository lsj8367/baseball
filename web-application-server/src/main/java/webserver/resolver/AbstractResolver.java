package webserver.resolver;

import java.util.Map;
import webserver.Resolver;

public abstract class AbstractResolver implements Resolver {

    @Override
    public byte[] getFiles() {
        return new byte[0];
    }

    abstract Map<String, String> getParams();

}
