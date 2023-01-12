package webserver;

import controller.UserController;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import webserver.resolver.RestApiResolver;

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

            final String httpMethod = HttpRequestUtils.getHttpMethod(requestUrl);

            final String url = HttpRequestUtils.splitUrlPath(requestUrl);

            doLogRequestBody(br);

            DataOutputStream dos = new DataOutputStream(out);

            final ResolverFactory resolverFactory = new ResolverFactory(url);
            final Resolver resolver = resolverFactory.resolver();

            if (resolver instanceof RestApiResolver restApiResolver) {
                //TODO request url HTTP method 와 url 분석
                UserController userController = new UserController();
                //해당 url을 처리할 수 있는 컨트롤러를 탐색함.
                userController.signUp(restApiResolver.getRequestPath(), restApiResolver.getParams());
                response200Header(dos, 0);
                responseBody(dos, new byte[]{});
            } else {
                byte[] body = resolver.getFiles();
                response200Header(dos, body.length);
                responseBody(dos, body);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private static void doLogRequestBody(final BufferedReader br) throws IOException {
        String bodyData;
        StringBuilder sb = new StringBuilder();
        while((bodyData = br.readLine()) != null) {
            sb.append(bodyData).append("\n");

            if (bodyData.isBlank()) {
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
