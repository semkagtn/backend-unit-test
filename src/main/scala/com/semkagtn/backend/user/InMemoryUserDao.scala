package com.semkagtn.backend.user

class InMemoryUserDao
  extends UserDao {

  private var map = Map.empty[UserId, User]

  override def get(id: UserId): Option[User] = synchronized {
    map.get(id)
  }

  override def put(user: User): Unit = synchronized {
    map = map.updated(user.id, user)
  }

  override def delete(id: UserId): Boolean = synchronized {
    val result = map.contains(id)
    map = map.removed(id)
    result
  }
}
