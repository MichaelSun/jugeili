package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

@RestMethodUrl("http://www.23jugeili.com:90/jgl/index.php?m=api&a=getRecommends")
public class GetRecommendsRequest extends RequestBase<GetRecommendsResponse> {
	@OptionalParam("num")
    private int num;

    public GetRecommendsRequest(int num) {
    	this.num = num;
    }
}
