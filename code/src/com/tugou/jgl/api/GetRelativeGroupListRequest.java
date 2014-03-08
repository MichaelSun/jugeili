package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;
import com.plugin.internet.core.annotations.RestMethodUrl;

@RestMethodUrl("http://www.23jugeili.com:90/jgl/index.php?m=api&a=getRelativeGroupList")
public class GetRelativeGroupListRequest extends RequestBase<GetRelativeGroupListResponse> {
	@OptionalParam("groupId")
    private int groupId;
    
    public GetRelativeGroupListRequest(int groupId) {
    	this.groupId = groupId;
    }
}
