package com.tugou.jgl.api;

import java.util.Arrays;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;
import com.tugou.jgl.api.GetAreaListResponse.GroupAreaInfo;
import com.tugou.jgl.api.GetAreaListResponse.SubList;

public class getCommentsResponse extends ResponseBase {
	
	@Override
	public String toString() {
		return "getCommentsResponse [result=" + result + ", status=" + status
				+ ", message=" + message + "]";
	}

	public static final class CommentsInfo {

		@Override
		public String toString() {
			return "CommentsInfo [content=" + content + ", star=" + star
					+ ", user_name=" + user_name + ", create_time="
					+ create_time + ", reply_type=" + reply_type + "]";
		}

		public String content;
		public int star;
		public String user_name;
		public int create_time;
		public int reply_type;
		
		@JsonCreator
        public CommentsInfo(
                @JsonProperty("content") String content,
                @JsonProperty("star") int star, 
                @JsonProperty("user_name") String user_name,
                @JsonProperty("create_time") int create_time,
                @JsonProperty("reply_type") int reply_type){
			
			this.content  = content;
			this.star  = star;
			this.user_name  = user_name;
			this.create_time  = create_time;
			this.reply_type  = reply_type;
		}
	}
	
	public static final class Result {

		public CommentsInfo[] list;
		@Override
		public String toString() {
			return "Result [list=" + Arrays.toString(list) + ", total=" + total
					+ ", value=" + value + "]";
		}

		public int total;
		public float value;
		
		@JsonCreator
        public Result(
                @JsonProperty("list") CommentsInfo[] list,
                @JsonProperty("total") int total, 
                @JsonProperty("value") float value){
			
			this.list  = list;
			this.total  = total;
			this.value  = value;
		}
	}

	public Result result;
	public String status;
	public String message;
	
    @JsonCreator
    public getCommentsResponse(
            @JsonProperty("result") Result result,
            @JsonProperty("status") String status,
            @JsonProperty("message") String message) {
        this.result = result;
        this.status = status;
        this.message = message;
    }    
}
