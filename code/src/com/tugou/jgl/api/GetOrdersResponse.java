package com.tugou.jgl.api;

import java.util.Arrays;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;
import com.tugou.jgl.api.getCommentsResponse.CommentsInfo;
import com.tugou.jgl.api.getCommentsResponse.Result;

public class GetOrdersResponse extends ResponseBase {
	
	public static final class List {
		public String name_1;
		public String goods_name;
		public String small_img;
		public int order_total_price;
		public int number;
		public String money_status_format;
		
		@JsonCreator
        public List(
                @JsonProperty("name_1") String name_1,
                @JsonProperty("goods_name") String goods_name, 
                @JsonProperty("small_img") String small_img,
                @JsonProperty("order_total_price") int order_total_price,
                @JsonProperty("number") int number,
                @JsonProperty("money_status_format") String money_status_format){
			
			this.name_1  = name_1;
			this.goods_name  = goods_name;
			this.small_img  = small_img;
			this.order_total_price  = order_total_price;
			this.number  = number;
			this.money_status_format  = money_status_format;
		}
	}
	
	public static final class Result {

		public List[] list;
		public int total;
		
		@JsonCreator
        public Result(
                @JsonProperty("list") List[] list,
                @JsonProperty("total") int total){
			
			this.list  = list;
			this.total  = total;
		}
	}

	public Result result;
	public String status;
	public String message;
	
    @JsonCreator
    public GetOrdersResponse(
            @JsonProperty("result") Result result,
            @JsonProperty("status") String status,
            @JsonProperty("message") String message) {
        this.result = result;
        this.status = status;
        this.message = message;
    }  
}
