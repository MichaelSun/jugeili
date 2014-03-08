package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

@RestMethodUrl("http://www.23jugeili.com:90/jgl/index.php?m=api&a=getComments")
public class getCommentsRequest extends RequestBase<getCommentsResponse> {
	@OptionalParam("groupId")
    private int groupId;
    
    @OptionalParam("page")
    private int page;

    public getCommentsRequest(int groupId, int page) {
    	this.groupId = groupId;
    	this.page = page;
    }
}
