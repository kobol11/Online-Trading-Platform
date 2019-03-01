package com.fdmgroup.dao;

import com.fdmgroup.model.PlatformUser;

public interface IUserDao extends ICreatable<PlatformUser>, IReadable<PlatformUser>, IUpdatable<PlatformUser>, IDeletable<PlatformUser> {

	PlatformUser readByUsername(String username);

	PlatformUser readByEmail(String email);

}
