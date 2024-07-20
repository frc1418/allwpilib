#!/usr/bin/env python3

import os
import shutil

from upstream_utils import (
    walk_cwd_and_copy_if,
    Lib,
)


def remove_tag(f: str):
    if "apriltag" in f:
        return False
    if "36h11" in f:
        return False
    if "16h5" in f:
        return False
    if "tag" in f:
        return True
    return False


def copy_upstream_src(wpilib_root):
    apriltag = os.path.join(wpilib_root, "apriltag")

    # Delete old install
    shutil.rmtree(
        os.path.join(apriltag, "src/main/native/thirdparty/apriltag"),
        ignore_errors=True,
    )
    shutil.rmtree(
        os.path.join(apriltag, "src/main/include/thirdparty/apriltag"),
        ignore_errors=True,
    )

    # Copy apriltag source files into allwpilib
    walk_cwd_and_copy_if(
        lambda dp, f: (f.endswith(".c") or f.endswith(".cpp"))
        and not dp.startswith("./example")
        and not f == "getopt.cpp"
        and not "py" in f
        and not remove_tag(f),
        os.path.join(apriltag, "src/main/native/thirdparty/apriltag/src"),
    )

    # Copy apriltag header files into allwpilib
    walk_cwd_and_copy_if(
        lambda dp, f: f.endswith(".h")
        and not f == "getopt.h"
        and not f == "postscript_utils.h"
        and not remove_tag(f),
        os.path.join(apriltag, "src/main/native/thirdparty/apriltag/include"),
    )


def main():
    name = "apriltag"
    url = "https://github.com/AprilRobotics/apriltag.git"
    tag = "ebdb2017e04b8e36f7d8a12ce60060416a905e12"
    patch_list = [
        "0001-apriltag_pose.c-Set-NULL-when-second-solution-could-.patch",
        "0002-zmaxheapify-Avoid-return-of-void-expression.patch",
        "0003-Avoid-unused-variable-warnings-in-release-builds.patch",
        "0004-Make-orthogonal_iteration-exit-early-upon-convergenc.patch",
    ]

    apriltag = Lib(name, url, tag, patch_list, copy_upstream_src)
    apriltag.main()


if __name__ == "__main__":
    main()
