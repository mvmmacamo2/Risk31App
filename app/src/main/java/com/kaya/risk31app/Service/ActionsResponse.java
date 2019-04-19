package com.kaya.risk31app.Service;

import com.kaya.risk31app.Models.Actions;

import java.util.List;

public class ActionsResponse {
    List<Actions> data;

    public List<Actions> getData() {
        return data;
    }

    public void setData(List<Actions> data) {
        this.data = data;
    }
}
