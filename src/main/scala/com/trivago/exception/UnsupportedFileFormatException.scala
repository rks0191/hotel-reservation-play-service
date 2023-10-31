package com.trivago.exception

import com.trivago.reader.FileType

class UnsupportedFileFormatException extends Exception {
  override def getMessage: String = s"Unsupported File Format. Current supported FileFormat include ${FileType.supportedFileTypes}"

}
