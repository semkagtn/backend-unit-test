package com.semkagtn.backend.user

trait UserDao {

  def get(id: UserId): Option[User]

  def put(user: User): Unit

  def delete(id: UserId): Boolean
}
