package com.ohdogcat.util;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FtpUploaderUtil {
    String upload(MultipartFile file, String servletPath) throws IOException;
    Resource download(String imgUrl) throws IOException;
    boolean delete(String imgUrl) throws IOException;
}
