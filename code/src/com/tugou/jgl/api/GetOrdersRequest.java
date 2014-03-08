package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

@RestMethodUrl("http://www.23jugeili.com:90/jgl/index.php?m=api&a=getOrders")
public class GetOrdersRequest extends RequestBase<GetOrdersResponse> {
	@OptionalParam("status")
    private int status;
	@OptionalParam("page")
    private int page;

    public GetOrdersRequest(int status, int page ) {
    	this.status = status;
    	this.page = page;
    }
}
