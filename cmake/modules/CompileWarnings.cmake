macro(wpilib_target_warnings target)
    if(NOT MSVC)
        target_compile_options(${target} PRIVATE -Wall -pedantic -Wextra -Werror -Wno-unused-parameter ${WPILIB_TARGET_WARNINGS})
    else()
        target_compile_options(${target} PRIVATE /wd4146 /wd4244 /wd4251 /wd4267 /WX /D_CRT_SECURE_NO_WARNINGS ${WPILIB_TARGET_WARNINGS})
    endif()

    # Suppress C++-specific OpenCV warning; C compiler rejects it with an error
    # https://github.com/opencv/opencv/issues/20269
    if(UNIX AND NOT APPLE)
        target_compile_options(${target} PRIVATE $<$<COMPILE_LANGUAGE:CXX>:-Wno-deprecated-enum-enum-conversion>)
    elseif(UNIX AND APPLE)
        target_compile_options(${target} PRIVATE $<$<COMPILE_LANGUAGE:CXX>:-Wno-deprecated-anon-enum-enum-conversion>)
    endif()

    # Compress debug info with GCC
    if (${CMAKE_BUILD_TYPE} STREQUAL "Debug" AND ${CMAKE_CXX_COMPILER_ID} STREQUAL "GNU")
      target_compile_options(${target} PRIVATE -gz=zlib)
    endif()
endmacro()
