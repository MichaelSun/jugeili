package com.tugou.jgl.api;

import java.util.Arrays;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;
import com.tugou.jgl.api.GetAreaListResponse.GroupAreaInfo;
import com.tugou.jgl.api.GetAreaListResponse.SubList;

public class GetCityListResponse extends ResponseBase {
	@Override
	public String toString() {
		return "GetCityListResponse [groupCityInfo="
				+ Arrays.toString(groupCityInfo) + "]";
	}

	public static final class GroupCityInfo {
		@Override
		public String toString() {
			return "GroupCityInfo [city_id=" + city_id + ", city_name="
					+ city_name + "]";
		}

		public String city_id;
		public String city_name;
		
		@JsonCreator
        public GroupCityInfo(
                @JsonProperty("id") String id,
                @JsonProperty("name") String name){
			
			this.city_id  = id;
			this.city_name  = name;
		}
	}

	public GroupCityInfo[] groupCityInfo ;
	
    @JsonCreator
    public GetCityListResponse(
            @JsonProperty("result") GroupCityInfo[] result) {
        this.groupCityInfo = result;
    }  
}
