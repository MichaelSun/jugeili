package com.tugou.jgl.api;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;

public class GetUserInfoResponse extends ResponseBase {

	@Override
	public String toString() {
		return "GetUserInfoResponse [result=" + result + ", status=" + status
				+ ", message=" + message + "]";
	}

	public static final class Result {

        @Override
        public String toString() {
            return "Result{" +
                       "userName='" + userName + '\'' +
                       ", sex='" + sex + '\'' +
                       ", email='" + email + '\'' +
                       ", create_time=" + create_time +
                       ", mobile='" + mobile + '\'' +
                       ", balance='" + balance + '\'' +
                       '}';
        }

        public String userName;
		public String sex;
		public String email;
		public int create_time;
		public String mobile;
		public int balance;
        public int groupon_ticket_num;

		@JsonCreator
        public Result(
                @JsonProperty("userName") String userName,
                @JsonProperty("sex") String sex, 
                @JsonProperty("email") String email,
                @JsonProperty("create_time") int create_time,
                @JsonProperty("mobile") String mobile,
                @JsonProperty("balance") int balance,
                @JsonProperty("groupon_ticket_num") int groupon_ticket_num){
			
			this.userName  = userName;
			this.sex  = sex;
			this.email  = email;
			this.create_time  = create_time;
			this.mobile  = mobile;
            this.balance = balance;
		}
	}

	public Result result;
	public String status;
	public String message;
	
    @JsonCreator
    public GetUserInfoResponse(
            @JsonProperty("result") Result result,
            @JsonProperty("status") String status,
            @JsonProperty("message") String message) {
        this.result = result;
        this.status = status;
        this.message = message;
    }  
}
