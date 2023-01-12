package webserver.resolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import webserver.Resolver;

public record DefaultResolver() implements Resolver {

    public static final String INDEX_HTML = "/index.html";

    @Override
    public byte[] getFiles() {
        try {
            return Files.readAllBytes(new File("./web-application-server/webapp" + INDEX_HTML).toPath());
        } catch (IOException e) {
            throw new IllegalStateException("파일을 불러올 수 없습니다.");
        }
    }

}
