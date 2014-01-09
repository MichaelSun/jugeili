package com.tugou.jgl.api;

import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.OptionalParam;

public class GetRelativeGroupListRequest extends RequestBase<GetRelativeGroupListResponse> {
	@OptionalParam("groupId")
    private int groupId;
    
    public GetRelativeGroupListRequest(int groupId) {
    	this.groupId = groupId;
    }
}
