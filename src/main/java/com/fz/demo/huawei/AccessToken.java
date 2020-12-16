package com.fz.demo.huawei;

public class AccessToken {

    /**
     * access_token : CV5rBi7KYm/yMll/YPLEW1ZbiVT3LK/X1Ye7Zg7T9zP9pbIO0r7hqCZUNFumCrzl/6gizUGkWHWEDyGchROBfnfmL87y
     * expires_in : 3600
     * token_type : Bearer
     */

    private String access_token;
    private int expires_in;
    private String token_type;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
