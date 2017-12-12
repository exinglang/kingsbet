package com.kingsbet.wzry.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.kingsbet.wzry.entity.RequestJsonRoot;
import org.apache.log4j.Logger;

public class BaseController {
    protected Logger logger = Logger.getLogger(getClass());

    protected class MJsonParse {
        RequestJsonRoot jsonRoot;

        MJsonParse(RequestJsonRoot jsonRoot) {
            this.jsonRoot = jsonRoot;

        }

        protected String getString(String tag) {
            Gson gson = new Gson();
            LinkedTreeMap map = (LinkedTreeMap) jsonRoot.getReqsbody();
            JsonObject gsin = gson.toJsonTree(map).getAsJsonObject();
            return gsin.get(tag).getAsString();

        }
        protected int getInt(String tag) {
            Gson gson = new Gson();
            LinkedTreeMap map = (LinkedTreeMap) jsonRoot.getReqsbody();
            JsonObject gsin = gson.toJsonTree(map).getAsJsonObject();
            return gsin.get(tag).getAsInt();

        }
    }
}
