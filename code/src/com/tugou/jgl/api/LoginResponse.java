package com.tugou.jgl.api;

import com.plugin.internet.core.ResponseBase;
import com.plugin.internet.core.json.JsonCreator;
import com.plugin.internet.core.json.JsonProperty;

public class LoginResponse extends ResponseBase {
	public int user_id;
	public String status;
	public String message;
	
    @JsonCreator
    public LoginResponse(
            @JsonProperty("user_id") int user_id,
            @JsonProperty("status") String status,
            @JsonProperty("message") String message) {
        this.user_id = user_id;
        this.status = status;
        this.message = message;
    }
}
