package cn.abtion.keyboardtea.chat.models;


import cn.abtion.keyboardtea.base.model.BaseModel;

/**
 * @author abtion.
 * @since 17/10/23 21:15.
 * email caiheng@hrsoft.net.
 */

public class UserModel extends BaseModel {
    private String id;

    public UserModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
