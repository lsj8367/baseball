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
            final String requestUrl = br.readLine();

            final String url = HttpRequestUtils.splitUrlPath(requestUrl);

            Content content = new Content(0);
            Header.logHeader(br, content);

            final DataOutputStream dos = new DataOutputStream(out);

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
                Header.response302Header(dos, "/index.html");
            } else if ("/user/login".equals(url)) {
                final String body = IOUtils.readData(br, content.getLength());
                final Map<String, String> params = HttpRequestUtils.parseQueryString(body);
                final String userId = params.get("userId");
                final String password = params.get("password");
                final User user = DataBase.findUserById(userId);
                if (user == null || !password.equals(user.password())) {
                    Header.responseResources(dos, url);
                }

                Header.loginHeader(dos, "/index.html", true);
            } else {
                Header.responseResources(dos, url);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
