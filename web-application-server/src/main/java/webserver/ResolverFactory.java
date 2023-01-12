package webserver;

import webserver.resolver.DefaultResolver;
import webserver.resolver.RestApiResolver;
import webserver.resolver.ViewResolver;

public record ResolverFactory(String urlPath) {

    public ResolverFactory {
        if (urlPath.isEmpty()) {
            throw new IllegalArgumentException("urlPath는 필수값입니다.");
        }
    }

    public Resolver resolver() {
        if ("/".equals(urlPath)) {
            return new DefaultResolver();
        }

        if (".html".equals(urlPath.substring(urlPath.length() - 5))) {
            return new ViewResolver(urlPath);
        }

        return new RestApiResolver(urlPath);
    }

}
