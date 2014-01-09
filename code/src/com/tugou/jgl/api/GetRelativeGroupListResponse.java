package com.tugou.jgl.api;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;
import com.tugou.jgl.api.GetCityListResponse.GroupCityInfo;

public class GetRelativeGroupListResponse extends ResponseBase {

	@Override
	public String toString() {
		return "GetRelativeGroupListResponse [groupRelativeInfo="
				+ Arrays.toString(groupRelativeInfo) + "]";
	}

	public static final class GroupRelativeInfo {

		@Override
		public String toString() {
			return "GroupRelativeInfo [id=" + id + ", buy_count=" + buy_count
					+ ", market_price=" + market_price + ", shop_price="
					+ shop_price + ", brief_1=" + brief_1 + ", name_1="
					+ name_1 + "]";
		}

		public String id;
		public String buy_count;
		public String market_price;
		public String shop_price;
		public String brief_1;
		public String name_1;
		
		@JsonCreator
        public GroupRelativeInfo(
                @JsonProperty("id") String id,
                @JsonProperty("buy_count") String buy_count,
                @JsonProperty("market_price") String market_price,
                @JsonProperty("shop_price") String shop_price,
                @JsonProperty("brief_1") String brief_1,
                @JsonProperty("name_1") String name_1){
			
			this.id  = id;
			this.buy_count  = buy_count;
			this.market_price  = market_price;
			this.shop_price  = shop_price;
			this.brief_1  = brief_1;
			this.name_1  = name_1;
		}
	}

	public GroupRelativeInfo[] groupRelativeInfo ;
	
    @JsonCreator
    public GetRelativeGroupListResponse(
            @JsonProperty("result") GroupRelativeInfo[] result) {
        this.groupRelativeInfo = result;
        
    }
}
