package com.isleqi.graduationproject.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {
    /**
     * 保存文件，直接以multipartFile形式
     *
     * @param multipartFile
     * @return 返回文件名
     * @throws IOException
     */
    public static String saveImg(MultipartFile multipartFile, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName =System.currentTimeMillis() + multipartFile.getOriginalFilename();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }
}
