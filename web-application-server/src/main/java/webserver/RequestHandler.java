package webserver;

import db.DataBase;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Map;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import util.IOUtils;

public class RequestHandler implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private final Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    @Override
    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (
            InputStream in = connection.getInputStream();
            OutputStream out = connection.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in))
        ) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            final String requestUrl = br.readLine();

            final String url = HttpRequestUtils.splitUrlPath(requestUrl);

            Content content = new Content(0);
            logHeader(br, content);

            //TODO request url HTTP method 와 url 분석
            if ("/user/create".equals(url)) {
                final String s = IOUtils.readData(br, content.getLength());
                final Map<String, String> params = HttpRequestUtils.parseQueryString(s);
                DataBase.addUser(new User(
                    params.get("userId"),
                    params.get("password"),
                    params.get("name"),
                    params.get("email"))
                );

                //해당 url을 처리할 수 있는 컨트롤러를 탐색함.
                final User savedUser = DataBase.findUserById(params.get("userId"));
                log.info("저장 완료 {}", savedUser);
            } else {
                final DataOutputStream dos = new DataOutputStream(out);
                byte[] body = Files.readAllBytes(new File("./web-application-server/webapp" + url).toPath());
                response200Header(dos, body.length);
                responseBody(dos, body);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private static void logHeader(final BufferedReader br, final Content content) throws IOException {
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

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
