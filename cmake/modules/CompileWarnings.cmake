macro(wpilib_target_warnings target)
    if(NOT MSVC)
        target_compile_options(${target} PRIVATE -Wall -pedantic -Wextra -Werror -Wno-unused-parameter)
    else()
        target_compile_options(${target} PRIVATE /wd4244 /wd4267 /wd4146)
        target_compile_options(${target} PRIVATE /WX /wd4996)
    endif()
endmacro()
