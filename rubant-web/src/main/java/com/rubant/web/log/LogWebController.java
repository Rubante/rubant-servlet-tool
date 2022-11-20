package com.rubant.web.log;

import com.rubant.io.file.FileResult;
import com.rubant.io.file.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 前后端一体接口
 *
 * @author rubant
 * @date 2022/11/13 17:46
 */
@Controller
@RequestMapping("/web")
public class LogWebController extends LogBaseController {

    @RequestMapping("/search")
    public String search(ModelMap mmap) {
        List<FileResult> dirs = FileUtils.getDictionarys(getBaseLogDir());
        mmap.put("dirs", dirs);

        return "log/search";
    }
}
