package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

@RestMethodUrl("http://www.23jugeili.com:90/jgl/index.php?m=api&a=getGoodDetail")
public class GetGoodDetailRequest extends RequestBase<GetGoodDetailResponse> {
	@OptionalParam("id")
    private int id;
    
    public GetGoodDetailRequest(int id) {
    	this.id = id;
    }
}
