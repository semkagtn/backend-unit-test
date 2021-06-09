package com.semkagtn.backend.user

import com.semkagtn.backend.{Generators, SpecBase}

trait UserDaoSpecBase
  extends SpecBase {

  def userDao: UserDao

  describe("get") {

    it("return existent value") {
      val user = Generators.user.next
      userDao.put(user)

      val actualResult = userDao.get(user.id)
      val expectedResult = Some(user)
      assertThat(actualResult, equalTo(expectedResult))
    }

    it("return nonexistent value") {
      val userId = Generators.userId.next

      val actualResult = userDao.get(userId)
      val expectedResult = Option.empty[User]
      assertThat(actualResult, equalTo(expectedResult))
    }
  }

  describe("put") {

    it("update value") {
      val user = Generators.user.next
      val updatedUser = user.copy(name = user.name + "1")
      userDao.put(user)
      userDao.put(updatedUser)

      val actualResult = userDao.get(user.id)
      val expectedResult = Some(updatedUser)
      assertThat(actualResult, equalTo(expectedResult))
    }
  }

  describe("delete") {

    it("delete existent") {
      val user = Generators.user.next
      userDao.put(user)

      val actualReturned = userDao.delete(user.id)
      val expectedReturned = true
      assertThat(actualReturned, equalTo(expectedReturned))

      val actualResult = userDao.get(user.id)
      val expectedResult = Option.empty[User]
      assertThat(actualResult, equalTo(expectedResult))
    }

    it("delete nonexistent") {
      val userId = Generators.userId.next

      val actualReturned = userDao.delete(userId)
      val expectedReturned = false
      assertThat(actualReturned, equalTo(expectedReturned))

      val actualResult = userDao.get(userId)
      val expectedResult = Option.empty[User]
      assertThat(actualResult, equalTo(expectedResult))
    }
  }
}
