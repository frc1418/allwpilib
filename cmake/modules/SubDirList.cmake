MACRO(SUBDIR_LIST result curdir)
  FILE(GLOB children RELATIVE ${curdir} ${curdir}/*)
  SET(dirlist "")
  FOREACH(child ${children})
    IF(IS_DIRECTORY ${curdir}/${child})
      LIST(APPEND dirlist ${child})
    ENDIF()
  ENDFOREACH()
  SET(${result} ${dirlist})
ENDMACRO()

MACRO(ADD_ALL_SUBDIRECTORIES curdir)
  SUBDIR_LIST (_SUBPROJECTS ${curdir})
  FOREACH (dir ${_SUBPROJECTS})
    ADD_SUBDIRECTORY (${dir})
  ENDFOREACH ()
ENDMACRO()
