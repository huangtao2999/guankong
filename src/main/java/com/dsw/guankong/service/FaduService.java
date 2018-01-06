package com.dsw.guankong.service;

import com.dsw.guankong.util.ActionResult;
import com.dsw.guankong.vo.BlContentVo;

import java.util.List;

public interface FaduService {


    public ActionResult queryBiluListByRyIdcard()  throws Exception;
    
    public BlContentVo queryBiluByBiluID(String blId,String isOuterId) throws Exception;

    public List<String> getSigntureFileContent(String blid)throws Exception;

    /**
     * 根据手环编号+在所状态获取笔录PDF 文件地址
     * @param bracelet_number
     * @return
     * @throws Exception
     */
    public List<BlContentVo> getBlContentToPdfPath(String bracelet_number) throws Exception;

    /**
     * 根据警情编号获取笔录PDF 文件地址
     * @param alertNumber
     * @return
     * @throws Exception
     */
    public List<BlContentVo> getBlContentToJpgPath(String alertNumber) throws Exception;
}
