package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RequiredParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

@RestMethodUrl("http://125.76.249.69:90/jgl/index.php?m=api&a=categoryList")
public class CategoryListRequest extends RequestBase<GetCategoryResponse> {
    
	@OptionalParam("cate_id")
    private int cate_id;
    
    @OptionalParam("city")
    private int city;

    public CategoryListRequest(int cate_id, int city) {
    	this.cate_id = cate_id ;
    	this.city = city;
    }
}
