package com.tugou.jgl.api;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;

public class GetListGroupResponse extends ResponseBase {
	public static final class GroupInfo {
		public String cover;
		public String name;
		public String description;
		public String price;
		public String regular_price;
		public String num_bought;
		public int is_new;
		
		@JsonCreator
        public GroupInfo(
                @JsonProperty("cover") String cover,
                @JsonProperty("name") String name, 
                @JsonProperty("description") String description,
                @JsonProperty("price") String price,
                @JsonProperty("regular_price") String regular_price,
                @JsonProperty("num_bought") String num_bought,
                @JsonProperty("num_bought") int is_new){
			this.cover  = cover;
			this.name  = name;
			this.description  = description;
			this.price  = price;
			this.regular_price  = regular_price;
			this.num_bought  = num_bought; 
			this.is_new = is_new;
		}
	}
	
	public static final class Result{
		public GroupInfo[] groupInfo ;
		
		@JsonCreator
        public Result(
                @JsonProperty("GroupInfo") GroupInfo[] groupInfo){
			this.groupInfo  = groupInfo;
		}
	}
	
	public Result result;	
	
    @JsonCreator
    public GetListGroupResponse(
            @JsonProperty("result") Result result) {
        this.result = result;
    }
}
