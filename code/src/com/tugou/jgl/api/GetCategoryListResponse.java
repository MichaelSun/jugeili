package com.tugou.jgl.api;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;
import com.tugou.jgl.api.GetListGroupResponse.GroupInfo;

public class GetCategoryListResponse extends ResponseBase {
	
	public static final class SubList {
		public String sub_id;
		public String name;
		
		@JsonCreator
        public SubList(
                @JsonProperty("id") String id,
                @JsonProperty("name") String name){
			this.sub_id  = id;
			this.name  = name;
		}
	}
	
	public static final class GroupCategoryInfo {
		public String category_id;
		public String category_name;
		public String is_new;
		public String is_hot;
		public SubList[] subList;
		
		@JsonCreator
        public GroupCategoryInfo(
                @JsonProperty("id") String id,
                @JsonProperty("name") String name, 
                @JsonProperty("is_new") String is_new,
                @JsonProperty("is_hot") String is_hot,
                @JsonProperty("sub_list") SubList[] sub_list){
			
			this.category_id  = id;
			this.category_name  = name;
			this.is_new  = is_new;
			this.is_hot  = is_hot;
			this.subList  = sub_list;
		}
	}

	public GroupCategoryInfo[] groupCategoryInfo ;
	
    @JsonCreator
    public GetCategoryListResponse(
            @JsonProperty("result") GroupCategoryInfo[] result) {
        this.groupCategoryInfo = result;
    }
}
