package com.tugou.jgl.api;

import java.util.Arrays;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;
import com.tugou.jgl.api.GetCityListResponse.GroupCityInfo;

public class SearchGoodsResponse extends ResponseBase {

	@Override
	public String toString() {
		return "SearchResponse [result=" + result + "]";
	}

	public static final class ListInfo {

		@Override
		public String toString() {
			return "ListInfo [id=" + id + ", cover=" + cover + ", name=" + name
					+ ", description=" + description + ", price=" + price
					+ ", regular_price=" + regular_price + ", num_bought="
					+ num_bought + ", is_new=" + is_new + ", is_no_order="
					+ is_no_order + ", is_holiday_can_use="
					+ is_holiday_can_use + "]";
		}

		public String id;
		public String cover;
		public String name;
		public String description;
		public String price;
		public String regular_price;
		public String num_bought;
		public String is_new;
		public String is_no_order;
		public String is_holiday_can_use;
		
		@JsonCreator
        public ListInfo(
                @JsonProperty("id") String id,
                @JsonProperty("cover") String cover,
                @JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("price") String price,
                @JsonProperty("regular_price") String regular_price,
                @JsonProperty("num_bought") String num_bought,
                @JsonProperty("is_new") String is_new,
                @JsonProperty("is_no_order") String is_no_order,
                @JsonProperty("is_holiday_can_use") String is_holiday_can_use){
			
			this.id = id;
			this.cover = cover;
			this.name = name;
			this.description = description;
			this.price = price;
			this.regular_price = regular_price;
			this.num_bought = num_bought;
			this.is_new = is_new;
			this.is_no_order = is_no_order;
			this.is_holiday_can_use = is_holiday_can_use;
		}
	}

	//public ListInfo[] list ;
	
	public static final class Result {

		@Override
		public String toString() {
			return "Result [list=" + Arrays.toString(list) + "]";
		}

		public ListInfo[] list ;
		
		@JsonCreator
        public Result(
                @JsonProperty("list") ListInfo[] list){
			
			this.list = list;
		}
	}
	
	public Result result;

    @JsonCreator
    public SearchGoodsResponse(
            @JsonProperty("result") Result result) {
        this.result = result;
    } 
}
