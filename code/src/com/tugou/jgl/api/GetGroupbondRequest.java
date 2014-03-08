package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

@RestMethodUrl("http://www.23jugeili.com:90/jgl/index.php?m=api&a=getGroupbond")
public class GetGroupbondRequest extends RequestBase<GetGroupbondResponse> {
	@OptionalParam("status")
    private int status;
	@OptionalParam("page")
    private int page;

    public GetGroupbondRequest(int status, int page ) {
    	this.status = status;
    	this.page = page;
    }
}
