MACRO(DOWNLOAD_AND_CHECK source destination)
  file(DOWNLOAD ${source} ${destination} STATUS download_status)
  list(GET download_status 0 status_code)
  list(GET download_status 1 status_message)

  if(${status_code} EQUAL 0)
    message(VERBOSE "Download of \"${source}\" successful.")
  else()
    message(FATAL_ERROR "Download of \"${source}\" failed: ${status_message}")
  endif()
ENDMACRO()
