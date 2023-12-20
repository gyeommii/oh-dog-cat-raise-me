package com.ohdogcat.util;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class FtpImgLoaderUtil {

    private String host = "192.168.20.11";
    private Integer port = 21;
    private String user = "admin";
    private String password = "123123";

    private FTPClient ftpClient;

    public FtpImgLoaderUtil() {
        ftpClient = new FTPClient();
        try {
            boolean hasConnected = connect();
            if (hasConnected) {
                log.debug("::FTP 서버 연결 완료");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


    public String upload(MultipartFile file, HttpServletRequest req) throws IOException {

        String result = null;

        List<String> filePath = mkDirByRequestUri(req);
        InputStream inputStream = file.getInputStream();

        setFtpClientConfig();

        String fileName =
            UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];

        boolean uploadResult = ftpClient.storeFile(fileName, inputStream);

        if (uploadResult) {
            filePath.add(fileName);
            log.debug("파일 업로드 완료");
            result = String.join("/", filePath);
        }

        return result;
    }

    public Resource download(String imgUrl) throws IOException {
        setFtpClientConfig();

        InputStream imgStream = ftpClient.retrieveFileStream(imgUrl);
        byte[] result = IOUtils.toByteArray(imgStream);

        if (result.length == 0) {
            log.error("::DB에 데이터 조회 실패");
            return null;
        }

        Resource resource = new ByteArrayResource(result);
        imgStream.close();
        disconnect();
        return resource;
    }

    public boolean connect() throws IOException {
        log.debug("connecting to... {}", host);

        ftpClient.addProtocolCommandListener(
            new PrintCommandListener(new PrintWriter(System.out), true));

        ftpClient.connect(host, port);
        int reply = ftpClient.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            disconnect();
            throw new RuntimeException("FTP_ERROR_IS_NOT_GOOD");
        }

        return ftpClient.login(user, password);
    }

    public void disconnect() throws IOException {
        log.debug("disconnecting from {}", host);

        ftpClient.logout();
        ftpClient.disconnect();
    }

    public void setFtpClientConfig() throws IOException {
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
        ftpClient.setAutodetectUTF8(true);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    public List<String> mkDirByRequestUri(HttpServletRequest req)
        throws RuntimeException, IOException {
        List<String> result = new ArrayList<>();

        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();
        String uriPath = uri.replace(contextPath, "");

        List<String> paths = Arrays.stream(uriPath.split("/")).toList();

        log.debug("paths={}", paths);
        for (String path : paths) {
            if (path.isEmpty()) {
                continue;
            }
            result.add(path);

            if (!ftpClient.changeWorkingDirectory(path)) {
                ftpClient.makeDirectory(path);
                ftpClient.changeWorkingDirectory(path);
            }
        }

        return result;
    }
}
