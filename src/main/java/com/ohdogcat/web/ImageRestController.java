package com.ohdogcat.web;

import com.ohdogcat.util.FtpImgLoaderUtil;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageRestController {

    private final FtpImgLoaderUtil imgUploader;

    @GetMapping("/")
    public ResponseEntity<Resource> getImage(@RequestParam String imgUrl) throws IOException {
        Resource resource = imgUploader.download(imgUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok().headers(headers).body(resource);
    }

}
