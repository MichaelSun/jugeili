package com.tugou.jgl.utils;

import com.tugou.jgl.api.GetListGroupResponse;

/**
 * Created by michael on 14-3-9.
 */
public class AppRuntime {

    public static GetListGroupResponse.GroupInfo[] groupInfo;

    public static void makeCrash() {
        //make crash
        String crash = null;
        crash.toString();
    }

}
