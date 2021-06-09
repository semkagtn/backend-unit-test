package com.semkagtn.backend

import java.io.{PrintWriter, StringWriter}

package object extensions {

  implicit class RichThrowable(val throwable: Throwable)
    extends AnyVal {

    def stackTrackAsString: String = {
      val stringWriter = new StringWriter
      throwable.printStackTrace(new PrintWriter(stringWriter))
      stringWriter.toString
    }
  }
}
