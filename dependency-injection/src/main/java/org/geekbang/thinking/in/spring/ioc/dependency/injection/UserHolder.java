package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link org.geekbang.thinking.in.spring.ioc.overview.domain.User} 的 Holder 类
 *
 * @author fangkuangzhang
 * @date 2021/7/26 16:35
 */
public class UserHolder {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }

}
