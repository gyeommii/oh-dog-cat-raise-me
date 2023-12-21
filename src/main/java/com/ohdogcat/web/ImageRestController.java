package com.ohdogcat.web;

import com.ohdogcat.util.FtpImgLoaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageRestController {

    private final FtpImgLoaderUtil imgUploader;

    @GetMapping("")
    public ResponseEntity<Resource> getImage(@RequestParam String imgUrl, HttpServletRequest req)
        throws IOException {
        log.debug("getImage(imgUrl={}", imgUrl);

        Resource resource = imgUploader.download(imgUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok().headers(headers).body(resource);
    }

}
