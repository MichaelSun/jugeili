package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

@RestMethodUrl("http://www.23jugeili.com:90/jgl/index.php?m=api&a=searchGoods")
public class SearchGoodsRequest extends RequestBase<SearchGoodsResponse> {
	@OptionalParam("keyword")
    private String keyword;
    
    public SearchGoodsRequest(String keyword) {
    	this.keyword = keyword;
    }
}
