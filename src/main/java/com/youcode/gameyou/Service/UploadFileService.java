package com.youcode.gameyou.Service;

import com.youcode.gameyou.Provider.UploadFileProvider.CloudinaryProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class UploadFileService {

    private final CloudinaryProvider cloudinaryProvider;

    public String getOnePath(MultipartFile file) throws RuntimeException {
        return cloudinaryProvider.uploadFile(file);
    }

    public String[] getPaths(MultipartFile[] files) throws RuntimeException {
        String[] paths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            paths[i] = cloudinaryProvider.uploadFile(files[i]);
        }
        return paths;
    }
}