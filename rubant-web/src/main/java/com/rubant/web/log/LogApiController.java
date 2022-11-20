package com.rubant.web.log;

import com.rubant.io.file.FileResult;
import com.rubant.io.file.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志相关服务
 *
 * @author rubant
 * @date 2022/11/13 16:56
 */
@Controller
@RequestMapping("/api")
public class LogApiController extends LogBaseController {

    /**
     * 根目录
     *
     * @return
     */
    @RequestMapping("/baseDirs")
    @ResponseBody
    public List<FileResult> getDirs() {
        return FileUtils.getDictionarys(getBaseLogDir());
    }

    @RequestMapping("/dirs")
    @ResponseBody
    public List<FileResult> getDirs(@RequestParam("path") String path) {
        if (path == null) {
            return new ArrayList<>();
        }

        path = path.replaceAll("[/\\\\]", "");

        return FileUtils.getDictionarys(getBaseLogDir() + "/" + path);
    }

    @RequestMapping("/files")
    @ResponseBody
    public List<FileResult> getFiles(@RequestParam("path") String path, @RequestParam("subPath") String subPath) {
        if (path == null) {
            return new ArrayList<>();
        }
        path = path.replaceAll("[/\\\\]", "");
        subPath = subPath.replaceAll("[/\\\\]", "");

        return FileUtils.getAllFiles(getBaseLogDir() + "/" + path + "/" + subPath);
    }
}
