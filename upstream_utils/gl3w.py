#!/usr/bin/env python3

import os
import shutil

from upstream_utils import (
    get_repo_root,
    clone_repo,
    walk_if,
    git_am,
    walk_cwd_and_copy_if,
    Lib,
)


def copy_upstream_src(wpilib_root):
    gl3w = os.path.join(wpilib_root, "thirdparty", "imgui_suite", "gl3w")

    walk_cwd_and_copy_if(
        lambda dp, f: f == "gl3w_gen.py",
        os.path.join(gl3w),
    )


def main():
    name = "gl3w"
    url = "https://github.com/skaslev/gl3w"
    committish = "5f8d7fd191ba22ff2b60c1106d7135bb9a335533"

    patch_list = []

    gl3w = Lib(name, url, committish, patch_list, copy_upstream_src)
    gl3w.main()


if __name__ == "__main__":
    main()
