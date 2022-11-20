package com.rubant.agent.log;

/**
 * log动作类型
 *
 * @author rubant
 * @date 2022/11/13 16:10
 */
public enum LogActionTypeEnum {
    /**
     * 查看目录，tail文件，搜索文件
     */
    DIR, TAIL, GREP;
}
