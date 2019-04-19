package com.kaya.risk31app.Service;

import com.kaya.risk31app.Models.Risk;

import java.util.List;

public class RiskResponse {
    List<Risk> data;

    public List<Risk> getData() {
        return data;
    }

    public void setData(List<Risk> data) {
        this.data = data;
    }
}
