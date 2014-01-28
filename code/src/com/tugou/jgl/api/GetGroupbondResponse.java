package com.tugou.jgl.api;

import java.util.Arrays;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;
import com.tugou.jgl.api.GetOrdersResponse.List;
import com.tugou.jgl.api.GetOrdersResponse.Result;

public class GetGroupbondResponse extends ResponseBase {
	@Override
	public String toString() {
		return "GetGroupbondResponse [result=" + result + ", status=" + status
				+ ", message=" + message + "]";
	}

	public static final class List {
		@Override
		public String toString() {
			return "List [name_1=" + name_1 + ", goods_name=" + goods_name
					+ ", small_img=" + small_img + ", order_total_price="
					+ order_total_price + ", number=" + number + ", status="
					+ status + ", sn=" + sn + ", password=" + password
					+ ", create_time_format=" + create_time_format
					+ ", end_time_format=" + end_time_format + "]";
		}

		public String name_1;
		public String goods_name;
		public String small_img;
		public int order_total_price;
		public int number;
		public int status;
		public String sn;
		public String password;
		public String create_time_format;
		public String end_time_format;
		
		@JsonCreator
        public List(
                @JsonProperty("name_1") String name_1,
                @JsonProperty("goods_name") String goods_name, 
                @JsonProperty("small_img") String small_img,
                @JsonProperty("order_total_price") int order_total_price,
                @JsonProperty("number") int number,
                @JsonProperty("status") int status,
                @JsonProperty("sn") String sn,
                @JsonProperty("password") String password,
                @JsonProperty("create_time_format") String create_time_format,
                @JsonProperty("end_time_format") String end_time_format){
			
			this.name_1 = name_1;
			this.goods_name = goods_name;
			this.small_img = small_img;
			this.order_total_price = order_total_price;
			this.number = number;
			this.status = status;
			this.sn = sn;
			this.password = password;
			this.create_time_format = create_time_format;
			this.end_time_format = end_time_format;
		}
	}
	
	public static final class Result {

		@Override
		public String toString() {
			return "Result [list=" + Arrays.toString(list) + ", total=" + total
					+ "]";
		}

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
    public GetGroupbondResponse(
            @JsonProperty("result") Result result,
            @JsonProperty("status") String status,
            @JsonProperty("message") String message) {
        this.result = result;
        this.status = status;
        this.message = message;
    }  
}
