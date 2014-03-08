package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

@RestMethodUrl("http://www.23jugeili.com:90/jgl/index.php?m=api&a=getUserInfo")
public class GetUserInfoRequest extends RequestBase<GetUserInfoResponse> {
	@OptionalParam("userId")
    private int userId;

    public GetUserInfoRequest(int userId ) {
    	this.userId = userId;
    }
}
