package com.company.authorization;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorizationParams {
    @Expose() public String id;
    @Expose() public String adress;
    @Expose(serialize = false) public String login;
    @Expose(serialize = false) public String userid;
    @Expose() public String role;

    public AutorizationParams(String id, String adress, String login,String userid, String role) {
        this.id = id;
        this.adress = adress;
        this.login = login;
        this.userid = userid;
        this.role = role;
    }
}