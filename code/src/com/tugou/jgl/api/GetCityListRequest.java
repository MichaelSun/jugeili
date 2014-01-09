package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

@RestMethodUrl("http://www.23jugeili.com:90/jgl/index.php?m=api&a=cityList")
public class GetCityListRequest extends RequestBase<GetCityListResponse> {
	@OptionalParam("city")
    private int city;
	
    public GetCityListRequest() {
    }
    
    public GetCityListRequest(int city) {
    	this.city = city;
    }
}
