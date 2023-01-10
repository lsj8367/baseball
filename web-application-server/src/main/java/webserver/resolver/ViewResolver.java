package webserver.resolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import webserver.Resolver;

public record ViewResolver(String redirectUrl) implements Resolver {

    @Override
    public byte[] getFiles() {
        try {
            return Files.readAllBytes(new File("./web-application-server/webapp" + redirectUrl).toPath());
        } catch (IOException e) {
            throw new IllegalStateException("파일을 불러올 수 없습니다.");
        }
    }

}
