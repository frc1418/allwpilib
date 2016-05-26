#!/usr/bin/env python3

# If there is already a comment block, a year range through the current year is
# created using the first year in the comment. If there is no comment block, a
# new one is added containing just the current year.

from datetime import date
import os
import re
import sys
import wpi

sep = os.sep
# If directory separator is backslash, escape it for regexes
if sep == "\\":
    sep += "\\"

# Files and directories which should be included in or excluded from processing
regexInclude = re.compile("|".join(["\." + ext + "$" for ext in
                                    ["cpp", "h", "hpp", "inc", "java"]]))
regexExclude = re.compile(wpi.regexExclude())

currentYear = str(date.today().year)

# Recursively create list of files in given directory
files = [os.path.join(dp, f) for dp, dn, fn in
         os.walk(os.path.expanduser(".")) for f in fn]

# Apply regex filters to list
files = [f for f in files if regexInclude.search(f)]
files = [f for f in files if not regexExclude.search(f)]

if not files:
    print("Error: no files to format", file=sys.stderr)
    sys.exit(1)

for name in files:
    # List names of files as they are processed if verbose flag was given
    if len(sys.argv) > 1 and sys.argv[1] == "-v":
        print("Processing", name,)
    with open(name, "r") as file:
        modifyCopyright = False
        year = ""

        # Get first line of file
        line = file.readline()

        # If first line is copyright comment
        if line[0:2] == "/*":
            modifyCopyright = True

            # Get next line
            line = file.readline()

            # Search for start of copyright year
            pos = line.find("20")

            # Extract it if found
            if pos != -1:
                year = line[pos:pos + 4]
            else:
                continue

            # Retrieve lines until one past end of comment block
            inComment = True
            inBlock = True
            while inBlock:
                if not inComment:
                    pos = line.find("/*", pos)
                    if pos != -1:
                        inComment = True
                    else:
                        inBlock = False
                else:
                    pos = line.find("*/", pos)
                    if pos != -1:
                        inComment = False

                    # This assumes no comments are started on the same line after
                    # another ends
                    line = file.readline()
                    pos = 0

        with open(name + ".tmp", "w", encoding = "ISO-8859-1") as temp:
            # Write first line of comment
            temp.write("/*")
            for i in range(0, 76):
                temp.write("-")
            temp.write("*/" + os.linesep)

            # Write second line of comment
            temp.write("/* Copyright (c) FIRST ")
            if modifyCopyright and year != currentYear:
                temp.write(year)
                temp.write("-")
            temp.write(currentYear)
            temp.write(". All Rights Reserved.")
            for i in range(0, 24):
                temp.write(" ")
            if not modifyCopyright or year == currentYear:
                for i in range(0, 5):
                    temp.write(" ")
            temp.write("*/" + os.linesep)

            # Write rest of lines of comment
            temp.write("/* Open Source Software - may be modified and shared by FRC teams. The code   */" + os.linesep + \
                       "/* must be accompanied by the FIRST BSD license file in the root directory of */" + os.linesep + \
                       "/* the project.                                                               */" + os.linesep + \
                       "/*")
            for i in range(0, 76):
                temp.write("-")
            temp.write("*/" + os.linesep)

            # If line after comment block isn't empty
            if len(line) > 1 and line[0] != " ":
                temp.write(os.linesep)
            temp.write(line)

            # Copy rest of original file into new one
            for line in file:
                temp.write(line)

    # Replace old file
    os.remove(name)
    os.rename(name + ".tmp", name)
