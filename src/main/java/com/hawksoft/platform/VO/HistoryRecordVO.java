package com.hawksoft.platform.VO;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecordVO {
    private List<PdfVO> pdfVOList = new ArrayList<>();
    private List<String> picList = new ArrayList<>();

    public HistoryRecordVO() {
    }

    public List<PdfVO> getPdfVOList() {
        return pdfVOList;
    }

    public void setPdfVOList(List<PdfVO> pdfVOList) {
        this.pdfVOList = pdfVOList;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }
}
