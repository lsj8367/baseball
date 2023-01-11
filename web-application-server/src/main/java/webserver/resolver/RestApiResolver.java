package webserver.resolver;

import java.util.Map;
import util.HttpRequestUtils;

public class RestApiResolver extends AbstractResolver {

    private final String requestPath;
    private final Map<String, String> params;

    public RestApiResolver(final String url) {
        final int index = url.indexOf("?");
        final String paramString = url.substring(index + 1);

        this.requestPath = url.substring(0, index);
        this.params = HttpRequestUtils.parseQueryString(paramString);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
