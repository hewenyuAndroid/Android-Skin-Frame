package com.hwy.skin.config;

/**
 * 作者: hewenyu
 * 日期: 2019/2/18 15:20
 * 说明:
 */
public class SkinConfig {

    /**
     * sp 文件名称
     */
    public static final String SKIN_INFO_NAME = "skin_info";

    /**
     * sp 缓存皮肤名称的 key
     */
    public static final String SKIN_PATH_NAME = "path_name";

    /**
     * 当前就是需要修改的皮肤，不需要修改
     */
    public static final int SKIN_EXCHANGE_NOT_EXCHANGE = -1;

    /**
     * 皮肤文件不存在
     */
    public static final int SKIN_EXCHANGE_FILE_NOT_EXISTS = -2;

    /**
     * 皮肤文件损坏
     */
    public static final int SKIN_EXCHANGE_FILE_DAMAGE = -3;

    /**
     * 皮肤文件的签名错误
     */
    public static final int SKIN_EXCHANGE_SIGNATURE_ERROR = -4;

    /**
     * 皮肤文件完整有效
     */
    public static final int SKIN_EXCHANGE_FILE_VALID = 0;

    /**
     * 换肤成功
     */
    public static final int SKIN_EXCHANGE_SUCCESS = 1;

}
