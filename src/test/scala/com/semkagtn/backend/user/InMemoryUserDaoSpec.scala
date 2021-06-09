package com.semkagtn.backend.user

class InMemoryUserDaoSpec
  extends UserDaoSpecBase {

  override val userDao: UserDao = new InMemoryUserDao
}
