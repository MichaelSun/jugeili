package com.tugou.jgl.api;

import java.util.Arrays;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;
import com.tugou.jgl.api.GetCityListResponse.GroupCityInfo;

public class GetGoodDetailResponse extends ResponseBase {

	@Override
	public String toString() {
		return "GetGoodDetailResponse [result=" + result + "]";
	}

	public static final class Result {

		@Override
		public String toString() {
			return "Result [id=" + id + ", name=" + name + ", description="
					+ description + ", cover=" + cover + ", price=" + price
					+ ", regular_price=" + regular_price + ", num_bought="
					+ num_bought + ", is_new=" + is_new + ", is_no_order="
					+ is_no_order + ", is_holiday_can_use="
					+ is_holiday_can_use + ", merchant_id=" + merchant_id
					+ ", purchase_notes=" + purchase_notes + ", is_best="
					+ is_best + ", promote_end_time=" + promote_end_time
					+ ", merchant_name=" + merchant_name + ", merchant_addr="
					+ merchant_addr + ", merchant_phone=" + merchant_phone
					+ ", merchant_num=" + merchant_num + ", grade=" + grade
					+ ", grade_num=" + grade_num + ", left_time=" + left_time
					+ "]";
		}

		public String id;
		public String name;
		public String description;
		public String cover;
		public String price;
		public String regular_price;
		public String num_bought;
		public String is_new;
		public String is_no_order;
		public String is_holiday_can_use;
		public String merchant_id;
		public String purchase_notes;
		public String is_best;
		public String promote_end_time;
		public String merchant_name;
		public String merchant_addr;
		public String merchant_phone;
		public String merchant_num;
		public String grade;
		public String grade_num;
		public String left_time;
		
		@JsonCreator
        public Result(
                @JsonProperty("id") String id,
                @JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("cover") String cover,
                @JsonProperty("price") String price,
                @JsonProperty("regular_price") String regular_price,
                @JsonProperty("num_bought") String num_bought,
                @JsonProperty("is_new") String is_new,
                @JsonProperty("is_no_order") String is_no_order,
                @JsonProperty("is_holiday_can_use") String is_holiday_can_use,
                @JsonProperty("merchant_id") String merchant_id,
                @JsonProperty("purchase_notes") String purchase_notes,
                @JsonProperty("is_best") String is_best,
                @JsonProperty("promote_end_time") String promote_end_time,
                @JsonProperty("merchant_name") String merchant_name,
                @JsonProperty("merchant_addr") String merchant_addr,
                @JsonProperty("merchant_phone") String merchant_phone,
                @JsonProperty("merchant_num") String merchant_num,
                @JsonProperty("grade") String grade,
                @JsonProperty("grade_num") String grade_num,
                @JsonProperty("left_time") String left_time){
			
			this.id = id;
			this.name = name;
			this.description = description;
			this.cover = cover;
			this.price = price;
			this.regular_price = regular_price;
			this.num_bought = num_bought;
			this.is_new = is_new;
			this.is_no_order = is_no_order;
			this.is_holiday_can_use = is_holiday_can_use;
			this.merchant_id = merchant_id;
			this.purchase_notes = purchase_notes;
			this.is_best = is_best;
			this.promote_end_time = promote_end_time;
			this.merchant_name = merchant_name;
			this.merchant_addr = merchant_addr;
			this.merchant_phone = merchant_phone;
			this.merchant_num = merchant_num;
			this.grade = grade;
			this.grade_num = grade_num;
			this.left_time = left_time;
		}
	}

	public Result result ;
	
    @JsonCreator
    public GetGoodDetailResponse(
            @JsonProperty("result") Result result) {
        this.result = result;
    }  
}
