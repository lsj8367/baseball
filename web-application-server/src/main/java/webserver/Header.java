package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Header {

    private static final Logger log = LoggerFactory.getLogger(Header.class);

    public Header() {
        throw new IllegalStateException("생성할 수 없는 유틸클래스 입니다.");
    }

    public static void responseResources(final DataOutputStream dos, final String url) throws IOException {
        byte[] body = Files.readAllBytes(new File("./web-application-server/webapp" + url).toPath());
        response200Header(dos, body.length);
        responseBody(dos, body);
    }

    public static void logHeader(final BufferedReader br, final Content content) throws IOException {
        String header;
        StringBuilder sb = new StringBuilder();
        while((header = br.readLine()) != null) {
            if (header.contains("Content-Length")) {
                final String[] split = header.split(":");
                content.setLength(Integer.parseInt(split[1].trim()));
            }
            sb.append(header).append("\n");

            if (header.isBlank()) {
                break;
            }
        }
        log.info(sb.toString());
    }

    public static void response302Header(final DataOutputStream dos, final String url) {
        try {
            dos.writeBytes("HTTP/1.1 302 Redirect \r\n");
            dos.writeBytes("Location: " + url + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void loginHeader(DataOutputStream dos, final String url, final boolean isLogin) {
        try {
            dos.writeBytes("HTTP/1.1 302 Redirect \r\n");
            dos.writeBytes("Set-Cookie: logined=" + isLogin + "\r\n");
            dos.writeBytes("Location : " + url + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
