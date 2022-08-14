package com.d209.mungtopia.controller;

import com.d209.mungtopia.service.BoardService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/image")
@Api(value = "이미지 API", tags = "Image Controller - 이미지 API")
@RequiredArgsConstructor
public class ImageController {

    private final BoardService boardService;

    @GetMapping("/{boardId}/{order}")
    @ResponseBody
    public ResponseEntity<byte[]> getImgFile(@PathVariable long boardId, @PathVariable int order, HttpServletRequest request) throws IOException {
        Resource resource = boardService.getImgFile(boardId, order);
        // Try to determine file's content type
        System.out.println("resource = " + resource.getURL());
        System.out.println("resource.getURI() = " + resource.getURI());
        System.out.println("resource.getFilename() = " + resource.getFilename());

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        InputStream in = resource.getInputStream();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(IOUtils.toByteArray(in));
    }
}
