package com.jawesa.home.user;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import com.jawesa.home.common.EntityLongIdHome;
import com.jawesa.model.user.User;
import java.io.Serializable;

@Named
@ConversationScoped
@SuppressWarnings("serial")
public class UserHome extends EntityLongIdHome<User> implements Serializable {

}
