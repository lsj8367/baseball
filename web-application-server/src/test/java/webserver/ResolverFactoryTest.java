package webserver;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import webserver.resolver.DefaultResolver;
import webserver.resolver.ViewResolver;

class ResolverFactoryTest {

    private ResolverFactory factory;

    @Test
    void defaultResolverTest() {
        factory = new ResolverFactory("/");
        final Resolver resolver = factory.resolver();
        assertThat(resolver).isInstanceOf(DefaultResolver.class);
    }

    @Test
    void viewResolverTest() {
        factory = new ResolverFactory("/index.html");
        final Resolver resolver = factory.resolver();
        assertThat(resolver).isInstanceOf(ViewResolver.class);
    }

}
