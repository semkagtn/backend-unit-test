package com.semkagtn.backend.util

class IdGenerator {

  private var lastId = 0L

  def newId(): Long = synchronized {
    lastId += 1
    lastId
  }
}
